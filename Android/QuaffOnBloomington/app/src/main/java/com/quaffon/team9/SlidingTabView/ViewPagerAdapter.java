package com.quaffon.team9.SlidingTabView;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.quaffon.team9.quaffonbloomington.AboutTab;
import com.quaffon.team9.quaffonbloomington.BeerTab;
import com.quaffon.team9.quaffonbloomington.FoodTab;
import com.quaffon.team9.quaffonbloomington.HomeTab;
import com.quaffon.team9.quaffonbloomington.MapsTab;
import com.quaffon.team9.quaffonbloomington.R;


/**
 * Created by Ike on 4/1/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    static private int[] imageResId = {
            R.mipmap.ic_maps_tab,
            R.mipmap.ic_quaff_logo_tab,
            R.mipmap.ic_home_tab,
            R.mipmap.ic_food_tab,
            R.mipmap.ic_beer_tab
    };
    final int PAGE_COUNT = 5;
    private String tabTitles[] = new String[] { "Maps", "About", "Home", "Food", "Beer" };
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return new MapsTab();
        } else if(position == 1) {
            return new AboutTab();
        } else if(position == 2){
            return new HomeTab();
        } else if(position == 3) {
            return new FoodTab();
        } else if(position == 4) {
            return new BeerTab();
        }
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];
        /*
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
        */
        return null;
    }

    static public int getDrawableId(int position){
        return imageResId[position];
    }
}
