package com.adosa.opensrp.chw.household.activity.application;

import org.smartregister.Context;
import org.smartregister.CoreLibrary;
import com.adosa.opensrp.chw.household.PathfinderModelHouseholdLibrary;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;
import org.smartregister.commonregistry.CommonFtsObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import com.adosa.opensrp.chw.household.BuildConfig;
import com.adosa.opensrp.chw.household.activity.repository.SampleRepository;
import com.adosa.opensrp.chw.household.activity.utils.SampleConstants;
import org.smartregister.receiver.SyncStatusBroadcastReceiver;
import org.smartregister.repository.Repository;
import org.smartregister.repository.UniqueIdRepository;
import org.smartregister.view.activity.DrishtiApplication;

import timber.log.Timber;

public class SampleApplication extends DrishtiApplication {
    private static final String TAG = SampleApplication.class.getCanonicalName();

    private static CommonFtsObject commonFtsObject;
    private UniqueIdRepository uniqueIdRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = Context.getInstance();
        context.updateApplicationContext(getApplicationContext());
        context.updateCommonFtsObject(createCommonFtsObject());

        //Initialize Modules
        CoreLibrary.init(context);
        ConfigurableViewsLibrary.init(context, getRepository());
        PathfinderModelHouseholdLibrary.init(context, getRepository(), BuildConfig.VERSION_CODE, BuildConfig.DATABASE_VERSION);

        SyncStatusBroadcastReceiver.init(this);

        //Auto login by default
        String password = "pwd";
        context.session().start(context.session().lengthInMilliseconds());
        context.configuration().getDrishtiApplication().setPassword(password);
        context.session().setPassword(password);

    }

    @Override
    public void logoutCurrentUser() {
    }

    public static synchronized SampleApplication getInstance() {
        return (SampleApplication) mInstance;
    }

    @Override
    public Repository getRepository() {
        try {
            if (repository == null) {
                repository = new SampleRepository(getInstance().getApplicationContext(), context);
            }
        } catch (UnsatisfiedLinkError e) {
            Timber.e(TAG, e.getMessage(), e);
        }
        return repository;
    }


    public static CommonFtsObject createCommonFtsObject() {
        if (commonFtsObject == null) {
            commonFtsObject = new CommonFtsObject(getFtsTables());
            for (String ftsTable : commonFtsObject.getTables()) {
                commonFtsObject.updateSearchFields(ftsTable, getFtsSearchFields(ftsTable));
                commonFtsObject.updateSortFields(ftsTable, getFtsSortFields(ftsTable));
            }
        }
        return commonFtsObject;
    }

    private static String[] getFtsTables() {
        return new String[]{SampleConstants.TABLE_NAME.FAMILY, SampleConstants.TABLE_NAME.FAMILY_MEMBER};
    }

    private static String[] getFtsSearchFields(String tableName) {
        if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY)) {
            return new String[]{
                    PathfinderModelHouseholdConstants.DBConstants.BASE_ENTITY_ID,
                    PathfinderModelHouseholdConstants.DBConstants.VILLAGE_TOWN,
                    PathfinderModelHouseholdConstants.DBConstants.FIRST_NAME,
                    PathfinderModelHouseholdConstants.DBConstants.LAST_NAME
            };
        } else if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY_MEMBER)) {
            return new String[]{
                    PathfinderModelHouseholdConstants.DBConstants.BASE_ENTITY_ID,
                    PathfinderModelHouseholdConstants.DBConstants.FIRST_NAME,
                    PathfinderModelHouseholdConstants.DBConstants.MIDDLE_NAME,
                    PathfinderModelHouseholdConstants.DBConstants.LAST_NAME};
        }
        return null;
    }

    private static String[] getFtsSortFields(String tableName) {
        if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY)) {
            return new String[]{
                    PathfinderModelHouseholdConstants.DBConstants.LAST_INTERACTED_WITH,
                    PathfinderModelHouseholdConstants.DBConstants.DATE_REMOVED};
        } else if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY_MEMBER)) {
            return new String[]{
                    PathfinderModelHouseholdConstants.DBConstants.DOB,
                    PathfinderModelHouseholdConstants.DBConstants.LAST_INTERACTED_WITH,
                    PathfinderModelHouseholdConstants.DBConstants.DATE_REMOVED};
        }
        return null;
    }

}