package com.adosa.opensrp.chw.household.activity.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.adosa.opensrp.chw.household.dao.PathfinderModelHouseholdOngoingActivitiesDao;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdOngoingActivitiesObject;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;

import java.util.List;

public class PageViewModel extends ViewModel {
    private String type;
    private MutableLiveData<String> mIndex = new MutableLiveData<>();
    private LiveData<List<PathfinderModelHouseholdOngoingActivitiesObject>> mText = Transformations.map(mIndex, new Function<String, List<PathfinderModelHouseholdOngoingActivitiesObject>>() {
        @Override
        public List<PathfinderModelHouseholdOngoingActivitiesObject> apply(String baseEntityId) {
            if (type.equalsIgnoreCase(PathfinderModelHouseholdConstants.EvaluationTypes.ALL))
                return PathfinderModelHouseholdOngoingActivitiesDao.getOngoingActivities(baseEntityId);
            else
                return PathfinderModelHouseholdOngoingActivitiesDao.getOngoingActivitiesByType(baseEntityId, type);
        }
    });

    public void setBaseEntityId(String baseEntityId) {
        mIndex.setValue(baseEntityId);
    }

    public void setType(String type) {
        this.type = type;
    }

    public LiveData<List<PathfinderModelHouseholdOngoingActivitiesObject>> getOngoingActivities() {
        return mText;
    }
}