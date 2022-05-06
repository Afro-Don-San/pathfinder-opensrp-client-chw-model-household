package com.adosa.opensrp.chw.household.activity;

import static com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants.ActivityPayload.BASE_ENTITY_ID;
import static com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants.ActivityPayload.EVALUATION_TYPE;
import static com.adosa.opensrp.chw.household.util.PathfinderModelHouseholdConstants.ActivityPayload.TITLE_TEXT;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.adosa.opensrp.chw.household.R;
import com.adosa.opensrp.chw.household.activity.ui.main.SectionsPagerAdapter;
import com.adosa.opensrp.chw.household.databinding.ActivityModelHouseholdEvaluationBinding;
import com.google.android.material.tabs.TabLayout;

public class BaseModelHouseholdEvaluationActivity extends AppCompatActivity {

    private ActivityModelHouseholdEvaluationBinding binding;
    protected String evaluationType;
    protected String baseEntityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityModelHouseholdEvaluationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        evaluationType = getIntent().getStringExtra(EVALUATION_TYPE);
        baseEntityId = getIntent().getStringExtra(BASE_ENTITY_ID);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), baseEntityId, evaluationType);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String titleString = getIntent().getStringExtra(TITLE_TEXT);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (titleString != null)
                actionBar.setTitle(titleString);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}