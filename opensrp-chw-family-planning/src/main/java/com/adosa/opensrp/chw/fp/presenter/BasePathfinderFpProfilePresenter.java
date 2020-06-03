package com.adosa.opensrp.chw.fp.presenter;

import com.adosa.opensrp.chw.fp.contract.BaseFpProfileContract;
import com.adosa.opensrp.chw.fp.domain.PathfinderFpMemberObject;

import org.smartregister.domain.AlertStatus;
import org.smartregister.view.contract.BaseProfileContract;

import java.lang.ref.WeakReference;
import java.util.Date;

public class BasePathfinderFpProfilePresenter implements BaseProfileContract, BaseFpProfileContract.Presenter, BaseFpProfileContract.InteractorCallback {
    private WeakReference<BaseFpProfileContract.View> view;
    private PathfinderFpMemberObject pathfinderFpMemberObject;
    private BaseFpProfileContract.Interactor interactor;

    public BasePathfinderFpProfilePresenter(BaseFpProfileContract.View view, BaseFpProfileContract.Interactor interactor, PathfinderFpMemberObject pathfinderFpMemberObject) {
        this.view = new WeakReference<>(view);
        this.interactor = interactor;
        this.pathfinderFpMemberObject = pathfinderFpMemberObject;
    }

    @Override
    public BaseFpProfileContract.View getView() {
        if (view != null && view.get() != null)
            return view.get();

        return null;
    }

    @Override
    public void refreshProfileData() {
        if (getView() != null) {
            getView().showProgressBar(true);
        }
        interactor.refreshProfileView(pathfinderFpMemberObject, false, this);
    }

    @Override
    public void refreshProfileFpStatusInfo() {
        interactor.updateProfileFpStatusInfo(pathfinderFpMemberObject, this);
    }

    @Override
    public void refreshLastVisit(Date lastVisitDate) {
        if (getView() != null) {
            getView().updateLastVisitRow(lastVisitDate);
        }
    }

    @Override
    public void refreshProfileTopSection(PathfinderFpMemberObject pathfinderFpMemberObject) {
        if (getView() != null) {
            getView().setProfileViewDetails(pathfinderFpMemberObject);
            getView().showProgressBar(false);
        }
    }

    @Override
    public void refreshUpComingServicesStatus(String service, AlertStatus status, Date date) {
        if (getView() != null) {
            getView().setUpComingServicesStatus(service, status, date);
        }
    }

    @Override
    public void refreshFamilyStatus(AlertStatus status) {
        if (getView() != null) {
            getView().setFamilyStatus(status);
        }
    }
}
