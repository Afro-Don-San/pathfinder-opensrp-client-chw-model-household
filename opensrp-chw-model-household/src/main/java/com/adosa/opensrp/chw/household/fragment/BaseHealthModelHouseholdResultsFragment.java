package com.adosa.opensrp.chw.household.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.dao.PathfinderModelHouseholdDao;

import org.smartregister.view.customcontrols.CustomFontTextView;

public class BaseHealthModelHouseholdResultsFragment extends Fragment {
    protected static final String BASE_ENTITY_ID = "base_entity_id";

    private String baseEntityId;

    public BaseHealthModelHouseholdResultsFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param baseEntityId Client's BaseEntityId.
     * @return A new instance of fragment ModelHouseholdParametersFragment.
     */
    public static BaseHealthModelHouseholdResultsFragment newInstance(String baseEntityId) {
        BaseHealthModelHouseholdResultsFragment fragment = new BaseHealthModelHouseholdResultsFragment();
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
        View view = inflater.inflate(R.layout.fragment_health_model_household_evaluation_results, container, false);
        setupViews(view);

        return view;
    }

    protected void setupViews(View view) {
        ((CustomFontTextView) view.findViewById(R.id.toilet_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "toilet_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.bathroom_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "bathroom_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.kibuyu_chirizi_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "kibuyu_chirizi_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.household_cleanliness_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "household_cleanliness_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.dishes_drying_container_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "dishes_drying_container_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.mosquito_net_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "llin_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.health_services_usage_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "use_of_health_facility_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.drinking_water_treatment_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "clean_drinking_water_evaluation_score")));
        ((CustomFontTextView) view.findViewById(R.id.fp_percentage)).setText(String.valueOf(PathfinderModelHouseholdDao.getScore(baseEntityId, "fp_education_evaluation_score")));
    }
}