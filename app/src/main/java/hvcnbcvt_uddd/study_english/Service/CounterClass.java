package hvcnbcvt_uddd.study_english.Service;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class CounterClass extends CountDownTimer {

    TextView tv_timer;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CounterClass(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
        tv_timer.setText(countTime); //SetText cho textview hiện thị thời gian.
    }

    @Override
    public void onFinish() {
        tv_timer.setText("00:00");  //SetText cho textview hiện thị thời gian.
    }
}
