package com.adosa.opensrp.chw.fp.listener;

import android.view.View;

import com.adosa.opensrp.chw.fp.fragment.BasePathfinderFpCallDialogFragment;
import com.adosa.opensrp.chw.fp.util.FpUtil;
import com.adosa.opensrp.chw.fp.R;

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
                FpUtil.launchDialer(callDialogFragment.getActivity(), callDialogFragment, phoneNumber);
                callDialogFragment.dismiss();
            } catch (Exception e) {
                Timber.e(e);
            }
        } else if (i == R.id.call_fp_woman_phone) {
            try {
                String phoneNumber = (String) v.getTag();
                FpUtil.launchDialer(callDialogFragment.getActivity(), callDialogFragment, phoneNumber);
                callDialogFragment.dismiss();
            } catch (Exception e) {
                Timber.e(e);
            }
        }
    }
}
