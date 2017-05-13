package kamil.ciupa.astrotime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class MainActivity extends AppCompatActivity {

    AstroCalculator.Location location;
    double latitude;
    double longitude;
    AlertDialog b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomPageAdapter(this));


        latitude = 10.00;
        longitude = 10.00;
        location = new AstroCalculator.Location(latitude,longitude);


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

        public void calculateSomething() {
            AstroDateTime datetime = new AstroDateTime();

            AstroCalculator calcu = new AstroCalculator(datetime, location);

            calcu.getSunInfo().getAzimuthRise();

        }





   }

