package com.adosa.opensrp.chw.household.dao;

import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdOngoingActivitiesObject;

import org.smartregister.dao.AbstractDao;

import java.util.List;

public class PathfinderModelHouseholdOngoingActivitiesDao extends AbstractDao {

    public static List<PathfinderModelHouseholdOngoingActivitiesObject> getOngoingActivities(String baseEntityID) {
        String sql = " SELECT * FROM (SELECT * FROM ec_model_household_ongoing_activities WHERE entity_id = '"+baseEntityID+"' ORDER BY last_interacted_with DESC)\n" +
                "GROUP BY entity_id,type ORDER BY last_interacted_with DESC";

        DataMap<PathfinderModelHouseholdOngoingActivitiesObject> dataMap = cursor -> {
            PathfinderModelHouseholdOngoingActivitiesObject ongoingActivities = new PathfinderModelHouseholdOngoingActivitiesObject();

            ongoingActivities.setBaseEntityId(getCursorValue(cursor, "entity_id", ""));
            ongoingActivities.setType(getCursorValue(cursor, "type", ""));
            ongoingActivities.setHealthAreasOfImprovementsBeingWorkedOn(getCursorValue(cursor, "health_areas_of_improvements_being_worked_on", ""));
            ongoingActivities.setItemsOnToiletUsageBeingWorkedOn(getCursorValue(cursor, "items_on_toilet_usage_being_worked_on", ""));
            ongoingActivities.setItemsOnBathroomUsageBeingWorkedOn(getCursorValue(cursor, "items_on_bathroom_usage_being_worked_on", ""));
            ongoingActivities.setItemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn(getCursorValue(cursor, "items_on_hands_washing_area_outside_the_toilet_being_worked_on", ""));
            ongoingActivities.setItemsOnHouseholdCleanlinessBeingWorkedOn(getCursorValue(cursor, "items_on_household_cleanliness_being_worked_on", ""));
            ongoingActivities.setItemsOnDishesDryingContainerBeingWorkedOn(getCursorValue(cursor, "items_on_dishes_drying_container_being_worked_on", ""));
            ongoingActivities.setItemsOnTreatingDrinkingWaterBeingWorkedOn(getCursorValue(cursor, "items_on_treating_drinking_water_being_worked_on", ""));
            ongoingActivities.setThingsOnUsageOfMosquitoNetsBeingWorkedOn(getCursorValue(cursor, "things_on_usage_of_mosquito_nets_being_worked_on", ""));
            ongoingActivities.setThingsOnFpBeingWorkedOn(getCursorValue(cursor, "things_on_fp_being_worked_on", ""));
            ongoingActivities.setHealthFacilitiesItemsBeingWorkedOn(getCursorValue(cursor, "health_facilities_items_being_worked_on", ""));
            ongoingActivities.setSocialIntegrationItemsBeingWorkedOn(getCursorValue(cursor, "social_integration_items_being_worked_on", ""));
            ongoingActivities.setItemsOnLandBeingWorkedOn(getCursorValue(cursor, "items_on_land_being_worked_on", ""));
            ongoingActivities.setFarmingItemsBeingWorkedOn(getCursorValue(cursor, "farming_items_being_worked_on", ""));
            ongoingActivities.setLivestockItemsBeingWorkedOn(getCursorValue(cursor, "livestock_items_being_worked_on", ""));
            ongoingActivities.setLastInteractedWith(getCursorValue(cursor, "last_interacted_with", ""));

            return ongoingActivities;
        };

        List<PathfinderModelHouseholdOngoingActivitiesObject> res = readData(sql, dataMap);
        if (res == null)
            return null;

        return res;
    }

    public static List<PathfinderModelHouseholdOngoingActivitiesObject> getOngoingActivitiesByType(String baseEntityID, String type) {
        String sql = " SELECT * FROM (SELECT * FROM ec_model_household_ongoing_activities WHERE entity_id = '"+baseEntityID+"'AND type = '"+type+"' ORDER BY last_interacted_with DESC)\n" +
                "GROUP BY entity_id,type ORDER BY last_interacted_with DESC";

        DataMap<PathfinderModelHouseholdOngoingActivitiesObject> dataMap = cursor -> {
            PathfinderModelHouseholdOngoingActivitiesObject ongoingActivities = new PathfinderModelHouseholdOngoingActivitiesObject();

            ongoingActivities.setBaseEntityId(getCursorValue(cursor, "entity_id", ""));
            ongoingActivities.setType(getCursorValue(cursor, "type", ""));
            ongoingActivities.setHealthAreasOfImprovementsBeingWorkedOn(getCursorValue(cursor, "health_areas_of_improvements_being_worked_on", ""));
            ongoingActivities.setItemsOnToiletUsageBeingWorkedOn(getCursorValue(cursor, "items_on_toilet_usage_being_worked_on", ""));
            ongoingActivities.setItemsOnBathroomUsageBeingWorkedOn(getCursorValue(cursor, "items_on_bathroom_usage_being_worked_on", ""));
            ongoingActivities.setItemsOnHandsWashingAreaOutsideTheToiletBeingWorkedOn(getCursorValue(cursor, "items_on_hands_washing_area_outside_the_toilet_being_worked_on", ""));
            ongoingActivities.setItemsOnHouseholdCleanlinessBeingWorkedOn(getCursorValue(cursor, "items_on_household_cleanliness_being_worked_on", ""));
            ongoingActivities.setItemsOnDishesDryingContainerBeingWorkedOn(getCursorValue(cursor, "items_on_dishes_drying_container_being_worked_on", ""));
            ongoingActivities.setItemsOnTreatingDrinkingWaterBeingWorkedOn(getCursorValue(cursor, "items_on_treating_drinking_water_being_worked_on", ""));
            ongoingActivities.setThingsOnUsageOfMosquitoNetsBeingWorkedOn(getCursorValue(cursor, "things_on_usage_of_mosquito_nets_being_worked_on", ""));
            ongoingActivities.setThingsOnFpBeingWorkedOn(getCursorValue(cursor, "things_on_fp_being_worked_on", ""));
            ongoingActivities.setHealthFacilitiesItemsBeingWorkedOn(getCursorValue(cursor, "health_facilities_items_being_worked_on", ""));
            ongoingActivities.setSocialIntegrationItemsBeingWorkedOn(getCursorValue(cursor, "social_integration_items_being_worked_on", ""));
            ongoingActivities.setItemsOnLandBeingWorkedOn(getCursorValue(cursor, "items_on_land_being_worked_on", ""));
            ongoingActivities.setFarmingItemsBeingWorkedOn(getCursorValue(cursor, "farming_items_being_worked_on", ""));
            ongoingActivities.setLivestockItemsBeingWorkedOn(getCursorValue(cursor, "livestock_items_being_worked_on", ""));
            ongoingActivities.setLastInteractedWith(getCursorValue(cursor, "last_interacted_with", ""));

            return ongoingActivities;
        };

        List<PathfinderModelHouseholdOngoingActivitiesObject> res = readData(sql, dataMap);
        if (res == null)
            return null;

        return res;
    }
}

