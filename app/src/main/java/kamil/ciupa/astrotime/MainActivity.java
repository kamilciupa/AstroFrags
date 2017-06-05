package kamil.ciupa.astrotime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    
    double latitude = 10.0;
    double longitude = 10.0;
    AlertDialog b;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    List<String> fragmentsList;

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        fragmentsList = new ArrayList<>();
        fragmentsList.add(FragmentSun.class.getName());
        fragmentsList.add(FragmentMoon.class.getName());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new MainActivity.MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showOptionsDialog();
        }
        return super.onOptionsItemSelected(item);
    }


    /*
    Shows dialog with options. Allow to change longitude, latitude and frequency of refresh data.
     */
    private void showOptionsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.options_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");

        b = dialogBuilder.create();

        Button a = (Button) dialogView.findViewById(R.id.bOK);
        final EditText lt = (EditText) dialogView.findViewById(R.id.LatitudeET);
        final EditText lg = (EditText) dialogView.findViewById(R.id.LongitudeET);
        lt.setText(Double.toString(latitude));
        lg.setText(Double.toString(longitude));

        a.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                latitude = Double.parseDouble(lt.getText().toString());
                longitude = Double.parseDouble(lg.getText().toString());

                b.dismiss();
            }
        });
        b.show();
    }



    private class MyPagerAdapter extends FragmentPagerAdapter {

        public List<String> fragmentsListAdapter = new ArrayList<>();
        private int NUM_COUNT = 2;
        double longitude, latitude;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragmentsListAdapter = fragmentsList;
        }

        @Override
        public int getCount() {
            return NUM_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(getBaseContext(), fragmentsListAdapter.get(position));
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
//        }
//    }
    }
}



