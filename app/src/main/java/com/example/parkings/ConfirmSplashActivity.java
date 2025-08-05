package com.example.parkings;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmSplashActivity extends AppCompatActivity {
    private static final long DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_splash);

        new Handler().postDelayed(() -> {
            // Return to AdminActivity
            startActivity(new Intent(this, AdminActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }, DELAY);
    }
}
