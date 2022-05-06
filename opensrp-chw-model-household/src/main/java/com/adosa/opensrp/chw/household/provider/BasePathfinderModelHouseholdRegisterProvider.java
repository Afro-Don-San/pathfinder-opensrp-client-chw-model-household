package com.adosa.opensrp.chw.household.provider;

import static org.smartregister.util.Utils.getName;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.dao.PathfinderModelHouseholdDao;
import com.adosa.opensrp.chw.household.domain.PathfinderModelHouseholdMemberObject;
import com.adosa.opensrp.chw.household.fragment.BasePathfinderModelHouseholdRegisterFragment;
import com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.cursoradapter.RecyclerViewProvider;
import org.smartregister.util.Utils;
import org.smartregister.view.contract.SmartRegisterClient;
import org.smartregister.view.contract.SmartRegisterClients;
import org.smartregister.view.dialog.FilterOption;
import org.smartregister.view.dialog.ServiceModeOption;
import org.smartregister.view.dialog.SortOption;
import org.smartregister.view.viewholder.OnClickFormLauncher;

import java.text.MessageFormat;
import java.util.Set;

import timber.log.Timber;

public class BasePathfinderModelHouseholdRegisterProvider implements RecyclerViewProvider<BasePathfinderModelHouseholdRegisterProvider.RegisterViewHolder> {

    private final LayoutInflater inflater;
    protected View.OnClickListener onClickListener;
    private final View.OnClickListener paginationClickListener;
    private final Context context;
    private final Set<org.smartregister.configurableviews.model.View> visibleColumns;

