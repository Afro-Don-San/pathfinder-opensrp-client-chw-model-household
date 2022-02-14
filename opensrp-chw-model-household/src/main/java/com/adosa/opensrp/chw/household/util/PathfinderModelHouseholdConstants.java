package com.adosa.opensrp.chw.household.util;

public interface PathfinderModelHouseholdConstants {
    int REQUEST_CODE_GET_JSON = 2244;

    interface JsonFromExtra {
        String JSON = "json";
    }

    interface EventType {
        String FAMILY_PLANNING_REGISTRATION = "Family Planning Registration";
        String UPDATE_FAMILY_PLANNING_REGISTRATION = "Update Family Planning Registration";
        String FAMILY_PLANNING_CHANGE_METHOD = "Family Planning Change Method";
        String FP_FOLLOW_UP_VISIT = "Fp Follow Up Visit";
        String FP_FOLLOW_UP_VISIT_RESUPPLY = "FP Follow up Visit Resupply";
        String FP_FOLLOW_UP_VISIT_COUNSELLING = "FP Follow up visit Counselling";
        String INTRODUCTION_TO_FAMILY_PLANNING= "Introduction to Family Planning";
        String FAMILY_PLANNING_PREGNANCY_SCREENING = "Family Planning Pregnancy Screening";
        String FAMILY_PLANNING_PREGNANCY_TEST_REFERRAL_FOLLOWUP = "Family Planning Pregnancy Test Referral Followup";
        String CHOOSING_FAMILY_PLANNING_METHOD = "Family Planning Method Choice";
        String GIVE_FAMILY_PLANNING_METHOD = "Give Family Planning Method";
        String FAMILY_PLANNING_DISCONTINUATION= "Family Planning Discontinuation";
        String ANC_REFERRAL = "ANC Referral";
        String FAMILY_PLANNING_REFERRAL = "Family Planning Referral";
        String FAMILY_PLANNING_REFERRAL_FOLLOWUP = "Family Planning Referral Followup";
        String FAMILY_PLANNING_METHOD_REFERRAL_FOLLOWUP = "Family Planning Method Referral Followup";
    }

    interface FORM_SUBMISSION_FIELD {
        String SERVICE_PROVIDED = "service_provided";
    }

    interface Forms {
        String FAMILY_PLANNING_REGISTRATION_FORM = "pathfinder_female_family_planning_registration";
    }

    interface CONFIGURATION {
        String MODEL_HOUSEHOLD_REGISTER = "model_household_register";
    }

    interface DBConstants {
        String MODEL_HOUSEHOLD_TABLE = "ec_model_household";
        String FAMILY_MEMBER = "ec_family_member";
        String FAMILY = "ec_family";
        String FIRST_NAME = "first_name";
        String MIDDLE_NAME = "middle_name";
        String LAST_NAME = "last_name";
        String BASE_ENTITY_ID = "base_entity_id";
        String UNIQUE_ID = "unique_id";
        String GENDER = "gender";
        String DOB = "dob";
        String AGE = "age";
        String LAST_INTERACTED_WITH = "last_interacted_with";
        String VILLAGE_TOWN = "village_town";
        String NEAREST_FACILITY = "nearest_facility";
        String DATE_REMOVED = "date_removed";
        String RELATIONALID = "relationalid";
        String FAMILY_HEAD = "family_head";
        String PRIMARY_CARE_GIVER = "primary_caregiver";
        String RELATIONAL_ID = "relational_id";
        String DETAILS = "details";
        String HOUSEHOLD_TYPE = "household_type";
        String DOES_HOUSEHOLD_CONTAIN_PREGNANT_MOTHER = "does_the_household_contain_a_pregnant_mother";
        String DOES_HOUSEHOLD_CONTAIN_CHILDREN_UNDER_2 = "does_the_household_contain_children_under_2";
    }

    interface ActivityPayload {
        String BASE_ENTITY_ID = "BASE_ENTITY_ID";
        String ACTION = "ACTION";
        String MODEL_HOUSEHOLD_FORM_NAME = "MODEL_HOUSEHOLD_FORM_NAME";
        String HOUSEHOLD_REGISTRATION_PAYLOAD_TYPE = "HOUSEHOLD_REGISTRATION";
        String UPDATE_HOUSEHOLD_REGISTRATION_PAYLOAD_TYPE = "UPDATE_HOUSEHOLD_REGISTRATION";
        String MODEL_HOUSEHOLD_EVALUATION_PAYLOAD_TYPE = "EVALUATION";
        String MODEL_HOUSEHOLD_DISCUSSION_PAYLOAD_TYPE = "DISCUSSION";
        String MODEL_HOUSEHOLD_ON_PROGRESS_ACTIVITIES_PAYLOAD_TYPE = "ON_PROGRESS";
        String DOB = "DOB";
        String FORM_AS_STRING = "FORM_AS_STRING";
        String TITLE_TEXT = "title_text";
        String EVALUATION_TYPE = "evaluation_type";
    }

    interface EvaluationTypes {
        String HEALTH = "HEALTH";
        String LAND = "LAND";
        String SOCIAL_INTEGRATION = "SOCIAL_INTEGRATION";
        String FARMING = "FARMING";
        String LIVESTOCK = "LIVESTOCK";
        String ALL = "ALL";
    }
}
