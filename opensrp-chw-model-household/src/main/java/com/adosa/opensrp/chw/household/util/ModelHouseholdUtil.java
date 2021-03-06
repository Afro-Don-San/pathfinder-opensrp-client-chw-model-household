package com.adosa.opensrp.chw.household.util;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.widget.Toast;

import com.adosa.opensrp.chw.household.PathfinderModelHouseholdLibrary;
import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.contract.BaseFpCallDialogContract;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.repository.BaseRepository;
import org.smartregister.sync.ClientProcessorForJava;
import org.smartregister.sync.helper.ECSyncHelper;
import org.smartregister.util.PermissionUtils;

import java.util.Date;

import timber.log.Timber;

public class ModelHouseholdUtil extends org.smartregister.util.Utils  {
    public static ClientProcessorForJava getClientProcessorForJava() {
        return PathfinderModelHouseholdLibrary.getInstance().getClientProcessorForJava();
    }

    public static ECSyncHelper getSyncHelper() {
        return PathfinderModelHouseholdLibrary.getInstance().getEcSyncHelper();
    }

    public static void saveFormEvent(final String jsonString) throws Exception {
        AllSharedPreferences allSharedPreferences = PathfinderModelHouseholdLibrary.getInstance().context().allSharedPreferences();
        Event baseEvent = ModelHouseholdJsonFormUtils.processJsonForm(allSharedPreferences, jsonString);
        processEvent(allSharedPreferences, baseEvent);
    }

    public static String getFullName(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject) {
        StringBuilder nameBuilder = new StringBuilder();
        String firstName = pathfinderModelHouseholdMemberObject.getFirstName();
        String lastName = pathfinderModelHouseholdMemberObject.getLastName();
        String middleName = pathfinderModelHouseholdMemberObject.getMiddleName();
        if (StringUtils.isNotBlank(firstName)) {
            nameBuilder.append(firstName);
        } else if (StringUtils.isNotBlank(middleName)) {
            nameBuilder.append(" ");
            nameBuilder.append(middleName);
        } else if (StringUtils.isNotBlank(lastName)) {
            nameBuilder.append(" ");
            nameBuilder.append(lastName);
        }
        return nameBuilder.toString();
    }

    public static void processEvent(AllSharedPreferences allSharedPreferences, Event baseEvent) throws Exception {
        if (baseEvent != null) {
            ModelHouseholdJsonFormUtils.tagEvent(allSharedPreferences, baseEvent);
            JSONObject eventJson = new JSONObject(ModelHouseholdJsonFormUtils.gson.toJson(baseEvent));
            getSyncHelper().addEvent(baseEvent.getBaseEntityId(), eventJson);

            startClientProcessing();

            long lastSyncTimeStamp = getAllSharedPreferences().fetchLastUpdatedAtDate(0);
            Date lastSyncDate = new Date(lastSyncTimeStamp);
            getClientProcessorForJava().processClient(getSyncHelper().getEvents(lastSyncDate, BaseRepository.TYPE_Unsynced));
            getAllSharedPreferences().saveLastUpdatedAtDate(lastSyncDate.getTime());

        }
    }

    public static void startClientProcessing() {
        try {
            long lastSyncTimeStamp = getAllSharedPreferences().fetchLastUpdatedAtDate(0);
            Date lastSyncDate = new Date(lastSyncTimeStamp);
            getClientProcessorForJava().processClient(getSyncHelper().getEvents(lastSyncDate, BaseRepository.TYPE_Unprocessed));
            getAllSharedPreferences().saveLastUpdatedAtDate(lastSyncDate.getTime());
        } catch (Exception e) {
            Timber.d(e);
        }

    }

    public static Spanned fromHtml(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }
    }

    public static boolean launchDialer(final Activity activity, final BaseFpCallDialogContract.View callView, final String phoneNumber) {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            // set a pending call execution request
            if (callView != null) {
                callView.setPendingCallRequest(() -> ModelHouseholdUtil.launchDialer(activity, callView, phoneNumber));
            }

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, PermissionUtils.PHONE_STATE_PERMISSION_REQUEST_CODE);

            return false;
        } else {

            // Permissions should be in the module making use of this FP lib
            if (((TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number() == null) {

                Timber.i("No dial application so we launch 'Copy to clipboard'...");

                ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(activity.getText(R.string.copied_phone_number), phoneNumber);
                clipboard.setPrimaryClip(clip);

                CopyToClipboardDialog copyToClipboardDialog = new CopyToClipboardDialog(activity, R.style.copy_clipboard_dialog);
                copyToClipboardDialog.setContent(phoneNumber);
                copyToClipboardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                copyToClipboardDialog.show();
                // no phone
                Toast.makeText(activity, activity.getText(R.string.copied_phone_number), Toast.LENGTH_SHORT).show();

            } else {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                activity.startActivity(intent);
            }
            return true;
        }
    }
}
