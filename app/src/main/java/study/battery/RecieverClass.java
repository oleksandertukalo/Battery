package study.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class RecieverClass extends BroadcastReceiver {
    private static RecieverClass.Listener listener;
    //private Listener listener;
    //private OnIndReceivedListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        int indicator = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        String batlevel = Integer.toString(indicator);
        if (listener != null) {
            listener.Listener(batlevel,isCharging);
        }

    }

    public interface Listener {
        public void Listener(String batlevel,boolean isCharging);
    }
    public void setListener(Listener listener) {
        RecieverClass.listener = listener;
    }
}
