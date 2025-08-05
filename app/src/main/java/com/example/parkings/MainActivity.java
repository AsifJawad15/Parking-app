package com.example.parkings;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // In‑memory list of cycles
    private final List<Cycle> cycleList = new ArrayList<>();

    // ZXing’s ActivityResultLauncher for scanning
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher =
            registerForActivityResult(new ScanContract(), result -> {
                if (result.getContents() != null) {
                    String[] parts = result.getContents().split(":");
                    if (parts.length == 2) {
                        handleScan(parts[0], parts[1]);
                    } else {
                        Toast.makeText(this, "QR not in format ID:PIN", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHardcodedCycles();

        // Lottie animation view for scan gesture
        LottieAnimationView scanAnim = findViewById(R.id.scanAnim);
        scanAnim.setOnClickListener(v -> startScanner());

        // Admin button to open PIN screen
        Button adminBtn = findViewById(R.id.btnAdmin);
        adminBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PinActivity.class);
            startActivity(intent);
        });
    }

    /** Launch the ZXing scanner with custom options */
    private void startScanner() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scan Cycle QR");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        barcodeLauncher.launch(options);
    }

    /** Initialize the 13 hardcoded cycles (status defaults to "In") */
    private void initHardcodedCycles() {
        cycleList.add(new Cycle("1",  "c001", "1234", "In"));
        cycleList.add(new Cycle("2",  "c002", "5678", "In"));
        cycleList.add(new Cycle("3",  "c003", "2222", "OUT"));
        cycleList.add(new Cycle("4",  "c004", "1111", "OUT"));
        cycleList.add(new Cycle("5",  "c005", "9999", "In"));
        cycleList.add(new Cycle("6",  "c006", "5432", "OUT"));
        cycleList.add(new Cycle("7",  "c007", "1010", "In"));
        cycleList.add(new Cycle("8",  "c008", "2020", "OUT"));
        cycleList.add(new Cycle("9",  "c009", "3333", "In"));
        cycleList.add(new Cycle("10", "c010", "4444", "OUT"));
        cycleList.add(new Cycle("11", "c011", "5555", "In"));
        cycleList.add(new Cycle("12", "c012", "6666", "In"));
        cycleList.add(new Cycle("13", "c013", "7777", "OUT"));
        cycleList.add(new Cycle("14", "c014", "8888", "In"));
        cycleList.add(new Cycle("15", "c015", "9990", "In"));
        cycleList.add(new Cycle("16", "c016", "0000", "In"));

    }

    /**
     * Called after a successful scan.
     * Toggles the matching cycle's status and shows a Toast.
     */
    private void handleScan(String cycleId, String pin) {
        for (Cycle c : cycleList) {
            if (c.cycleId.equals(cycleId) && c.pin.equals(pin)) {
                c.toggleStatus();
                Toast.makeText(
                        this,
                        "Cycle " + cycleId + " is now " + c.status,
                        Toast.LENGTH_LONG
                ).show();
                return;
            }
        }
        Toast.makeText(this, "No match for ID or PIN", Toast.LENGTH_LONG).show();
    }
}
