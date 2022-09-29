package com.example.myplan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myplan.utils.DbHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPlan extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();

    MaterialButton submitBtn;
    EditText nameEdt,descriptionEdt,dateEdt,timeEdt;
    MaterialCheckBox checkBox;
    String myDate, myTime;
    int isChecked = 0;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameEdt = findViewById(R.id.name_edt);
        descriptionEdt = findViewById(R.id.description_edt);
        dateEdt = findViewById(R.id.date_edt);
        timeEdt = findViewById(R.id.time_edt);
        checkBox = findViewById(R.id.checkbox);

        submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(view -> prepareForm());

        dbHandler = new DbHandler(this);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateEditText();
            }
        };

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                String time = cleanNumber(String.valueOf(selectedHour))+ ":"+cleanNumber(String.valueOf(selectedMinute));
                timeEdt.setText(time);

            }
        };

        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddPlan.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        timeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);

                new TimePickerDialog(AddPlan.this,time,hour,minute,false).show();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                isChecked = checked ? 1 : 0;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean validate() {
        //errorMsgTxt.setVisibility(View.GONE);
        boolean valid = true;

        String _name = nameEdt.getText().toString().trim();
        String _description = descriptionEdt.getText().toString().trim();
        String _date = dateEdt.getText().toString().trim();
        String _time = timeEdt.getText().toString().trim();

        if (_name.isEmpty()) {
            nameEdt.setError(Html.fromHtml("<font color='#ffffff'>Please enter name</font>"));
            nameEdt.requestFocus();
            valid = false;
        }else if(_description.isEmpty()) {
            descriptionEdt.setError(Html.fromHtml("<font color='#ffffff'>Please enter description</font>"));
            descriptionEdt.requestFocus();
            valid = false;
        }else if(_date.isEmpty()) {
            dateEdt.setError(Html.fromHtml("<font color='#ffffff'>Please select date</font>"));
            dateEdt.requestFocus();
            valid = false;
        }else if(_time.isEmpty()) {
            dateEdt.setError(null);
            timeEdt.setError(Html.fromHtml("<font color='#ffffff'>Please select time</font>"));
            timeEdt.requestFocus();
            valid = false;
        }

        dateEdt.setError(null);
        timeEdt.setError(null);

        return valid;
    }

    public void prepareForm() {

        if (!validate()) {
            return;
        }


        //View softInputView = this.getCurrentFocus();
        //if (softInputView != null) {
        //    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //    imm.hideSoftInputFromWindow(softInputView.getWindowToken(), 0);
        //}

        //httpCreateProduct();
        //Toast.makeText(getApplicationContext(),"done", Toast.LENGTH_LONG).show();
        savePlan();

    }

    private void updateEditText(){
        String myFormat="dd MMMM yyyy";
        String myFormat2="yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat2, Locale.US);
        dateEdt.setText(dateFormat.format(myCalendar.getTime()));
    }

    public static String cleanNumber(String number)
    {
        number = number.replaceAll("\"", "");
        return Integer.valueOf(number)<=9?"0"+number:number;
    }

    public void savePlan(){

        dbHandler.addPlan(nameEdt.getText().toString(), descriptionEdt.getText().toString(),
                dateEdt.getText().toString(), timeEdt.getText().toString(),String.valueOf(isChecked));
        //Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_LONG).show();
        finish();
    }

}