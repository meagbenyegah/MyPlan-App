package com.example.myplan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPlan extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();

    MaterialButton submitBtn;
    EditText nameEdt,descriptionEdt,dateEdt,timeEdt;
    CheckBox checkBox;

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

        submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(view -> prepareForm());

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateEditText();
            }
        };

        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddPlan.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
        }

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
        Toast.makeText(getApplicationContext(),"done", Toast.LENGTH_LONG).show();

    }

    private void updateEditText(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dateEdt.setText(dateFormat.format(myCalendar.getTime()));
    }

}