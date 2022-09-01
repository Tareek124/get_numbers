package com.example.get_numbers;

import java.util.HashMap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.EventChannel.EventSink;
import io.flutter.plugin.common.EventChannel.StreamHandler;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
    private static final String Number_Channel = "getNumbers";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {

        new MethodChannel(flutterEngine.getDartExecutor(),
                Number_Channel).setMethodCallHandler(
                        new MethodCallHandler() {
                            @Override
                            public void onMethodCall(MethodCall call, Result result) {
                                if (call.method.equals("getTwoNumbers")) {
                                    HashMap<String, Integer> arguments = call.arguments();
                                    int number = sumTwoNumbers(arguments.get("num1"), arguments.get("num2"));

                                    if (number < 50) {
                                        result.success(number);
                                    }
                                } else {
                                    result.notImplemented();
                                }
                            }
                        });
    }

    private int sumTwoNumbers(int num1, int num2) {
        return num1 + num2;
    }
}
