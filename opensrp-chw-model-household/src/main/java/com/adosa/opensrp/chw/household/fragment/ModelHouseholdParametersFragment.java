package com.adosa.opensrp.chw.household.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adosa.opensrp.chw.household.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModelHouseholdParametersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModelHouseholdParametersFragment extends Fragment {
    private static final String BASE_ENTITY_ID = "base_entity_id";

    // TODO: Rename and change types of parameters
    private String baseEntityId;

    public ModelHouseholdParametersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param baseEntityId CLient's BaseEntityId.
     * @return A new instance of fragment ModelHouseholdParametersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModelHouseholdParametersFragment newInstance(String baseEntityId) {
        ModelHouseholdParametersFragment fragment = new ModelHouseholdParametersFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_household_parameters, container, false);
    }
}