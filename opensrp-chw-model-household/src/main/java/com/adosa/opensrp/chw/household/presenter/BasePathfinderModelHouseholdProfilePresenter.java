package com.adosa.opensrp.chw.household.presenter;

import static org.smartregister.util.Utils.getName;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdProfileContract;
import com.adosa.opensrp.chw.household.interactor.BasePathfinderModelHouseholdProfileInteractor;
import com.adosa.opensrp.chw.household.util.ModelHouseholdUtil;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;

import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.view.contract.BaseProfileContract;

import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.TreeSet;

public class BasePathfinderModelHouseholdProfilePresenter implements BaseProfileContract.Presenter, BaseModelHouseholdProfileContract.Presenter, BaseModelHouseholdProfileContract.InteractorCallBack {

    protected WeakReference<BaseModelHouseholdProfileContract.View> viewReference;

    protected BaseModelHouseholdProfileContract.Model model;

    protected RegisterConfiguration config;

    protected String baseEntityId;
    protected String familyHead;
    protected String primaryCaregiver;
    protected String villageTown;

    protected Set<org.smartregister.configurableviews.model.View> visibleColumns = new TreeSet<>();

    private BasePathfinderModelHouseholdProfileInteractor interactor;

    private String viewConfigurationIdentifier;

    public BasePathfinderModelHouseholdProfilePresenter(BaseModelHouseholdProfileContract.View view, BaseModelHouseholdProfileContract.Model model, String viewConfigurationIdentifier, String baseEntityId, String familyHead, String primaryCaregiver, String villageTown) {
        this.viewReference = new WeakReference<>(view);
        this.model = model;
        this.viewConfigurationIdentifier = viewConfigurationIdentifier;
        this.config = model.defaultRegisterConfiguration();

        this.baseEntityId = baseEntityId;
        this.familyHead = familyHead;
        this.primaryCaregiver = primaryCaregiver;
        this.villageTown = villageTown;

        this.interactor = new BasePathfinderModelHouseholdProfileInteractor();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

        viewReference = null;//set to null on destroy

        // Inform interactor
        interactor.onDestroy(isChangingConfiguration);

        // Activity destroyed set interactor to null
        if (!isChangingConfiguration) {
            interactor = null;
        }
    }

    protected BaseModelHouseholdProfileContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }


    @Override
    public void fetchProfileData() {
        interactor.refreshProfileView(baseEntityId, this);
    }

    @Override
    public void refreshProfileView() {
        interactor.refreshProfileView(baseEntityId, this);
    }


    private void setVisibleColumns(Set<org.smartregister.configurableviews.model.View> visibleColumns) {
        this.visibleColumns = visibleColumns;
    }

    public void setModel(BaseModelHouseholdProfileContract.Model model) {
        this.model = model;
    }

    public String getBaseEntityId() {
        return baseEntityId;
    }


    @Override
    public void refreshProfileTopSection(CommonPersonObjectClient client) {

        if (client == null || client.getColumnmaps() == null) {
            return;
        }

        String firstName = ModelHouseholdUtil.getValue(client.getColumnmaps(), PathfinderModelHouseholdConstants.DBConstants.FIRST_NAME, true);
        String lastName = ModelHouseholdUtil.getValue(client.getColumnmaps(), PathfinderModelHouseholdConstants.DBConstants.LAST_NAME, true);

        getView().setProfileName(getName(firstName, lastName));

        String gender = ModelHouseholdUtil.getValue(client.getColumnmaps(), PathfinderModelHouseholdConstants.DBConstants.GENDER, true);
        getView().setProfileDetailOne(gender);

        getView().setProfileDetailTwo(villageTown);

        String uniqueId = ModelHouseholdUtil.getValue(client.getColumnmaps(), PathfinderModelHouseholdConstants.DBConstants.UNIQUE_ID, false);
        getView().setProfileDetailThree(String.format(getView().getString(R.string.id_with_value), uniqueId));

        if (baseEntityId.equals(familyHead)) {
            getView().toggleFamilyHead(true);
        } else {
            getView().toggleFamilyHead(false);
        }

        if (baseEntityId.equals(primaryCaregiver)) {
            getView().togglePrimaryCaregiver(true);
        } else {
            getView().togglePrimaryCaregiver(false);
        }

        /*String dobString = Utils.getDuration(Utils.getValue(client.getColumnmaps(), DBConstants.KEY.DOB, false));
        dobString = dobString.contains("y") ? dobString.substring(0, dobString.indexOf("y")) : dobString;
        dobString = String.format(getView().getString(R.string.age_text), dobString);
        getView().setProfileDetailTwo(dobString);

        String phoneNumber = Utils.getValue(client.getColumnmaps(), DBConstants.KEY.PHONE_NUMBER, false);
        getView().setProfileDetailThree(phoneNumber);*/

        getView().setProfileImage(client.getCaseId());
    }
}
