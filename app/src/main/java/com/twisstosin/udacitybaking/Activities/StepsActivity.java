package com.twisstosin.udacitybaking.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twisstosin.udacitybaking.DataModel.Step;
import com.twisstosin.udacitybaking.Fragments.StepFragment;
import com.twisstosin.udacitybaking.R;
import com.twisstosin.udacitybaking.databinding.ActivityStepViewpagerBinding;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity {
    private static final String BUNDLE_STEP_DATA =
            "com.twisstosin.udacity_baking.step_data";
    private static final String BUNDLE_CURRENT_RECIPE =
            "com.twisstosin.udacity_baking.current_recipe";
    private static final String BUNDLE_CURRENT_STEP =
            "com.twisstosin.udacity_baking.current_step";
    private ViewPager mViewPager;

    private ActivityStepViewpagerBinding binding;

    public static Intent newIntent(Context packageContext, ArrayList<Step> stepList,
                                   int currentStep, String recipeName) {
        Intent intent = new Intent(packageContext, StepsActivity.class);
        intent.putExtra(BUNDLE_STEP_DATA, stepList);
        intent.putExtra(BUNDLE_CURRENT_STEP, currentStep);
        intent.putExtra(BUNDLE_CURRENT_RECIPE, recipeName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_step_viewpager);

        final ArrayList<Step> stepList = getIntent().getExtras().getParcelableArrayList(BUNDLE_STEP_DATA);
        final int currentStep = getIntent().getExtras().getInt(BUNDLE_CURRENT_STEP);
        String currentRecipeName = getIntent().getExtras().getString(BUNDLE_CURRENT_RECIPE);

        setSupportActionBar(binding.tbToolbar.toolbar);
        binding.tbToolbar.toolbar.setTitle(currentRecipeName);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_activity_step_viewpager);
        for(Step step : stepList) {
            tabLayout.addTab(tabLayout.newTab().setText(
                    String.format(getString(R.string.step_number_format), (step.getId() + 1))));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //Fullscreen mode for non-tablet landscape orientation
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.tbToolbar.toolbar.setVisibility(View.GONE);
            binding.tlActivityStepViewpager.setVisibility(View.GONE);
        }

        mViewPager = (ViewPager) findViewById(R.id.vp_activity_step_viewpager);
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
                                  @Override
                                  public Fragment getItem(int position) {
                                      return StepFragment.newInstance(stepList.get(position));
                                  }
                                  @Override
                                  public int getCount() {
                                      return stepList.size();
                                  }
                              });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        mViewPager.setCurrentItem(currentStep);
    }
}
