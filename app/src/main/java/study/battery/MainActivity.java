package study.battery;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvBatteryCapacity;
    private CheckBox cbActivate;
    private Manualinput fragment;
    private BroadcastReceiver reciever;
    boolean isRegister;
    private FrameLayout fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new Manualinput();
        if (reciever == null) {
            reciever = new Reciever();
        }
        tvBatteryCapacity = findViewById(R.id.tvBatteryCapacity);

        cbActivate = findViewById(R.id.cbActivate);
        cbActivate.setOnClickListener(this);
        fl = findViewById(R.id.FrameView);
        fl.addView(new Battery(getApplicationContext()));

    }

    @Override
    protected void onResume() {
        super.onResume();
            registerReceiver(reciever, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            isRegister = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isRegister) {
            unregisterReceiver(reciever);
            isRegister = false;
        }
    }



    @Override
    public void onClick(View view) {
        if (cbActivate.isChecked()) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
                registerReceiver(reciever, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        } else {
            getFragmentManager().beginTransaction().add(R.id.frameLayout, fragment, null).commit();
            if (isRegister) {
                unregisterReceiver(reciever);
                isRegister = false;
            }
        }
    }

    public void click(View v){
        EditText editText = fragment.getView().findViewById(R.id.etValueBat);
        CheckBox cbCharging = fragment.getView().findViewById(R.id.cbCharging);
        String value = editText.getText().toString();
   //     tvBatteryCapacity.setText(value + "%");
        if (cbCharging.isChecked()){
            tvBatteryCapacity.setText("Charging " + value + "%");
        }else {
            tvBatteryCapacity.setText(value + "%");
        }
    }

       private class Reciever extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                int indicator = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
                if (isCharging) {
                    tvBatteryCapacity.setText("Charging - " + Integer.toString(indicator) + "%");
                } else {
                    tvBatteryCapacity.setText(Integer.toString(indicator) + "%");
                }
            }
        }
    }
