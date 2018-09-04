package hvcnbcvt_uddd.study_english.Ui.Activity.Vocabulary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import hvcnbcvt_uddd.study_english.Data.Local.WordController;
import hvcnbcvt_uddd.study_english.Data.Model.Word;
import hvcnbcvt_uddd.study_english.R;
import hvcnbcvt_uddd.study_english.Service.CounterService;
import hvcnbcvt_uddd.study_english.Ui.Fragment.Vocabulary.SlidePageFragment;

public class SlideActivity extends FragmentActivity {

    private static final int NUM_PAGES = 15;//số slide
    private static final String TITLE_DIALOG = "Kết quả";
    private static final String TAG = "bug";

    private ViewPager mPager;// trang slide h
    private PagerAdapter mPagerAdapter;
    public  int checkAnswer = 0;
    private int mNumAns;
    private long mTimeCounted = 0;

    TextView tv_submit, tv_timer, tv_score, tv_title_time;
    Button btn_previous, btn_continue;


    //CSDL
    WordController wordController;
    ArrayList<Word> array_word;
    int num_exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_activity_detail);

        initView();
        initEvent();
    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.pager);
        tv_submit = (TextView) findViewById(R.id.tv_submit_vocabulary);
        tv_timer = (TextView) findViewById(R.id.tv_timer);

        //set slide adapter
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        // gửi mã đề cho slidefragment
        Intent intent = getIntent();
        num_exam = intent.getIntExtra("num_exam",0);
        //Đọc đề đó ra từ csdl
        wordController = new WordController(this);
        array_word = new ArrayList<Word>();
        array_word = wordController.getWord(num_exam);
    }

    private void initEvent() {
        //set thời gian chạy
        tv_timer.setText(getStringDatefromlong(mTimeCounted));
        final Intent intent = new Intent(this,CounterService.class);
        EventBus.getDefault().postSticky(mTimeCounted);//gửi counter cho service
        startService(intent);// start servie
//
//        if((array_word.get(mPager.getCurrentItem()).getmResult().equals(array_word.get(mPager.getCurrentItem()).getWordVietnamese()) == true)
//                && (mPager.getCurrentItem()<array_word.size()-1)){
//            mPager.setCurrentItem(mPager.getCurrentItem()+1);
//        }

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
                checkAnswer = 1;
                if(mPager.getCurrentItem() >= 5) {
                    mPager.setCurrentItem(0);
                } else{
                    mPager.setCurrentItem(mPager.getCurrentItem()+4);
                }
                mNumAns = 0;
                checkAnswer();
            }
        });
    }

    public ArrayList<Word> getData() {
        return array_word;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void checkAnswer(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_check_answer);
        dialog.setTitle(TITLE_DIALOG);
        dialog.show();

        btn_previous = (Button) dialog.findViewById(R.id.btn_previous);
        btn_continue = (Button) dialog.findViewById(R.id.btn_continue);
        tv_score = (TextView) dialog.findViewById(R.id.tv_score);
        tv_title_time = (TextView) dialog.findViewById(R.id.tv_title_time);


        tv_title_time.setText(getStringDatefromlong(mTimeCounted));

        checkResult();
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SlideActivity.this,VocabularyActivity.class);
                startActivity(intent);
            }
        });
    }

    //Check kq
    public void checkResult(){
        for (int i = 0; i<array_word.size(); i++) {
            if(array_word.get(i).getmResult().equals(array_word.get(i).getWordVietnamese()) == true){
                mNumAns++;
            }
        }
        tv_score.setText(mNumAns+"/"+array_word.size());
    }

    // Đếm thời gian
    private String getStringDatefromlong(long l) {
        Date date = new Date(l * 1000);//1000 = 1 giay
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.getDefault());
        return sdf.format(date);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    //nhận counter đếm từ service
    public void onEvent(Long l) {
        mTimeCounted = l;
        tv_timer.setText(getStringDatefromlong(mTimeCounted));
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);//hủy đăng ký Eventbus
        super.onDestroy();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SlidePageFragment.create(position,checkAnswer);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
