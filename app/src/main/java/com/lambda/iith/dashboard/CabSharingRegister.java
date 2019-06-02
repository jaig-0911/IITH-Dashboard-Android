package com.lambda.iith.dashboard;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CabSharingRegister extends AppCompatActivity {
    private Button date , from, to;
    private  int mDay , mMonth , mYear, mHour , mMinute;
    private Button Book ;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_sharing_register2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        date = (Button) findViewById(R.id.Reg_Date);
        from = (Button) findViewById(R.id.csr_from);
        to = (Button) findViewById(R.id.to);

        Book = (Button) findViewById(R.id.cs_book);

        Calendar calendar = Calendar.getInstance();
        mDay = calendar.DAY_OF_WEEK;


        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(calendar.getTime());
        date.setText(formattedDate);
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
        String formattedDate2 = df2.format(calendar.getTime());
        from.setText(formattedDate2);
        SimpleDateFormat df3 = new SimpleDateFormat("HH:mm");
        calendar.add(Calendar.HOUR_OF_DAY , 1);
        String formattedDate3 = df3.format(calendar.getTime());
        to.setText(formattedDate3);



        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction().replace(R.id.fragmentlayout, new CabSharing()).commit();

            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerObj(from , 0);




            }
        });




        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Date = from.getText().toString();
                int hour = Character.getNumericValue(Date.charAt(0))*10 +  Character.getNumericValue(Date.charAt(1));

                int minute = Character.getNumericValue(Date.charAt(3))*10 + Character.getNumericValue(Date.charAt(4));
                to.setText(String.format("%02d:%02d", hour+1, minute));
                TimePickerObj(to, 1);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerObj(date);
            }
        });


    }

    public void TimePickerObj(final TextView textView , final int k){
        final Calendar mcurrentTime = Calendar.getInstance();
        String Date = textView.getText().toString();
        int hour = Character.getNumericValue(Date.charAt(0))*10 +  Character.getNumericValue(Date.charAt(1));

        int minute = Character.getNumericValue(Date.charAt(3))*10 + Character.getNumericValue(Date.charAt(4));


        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                textView.setText(String.format("%02d:%02d", hourOfDay+k, minutes));
            }
        }, hour, minute, false);

        timePickerDialog.show();

    }

    public void DatePickerObj(final Button textView){
        final Calendar mcurrentTime = Calendar.getInstance();
        //int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);
        //final int month = mcurrentTime.get(Calendar.MONTH);
        //int year = mcurrentTime.get(Calendar.YEAR);

        String Date = textView.getText().toString();
        int day = Character.getNumericValue(Date.charAt(0))*10 +  Character.getNumericValue(Date.charAt(1));

        int year = Character.getNumericValue(Date.charAt(6))*1000 + Character.getNumericValue(Date.charAt(7))*100+ Character.getNumericValue(Date.charAt(8))*10 + Character.getNumericValue(Date.charAt(9));
        int month = Character.getNumericValue(Date.charAt(3))*10 + Character.getNumericValue(Date.charAt(4));



        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                textView.setText(String.format("%02d-%02d-%02d", dayOfMonth, month , year));
            }


        }, year, month, day);

        datePickerDialog.show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
