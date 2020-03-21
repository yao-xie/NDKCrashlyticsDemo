package com.xieyao.ndkcrashlyticsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        findViewById(R.id.btn_java_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });

        findViewById(R.id.btn_unsatisfied_link_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonExsitedNativeMethod();
            }
        });

        findViewById(R.id.btn_native_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = triggerNdkCrash();
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String nonExsitedNativeMethod();

    public native String triggerNdkCrash();
}
