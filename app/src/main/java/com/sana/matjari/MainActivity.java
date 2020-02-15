package com.sana.matjari;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View tabHomeIconLayout;
    private View tabSearchIconLayout;
    private View tabQRScanIconLayout;
    private View tabCommandeIconLayout;
    private View tabPersonIconLayout;

    private Animation rotate;


    private int[] tabIcons = {
            R.drawable.ic_home_24dp,
            R.drawable.ic_search_24dp,
            R.drawable.ic_attachment_24dp,
            R.drawable.ic_photo_camera_24dp,
            R.drawable.ic_person_24dp,
    };

    private int[] tabIconsFocus = {
            R.drawable.ic_home_focus_24dp,
            R.drawable.ic_search_focus_24dp,
            R.drawable.ic_attachment_focus_24dp,
            R.drawable.ic_photo_camera_focus_24dp,
            R.drawable.ic_person_focus_24dp,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Get the bar title.


        tabHomeIconLayout = getLayoutInflater().inflate(R.layout.custom_icon_home, null);
        tabSearchIconLayout = getLayoutInflater().inflate(R.layout.custom_icon_search, null);
        tabCommandeIconLayout = getLayoutInflater().inflate(R.layout.custom_icon_commande, null);
        tabQRScanIconLayout = getLayoutInflater().inflate(R.layout.custom_icon_qr_scan, null);
        tabPersonIconLayout = getLayoutInflater().inflate(R.layout.custom_icon_person, null);

        rotate= AnimationUtils.loadAnimation(this, R.anim.rotate);
        tabHomeIconLayout.startAnimation(rotate);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorWhiteBackGround));
        tabSelectorController();

        // Set up the first focus tab icon
        setupTabIcons();
        switch(tabLayout.getSelectedTabPosition()) {
            case 0:
                tabHomeIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[0]);
                tabHomeIconLayout.startAnimation(rotate);
                break;
            case 1:
                tabSearchIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[1]);
                tabSearchIconLayout.startAnimation(rotate);

                break;
            case 2:
                tabCommandeIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[2]);
                tabCommandeIconLayout.startAnimation(rotate);
                break;
            case 3:
                tabQRScanIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[3]);
                tabQRScanIconLayout.startAnimation(rotate);
                break;
            case 4:
                tabPersonIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[4]);
                tabPersonIconLayout.startAnimation(rotate);
                break;
            default:
                break;
        }
    }

    /**
     * Tab selector controller (add events handlers to tab title and other buttons)
     * **/

    private void tabSelectorController() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tabLayout.getSelectedTabPosition()) {
                    case 0:
                        tabHomeIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[0]);
                        tabHomeIconLayout.startAnimation(rotate);
                        break;
                    case 1:
                        tabSearchIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[1]);
                        tabSearchIconLayout.startAnimation(rotate);
                        break;
                    case 2:
                        tabCommandeIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[2]);
                        tabCommandeIconLayout.startAnimation(rotate);
                        break;
                    case 3:
                        tabQRScanIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[3]);
                        tabQRScanIconLayout.startAnimation(rotate);
                        break;
                    case 4:
                        tabPersonIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIconsFocus[4]);
                        tabPersonIconLayout.startAnimation(rotate);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch(tabLayout.getSelectedTabPosition()) {
                    case 0:
                        tabHomeIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIcons[0]);
                        tabHomeIconLayout.clearAnimation();
                        break;
                    case 1:
                        tabSearchIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIcons[1]);
                        tabSearchIconLayout.clearAnimation();

                        break;
                    case 2:
                        tabCommandeIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIcons[2]);
                        tabCommandeIconLayout.clearAnimation();
                        break;
                    case 3:
                        tabQRScanIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIcons[3]);
                        tabQRScanIconLayout.clearAnimation();
                        break;
                    case 4:
                        tabPersonIconLayout.findViewById(R.id.icon).setBackgroundResource(tabIcons[4]);
                        tabPersonIconLayout.clearAnimation();

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupTabIcons() {
        //tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        //tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        //tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        tabHomeIconLayout.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_home_24dp);
        tabLayout.getTabAt(0).setCustomView(tabHomeIconLayout);


        tabSearchIconLayout.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_search_24dp);
        tabLayout.getTabAt(1).setCustomView(tabSearchIconLayout);

        tabCommandeIconLayout.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_attachment_24dp);
        tabLayout.getTabAt(2).setCustomView(tabCommandeIconLayout);

        tabQRScanIconLayout.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_photo_camera_24dp);
        tabLayout.getTabAt(3).setCustomView(tabQRScanIconLayout);


        tabPersonIconLayout.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_person_24dp);
        tabLayout.getTabAt(4).setCustomView(tabPersonIconLayout);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeController(), "");
        adapter.addFragment(new SearchController(), "");
        adapter.addFragment(new CommandeController(), "");
        adapter.addFragment(new QRScanerController(), "");
        adapter.addFragment(new ProfilController(), "");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
