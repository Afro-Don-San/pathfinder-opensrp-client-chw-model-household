package com.adosa.opensrp.chw.fp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import com.adosa.opensrp.chw.fp.contract.BaseFpCallDialogContract;
import com.adosa.opensrp.chw.fp.domain.PathfinderFpMemberObject;
import com.adosa.opensrp.chw.fp.listener.BaseFpCallWidgetDialogListener;
import com.adosa.opensrp.chw.fp.util.FpUtil;
import com.adosa.opensrp.chw.fp.R;

import static android.view.View.GONE;
import static org.smartregister.util.Utils.getName;

public class BasePathfinderFpCallDialogFragment extends DialogFragment implements BaseFpCallDialogContract.View {

    public static final String DIALOG_TAG = "BaseFpCallWidgetDialogFragment_DIALOG_TAG";
    private static PathfinderFpMemberObject pathfinderFpMemberObject;
    private View.OnClickListener listener = null;

    public static BasePathfinderFpCallDialogFragment launchDialog(Activity activity, PathfinderFpMemberObject memberObject) {
        BasePathfinderFpCallDialogFragment dialogFragment = BasePathfinderFpCallDialogFragment.newInstance();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        Fragment prev = activity.getFragmentManager().findFragmentByTag(DIALOG_TAG);
        pathfinderFpMemberObject = memberObject;
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        dialogFragment.show(ft, DIALOG_TAG);

        return dialogFragment;
    }

    public static BasePathfinderFpCallDialogFragment newInstance() {
        return new BasePathfinderFpCallDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ChwTheme_Dialog_FullWidth);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup dialogView = (ViewGroup) inflater.inflate(R.layout.fp_member_call_widget_dialog_fragment, container, false);
        setUpPosition();
        TextView callTitle = dialogView.findViewById(R.id.call_fp_woman_title);
        callTitle.setText(getString((R.string.call_fp_woman)));

        if (listener == null) {
            listener = new BaseFpCallWidgetDialogListener(this);
        }

        initUI(dialogView);
        return dialogView;
    }

    private void initUI(ViewGroup rootView) {

        if (StringUtils.isNotBlank(pathfinderFpMemberObject.getPhoneNumber())) {
            TextView fpWomanNameTextView = rootView.findViewById(R.id.call_fp_woman_name);
            fpWomanNameTextView.setText(FpUtil.getFullName(pathfinderFpMemberObject));

            TextView fpWomanPhone = rootView.findViewById(R.id.call_fp_woman_phone);
            fpWomanPhone.setTag(pathfinderFpMemberObject.getPhoneNumber());
            fpWomanPhone.setText(getName(getCurrentContext().getString(R.string.call), pathfinderFpMemberObject.getPhoneNumber()));
            fpWomanPhone.setOnClickListener(listener);
        } else {
            rootView.findViewById(R.id.layout_fp_woman).setVisibility(GONE);
        }

        if (StringUtils.isNotBlank(pathfinderFpMemberObject.getFamilyHeadPhoneNumber())) {
            TextView fpPrimaryCaregiverTitle = rootView.findViewById(R.id.call_primary_caregiver_title);
            if (StringUtils.isNotBlank(pathfinderFpMemberObject.getPrimaryCareGiver()) &&
                    pathfinderFpMemberObject.getFamilyHead().equals(pathfinderFpMemberObject.getPrimaryCareGiver())) {
                fpPrimaryCaregiverTitle.setVisibility(View.VISIBLE);
            } else {
                fpPrimaryCaregiverTitle.setVisibility(GONE);
            }
            TextView familyHeadName = rootView.findViewById(R.id.fp_call_head_name);
            familyHeadName.setText(pathfinderFpMemberObject.getFamilyHeadName());

            TextView callHeadPhoneNumber = rootView.findViewById(R.id.fp_call_head_phone_number);
            callHeadPhoneNumber.setTag(pathfinderFpMemberObject.getFamilyHeadPhoneNumber());
            callHeadPhoneNumber.setText(getName(getCurrentContext().getString(R.string.call), pathfinderFpMemberObject.getFamilyHeadPhoneNumber()));
            callHeadPhoneNumber.setOnClickListener(listener);

        } else {
            rootView.findViewById(R.id.fp_layout_family_head).setVisibility(GONE);
        }

        rootView.findViewById(R.id.fp_call_close).setOnClickListener(listener);

    }

    private void setUpPosition() {
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;
        p.y = 20;
        getDialog().getWindow().setAttributes(p);
    }

    @Override
    public BaseFpCallDialogContract.Dialer getPendingCallRequest() {
        return null;
    }

    @Override
    public void setPendingCallRequest(BaseFpCallDialogContract.Dialer dialer) {
        // TODO :: set a pending call request
    }

    @Override
    public Context getCurrentContext() {
        return getActivity();
    }
}
