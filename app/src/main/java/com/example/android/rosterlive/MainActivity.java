package com.example.android.rosterlive;

import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.android.rosterlive.fragment.HomeFragment;
import com.example.android.rosterlive.fragment.MingguanFragment;
import com.example.android.rosterlive.fragment.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_container)
    FrameLayout mainContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    // configure icons
    private int[] imageResId = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_date_range_black_24dp,
            R.drawable.ic_person_black_24dp
    };

    @BindView(R.id.bottom_tab_layout)
    TabLayout bottomTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();

        initTab();

        //handling tab click event
        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new HomeFragment());
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new MingguanFragment());
                } else if (tab.getPosition() == 2) {
                    replaceFragment(new ProfileFragment());
                }

                tab.getIcon().setColorFilter(
                        ResourcesCompat.getColor(getResources(), R.color.colorAccent, null),
                        PorterDuff.Mode.SRC_IN
                );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(
                        ResourcesCompat.getColor(getResources(), R.color.colorIconTab, null),
                        PorterDuff.Mode.SRC_IN
                );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initTab() {
        if (bottomTabLayout != null) {
            for (int i = 0; i < imageResId.length; i++) {
                bottomTabLayout.addTab(bottomTabLayout.newTab());
                bottomTabLayout.getTabAt(i).setIcon(imageResId[i]);
                bottomTabLayout.getTabAt(i).getIcon().setColorFilter(
                            ResourcesCompat.getColor(getResources(), R.color.colorIconTab, null),
                            PorterDuff.Mode.SRC_IN
                );
            }

            TabLayout.Tab tab = bottomTabLayout.getTabAt(0);
            tab.select();
            tab.getIcon().setColorFilter(
                    ResourcesCompat.getColor(getResources(), R.color.colorAccent, null),
                    PorterDuff.Mode.SRC_IN
            );

            //replace default fragment
            replaceFragment(new HomeFragment());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment);

        transaction.commit();
    }

}
