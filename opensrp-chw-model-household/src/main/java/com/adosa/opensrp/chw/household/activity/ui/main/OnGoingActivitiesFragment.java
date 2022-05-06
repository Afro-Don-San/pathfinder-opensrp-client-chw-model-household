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
import com.adosa.opensrp.chw.household.databinding.FragmentModelHouseholdOngoningActivitiesBinding;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdOngoingActivitiesObject;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

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
        pageViewModel.getOngoingActivities().observe(getViewLifecycleOwner(), new Observer<List<PathfinderModelHouseholdOngoingActivitiesObject>>() {
            @Override
            public void onChanged(@Nullable List<PathfinderModelHouseholdOngoingActivitiesObject> ongoingActivities) {
                onActivitiesList.removeAllViews();
                for (PathfinderModelHouseholdOngoingActivitiesObject pathfinderModelHouseholdOngoingActivitiesObject : ongoingActivities) {
                    String lineSeparator;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        lineSeparator = System.lineSeparator();
                    } else {
                        lineSeparator = "\n";
                    }

                    ArrayList<String> list = new ArrayList<>();

                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.HEALTH)) {
                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnToiletUsageBeingWorkedOn().equals("")) {
                            String itemsOnToiletUsageBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnToiletUsageBeingWorkedOn();
                            if (itemsOnToiletUsageBeingWorkedOn.contains("[")) {
                                itemsOnToiletUsageBeingWorkedOn = itemsOnToiletUsageBeingWorkedOn.substring(1, itemsOnToiletUsageBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(itemsOnToiletUsageBeingWorkedOn.split(",\\s*")));
                        }

                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnBathroomUsageBeingWorkedOn().equals("")) {
                            String itemsOnBathroomUsageBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnBathroomUsageBeingWorkedOn();
                            if (itemsOnBathroomUsageBeingWorkedOn.contains("[")) {
                                itemsOnBathroomUsageBeingWorkedOn = itemsOnBathroomUsageBeingWorkedOn.substring(1, itemsOnBathroomUsageBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(itemsOnBathroomUsageBeingWorkedOn.split(",\\s*")));
                        }

                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn().equals("")) {
                            String itemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn();
                            if (itemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn.contains("[")) {
                                itemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn = itemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn.substring(1, itemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(itemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn.split(",\\s*")));
                        }

                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnDishesDryingContainerBeingWorkedOn().equals("")) {
                            String itemsOnDishesDryingContainerBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnDishesDryingContainerBeingWorkedOn();
                            if (itemsOnDishesDryingContainerBeingWorkedOn.contains("[")) {
                                itemsOnDishesDryingContainerBeingWorkedOn = itemsOnDishesDryingContainerBeingWorkedOn.substring(1, itemsOnDishesDryingContainerBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(itemsOnDishesDryingContainerBeingWorkedOn.split(",\\s*")));
                        }

                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnTreatingDrinkingWaterBeingWorkedOn().equals("")) {
                            String itemsOnTreatingDrinkingWaterBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnTreatingDrinkingWaterBeingWorkedOn();
                            if (itemsOnTreatingDrinkingWaterBeingWorkedOn.contains("[")) {
                                itemsOnTreatingDrinkingWaterBeingWorkedOn = itemsOnTreatingDrinkingWaterBeingWorkedOn.substring(1, itemsOnTreatingDrinkingWaterBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(itemsOnTreatingDrinkingWaterBeingWorkedOn.split(",\\s*")));
                        }

                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getThingsOnUsageOfMosquitoNetsBeingWorkedOn().equals("")) {
                            String usageOfMosquitoNetsBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getThingsOnUsageOfMosquitoNetsBeingWorkedOn();
                            if (usageOfMosquitoNetsBeingWorkedOn.contains("[")) {
                                usageOfMosquitoNetsBeingWorkedOn = usageOfMosquitoNetsBeingWorkedOn.substring(1, usageOfMosquitoNetsBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(usageOfMosquitoNetsBeingWorkedOn.split(",\\s*")));
                        }

                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getThingsOnFpBeingWorkedOn().equals("")) {
                            String fpBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getThingsOnFpBeingWorkedOn();
                            if (fpBeingWorkedOn.contains("[")) {
                                fpBeingWorkedOn = fpBeingWorkedOn.substring(1, fpBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(fpBeingWorkedOn.split(",\\s*")));
                        }

                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getHealthFacilitiesItemsBeingWorkedOn().equals("")) {
                            String healthFacilitiesItemsBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getHealthFacilitiesItemsBeingWorkedOn();
                            if (healthFacilitiesItemsBeingWorkedOn.contains("[")) {
                                healthFacilitiesItemsBeingWorkedOn = healthFacilitiesItemsBeingWorkedOn.substring(1, healthFacilitiesItemsBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(healthFacilitiesItemsBeingWorkedOn.split(",\\s*")));
                        }
                    }

                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.SOCIAL_INTEGRATION)) {
                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getSocialIntegrationItemsBeingWorkedOn().equals("")) {
                            String socialIntegrationItemsBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getSocialIntegrationItemsBeingWorkedOn();
                            if (socialIntegrationItemsBeingWorkedOn.contains("[")) {
                                socialIntegrationItemsBeingWorkedOn = socialIntegrationItemsBeingWorkedOn.substring(1, socialIntegrationItemsBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(socialIntegrationItemsBeingWorkedOn.split(",\\s*")));
                        }
                    }

                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.LAND)) {
                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnLandBeingWorkedOn().equals("")) {
                            String itemsOnLandBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getItemsOnLandBeingWorkedOn();
                            if (itemsOnLandBeingWorkedOn.contains("[")) {
                                itemsOnLandBeingWorkedOn = itemsOnLandBeingWorkedOn.substring(1, itemsOnLandBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(itemsOnLandBeingWorkedOn.split(",\\s*")));
                        }
                    }

                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.FARMING)) {
                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getFarmingItemsBeingWorkedOn().equals("")) {
                            String farmingItemsBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getFarmingItemsBeingWorkedOn();
                            if (farmingItemsBeingWorkedOn.contains("[")) {
                                farmingItemsBeingWorkedOn = farmingItemsBeingWorkedOn.substring(1, farmingItemsBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(farmingItemsBeingWorkedOn.split(",\\s*")));
                        }
                    }

                    if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL) || type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.LIVESTOCK)) {
                        if (!pathfinderModelHouseholdOngoingActivitiesObject.getLivestockItemsBeingWorkedOn().equals("")) {
                            String livestockItemsBeingWorkedOn = pathfinderModelHouseholdOngoingActivitiesObject.getLivestockItemsBeingWorkedOn();
                            if (livestockItemsBeingWorkedOn.contains("[")) {
                                livestockItemsBeingWorkedOn = livestockItemsBeingWorkedOn.substring(1, livestockItemsBeingWorkedOn.length() - 1);
                            }
                            list.addAll(Arrays.asList(livestockItemsBeingWorkedOn.split(",\\s*")));
                        }
                    }


                    View itemInProgress = getLayoutInflater().inflate(R.layout.fragment_item_in_progress, null);

                    SpannableString spanString;
                    StringBuilder sb = new StringBuilder();


                    for (String onGoingActivityString : list) {
                        try {
                            sb.append(lineSeparator + getStringResourceByName(onGoingActivityString));
                        } catch (Exception e) {
                            Timber.e(e);
                        }
                    }

                    String concat = sb.toString();
                    spanString = new SpannableString(concat);

                    for (String onGoingActivityString : list) {
                        try {
                            addBullet(getStringResourceByName(onGoingActivityString), concat, spanString);
                        } catch (Exception e) {
                            Timber.e(e);
                        }
                    }

                    if (StringUtils.isNotBlank(concat)) {
                        TextView ongoingActivitiesTv = itemInProgress.findViewById(R.id.ongoing_activities);
                        TextView dateTv = itemInProgress.findViewById(R.id.date);

                        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = new Date(Long.parseLong(pathfinderModelHouseholdOngoingActivitiesObject.getLastInteractedWith()));
                        dateTv.setText(lineSeparator + sf.format(date));
                        ongoingActivitiesTv.setText(spanString);
                        onActivitiesList.addView(itemInProgress);
                    }
                }
            }
        });


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

    private String getStringResourceByName(String aString) {
        String packageName = getContext().getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }
}