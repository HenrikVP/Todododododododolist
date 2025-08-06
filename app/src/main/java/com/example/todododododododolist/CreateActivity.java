package com.example.todododododododolist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class CreateActivity extends AppCompatActivity {

    private TodoItem todoItem;

    private LocalDateTime dateTime;
    private Thread messageThread;
    private boolean threadRunning = false;
    //private Handler mainHandler = new Handler(Looper.getMainLooper());

    Button dateTimeButton;
    EditText title;
    NumberPicker pickPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initGui();
        //runOnUiThread();
    }

    private void initGui() {
        title = findViewById(R.id.edit_title);

        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoItem = new TodoItem();
                todoItem.setName(title.getText().toString());
                todoItem.setDeadline(dateTime);
                todoItem.setPoint(pickPoints.getValue());
                todoItem.setCreated(LocalDateTime.now());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("TodoItem", todoItem);
                startActivity(intent);
            }
        });
        dateTimeButton = findViewById(R.id.btn_setdatetime);

        dateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePopup();

                //TODO SET DATE AND TIME IN BUTTON
            }
        });

        pickPoints = findViewById(R.id.num_points);
        pickPoints.setMinValue(1);
        pickPoints.setMaxValue(10);
        pickPoints.setValue(5);

        pickPoints.setOnValueChangedListener((pickPoints, oldVal, newVal) -> {
            showDelayedMessage(newVal);
            //t;
        });
    }

    private void showDelayedMessage(int newVal) {
        if (threadRunning) messageThread.interrupt();
        messageThread = new Thread(() -> {
            threadRunning = true;
            try {
                Thread.sleep(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CreateActivity.this.getApplicationContext(), "points selected:" + newVal, Toast.LENGTH_LONG).show();
                    }
                });
//                mainHandler.post(() -> {
//                    Toast.makeText(getApplicationContext(), "points selected:" + newVal, Toast.LENGTH_LONG).show();
//                });
                threadRunning = false;
            } catch (
                    InterruptedException e) {
            }
        });
        messageThread.start();
    }

    private void showDateTimePopup() {
        // 1️⃣ Date Picker
        MaterialDatePicker<Long> datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Due Date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            LocalDate selectedDate = Instant.ofEpochMilli(selection)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // After date is picked, show time picker
            showTimePopup(selectedDate);
        });

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    private void showTimePopup(LocalDate date) {
        // 2️⃣ Time Picker
        MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Time")
                .build();

        timePicker.addOnPositiveButtonClickListener(view -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            // Combine into LocalDateTime
            dateTime = date.atTime(hour, minute);
            String localFormat = dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));

            dateTimeButton.setText(localFormat);
            Toast.makeText(this, "Due: " + dateTime, Toast.LENGTH_LONG).show();
        });
        timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
    }
}