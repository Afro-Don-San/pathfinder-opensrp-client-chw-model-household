package com.adosa.opensrp.chw.household.presenter;

import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdProfileContract;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;

import org.smartregister.domain.AlertStatus;
import org.smartregister.view.contract.BaseProfileContract;

import java.lang.ref.WeakReference;
import java.util.Date;

public class BasePathfinderModelHouseholdProfilePresenter implements BaseProfileContract, BaseModelHouseholdProfileContract.Presenter, BaseModelHouseholdProfileContract.InteractorCallback {
    private WeakReference<BaseModelHouseholdProfileContract.View> view;
    private PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject;
    private BaseModelHouseholdProfileContract.Interactor interactor;

    public BasePathfinderModelHouseholdProfilePresenter(BaseModelHouseholdProfileContract.View view, BaseModelHouseholdProfileContract.Interactor interactor, PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject) {
        this.view = new WeakReference<>(view);
        this.interactor = interactor;
        this.pathfinderModelHouseholdMemberObject = pathfinderModelHouseholdMemberObject;
    }

    @Override
    public BaseModelHouseholdProfileContract.View getView() {
        if (view != null && view.get() != null)
            return view.get();

        return null;
    }

    @Override
    public void refreshProfileData() {
        if (getView() != null) {
            getView().showProgressBar(true);
        }
        interactor.refreshProfileView(pathfinderModelHouseholdMemberObject, false, this);
    }

    @Override
    public void refreshProfileFpStatusInfo() {
        interactor.updateProfileFpStatusInfo(pathfinderModelHouseholdMemberObject, this);
    }

    @Override
    public void refreshLastVisit(Date lastVisitDate) {
        if (getView() != null) {
            getView().updateLastVisitRow(lastVisitDate);
        }
    }

    @Override
    public void refreshProfileTopSection(PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject) {
        if (getView() != null) {
            getView().setProfileViewDetails(pathfinderModelHouseholdMemberObject);
            getView().showProgressBar(false);
        }
    }

    @Override
    public void refreshUpComingServicesStatus(String service, AlertStatus status, Date date) {
        if (getView() != null) {
            getView().setUpComingServicesStatus(service, status, date);
        }
        refreshProfileTopSection(pathfinderModelHouseholdMemberObject);
    }

    @Override
    public void refreshFamilyStatus(AlertStatus status) {
        if (getView() != null) {
            getView().setFamilyStatus(status);
        }
    }
}
