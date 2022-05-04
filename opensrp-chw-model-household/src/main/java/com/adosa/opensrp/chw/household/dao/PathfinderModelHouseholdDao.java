package com.adosa.opensrp.chw.household.dao;

import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.smartregister.dao.AbstractDao;

import java.util.List;

import timber.log.Timber;

public class PathfinderModelHouseholdDao extends AbstractDao {

    public static boolean isRegisteredForModelHousehold(String baseEntityID) {
        String sql = String.format(
                "select count(ec_model_household.base_entity_id) count\n" +
                        "from ec_model_household\n" +
                        "where base_entity_id = '%s'\n" +
                        "  and ec_model_household.is_closed = 0\n" +
                        "  and ec_model_household.does_the_client_want_to_be_enrolled_into_model_household = 'yes'", baseEntityID);

        DataMap<Integer> dataMap = cursor -> getCursorIntValue(cursor, "count");

        List<Integer> res = readData(sql, dataMap);
        if (res == null || res.size() != 1)
            return false;

        return res.get(0) > 0;
    }


    public static float getScore(String baseEntityID, String scoreType) {
        String sql = "select "+scoreType+" as score " +
                "from ec_model_household " +
                "where base_entity_id = '" + baseEntityID + "'" +
                "  and ec_model_household.is_closed = 0 " +
                "  and ec_model_household.does_the_client_want_to_be_enrolled_into_model_household = 'yes'";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "score");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() > 0 && res.get(0) != null)
            return Float.parseFloat(res.get(0));
        return 0;
    }


    public static String getItemBeingWorkedOn(String baseEntityID, String actionBeingWorkedOn, String type) {
        String sql = "select "+actionBeingWorkedOn+" as action " +
                "from ec_model_household_ongoing_activities " +
                "where base_entity_id = '" + baseEntityID + "'" +
                "  and type = '"+type+"'";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "action");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() > 0 && res.get(0) != null)
            return res.get(0);
        return null;
    }

    public static PathfinderModelHouseholdMemberObject getMember(String baseEntityID) {
        String sql = "select m.base_entity_id , m.unique_id , m.relational_id , m.dob , m.first_name , " +
                "m.middle_name , m.last_name , m.gender , m.phone_number , m.other_phone_number , " +
                "f.first_name family_name ,f.primary_caregiver , f.family_head , f.village_town, f.nearest_facility , f.landmark,  f.gps ," +
                "fh.first_name family_head_first_name , fh.middle_name family_head_middle_name , " +
                "fh.last_name family_head_last_name, fh.phone_number family_head_phone_number, " +
                "pcg.first_name pcg_first_name , pcg.last_name pcg_last_name , pcg.middle_name pcg_middle_name , " +
                "pcg.phone_number  pcg_phone_number , mr.* from ec_family_member m " +
                "inner join ec_family f  ON f.unique_id LIKE ('%' || m.unique_id || '%')" +
                "inner join ec_model_household mr on mr.base_entity_id = m.base_entity_id " +
                "left join ec_family_member fh on fh.base_entity_id = f.family_head " +
                "left join ec_family_member pcg on pcg.base_entity_id = f.primary_caregiver where m.base_entity_id ='" + baseEntityID + "' ";

        DataMap<PathfinderModelHouseholdMemberObject> dataMap = cursor -> {
            PathfinderModelHouseholdMemberObject memberObject = new PathfinderModelHouseholdMemberObject();

            memberObject.setFirstName(getCursorValue(cursor, "first_name", ""));
            memberObject.setMiddleName(getCursorValue(cursor, "middle_name", ""));
            memberObject.setLastName(getCursorValue(cursor, "last_name", ""));
            JSONArray locationArray = null;
            try {
                locationArray = new JSONArray(getCursorValue(cursor, "nearest_facility"));
                memberObject.setAddress(locationArray.getString(locationArray.length() - 1));
            } catch (JSONException e) {
                Timber.e(e);
                memberObject.setAddress(getCursorValue(cursor, "village_town"));
            }
            memberObject.setGender(getCursorValue(cursor, "gender"));
            memberObject.setUniqueId(getCursorValue(cursor, "unique_id", ""));
            memberObject.setAge(getCursorValue(cursor, "dob"));
            memberObject.setFamilyBaseEntityId(getCursorValue(cursor, "relational_id", ""));
            memberObject.setRelationalId(getCursorValue(cursor, "relational_id", ""));
            memberObject.setPrimaryCareGiver(getCursorValue(cursor, "primary_caregiver"));
            memberObject.setFamilyName(getCursorValue(cursor, "family_name", ""));
            memberObject.setPhoneNumber(getCursorValue(cursor, "phone_number", ""));
            memberObject.setBaseEntityId(getCursorValue(cursor, "base_entity_id", ""));
            memberObject.setFamilyHead(getCursorValue(cursor, "family_head", ""));
            memberObject.setFamilyHeadPhoneNumber(getCursorValue(cursor, "pcg_phone_number", ""));
            memberObject.setFamilyHeadPhoneNumber(getCursorValue(cursor, "family_head_phone_number", ""));
            memberObject.setHouseholdType(getCursorValue(cursor, "household_type", ""));
            memberObject.setLocation(getCursorValue(cursor, "nearest_facility", ""));
            String familyHeadName = getCursorValue(cursor, "family_head_first_name", "") + " "
                    + getCursorValue(cursor, "family_head_middle_name", "");

            familyHeadName =
                    (familyHeadName.trim() + " " + getCursorValue(cursor, "family_head_last_name", "")).trim();
            memberObject.setFamilyHeadName(familyHeadName);

            String familyPcgName = getCursorValue(cursor, "pcg_first_name", "") + " "
                    + getCursorValue(cursor, "pcg_middle_name", "");

            familyPcgName =
                    (familyPcgName.trim() + " " + getCursorValue(cursor, "pcg_last_name", "")).trim();
            memberObject.setPrimaryCareGiverName(familyPcgName);

            return memberObject;
        };

        List<PathfinderModelHouseholdMemberObject> res = readData(sql, dataMap);
        if (res == null || res.size() != 1)
            return null;

        return res.get(0);
    }

    public static void closeFpMemberFromRegister(String baseEntityID) {
        String sql = "update ec_family_planning set is_closed = 1 where base_entity_id = '" + baseEntityID + "'";
        updateDB(sql);
    }
}

