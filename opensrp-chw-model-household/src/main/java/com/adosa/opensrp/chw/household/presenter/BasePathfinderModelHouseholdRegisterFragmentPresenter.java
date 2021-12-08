package com.adosa.opensrp.chw.household.presenter;

import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdRegisterFragmentContract;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;

import org.smartregister.configurableviews.model.Field;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BasePathfinderModelHouseholdRegisterFragmentPresenter implements BaseModelHouseholdRegisterFragmentContract.Presenter {

    protected WeakReference<BaseModelHouseholdRegisterFragmentContract.View> viewReference;

    protected BaseModelHouseholdRegisterFragmentContract.Model model;

    protected RegisterConfiguration config;

    protected Set<View> visibleColumns = new TreeSet<>();

    public BasePathfinderModelHouseholdRegisterFragmentPresenter(BaseModelHouseholdRegisterFragmentContract.View view, BaseModelHouseholdRegisterFragmentContract.Model model) {
        this.viewReference = new WeakReference<>(view);
        this.model = model;
        this.config = model.defaultRegisterConfiguration();
    }

    @Override
    public void updateSortAndFilter(List<Field> filterList, Field sortField) {
//        implement
    }

    @Override
    public String getMainCondition() {
        return " ec_family_member.date_removed is null AND ec_model_household.is_closed = 0 AND ec_model_household.does_the_client_want_to_be_enrolled_into_model_household = 'yes' ";
    }

    @Override
    public String getDefaultSortQuery() {
        return "ec_model_household.last_interacted_with DESC ";

    }

    @Override
    public void processViewConfigurations() {

        ViewConfiguration viewConfiguration = model.getViewConfiguration(PathfinderModelHouseholdConstants.CONFIGURATION.MODEL_HOUSEHOLD_REGISTER);
        if (viewConfiguration != null) {
            config = (RegisterConfiguration) viewConfiguration.getMetadata();
            this.visibleColumns = model.getRegisterActiveColumns(PathfinderModelHouseholdConstants.CONFIGURATION.MODEL_HOUSEHOLD_REGISTER);
        }

        if (config.getSearchBarText() != null && getView() != null) {
            getView().updateSearchBarHint(config.getSearchBarText());
        }
    }

    @Override
    public void initializeQueries(String mainCondition) {
        String tableName = PathfinderModelHouseholdConstants.DBConstants.MODEL_HOUSEHOLD_TABLE;

        String countSelect = model.countSelect(tableName, "");
        String mainSelect = model.mainSelect(tableName, "");

        getView().initializeQueryParams(tableName, countSelect, mainSelect);
        getView().initializeAdapter(visibleColumns);

        getView().countExecute();
        getView().filterandSortInInitializeQueries();
    }

    protected BaseModelHouseholdRegisterFragmentContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }

    @Override
    public void startSync() {
//        implement
    }

    @Override
    public void searchGlobally(String s) {
//        implement

    }

    @Override
    public String getDueFilterCondition() {
//        TODO implement using schedule table for visit
        return " (cast( julianday(STRFTIME('%Y-%m-%d', datetime('now'))) -  julianday(IFNULL(SUBSTR(fp_reg_date,7,4)|| '-' || SUBSTR(fp_reg_date,4,2) || '-' || SUBSTR(fp_reg_date,1,2),'')) as integer) between 0 and 14) ";
    }


    @Override
    public String getMainTable() {
        return PathfinderModelHouseholdConstants.DBConstants.MODEL_HOUSEHOLD_TABLE;
    }
}
