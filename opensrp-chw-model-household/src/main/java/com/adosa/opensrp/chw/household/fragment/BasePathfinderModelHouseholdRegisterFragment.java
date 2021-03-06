package com.adosa.opensrp.chw.household.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.activity.BasePathfinderModelHouseholdProfileActivity;
import com.adosa.opensrp.chw.household.contract.BaseModelHouseholdRegisterFragmentContract;
import com.adosa.opensrp.chw.household.model.BaseModelHouseholdRegisterFragmentModel;
import com.adosa.opensrp.chw.household.presenter.BasePathfinderModelHouseholdRegisterFragmentPresenter;
import com.adosa.opensrp.chw.household.provider.BasePathfinderModelHouseholdRegisterProvider;

import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.configurableviews.model.View;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;
import org.smartregister.view.customcontrols.CustomFontTextView;
import org.smartregister.view.customcontrols.FontVariant;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.HashMap;
import java.util.Set;

public class BasePathfinderModelHouseholdRegisterFragment extends BaseRegisterFragment implements BaseModelHouseholdRegisterFragmentContract.View {
    public static final String CLICK_VIEW_NORMAL = "click_view_normal";
    public static final String FOLLOW_UP_VISIT = "follow_up_visit";

    @Override
    public void initializeAdapter(Set<View> visibleColumns) {
        BasePathfinderModelHouseholdRegisterProvider fpRegisterProvider = new BasePathfinderModelHouseholdRegisterProvider(getActivity(), paginationViewHandler, registerActionHandler, visibleColumns);
        clientAdapter = new RecyclerViewPaginatedAdapter(null, fpRegisterProvider, context().commonrepository(this.tablename));
        clientAdapter.setCurrentlimit(20);
        clientsView.setAdapter(clientAdapter);
    }

    @Override
    public void setupViews(android.view.View view) {
        super.setupViews(view);

        // Update top left icon
        qrCodeScanImageView = view.findViewById(org.smartregister.R.id.scanQrCode);
        if (qrCodeScanImageView != null) {
            qrCodeScanImageView.setVisibility(android.view.View.GONE);
        }

        // Update Search bar
        android.view.View searchBarLayout = view.findViewById(org.smartregister.R.id.search_bar_layout);
        searchBarLayout.setBackgroundResource(R.color.customAppThemeBlue);

        if (getSearchView() != null) {
            getSearchView().setBackgroundResource(R.color.white);
            getSearchView().setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_search, 0, 0, 0);
        }

        // Update sort filter
        TextView filterView = view.findViewById(org.smartregister.R.id.filter_text_view);
        if (filterView != null) {
            filterView.setText(getString(R.string.sort));
        }

        // Update title name
        ImageView logo = view.findViewById(org.smartregister.R.id.opensrp_logo_image_view);
        if (logo != null) {
            logo.setVisibility(android.view.View.GONE);
        }

        CustomFontTextView titleView = view.findViewById(R.id.txt_title_label);
        if (titleView != null) {
            titleView.setVisibility(android.view.View.VISIBLE);
            titleView.setText(getString(R.string.model_household));
            titleView.setFontVariant(FontVariant.REGULAR);
        }
    }

    @Override
    public BaseModelHouseholdRegisterFragmentContract.Presenter presenter() {
        return (BaseModelHouseholdRegisterFragmentContract.Presenter) presenter;
    }

    @Override
    protected void initializePresenter() {
        if (getActivity() == null) {
            return;
        }
        presenter = new BasePathfinderModelHouseholdRegisterFragmentPresenter(this, new BaseModelHouseholdRegisterFragmentModel());
    }

    @Override
    public void setUniqueID(String s) {
        if (getSearchView() != null) {
            getSearchView().setText(s);
        }
    }

    @Override
    public void setAdvancedSearchFormData(HashMap<String, String> hashMap) {
//        implement search here
    }

    @Override
    protected String getMainCondition() {
        return presenter().getMainCondition();
    }

    @Override
    protected String getDefaultSortQuery() {
        return presenter().getDefaultSortQuery();
    }

    @Override
    protected void startRegistration() {
//        start forms here if the module requires registration
    }

    @Override
    protected void onViewClicked(android.view.View view) {
        if (getActivity() == null) {
            return;
        }
        if (view.getTag() instanceof CommonPersonObjectClient && view.getTag(R.id.VIEW_ID) == CLICK_VIEW_NORMAL) {
            openProfile((CommonPersonObjectClient) view.getTag());
        }
    }

    protected void openProfile(CommonPersonObjectClient client) {
    }


    @Override
    public void showNotFoundPopup(String s) {
//        implement dialog
    }

}
