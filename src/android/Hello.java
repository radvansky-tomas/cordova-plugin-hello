package com.example.plugin;

import org.apache.cordova.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import org.json.JSONArray;
import org.json.JSONException;

public class Hello extends CordovaPlugin {

      private CallbackContext batteryCallbackContext = null;

        public void getBatteryLevel() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
            BroadcastReceiver receiver =  new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                    int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                    // Error checking that probably isn't needed but I added just in case.

                    float batLevel = 0;
                    if(level == -1 || scale == -1) {
                        batLevel = 50.0f;
                    }

                    batLevel = ((float)level / (float)scale) * 100.0f;

                    PluginResult result = new PluginResult(PluginResult.Status.OK, batLevel);
                    result.setKeepCallback(false);
                    batteryCallbackContext.sendPluginResult(result);
                }
            };

            webView.getContext().registerReceiver(receiver, intentFilter);
        }

        @Override
        public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

            if (action.equals("greet")) {

                this.batteryCallbackContext = callbackContext;
                getBatteryLevel();

                 PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                            pluginResult.setKeepCallback(true);
                            callbackContext.sendPluginResult(pluginResult);

                return true;

            } else {

                return false;

            }
        }
}
