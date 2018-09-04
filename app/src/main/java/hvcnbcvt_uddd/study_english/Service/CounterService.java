package hvcnbcvt_uddd.study_english.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import de.greenrobot.event.EventBus;


public class CounterService extends Service {
    private static final String TAG = "CounterService";
    private long counter;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timerHandler.postDelayed(this, 1000);
            counter += 1;
            EventBus.getDefault().postSticky(counter);
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().registerSticky(this);
    }

    public void onEvent(Long l) {
        counter = l;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timerHandler.postDelayed(timerRunnable, 0);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        timerHandler.removeCallbacks(timerRunnable);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
