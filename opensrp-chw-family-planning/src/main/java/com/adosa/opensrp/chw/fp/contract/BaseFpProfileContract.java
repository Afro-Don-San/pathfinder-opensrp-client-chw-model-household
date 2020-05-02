package com.adosa.opensrp.chw.fp.contract;

import android.content.Context;

import com.adosa.opensrp.chw.fp.domain.FpMemberObject;
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

        void openChooseFpMethod();

        void openGiveFpMethodButton();

        void openUpcomingServices();

        void openFamilyDueServices();

        void openFpRegistrationForm();

        void openFollowUpVisitForm(boolean isEdit);

        void setUpComingServicesStatus(String service, AlertStatus status, Date date);

        void setFamilyStatus(AlertStatus status);

        void setProfileViewDetails(FpMemberObject fpMemberObject);

        void setupFollowupVisitEditViews(boolean isWithin24Hours);

        void updateLastVisitRow(Date lastVisitDate);

        void setFollowUpButtonOverdue();

        void setFollowUpButtonDue();

        void hideFollowUpVisitButton();

        void showFollowUpVisitButton();

        void showIntroductionToFpButton();

        void showFpPregnancyScreeningButton();

        void showChooseFpMethodButton();

        void showGiveFpMethodButton();

        void showProgressBar(boolean status);

        void onMemberDetailsReloaded(FpMemberObject fpMemberObject);

    }

    interface Presenter {

        View getView();

        void refreshProfileData();

        void refreshProfileFpStatusInfo();

    }

    interface Interactor {

        void refreshProfileView(FpMemberObject fpMemberObject, boolean isForEdit, BaseFpProfileContract.InteractorCallback callback);

        void updateProfileFpStatusInfo(FpMemberObject memberObject, BaseFpProfileContract.InteractorCallback callback);

    }

    interface InteractorCallback {

        void refreshProfileTopSection(FpMemberObject fpMemberObject);

        void refreshUpComingServicesStatus(String service, AlertStatus status, Date date);

        void refreshFamilyStatus(AlertStatus status);

        void refreshLastVisit(Date lastVisitDate);
    }
}
