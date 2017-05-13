package kamil.ciupa.astrotime;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;





//1
public class FragmentMoon extends Fragment {
    //2
    public static FragmentMoon newInstance() {
        return new FragmentMoon();
    }

    //3
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moon, container, false);
    }
}