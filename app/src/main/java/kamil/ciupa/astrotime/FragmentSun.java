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


//1
public class FragmentSun extends Fragment {
    //2

    TextView wschodTime;



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
        wschodTime = (TextView) view.findViewById(R.id.WschodTimeWart);
        double lg = getArguments().getDouble("longitude");
        double lt = getArguments().getDouble("latitude");
        wschodTime.setText(Double.toString(lg + lt));
        /*
        Tutaj rzeczy z xmla
         */
        return view;
    }
}