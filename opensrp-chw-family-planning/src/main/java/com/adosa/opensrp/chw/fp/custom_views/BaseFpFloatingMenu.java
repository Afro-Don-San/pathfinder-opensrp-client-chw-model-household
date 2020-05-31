package com.adosa.opensrp.chw.fp.custom_views;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.LinearLayout;

import com.adosa.opensrp.chw.fp.domain.PathfinderFpMemberObject;
import com.adosa.opensrp.chw.fp.fragment.BasePathfinderFpCallDialogFragment;
import com.adosa.opensrp.chw.fp.R;


public class BaseFpFloatingMenu extends LinearLayout implements View.OnClickListener {
    private PathfinderFpMemberObject pathfinderFpMemberObject;

    public BaseFpFloatingMenu(Context context, PathfinderFpMemberObject pathfinderFpMemberObject) {
        super(context);
        this.pathfinderFpMemberObject = pathfinderFpMemberObject;
        initUi();
    }

    protected void initUi() {
        inflate(getContext(), R.layout.fp_call_floating_menu, this);
        FloatingActionButton fab = findViewById(R.id.fp_fab);
        if (fab != null)
            fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fp_fab) {
            Activity activity = (Activity) getContext();
            BasePathfinderFpCallDialogFragment.launchDialog(activity, pathfinderFpMemberObject);
        }
    }
}