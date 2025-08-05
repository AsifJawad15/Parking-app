package com.example.parkings;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class AddCycleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cycle);

        MaterialToolbar tb = findViewById(R.id.toolbarAdd);
        setSupportActionBar(tb);
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText inUser = findViewById(R.id.inputUserId);
        EditText inCid  = findViewById(R.id.inputCycleId);
        EditText inPin  = findViewById(R.id.inputPin);
        Button btnOk    = findViewById(R.id.btnConfirmAdd);

        btnOk.setOnClickListener(v -> {
            // create new cycle with default status "In"
            Cycle newC = new Cycle(
                    inUser.getText().toString().trim(),
                    inCid.getText().toString().trim(),
                    inPin.getText().toString().trim(),
                    "In"
            );
            CycleStore.add(newC);

            // launch 2‑second confirm splash
            startActivity(new Intent(this, ConfirmSplashActivity.class));
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
