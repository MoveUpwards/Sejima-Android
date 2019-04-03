package com.rlab.sejima.features;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rlab.sejima.R;

import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/*
    Created by Antoine RICHE on 2019/04/01.
 */
public class MUHorizontalPager extends ViewPager implements MUPageControl.MUPageControlListener {

    /**
     * The current index of the view pager
     */
    private int mCurrentIndex = 0;
    /**
     * The horizontal margins used for subviews
     */
    private float mHorizontalMargins = 0;
    /**
     * The container view
     */
    private MUHorizontalPager mRootView;
    /**
     * The listener for scroll events
     */
    private MUHorizontalPagerListener mMUHorizontalPagerListener;
    /**
     * The page adapter attached to the view pager
     */
    private MyPagerAdapter mMyPagerAdapter;
    /**
     * The page control attached to the view pager
     */
    private MUPageControl mMUPageControl;

    /**
     * Default constructor
     * @param context the view context
     */
    public MUHorizontalPager(@NonNull Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor with attributes
     * @param context the view context
     * @param attrs the XML attributes for the view
     */
    public MUHorizontalPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setPadding((int) mHorizontalMargins, 0, (int) mHorizontalMargins, 0);
    }

    private void init(Context context){
        mRootView = this;
        mMyPagerAdapter = new MyPagerAdapter(context);
        setAdapter(mMyPagerAdapter);

        mMyPagerAdapter.addView(new MViewObject(R.string.title, R.layout.test_pager));
        mMyPagerAdapter.addView(new MViewObject(R.string.title, R.layout.fragment_mu_avatar));
        mMyPagerAdapter.addView(new MViewObject(R.string.title, R.layout.test_pager));
        mMyPagerAdapter.addView(new MViewObject(R.string.title, R.layout.fragment_mu_avatar));
        mMyPagerAdapter.addView(new MViewObject(R.string.title, R.layout.test_pager));
        mMyPagerAdapter.addView(new MViewObject(R.string.title, R.layout.fragment_mu_avatar));


        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
                if (null !=  mMUHorizontalPagerListener){
                    mMUHorizontalPagerListener.scrolledTo(mRootView, position);
                }

                if(null != mMUPageControl){
                    mMUPageControl.setCurrentPosition(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //TODO
    private void addViews(View[] views, float margins){
        View v = views[0];
//        v.getLa
    }

    /**
     * Get the adapter attached to the ViewPager
     * @return the custom {@link MyPagerAdapter} adapter
     */
    @Override
    public MyPagerAdapter getAdapter() {
        return mMyPagerAdapter;
    }

    /**
     * Get the number of views attached to the ViewPager
     * @return the number of views
     */
    public int getPageCount() {
        return mMyPagerAdapter.getCount();
    }

    /**
     * Get the current horizontal margins
     * @return the horizontal margins in dp
     */
    public float getHorizontalMargins() {
        return mHorizontalMargins;
    }

    /**
     * Update the current horizontal margins
     * @param horizontalMargins the new horizontal margins in dp
     */
    public void setHorizontalMargins(float horizontalMargins) {
        mHorizontalMargins = Math.max(horizontalMargins, 0);
        invalidate();
    }

    /**
     * Get the index of the current page
     * @return the position of the current page
     */
    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    /**
     * Go to the given position without auto-scrolling
     * @param currentIndex the index of the page to display
     */
    public void setCurrentIndex(int currentIndex) {
        this.setCurrentIndex(currentIndex, false);
    }

    /**
     * Go to the given position
     * @param currentIndex the index of the page to display
     * @param autoScroll a boolean value enabling/disabling animation
     */
    public void setCurrentIndex(int currentIndex, boolean autoScroll) {
        if((currentIndex >= 0 && currentIndex < getPageCount())
                && currentIndex != mCurrentIndex) {
            mCurrentIndex = currentIndex;
            setCurrentItem(mCurrentIndex, autoScroll);
        }
    }

    public MUPageControl getMUPageControl() {
        return mMUPageControl;
    }

    public void setMUPageControl(MUPageControl MUPageControl) {
        mMUPageControl = MUPageControl;
        if(mMUPageControl != null){
            mMUPageControl.setPageControlListener(this);
            mMUPageControl.setCurrentPosition(mCurrentIndex);
        }
    }

    /**
     * Get the listener attached to the view pager
     * @return the current listener attached, null if not
     */
    public MUHorizontalPagerListener getMUHorizontalPagerListener() {
        return mMUHorizontalPagerListener;
    }

    /**
     * Register a listener for the scrolling events
     * @param myListener the listener to handle user scrolling
     */
    public void setMUHorizontalPagerListener(MUHorizontalPagerListener myListener) {
        mMUHorizontalPagerListener = myListener;
    }

    @Override
    public void clickOnIndex(MUPageControl muPageControl, int index) {
        this.setCurrentIndex(index, true);
    }


    /**
     * Representation of a page as an object
     */
    public static class MViewObject {

        /**
         * The title resource id of the view
         */
        private int mTitleResId;
        /**
         * The layout resource id
         */
        private int mLayoutResId;

        /**
         * Constructor for pages
         * @param titleResId title resource id of the view
         * @param layoutResId layout resource id
         */
        MViewObject(int titleResId, int layoutResId) {
            mTitleResId = titleResId;
            mLayoutResId = layoutResId;
        }

        /**
         * Get the resource id of the title
         * @return the resource id of the title
         */
        int getTitleResId() {
            return mTitleResId;
        }

        /**
         * Get tje layout id for the page
         * @return the layout resource id
         */
        int getLayoutResId() {
            return mLayoutResId;
        }
    }

    /**
     * A custom adapter extending PagerAdapter
     */
    public static class MyPagerAdapter extends PagerAdapter {

        /**
         * The context used to inflate the pages
         */
        private Context mContext;
        /**
         * The list containing the current pages attached to the adapter
         */
        private LinkedList<MViewObject> mViews;

        /**
         * Constructor of the custom adapter
         * @param context the context used to inflate the pages
         */
        MyPagerAdapter(Context context) {
            mContext = context;
            mViews = new LinkedList<>();
        }

        /**
         * Enable to add a view to the current view pager
         * @param view the view to attach
         */
        void addView(MViewObject view){
            mViews.add(view);
            notifyDataSetChanged();
        }

        /**
         * Get the number of pages
         * @return the page count
         */
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            MViewObject currentView = mViews.get(position);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View layout = inflater.inflate(currentView.getLayoutResId(), collection, false);
            collection.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            MViewObject currentView = mViews.get(position);
            return mContext.getString(currentView.getTitleResId());
        }
    }

    /**
     * Custom interface enabling to handle user scrolling
     */
    public interface MUHorizontalPagerListener {
        /**
         * Called each time the current page has changed
         * @param horizontalPager the {@link MUHorizontalPagerListener} containing the current page
         * @param toIndex the index of the displayed page
         */
        void scrolledTo(MUHorizontalPager horizontalPager, int toIndex);
    }

}
