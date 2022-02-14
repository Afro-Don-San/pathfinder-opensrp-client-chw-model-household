package com.adosa.opensrp.chw.household.activity.ui.main;

import static com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants.ActivityPayload.BASE_ENTITY_ID;
import static com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants.ActivityPayload.EVALUATION_TYPE;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.dao.PathfinderModelHouseholdOngoingActivitiesDao;
import com.adosa.opensrp.chw.household.databinding.FragmentModelHouseholdOngoningActivitiesBinding;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdOngoingActivitiesObject;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class OnGoingActivitiesFragment extends Fragment {
    private PageViewModel pageViewModel;
    private FragmentModelHouseholdOngoningActivitiesBinding binding;
    protected String baseEntityId;
    protected String type;

    public static OnGoingActivitiesFragment newInstance(String baseEntityId, String type) {
        OnGoingActivitiesFragment fragment = new OnGoingActivitiesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BASE_ENTITY_ID, baseEntityId);
        bundle.putString(EVALUATION_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        if (getArguments() != null) {
            baseEntityId = getArguments().getString(BASE_ENTITY_ID);
            type = getArguments().getString(EVALUATION_TYPE);
        }
        pageViewModel.setBaseEntityId(baseEntityId);
        pageViewModel.setType(type);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentModelHouseholdOngoningActivitiesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LinearLayout onActivitiesList = binding.ongoingActivities;
//        pageViewModel.getOngoingActivities().observe(getViewLifecycleOwner(), new Observer<List<PathfinderModelHouseholdOngoingActivitiesObject>>() {
//            @Override
//            public void onChanged(@Nullable List<PathfinderModelHouseholdOngoingActivitiesObject> ongoingActivities) {
//                onActivitiesList.removeAllViews();
//                for (PathfinderModelHouseholdOngoingActivitiesObject pathfinderModelHouseholdOngoingActivitiesObject : ongoingActivities) {
//                    String lineSeparator;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                        lineSeparator = System.lineSeparator();
//                    } else {
//                        lineSeparator = "\n";
//                    }
//
//                    View itemInProgress = getLayoutInflater().inflate(R.layout.fragment_item_in_progress, null);
//
//                    TextView ongoingActivitiesTv = itemInProgress.findViewById(R.id.ongoing_activities);
//                    TextView dateTv = itemInProgress.findViewById(R.id.date);
//
//                    SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
//                    Date date = new Date(Long.parseLong(pathfinderModelHouseholdOngoingActivitiesObject.getLastInteractedWith()));
//                    dateTv.setText(lineSeparator + sf.format(date));
//
//                    SpannableString spanString;
//
//
//                    ArrayList<String> list = new ArrayList<>();
//
//                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.HEALTH)) {
//                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getHealthAreasOfImprovementsBeingWorkedOn().equals(""))
//                            list.add(pathfinderModelHouseholdOngoingActivitiesObject.getHealthAreasOfImprovementsBeingWorkedOn());
//                    }
//
//                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.SOCIAL_INTEGRATION)) {
//                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getSocialIntegrationItemsBeingWorkedOn().equals(""))
//                            list.add(pathfinderModelHouseholdOngoingActivitiesObject.getSocialIntegrationItemsBeingWorkedOn());
//                    }
//
//                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.LAND)) {
//                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnLandBeingWorkedOn().equals(""))
//                            list.add(pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnLandBeingWorkedOn());
//                    }
//
//                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.FARMING)) {
//                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getFarmingItemsBeingWorkedOn().equals(""))
//                            list.add(pathfinderModelHouseholdOngoingActivitiesObject.getFarmingItemsBeingWorkedOn());
//                    }
//
//                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.LIVESTOCK)) {
//                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getLivestockItemsBeingWorkedOn().equals(""))
//                            list.add(pathfinderModelHouseholdOngoingActivitiesObject.getLivestockItemsBeingWorkedOn());
//                    }
//
//
//                    StringBuilder sb = new StringBuilder();
//
//
//                    for (String onGoingActivityString : list) {
//                        sb.append(lineSeparator + onGoingActivityString);
//                    }
//
//                    String concat = sb.toString();
//                    spanString = new SpannableString(concat);
//
//                    for (String onGoingActivityString : list) {
//                        addBullet(onGoingActivityString, concat, spanString);
//                    }
//
//                    ongoingActivitiesTv.setText(spanString);
//
//
//                    onActivitiesList.addView(itemInProgress);
//                }
//            }
//        });

        List<PathfinderModelHouseholdOngoingActivitiesObject> ongoingActivities;
        if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL))
            ongoingActivities = PathfinderModelHouseholdOngoingActivitiesDao.getOngoingActivities(baseEntityId);
        else
            ongoingActivities = PathfinderModelHouseholdOngoingActivitiesDao.getOngoingActivitiesByType(baseEntityId, type);

        for (PathfinderModelHouseholdOngoingActivitiesObject pathfinderModelHouseholdOngoingActivitiesObject : ongoingActivities) {
            String lineSeparator;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                lineSeparator = System.lineSeparator();
            } else {
                lineSeparator = "\n";
            }

            View itemInProgress = getLayoutInflater().inflate(R.layout.fragment_item_in_progress, null);

            TextView ongoingActivitiesTv = itemInProgress.findViewById(R.id.ongoing_activities);
            TextView dateTv = itemInProgress.findViewById(R.id.date);

            SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date(Long.parseLong(pathfinderModelHouseholdOngoingActivitiesObject.getLastInteractedWith()));
            dateTv.setText(lineSeparator + sf.format(date));

            SpannableString spanString;


            ArrayList<String> list = new ArrayList<>();

            if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.HEALTH)) {
                if (!pathfinderModelHouseholdOngoingActivitiesObject.getHealthAreasOfImprovementsBeingWorkedOn().equals(""))
                    list.add(pathfinderModelHouseholdOngoingActivitiesObject.getHealthAreasOfImprovementsBeingWorkedOn());
            }

            if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.SOCIAL_INTEGRATION)) {
                if (!pathfinderModelHouseholdOngoingActivitiesObject.getSocialIntegrationItemsBeingWorkedOn().equals(""))
                    list.add(pathfinderModelHouseholdOngoingActivitiesObject.getSocialIntegrationItemsBeingWorkedOn());
            }

            if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.LAND)) {
                if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnLandBeingWorkedOn().equals(""))
                    list.add(pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnLandBeingWorkedOn());
            }

            if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.FARMING)) {
                if (!pathfinderModelHouseholdOngoingActivitiesObject.getFarmingItemsBeingWorkedOn().equals(""))
                    list.add(pathfinderModelHouseholdOngoingActivitiesObject.getFarmingItemsBeingWorkedOn());
            }

            if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.LIVESTOCK)) {
                if (!pathfinderModelHouseholdOngoingActivitiesObject.getLivestockItemsBeingWorkedOn().equals(""))
                    list.add(pathfinderModelHouseholdOngoingActivitiesObject.getLivestockItemsBeingWorkedOn());
            }


            StringBuilder sb = new StringBuilder();


            for (String onGoingActivityString : list) {
                sb.append(lineSeparator + onGoingActivityString);
            }

            String concat = sb.toString();
            spanString = new SpannableString(concat);

            for (String onGoingActivityString : list) {
                addBullet(onGoingActivityString, concat, spanString);
            }

            ongoingActivitiesTv.setText(spanString);


            onActivitiesList.addView(itemInProgress);
        }




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void addBullet(String s, String txt, SpannableString spanString) {
        int index = txt.indexOf(s);
        // You can change the attributes as you need ... I just added a bit of color and formating
        BulletSpan bullet = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            bullet = new BulletSpan(20, Color.CYAN, 10);
        } else {
            bullet = new BulletSpan(20, Color.CYAN);
        }
        spanString.setSpan(bullet, index, index + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}