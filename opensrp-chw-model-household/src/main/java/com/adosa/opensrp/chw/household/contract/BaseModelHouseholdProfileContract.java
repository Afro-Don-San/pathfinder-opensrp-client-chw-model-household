package com.adosa.opensrp.chw.household.contract;

import android.content.Context;

import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;

import org.smartregister.domain.AlertStatus;
import org.smartregister.view.contract.BaseProfileContract;

import java.util.Date;

public interface BaseModelHouseholdProfileContract {

    interface View extends BaseProfileContract.View {

        Context getContext();

        void openMedicalHistory();

        void openFamilyPlanningRegistration();

        void openFamilyPlanningIntroduction();

        void openPregnancyScreening();

        void openPregnancyTestFollowup();

        void openChooseFpMethod();

        void openGiveFpMethodButton();

        void openRiskAssessment();

        void showRiskAssessmentButton();

        void openUpcomingServices();

        void openFamilyDueServices();

        void openFpRegistrationForm();

        void openCitizenReportCard();

        void openReferralFollowup();

        void openFollowUpVisitForm(boolean isEdit);

        void setUpComingServicesStatus(String service, AlertStatus status, Date date);

        void setFamilyStatus(AlertStatus status);

        void setProfileViewDetails(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject);

        void setupFollowupVisitEditViews(boolean isWithin24Hours);

        void updateLastVisitRow(Date lastVisitDate);

        void setFollowUpButtonOverdue();

        void setFollowUpButtonDue();

        void setPregnancyScreeningButtonDue();

        void setPregnancyScreeningButtonOverdue();

        void setPregnancyTestFollowupButtonDue();

        void setPregnancyTestFollowupButtonOverdue();

        void setReferralFollowupButtonDue();

        void setReferralFollowupButtonOverdue();

        void setFpMethodChoiceButtonDue();

        void setFpMethodChoiceButtonOverdue();

        void hideFollowUpVisitButton();

        void showFollowUpVisitButton();

        void showIntroductionToFpButton();

        void showFpPregnancyScreeningButton();

        void showFpPregnancyTestFollowupButton();

        void showChooseFpMethodButton();

        void setFamilyLocation();

        void openFamilyLocation();

        void showGiveFpMethodButton();

        void showCitizenReportCardButton();

        void showReferralFollowupButton();

        void showProgressBar(boolean status);

        void onMemberDetailsReloaded(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject);

    }

    interface Presenter {

        View getView();

        void refreshProfileData();

        void refreshProfileFpStatusInfo();

    }

    interface Interactor {

        void refreshProfileView(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject, boolean isForEdit, BaseModelHouseholdProfileContract.InteractorCallback callback);

        void updateProfileFpStatusInfo(PathfinderModelHouseholdMemberObject memberObject, BaseModelHouseholdProfileContract.InteractorCallback callback);

    }

    interface InteractorCallback {

        void refreshProfileTopSection(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject);

        void refreshUpComingServicesStatus(String service, AlertStatus status, Date date);

        void refreshFamilyStatus(AlertStatus status);

        void refreshLastVisit(Date lastVisitDate);
    }
}
