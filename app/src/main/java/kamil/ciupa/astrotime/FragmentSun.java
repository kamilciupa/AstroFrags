package kamil.ciupa.astrotime;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


//1
public class FragmentSun extends Fragment {
    //2

    TextView wschodTime;
    AstroDateTime astroDateTime;
    AstroCalculator.Location location;
    AstroCalculator calculator;
    TextView sunriseTime;
    TextView sunriseAz;
    TextView sunsetTime ;
    TextView sunsetAz ;
    TextView twilightTime;
    TextView dawnTime ;
    View view;
    TextView clock;

//    public static FragmentSun newInstance(double longitude, double latitude) {
//
//        FragmentSun fragmentSun = new FragmentSun();
//        Bundle args = new Bundle();
//        args.putDouble("longitude", longitude);
//        args.putDouble("latitude", latitude);
//        fragmentSun.setArguments(args);
//        return fragmentSun;
//    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }


//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_sun, container, false);
        setSunData(10.0, 10.0, view );

        clock = (TextView) view.findViewById(R.id.tTimeSun);
        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                Calendar calendar = Calendar.getInstance();
                clock.setText(String.format("%02d:%02d:%02d", calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));
            }
            public void onFinish() {
            }
        };
        newtimer.start();

        try {
            Timer autoUpdate = new Timer();
            autoUpdate.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                setSunData(((MainActivity) getActivity()).getLatitude(), ((MainActivity) getActivity()).getLongitude(), view);
                            }
                        });
                    } catch(Exception e) {}
                }

            }, 0, 60000*((MainActivity) getActivity()).getRefTime());
        } catch(Exception e) {}
        return view;
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_main, menu);
//        super.onCreateOptionsMenu(menu,inflater);
//    }

    public void initiateElements(View view){
        sunriseTime = (TextView) view.findViewById(R.id.tWschodSCzasWartosc);
        sunriseAz = (TextView) view.findViewById(R.id.tWschodSAzymWart);
        sunsetTime = (TextView) view.findViewById(R.id.tZachodSCzasWartosc);
        sunsetAz = (TextView) view.findViewById(R.id.tZachodSAzymutWarto);
        twilightTime = (TextView) view.findViewById(R.id.tZmierzchWart);
        dawnTime = (TextView) view.findViewById(R.id.tSwitWart);
    }


    public void setSunData(double latitude, double longitude, View view){

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        int timezoneOffset = c.get(Calendar.ZONE_OFFSET);

        AstroCalculator calculator = new AstroCalculator(new AstroDateTime(year, month, day + 1, hour, minute, second, timezoneOffset, true),
                new AstroCalculator.Location(latitude, longitude));

        initiateElements(view);

        sunsetAz.setText(Double.toString(Math.floor(calculator.getSunInfo().getAzimuthSet()) ));
        sunsetTime.setText(calculator.getSunInfo().getSunset().toString().substring(10,16));
        sunriseTime.setText(calculator.getSunInfo().getSunrise().toString().substring(10,16));
        sunriseAz.setText(Double.toString(Math.floor(calculator.getSunInfo().getAzimuthRise())));
        twilightTime.setText(calculator.getSunInfo().getTwilightMorning().toString().substring(10,16));
        dawnTime.setText(calculator.getSunInfo().getTwilightEvening().toString().substring(10,16));

    }

}