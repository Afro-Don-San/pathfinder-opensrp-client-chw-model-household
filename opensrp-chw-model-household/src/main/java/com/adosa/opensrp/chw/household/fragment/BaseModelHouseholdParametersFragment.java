package com.adosa.opensrp.chw.household.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.activity.BaseModelHouseholdEvaluationActivity;
import com.adosa.opensrp.chw.household.dao.PathfinderModelHouseholdDao;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;

public class BaseModelHouseholdParametersFragment extends Fragment implements View.OnClickListener {
    protected static final String BASE_ENTITY_ID = "base_entity_id";

    protected String baseEntityId;

    public BaseModelHouseholdParametersFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param baseEntityId Client's BaseEntityId.
     * @return A new instance of fragment ModelHouseholdParametersFragment.
     */
    public static BaseModelHouseholdParametersFragment newInstance(String baseEntityId) {
        BaseModelHouseholdParametersFragment fragment = new BaseModelHouseholdParametersFragment();
        Bundle args = new Bundle();
        args.putString(BASE_ENTITY_ID, baseEntityId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            baseEntityId = getArguments().getString(BASE_ENTITY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_model_household_parameters, container, false);

        RelativeLayout healthRow = view.findViewById(R.id.health_row);

        float healthScore =
                PathfinderModelHouseholdDao.getScore(baseEntityId, "toilet_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "bathroom_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "kibuyu_chirizi_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "household_cleanliness_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "dishes_drying_container_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "llin_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "use_of_health_facility_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "clean_drinking_water_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "fp_education_evaluation_score");
        Resources res = getResources();
        String textScore = res.getString(R.string.completion, String.valueOf(healthScore));
        ((TextView) healthRow.findViewById(R.id.text_view_health_profile)).setText(textScore);

        RelativeLayout socialIntegrationRow = view.findViewById(R.id.social_integration_row);

        float socialIntegrationScore =
                PathfinderModelHouseholdDao.getScore(baseEntityId, "economic_activities_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "village_activities_participation_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "joint_decision_making_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "children_school_attendance_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "stove_evaluation_score");
        textScore = res.getString(R.string.completion, String.valueOf(socialIntegrationScore));
        ((TextView) socialIntegrationRow.findViewById(R.id.text_view_social_integration_profile)).setText(textScore);

        RelativeLayout landRow = view.findViewById(R.id.land_row);

        float landScore =
                PathfinderModelHouseholdDao.getScore(baseEntityId, "natural_resources_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "trees_evaluation_score");
        textScore = res.getString(R.string.completion, String.valueOf(landScore));
        ((TextView) landRow.findViewById(R.id.text_view_land_profile)).setText(textScore);

        PathfinderModelHouseholdMemberObject householdMemberObject = PathfinderModelHouseholdDao.getMember(baseEntityId);

        RelativeLayout farmingRow = view.findViewById(R.id.farming_row);
        if (householdMemberObject.getLocation().contains("Kigoma") || householdMemberObject.getLocation().contains("Katavi")) {
            farmingRow.setVisibility(View.VISIBLE);
        } else {
            farmingRow.setVisibility(View.GONE);
        }

        float farmingScore = PathfinderModelHouseholdDao.getScore(baseEntityId, "farming_evaluation_score");
        textScore = res.getString(R.string.completion, String.valueOf(farmingScore));
        ((TextView) farmingRow.findViewById(R.id.text_view_farming_profile)).setText(textScore);


        RelativeLayout livestockRow = view.findViewById(R.id.livestock_row);

        if (householdMemberObject.getLocation().contains("Arusha") || householdMemberObject.getLocation().contains("Manyara")) {
            livestockRow.setVisibility(View.VISIBLE);
        } else {
            livestockRow.setVisibility(View.GONE);
        }

        float livestockScore = PathfinderModelHouseholdDao.getScore(baseEntityId, "livestock_evaluation_score");
        textScore = res.getString(R.string.completion, String.valueOf(livestockScore));
        ((TextView) livestockRow.findViewById(R.id.text_view_livestock_profile)).setText(textScore);

        healthRow.setOnClickListener(this);
        socialIntegrationRow.setOnClickListener(this);
        landRow.setOnClickListener(this);
        farmingRow.setOnClickListener(this);
        livestockRow.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        getActivity().startActivityForResult(new Intent(getActivity(), BaseModelHouseholdEvaluationActivity.class), 100);
    }
}