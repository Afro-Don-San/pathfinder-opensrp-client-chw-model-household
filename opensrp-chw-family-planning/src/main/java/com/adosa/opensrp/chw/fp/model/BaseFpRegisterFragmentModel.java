package com.adosa.opensrp.chw.fp.model;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import com.adosa.opensrp.chw.fp.PathfinderFpLibrary;
import com.adosa.opensrp.chw.fp.contract.BaseFpRegisterFragmentContract;
import com.adosa.opensrp.chw.fp.util.ConfigHelper;
import com.adosa.opensrp.chw.fp.util.PathfinderFamilyPlanningConstants;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.model.Field;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.cursoradapter.SmartRegisterQueryBuilder;
import org.smartregister.domain.Response;
import org.smartregister.domain.ResponseStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

public class BaseFpRegisterFragmentModel implements BaseFpRegisterFragmentContract.Model {

    @NonNull
    @Override
    public String mainSelect(@NonNull String tableName, @NonNull String mainCondition) {
        SmartRegisterQueryBuilder queryBuilder = new SmartRegisterQueryBuilder();
        queryBuilder.SelectInitiateMainTable(tableName, mainColumns(tableName));
        queryBuilder.customJoin("INNER JOIN " + PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + " ON  " + tableName + "." + PathfinderFamilyPlanningConstants.DBConstants.BASE_ENTITY_ID + " = " + PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.BASE_ENTITY_ID + " COLLATE NOCASE ");
        queryBuilder.customJoin("INNER JOIN " + PathfinderFamilyPlanningConstants.DBConstants.FAMILY + " ON  " + PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.RELATIONAL_ID + " = " + PathfinderFamilyPlanningConstants.DBConstants.FAMILY + "." + PathfinderFamilyPlanningConstants.DBConstants.BASE_ENTITY_ID);

        return queryBuilder.mainCondition(mainCondition);
    }


    @Override
    public RegisterConfiguration defaultRegisterConfiguration() {
        return ConfigHelper.defaultRegisterConfiguration(PathfinderFpLibrary.getInstance().context().applicationContext());
    }

    @Override
    public ViewConfiguration getViewConfiguration(String viewConfigurationIdentifier) {
        return ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().getViewConfiguration(viewConfigurationIdentifier);
    }

    @Override
    public Set<View> getRegisterActiveColumns(String viewConfigurationIdentifier) {
        return ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().getRegisterActiveColumns(viewConfigurationIdentifier);
    }

    @Override
    public String countSelect(String tableName, String mainCondition) {
        SmartRegisterQueryBuilder countQueryBuilder = new SmartRegisterQueryBuilder();
        countQueryBuilder.SelectInitiateMainTableCounts(tableName);
        return countQueryBuilder.mainCondition(mainCondition);
    }


    protected String[] mainColumns(String tableName) {
        Set<String> columnList = new HashSet<>();
        columnList.add(tableName + "." + PathfinderFamilyPlanningConstants.DBConstants.LAST_INTERACTED_WITH);
        columnList.add(tableName + "." + PathfinderFamilyPlanningConstants.DBConstants.BASE_ENTITY_ID);
        columnList.add(tableName + "." + PathfinderFamilyPlanningConstants.DBConstants.FP_METHOD_ACCEPTED);
        columnList.add(tableName + "." + PathfinderFamilyPlanningConstants.DBConstants.FP_FP_START_DATE);
        columnList.add(PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.RELATIONAL_ID + " as relationalid");
        columnList.add(PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.RELATIONAL_ID);
        columnList.add(PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.FIRST_NAME);
        columnList.add(PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.MIDDLE_NAME);
        columnList.add(PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.LAST_NAME);
        columnList.add(PathfinderFamilyPlanningConstants.DBConstants.FAMILY_MEMBER + "." + PathfinderFamilyPlanningConstants.DBConstants.DOB);
        columnList.add(PathfinderFamilyPlanningConstants.DBConstants.FAMILY + "." + PathfinderFamilyPlanningConstants.DBConstants.VILLAGE_TOWN);

        return columnList.toArray(new String[columnList.size()]);
    }

    @Override
    public String getFilterText(List<Field> list, String filterTitle) {
        List<Field> filterList = list;
        if (filterList == null) {
            filterList = new ArrayList<>();
        }

        String filter = filterTitle;
        if (filter == null) {
            filter = "";
        }
        return "<font color=#727272>" + filter + "</font> <font color=#f0ab41>(" + filterList.size() + ")</font>";
    }

    @Override
    public String getSortText(Field sortField) {
        String sortText = "";
        if (sortField != null) {
            if (StringUtils.isNotBlank(sortField.getDisplayName())) {
                sortText = "(Sort: " + sortField.getDisplayName() + ")";
            } else if (StringUtils.isNotBlank(sortField.getDbAlias())) {
                sortText = "(Sort: " + sortField.getDbAlias() + ")";
            }
        }
        return sortText;
    }

    @Override
    public JSONArray getJsonArray(Response<String> response) {
        try {
            if (response.status().equals(ResponseStatus.success)) {
                return new JSONArray(response.payload());
            }
        } catch (Exception e) {
            Timber.e(getClass().getName(), "", e);
        }
        return null;
    }
}
