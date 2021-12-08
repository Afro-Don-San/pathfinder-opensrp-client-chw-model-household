package com.adosa.opensrp.chw.household.custom_views;

import android.app.Activity;
import android.content.Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.widget.LinearLayout;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;
import com.adosa.opensrp.chw.household.fragment.BasePathfinderFpCallDialogFragment;


public class BaseModelHouseholdFloatingMenu extends LinearLayout implements View.OnClickListener {
    private PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject;

    public BaseModelHouseholdFloatingMenu(Context context, PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject) {
        super(context);
        this.pathfinderModelHouseholdMemberObject = pathfinderModelHouseholdMemberObject;
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
            BasePathfinderFpCallDialogFragment.launchDialog(activity, pathfinderModelHouseholdMemberObject);
        }
    }
}