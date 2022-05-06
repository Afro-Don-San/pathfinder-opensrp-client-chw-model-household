package com.adosa.opensrp.chw.household.activity.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.fragment.BaseFarmingModelHouseholdResultsFragment;
import com.adosa.opensrp.chw.household.fragment.BaseHealthModelHouseholdResultsFragment;
import com.adosa.opensrp.chw.household.fragment.BaseLandModelHouseholdResultsFragment;
import com.adosa.opensrp.chw.household.fragment.BaseLivestockModelHouseholdResultsFragment;
import com.adosa.opensrp.chw.household.fragment.BaseSocialIntegrationModelHouseholdResultsFragment;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_evaluation_marks, R.string.tab_things_in_progress};
    private final Context mContext;
    private final String type;
    private final String baseEntityId;


    public SectionsPagerAdapter(Context context, FragmentManager fm, String baseEntityId, String type) {
        super(fm);
        this.mContext = context;
        this.type = type;
        this.baseEntityId = baseEntityId;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return OnGoingActivitiesFragment.newInstance(baseEntityId, type);
        } else if (type.equals(PathfinderModelHouseholdConstants.EvaluationTypes.HEALTH)) {
            return BaseHealthModelHouseholdResultsFragment.newInstance(baseEntityId);
        } else if (type.equals(PathfinderModelHouseholdConstants.EvaluationTypes.LAND)) {
            return BaseLandModelHouseholdResultsFragment.newInstance(baseEntityId);
        } else if (type.equals(PathfinderModelHouseholdConstants.EvaluationTypes.FARMING)) {
            return BaseFarmingModelHouseholdResultsFragment.newInstance(baseEntityId);
        } else if (type.equals(PathfinderModelHouseholdConstants.EvaluationTypes.SOCIAL_INTEGRATION)) {
            return BaseSocialIntegrationModelHouseholdResultsFragment.newInstance(baseEntityId);
        } else if (type.equals(PathfinderModelHouseholdConstants.EvaluationTypes.LIVESTOCK)) {
            return BaseLivestockModelHouseholdResultsFragment.newInstance(baseEntityId);
        } else {
            return OnGoingActivitiesFragment.newInstance(baseEntityId, type);
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}