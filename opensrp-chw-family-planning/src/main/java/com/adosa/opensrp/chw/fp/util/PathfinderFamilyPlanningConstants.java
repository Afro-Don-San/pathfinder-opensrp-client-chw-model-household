package com.adosa.opensrp.chw.fp.util;

public interface PathfinderFamilyPlanningConstants {

    int REQUEST_CODE_GET_JSON = 2244;

    interface FamilyPlanningMemberObject {
        String MEMBER_OBJECT = "pathfinderFpMemberObject";
    }

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
        String FAMILY_PLANNING_REGISTER = "family_planning_register";
    }

    interface DBConstants {
        String FAMILY_PLANNING_TABLE = "ec_family_planning";
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
        String FP_METHOD_ACCEPTED = "fp_method_accepted";
        String FP_FP_START_DATE = "fp_start_date";
        String EDD = "edd";
        String FP_PILL_CYCLES = "no_pillcycles";
        String REASON_STOP_FP_CHW = "reason_stop_fp_chw";

        String FP_POP = "pop";
        String FP_COC = "coc";
        String FP_VASECTOMY = "vasectomy";
        String FP_FEMALE_CONDOM = "female_condom";
        String FP_MALE_CONDOM = "male_condom";
        String FP_INJECTABLE = "injection";
        String FP_IUD = "iud";
        String FP_TUBAL_LIGATION = "tubal_ligation";
        String FP_LAM = "lam";
        String FP_IMPLANTS = "implants";
        String FP_SDM= "sdm";
    }

    interface ActivityPayload {
        String BASE_ENTITY_ID = "BASE_ENTITY_ID";
        String ACTION = "ACTION";
        String FP_FORM_NAME = "FP_FORM_NAME";
        String REGISTRATION_PAYLOAD_TYPE = "REGISTRATION";
        String UPDATE_REGISTRATION_PAYLOAD_TYPE = "UPDATE_REGISTRATION";
        String GIVE_FP_METHOD = "GIVE_FP_METHOD";
        String CITIZEN_REPORT_CARD = "CITIZEN_REPORT_CARD";
        String CHANGE_METHOD_PAYLOAD_TYPE = "CHANGE_METHOD";
        String DOB = "DOB";
        String FORM_AS_STRING = "FORM_AS_STRING";
    }

    interface PregnancyStatus {
        String PREGNANT = "PREGNANT";
        String NOT_LIKELY_PREGNANT = "NOT LIKELY PREGNANT";
        String NOT_UNLIKELY_PREGNANT = "NOT UNLIKELY PREGNANT";
    }

    interface ChoosePregnancyTestReferral {
        String WAIT_FOR_NEXT_VISIT = "wait_for_next_visit";
        String RECEIVE_PREGNANCY_TEST_REFERRAL = "receive_pregnancy_test_referral";
        String COMPLETE_PREGNANCY_TEST_QUESTIONS_WITHOUT_COMPLETING_REFERRAL = "complete_pregnancy_screening_questions";
    }

}
