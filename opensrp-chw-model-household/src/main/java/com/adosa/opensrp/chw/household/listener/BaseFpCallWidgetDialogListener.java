package com.adosa.opensrp.chw.household.listener;

import android.view.View;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.fragment.BasePathfinderFpCallDialogFragment;
import com.adosa.opensrp.chw.household.util.ModelHouseholdUtil;

import timber.log.Timber;

public class BaseFpCallWidgetDialogListener implements View.OnClickListener {
    private BasePathfinderFpCallDialogFragment callDialogFragment;

    public BaseFpCallWidgetDialogListener(BasePathfinderFpCallDialogFragment dialogFragment) {
        callDialogFragment = dialogFragment;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.fp_call_close) {
            callDialogFragment.dismiss();
        } else if (i == R.id.fp_call_head_phone_number) {
            try {
                String phoneNumber = (String) v.getTag();
                ModelHouseholdUtil.launchDialer(callDialogFragment.getActivity(), callDialogFragment, phoneNumber);
                callDialogFragment.dismiss();
            } catch (Exception e) {
                Timber.e(e);
            }
        } else if (i == R.id.call_fp_woman_phone) {
            try {
                String phoneNumber = (String) v.getTag();
                ModelHouseholdUtil.launchDialer(callDialogFragment.getActivity(), callDialogFragment, phoneNumber);
                callDialogFragment.dismiss();
            } catch (Exception e) {
                Timber.e(e);
            }
        }
    }
}
