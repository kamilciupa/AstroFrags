package kamil.ciupa.astrotime;

/**
 * Created by Kamil on 2017-05-13.
 */

public enum  ModelObject {

    SUNNY(R.string.sun , R.layout.fragment_sun),
    MOONNY(R.string.moon , R.layout.fragment_moon);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId){
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId(){
        return mTitleResId;
    }

    public int getLayoutResId(){
        return mLayoutResId;
    }


}
