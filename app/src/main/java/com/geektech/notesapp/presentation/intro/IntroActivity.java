package com.geektech.notesapp.presentation.intro;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.badoualy.stepperindicator.StepperIndicator;
import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.presentation.main.MainActivity;

public class IntroActivity extends AppCompatActivity
    implements View.OnClickListener {

    private static String PREF_FIRST_LAUNCH = "first_launch";
    private static String KEY = "key";

    private IntroPagerAdapter mIntroAdapter;
    private ViewPager mViewPager;
    private StepperIndicator mStepper;

    private TextView mNextBtn;
    private TextView mSkipBtn;

    public static void start (Activity activity) {
        activity.startActivity(new Intent(activity, IntroActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (App.sharedStorage.get(PREF_FIRST_LAUNCH, true)) {
            setContentView(R.layout.activity_intro);

            App.sharedStorage.set(PREF_FIRST_LAUNCH, false);
            init();
        } else {
            MainActivity.start(this);
            finish();
        }

    }

    private void init() {
        initViewPager();

        mNextBtn = findViewById(R.id.intro_next_btn);
        mNextBtn.setOnClickListener(this);
        mSkipBtn = findViewById(R.id.intro_skip_btn);
        mSkipBtn.setOnClickListener(this);

    }

    private void initViewPager() {
        mIntroAdapter = new IntroPagerAdapter(getSupportFragmentManager());
        mStepper = findViewById(R.id.intro_stepper);
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mIntroAdapter);
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                onPageChanged(i);
                onPageSkip(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        mStepper.setViewPager(mViewPager, mIntroAdapter.getCount());
    }


    private void onPageChanged(int position) {
        String btnTextNext = "Next";
        if (position == 2) {
            btnTextNext = "Finish";
        }
        mNextBtn.setText(btnTextNext);

    }

    private void onPageSkip(int position){
        String btnTextFinish = "Skip";
        if(position==2) {
            btnTextFinish = "";
        }
        mSkipBtn.setText(btnTextFinish);
    }

    private void onNextClick() {
        if (mViewPager.getCurrentItem() == mIntroAdapter.getCount() - 1) {
            MainActivity.start(this);
            finish();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intro_next_btn:
                onNextClick();
                break;
            case R.id.intro_skip_btn:
                MainActivity.start(this);
                finish();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(KEY, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_intro, container, false);

            TextView textView = rootView.findViewById(R.id.intro_text_view);
            ImageView imageView = rootView.findViewById(R.id.intro_image_view);
            int page = getArguments().getInt(KEY);
            switch (page) {
                case 0:
                    textView.setText(getString(R.string.first_fragment));
                    imageView.setImageResource(R.drawable.ic_attachment);

                    break;
                case 1:
                    textView.setText(getString(R.string.second_fragment));
                    imageView.setImageResource(R.drawable.ic_attachment);

                    break;
                case 2:
                    textView.setText(getString(R.string.third_fragment));
                    imageView.setImageResource(R.drawable.ic_attachment);

                    break;
            }
            return rootView;
        }
    }

    public class IntroPagerAdapter extends FragmentPagerAdapter {
        private final int PAGES_COUNT = 3;

        public IntroPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {

            return PAGES_COUNT;
        }
    }
}
