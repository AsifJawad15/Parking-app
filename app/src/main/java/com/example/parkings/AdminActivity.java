package com.example.parkings;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private final List<Cycle> cycleList = new ArrayList<>();
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarAdmin);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        table = findViewById(R.id.tableCycles);
        Button btnAdd = findViewById(R.id.btnAddCycle);

        initHardcodedCycles();
        populateTable();

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddCycleActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh table in case new cycle was added
        populateTable();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Up arrow
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initHardcodedCycles() {
        // your existing 8 or 13 entries...
        cycleList.clear();
        cycleList.add(new Cycle("1",  "c001", "1234", "OUT"));
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
        // …etc.
        // plus any dynamically added cycles:
        cycleList.addAll(CycleStore.getAddedCycles());
    }

    private void populateTable() {
        table.removeAllViews();
        // Header
        TableRow header = new TableRow(this);
        for (String h : new String[]{"User ID","Cycle ID","PIN","Status"}) {
            TextView tv = new TextView(this);
            tv.setText(h);
            tv.setPadding(16,8,16,8);
            header.addView(tv);
        }
        table.addView(header);

        // Rows
        for (Cycle c : cycleList) {
            TableRow row = new TableRow(this);
            TextView u   = new TextView(this); u.setText(c.userId);
            TextView cid = new TextView(this); cid.setText(c.cycleId);
            TextView p   = new TextView(this); p.setText(c.pin);
            TextView s   = new TextView(this); s.setText(c.status);
            for (TextView tv : new TextView[]{u,cid,p,s}) {
                tv.setPadding(16,8,16,8);
                row.addView(tv);
            }
            table.addView(row);
        }
    }
}
