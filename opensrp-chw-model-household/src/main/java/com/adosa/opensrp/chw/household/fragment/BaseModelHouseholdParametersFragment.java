package com.adosa.opensrp.chw.household.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.activity.BaseModelHouseholdEvaluationActivity;

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
        RelativeLayout socialIntegrationRow = view.findViewById(R.id.social_integration_row);
        RelativeLayout landRow = view.findViewById(R.id.land_row);
        RelativeLayout farmingRow = view.findViewById(R.id.farming_row);
        RelativeLayout livestockRow = view.findViewById(R.id.livestock_row);

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