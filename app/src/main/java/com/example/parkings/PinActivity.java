package com.example.parkings;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PinActivity extends AppCompatActivity {
    private static final String ADMIN_PIN = "33539";
    private EditText[] digits = new EditText[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        // Set up toolbar with back arrow
        Toolbar tb = findViewById(R.id.toolbarPin);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Grab references to the 5 EditTexts
        digits[0] = findViewById(R.id.pin1);
        digits[1] = findViewById(R.id.pin2);
        digits[2] = findViewById(R.id.pin3);
        digits[3] = findViewById(R.id.pin4);
        digits[4] = findViewById(R.id.pin5);

        // Attach TextWatchers for auto-advance and backspace handling
        for (int i = 0; i < digits.length; i++) {
            final int index = i;
            digits[i].addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && index < digits.length - 1) {
                        // move to next field
                        digits[index + 1].requestFocus();
                    } else if (s.length() == 0 && index > 0) {
                        // if the user pressed backspace to delete, move back
                        digits[index - 1].requestFocus();
                    }
                }
            });
        }

        // OK button logic
        Button btnOk = findViewById(R.id.btnPinOk);
        btnOk.setOnClickListener(v -> {
            StringBuilder entered = new StringBuilder();
            for (EditText e : digits) {
                entered.append(e.getText().toString());
            }
            if (ADMIN_PIN.equals(entered.toString())) {
                startActivity(new Intent(this, AdminActivity.class));
            } else {
                Toast.makeText(this, "Wrong PIN", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle toolbar back arrow
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
