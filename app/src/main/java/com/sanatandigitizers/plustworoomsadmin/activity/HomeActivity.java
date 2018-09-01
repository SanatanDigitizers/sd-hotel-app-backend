package com.sanatandigitizers.plustworoomsadmin.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sanatandigitizers.plustworoomsadmin.R;
import com.sanatandigitizers.plustworoomsadmin.fragment.HotelFragment;
import com.sanatandigitizers.plustworoomsadmin.fragment.BookingFragment;
import com.sanatandigitizers.plustworoomsadmin.fragment.ProfileFragment;
import com.sanatandigitizers.plustworoomsadmin.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);

        viewPager=(ViewPager) findViewById(R.id.view_pager);

        callViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcons();
    }

    private void callViewPager(ViewPager viewPager){

        MyAdapter adapter= new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(new BookingFragment(),"Booking");
        adapter.addFragment(new HotelFragment(),"Hotel");
        adapter.addFragment(new UserFragment(),"AllUser");
        adapter.addFragment(new ProfileFragment(),"Profile");


        viewPager.setAdapter(adapter);

    }

    public void setLocation(View view) {


    }

    public  class MyAdapter extends FragmentPagerAdapter {

        List<Fragment> topList=new ArrayList<>();
        List<String>titleList=new ArrayList<>();



        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return topList.get(position);
        }

        @Override
        public int getCount() {

            return topList.size();
        }


        //user define function
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }


        public void addFragment(Fragment fragment,String string){

            topList.add(fragment);
            titleList.add(string);

        }
    }

    private void setUpTabIcons(){
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_emaill);
        tabLayout.getTabAt(1).setIcon(R.drawable.desk);
        tabLayout.getTabAt(2).setIcon(R.drawable.users);
        tabLayout.getTabAt(3).setIcon(R.drawable.user);
    }
}
