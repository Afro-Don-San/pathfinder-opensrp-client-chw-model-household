package com.adosa.opensrp.chw.household.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.adosa.opensrp.chw.household.R;

public class BaseSocialIntegrationModelHouseholdResultsFragment extends Fragment {
    protected static final String BASE_ENTITY_ID = "base_entity_id";

    private String baseEntityId;

    public BaseSocialIntegrationModelHouseholdResultsFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param baseEntityId CLient's BaseEntityId.
     * @return A new instance of fragment ModelHouseholdParametersFragment.
     */
    public static BaseSocialIntegrationModelHouseholdResultsFragment newInstance(String baseEntityId) {
        BaseSocialIntegrationModelHouseholdResultsFragment fragment = new BaseSocialIntegrationModelHouseholdResultsFragment();
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
        View view = inflater.inflate(R.layout.fragment_social_integration_model_household_evaluation_results, container, false);

        return view;
    }
}