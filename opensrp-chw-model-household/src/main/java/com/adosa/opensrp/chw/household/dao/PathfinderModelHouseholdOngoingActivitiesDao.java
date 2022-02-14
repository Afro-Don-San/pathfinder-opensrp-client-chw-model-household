package com.adosa.opensrp.chw.household.dao;

import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdOngoingActivitiesObject;

import org.smartregister.dao.AbstractDao;

import java.util.List;

public class PathfinderModelHouseholdOngoingActivitiesDao extends AbstractDao {

    public static List<PathfinderModelHouseholdOngoingActivitiesObject> getOngoingActivities(String baseEntityID) {
        String sql = "select m.base_entity_id , m.unique_id , m.relational_id , m.dob , m.first_name , " +
                "m.middle_name , m.last_name , m.gender , m.phone_number , m.other_phone_number , " +
                "f.first_name family_name ,f.primary_caregiver , f.family_head , f.village_town, f.nearest_facility , f.landmark,  f.gps ," +
                "fh.first_name family_head_first_name , fh.middle_name family_head_middle_name , " +
                "fh.last_name family_head_last_name, fh.phone_number family_head_phone_number, " +
                "pcg.first_name pcg_first_name , pcg.last_name pcg_last_name , pcg.middle_name pcg_middle_name , " +
                "pcg.phone_number  pcg_phone_number , mr.* from ec_family_member m " +
                "inner join ec_family f on m.relational_id = f.base_entity_id " +
                "inner join ec_model_household_ongoing_activities mr on mr.entity_id = m.base_entity_id " +
                "left join ec_family_member fh on fh.base_entity_id = f.family_head " +
                "left join ec_family_member pcg on pcg.base_entity_id = f.primary_caregiver where m.base_entity_id ='" + baseEntityID + "' ";

        DataMap<PathfinderModelHouseholdOngoingActivitiesObject> dataMap = cursor -> {
            PathfinderModelHouseholdOngoingActivitiesObject ongoingActivities = new PathfinderModelHouseholdOngoingActivitiesObject();

            ongoingActivities.setBaseEntityId(getCursorValue(cursor, "base_entity_id", ""));
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
        String sql = "select m.base_entity_id , m.unique_id , m.relational_id , m.dob , m.first_name , " +
                "m.middle_name , m.last_name , m.gender , m.phone_number , m.other_phone_number , " +
                "f.first_name family_name ,f.primary_caregiver , f.family_head , f.village_town, f.nearest_facility , f.landmark,  f.gps ," +
                "fh.first_name family_head_first_name , fh.middle_name family_head_middle_name , " +
                "fh.last_name family_head_last_name, fh.phone_number family_head_phone_number, " +
                "pcg.first_name pcg_first_name , pcg.last_name pcg_last_name , pcg.middle_name pcg_middle_name , " +
                "pcg.phone_number  pcg_phone_number , mr.* from ec_family_member m " +
                "inner join ec_family f on m.relational_id = f.base_entity_id " +
                "inner join ec_model_household_ongoing_activities mr on mr.base_entity_id = m.base_entity_id " +
                "left join ec_family_member fh on fh.base_entity_id = f.family_head " +
                "left join ec_family_member pcg on pcg.base_entity_id = f.primary_caregiver where m.base_entity_id ='" + baseEntityID + "' and mr.type = '"+type+"' ";

        DataMap<PathfinderModelHouseholdOngoingActivitiesObject> dataMap = cursor -> {
            PathfinderModelHouseholdOngoingActivitiesObject ongoingActivities = new PathfinderModelHouseholdOngoingActivitiesObject();

            ongoingActivities.setBaseEntityId(getCursorValue(cursor, "base_entity_id", ""));
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

