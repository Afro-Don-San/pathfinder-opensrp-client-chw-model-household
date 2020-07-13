package com.adosa.opensrp.chw.fp.contract;

import android.content.Context;

import com.adosa.opensrp.chw.fp.domain.PathfinderFpMemberObject;

import org.smartregister.domain.AlertStatus;
import org.smartregister.view.contract.BaseProfileContract;

import java.util.Date;

public interface BaseFpProfileContract {

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

        void setProfileViewDetails(PathfinderFpMemberObject pathfinderFpMemberObject);

        void setupFollowupVisitEditViews(boolean isWithin24Hours);

        void updateLastVisitRow(Date lastVisitDate);

        void setFollowUpButtonOverdue();

        void setFollowUpButtonDue();

        void setPregnancyScreeningButtonDue();

        void setPregnancyScreeningButtonOverdue();

        void setPregnancyTestFollowupButtonDue();

        void setPregnancyTestFollowupButtonOverdue();

        void setFpMethodChoiceButtonDue();

        void setFpMethodChoiceButtonOverdue();

        void hideFollowUpVisitButton();

        void showFollowUpVisitButton();

        void showIntroductionToFpButton();

        void showFpPregnancyScreeningButton();

        void showFpPregnancyTestFollowupButton();

        void showChooseFpMethodButton();

        void showGiveFpMethodButton();

        void showCitizenReportCardButton();

        void showReferralFollowupButton();

        void showProgressBar(boolean status);

        void onMemberDetailsReloaded(PathfinderFpMemberObject pathfinderFpMemberObject);

    }

    interface Presenter {

        View getView();

        void refreshProfileData();

        void refreshProfileFpStatusInfo();

    }

    interface Interactor {

        void refreshProfileView(PathfinderFpMemberObject pathfinderFpMemberObject, boolean isForEdit, BaseFpProfileContract.InteractorCallback callback);

        void updateProfileFpStatusInfo(PathfinderFpMemberObject memberObject, BaseFpProfileContract.InteractorCallback callback);

    }

    interface InteractorCallback {

        void refreshProfileTopSection(PathfinderFpMemberObject pathfinderFpMemberObject);

        void refreshUpComingServicesStatus(String service, AlertStatus status, Date date);

        void refreshFamilyStatus(AlertStatus status);

        void refreshLastVisit(Date lastVisitDate);
    }
}
