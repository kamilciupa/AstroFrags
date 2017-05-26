package kamil.ciupa.astrotime;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;


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


    public static FragmentSun newInstance(double longitude, double latitude) {

        FragmentSun fragmentSun = new FragmentSun();
        Bundle args = new Bundle();
        args.putDouble("longitude", longitude);
        args.putDouble("latitude", latitude);
        fragmentSun.setArguments(args);
        return fragmentSun;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sun, container, false);
//        wschodTime = (TextView) view.findViewById(R.id.WschodTimeWart);
        double lg = getArguments().getDouble("longitude");
        double lt = getArguments().getDouble("latitude");
        //
        astroDateTime = new AstroDateTime();
        location = new AstroCalculator.Location(lt, lg);
        calculator = new AstroCalculator(astroDateTime, location);

        initiateElements(view);

        sunriseTime.setText(calculator.getSunInfo().getSunrise().toString());
        sunriseAz.setText(Double.toString(calculator.getSunInfo().getAzimuthRise()));
        sunsetAz.setText(Double.toString(calculator.getSunInfo().getAzimuthSet()));
        sunsetTime.setText(calculator.getSunInfo().getSunset().toString());
        twilightTime.setText(calculator.getSunInfo().getTwilightMorning().toString());
        dawnTime.setText(calculator.getSunInfo().getTwilightEvening().toString());

        //
//        wschodTime.setText(Double.toString(lg + lt));
        /*
        Tutaj rzeczy z xmla
         */
        return view;
    }

    public void initiateElements(View view){
        sunriseTime = (TextView) view.findViewById(R.id.WschodTimeWart);
        sunriseAz = (TextView) view.findViewById(R.id.WschodAzymutWart);
        sunsetTime = (TextView) view.findViewById(R.id.ZachodTimeWart);
        sunsetAz = (TextView) view.findViewById(R.id.ZachodAzymutWart);
        twilightTime = (TextView) view.findViewById(R.id.TwilightTimeWart);
        dawnTime = (TextView) view.findViewById(R.id.DawnTimeWart);
    }


}