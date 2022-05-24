package com.adosa.opensrp.chw.household.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.dao.PathfinderModelHouseholdDao;

import org.smartregister.view.customcontrols.CustomFontTextView;

import java.text.DecimalFormat;

public class BaseLivestockModelHouseholdResultsFragment extends Fragment {
    protected static final String BASE_ENTITY_ID = "base_entity_id";

    private String baseEntityId;

    public BaseLivestockModelHouseholdResultsFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param baseEntityId Client's BaseEntityId.
     * @return A new instance of fragment ModelHouseholdParametersFragment.
     */
    public static BaseLivestockModelHouseholdResultsFragment newInstance(String baseEntityId) {
        BaseLivestockModelHouseholdResultsFragment fragment = new BaseLivestockModelHouseholdResultsFragment();
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
        View view = inflater.inflate(R.layout.fragment_livestock_model_household_evaluation_results, container, false);
        setupViews(view);
        return view;
    }

    protected void setupViews(View view) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);
        ((CustomFontTextView) view.findViewById(R.id.livestock_percentage)).setText(df.format(PathfinderModelHouseholdDao.getScore(baseEntityId, "livestock_evaluation_score") * 100 / 9) + "%");
    }
}