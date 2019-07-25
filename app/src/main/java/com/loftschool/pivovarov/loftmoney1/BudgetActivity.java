package com.loftschool.pivovarov.loftmoney1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.loftschool.pivovarov.loftmoney1.BudgetFragment.REQUEST_CODE;

public class BudgetActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private BudgetViewPagerAdapter mViewPagerAdapter;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mViewPagerAdapter = new BudgetViewPagerAdapter(getSupportFragmentManager());

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(R.string.outcome);
        mTabLayout.getTabAt(1).setText(R.string.income);

        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_indicator_color));

        mFloatingActionButton = findViewById(R.id.fab_add_screen);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                for (Fragment fragment : fragmentManager.getFragments()) {
                    if (fragment.getUserVisibleHint()) {
                        fragment.startActivityForResult(new Intent(BudgetActivity.this, AddItemActivity.class), REQUEST_CODE);
                    }
                }

            }
        });
    }

    @Override
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        super.onSupportActionModeStarted(mode);
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
        mTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
        mFloatingActionButton.hide();
    }

    @Override
    public void onActionModeStarted(android.view.ActionMode mode) {
        super.onActionModeStarted(mode);
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
        mTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey_blue));
        mFloatingActionButton.hide();
    }

    @Override
    public void onActionModeFinished(android.view.ActionMode mode) {
        super.onActionModeFinished(mode);
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mFloatingActionButton.show();
    }

    @Override
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        super.onSupportActionModeFinished(mode);
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mFloatingActionButton.show();
    }

    static class BudgetViewPagerAdapter extends FragmentPagerAdapter {


        public BudgetViewPagerAdapter(final FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return BudgetFragment.newInstance(FragmentType.income);
                case 1:
                    return BudgetFragment.newInstance(FragmentType.expense);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