    public BasePathfinderModelHouseholdRegisterProvider(Context context, View.OnClickListener paginationClickListener, View.OnClickListener onClickListener, Set visibleColumns) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.paginationClickListener = paginationClickListener;
        this.onClickListener = onClickListener;
        this.visibleColumns = visibleColumns;
        this.context = context;
    }

    @Override
    public void getView(Cursor cursor, SmartRegisterClient smartRegisterClient, RegisterViewHolder registerViewHolder) {
        CommonPersonObjectClient pc = (CommonPersonObjectClient) smartRegisterClient;
        PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject = PathfinderModelHouseholdDao.getMember(pc.getCaseId());

        if (visibleColumns.isEmpty()) {
            populatePatientColumn(pc, pathfinderModelHouseholdMemberObject, registerViewHolder);
        }
    }

    private void populatePatientColumn(CommonPersonObjectClient pc, PathfinderModelHouseholdMemberObject pathfinderModelHouseholdMemberObject, final RegisterViewHolder viewHolder) {
        try {
            String firstName = getName(
                    pathfinderModelHouseholdMemberObject.getFirstName(),
                    pathfinderModelHouseholdMemberObject.getMiddleName());

            String dobString = Utils.getValue(pc.getColumnmaps(), PathfinderModelHouseholdConstants.DBConstants.DOB, false);
            int age = new Period(new DateTime(dobString), new DateTime()).getYears();

            String patientName = getName(firstName, pathfinderModelHouseholdMemberObject.getLastName());

            viewHolder.patientName.setText(patientName + ", " + age);

            viewHolder.textViewVillage.setText(pathfinderModelHouseholdMemberObject.getAddress());
            viewHolder.textViewHouseholdType.setText(pathfinderModelHouseholdMemberObject.getHouseholdType());
            viewHolder.patientColumn.setOnClickListener(onClickListener);
            viewHolder.patientColumn.setTag(pc);
            viewHolder.patientColumn.setTag(R.id.VIEW_ID, BasePathfinderModelHouseholdRegisterFragment.CLICK_VIEW_NORMAL);


            viewHolder.rating.setTag(pc);
            viewHolder.rating.setTag(R.id.VIEW_ID, BasePathfinderModelHouseholdRegisterFragment.FOLLOW_UP_VISIT);
            viewHolder.rating.setRating(totalScore(pathfinderModelHouseholdMemberObject.getBaseEntityId()));
            viewHolder.registerColumns.setOnClickListener(onClickListener);

            viewHolder.registerColumns.setOnClickListener(v -> viewHolder.patientColumn.performClick());

        } catch (Exception e) {
            Timber.e(e);
        }
    }


    @Override
    public void getFooterView(RecyclerView.ViewHolder viewHolder, int currentPageCount, int totalPageCount, boolean hasNext, boolean hasPrevious) {
        FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;
        footerViewHolder.pageInfoView.setText(MessageFormat.format(context.getString(org.smartregister.R.string.str_page_info), currentPageCount, totalPageCount));

        footerViewHolder.nextPageView.setVisibility(hasNext ? View.VISIBLE : View.INVISIBLE);
        footerViewHolder.previousPageView.setVisibility(hasPrevious ? View.VISIBLE : View.INVISIBLE);

        footerViewHolder.nextPageView.setOnClickListener(paginationClickListener);
        footerViewHolder.previousPageView.setOnClickListener(paginationClickListener);
    }

    @Override
    public SmartRegisterClients updateClients(FilterOption filterOption, ServiceModeOption serviceModeOption, FilterOption filterOption1, SortOption sortOption) {
        return null;
    }

    @Override
    public void onServiceModeSelected(ServiceModeOption serviceModeOption) {
//        implement
    }

    @Override
    public OnClickFormLauncher newFormLauncher(String s, String s1, String s2) {
        return null;
    }

    @Override
    public LayoutInflater inflater() {
        return inflater;
    }

    @Override
    public RegisterViewHolder createViewHolder(ViewGroup parent) {
        View view = inflater.inflate(R.layout.model_household_register_list_row, parent, false);
        return new RegisterViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder createFooterHolder(ViewGroup parent) {
        View view = inflater.inflate(R.layout.smart_register_pagination, parent, false);
        return new FooterViewHolder(view);
    }

    @Override
    public boolean isFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        return viewHolder instanceof FooterViewHolder;
    }

    public class RegisterViewHolder extends RecyclerView.ViewHolder {
        public TextView patientName;
        public TextView parentName;
        public TextView textViewVillage;
        public TextView textViewHouseholdType;
        public RatingBar rating;
        public View patientColumn;

        public View registerColumns;
        public View dueWrapper;

        public RegisterViewHolder(View itemView) {
            super(itemView);

            parentName = itemView.findViewById(R.id.patient_parent_name);
            patientName = itemView.findViewById(R.id.patient_name_age);
            textViewVillage = itemView.findViewById(R.id.text_view_village);
            textViewHouseholdType = itemView.findViewById(R.id.text_view_household_type);
            rating = itemView.findViewById(R.id.rating);
            patientColumn = itemView.findViewById(R.id.patient_column);
            registerColumns = itemView.findViewById(R.id.register_columns);
            dueWrapper = itemView.findViewById(R.id.due_button_wrapper);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public TextView pageInfoView;
        public Button nextPageView;
        public Button previousPageView;

        public FooterViewHolder(View view) {
            super(view);

            nextPageView = view.findViewById(org.smartregister.R.id.btn_next_page);
            previousPageView = view.findViewById(org.smartregister.R.id.btn_previous_page);
            pageInfoView = view.findViewById(org.smartregister.R.id.txt_page_info);
        }
    }

    private float totalScore(String baseEntityId) {
        float healthScore =
                PathfinderModelHouseholdDao.getScore(baseEntityId, "toilet_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "bathroom_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "kibuyu_chirizi_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "household_cleanliness_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "dishes_drying_container_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "llin_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "use_of_health_facility_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "clean_drinking_water_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "fp_education_evaluation_score");

        float socialIntegrationScore =
                PathfinderModelHouseholdDao.getScore(baseEntityId, "economic_activities_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "village_activities_participation_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "joint_decision_making_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "children_school_attendance_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "stove_evaluation_score");

        float landScore =
                PathfinderModelHouseholdDao.getScore(baseEntityId, "natural_resources_evaluation_score") +
                        PathfinderModelHouseholdDao.getScore(baseEntityId, "trees_evaluation_score");

        float farmingScore = PathfinderModelHouseholdDao.getScore(baseEntityId, "farming_evaluation_score");

        float livestockScore = PathfinderModelHouseholdDao.getScore(baseEntityId, "livestock_evaluation_score");

        return (healthScore + socialIntegrationScore + landScore + farmingScore + livestockScore)/20;
    }
}
