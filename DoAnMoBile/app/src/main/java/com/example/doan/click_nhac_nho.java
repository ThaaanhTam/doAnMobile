package com.example.doan;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class click_nhac_nho extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button timeButton;
    int hour, minute;
    Button btnBlack, btnOK, btnHuy;

    private AlarmManager alamManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_nhac_nho);

        // Gán dateButton sau khi setContentView được gọi
        dateButton = findViewById(R.id.datePickerButton);
        timeButton = findViewById(R.id.selectTime);
        initDatePicker();
        dateButton.setText(getTodaysDate());
        timeButton.setText(getTimeNow());
        btnBlack = findViewById(R.id.btnBlack);
        btnOK = findViewById(R.id.btnOK);
        btnHuy = findViewById(R.id.btnHuy);
        alamManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                String ngay = dateButton.getText().toString();
                String tg = timeButton.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("ngay", ngay);
                bundle.putString("tg", tg);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String ngay = "";
                String tg = "";
                Bundle bundle = new Bundle();
                bundle.putString("ngay", ngay);
                bundle.putString("tg", tg);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                // setResult(MainActivity.RESULT_OK, intent);
                finish();
            }
        });
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private String makeDateString(int day, int month, int year) {
        return day + "/" + month + "/" + year;
    }
    public void openDatePicker(View view) {

        datePickerDialog.show();
    }
    public CharSequence getTimeNow() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        return hour + ":" + minute;
    }
    public void selectTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute) {
                Calendar calendar = Calendar.getInstance();
                hour = selectHour;
                minute = selectMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, minute, hour, true);
        timePickerDialog.setTitle("TIME");
        timePickerDialog.show();


    }
}