package com.cheekupeeku.testlayout;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cheekupeeku.testlayout.databinding.RegisterBinding;

public class MainActivity extends AppCompatActivity {
    RegisterBinding binding;
    String dob = "";
    String time = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.tvBirthTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tp = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time = i+":"+i1;
                        binding.tvBirthTime.setText("Birth time : "+time);
                    }
                },12,0,true);
                tp.show();
            }
        });
        binding.tvDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dp = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                       dob = i2+"-"+(i1+1)+"-"+i;
                       binding.tvDOB.setText("DOB : "+dob);
                    }
                },2000,0,1);
                dp.show();
            }
        });
    }
    public void registerUser(View v){
       String username = binding.etUsername.getText().toString();
       if(TextUtils.isEmpty(username)){
           binding.etUsername.setError("please enter username");
           return;
       }
       String password = binding.etPassword.getText().toString();
       String email =binding.etEmail.getText().toString();
       String gender = "";
       if(binding.rdMale.isChecked())
           gender = "Male";
       else if(binding.rdFemale.isChecked())
           gender = "Female";
       String maritalStatus = "";
       if(binding.chkMaritalStatus.isChecked())
           maritalStatus = "Married";
       else
           maritalStatus = "Unmarried";

       String info = "Username : "+username+
               "\nPassword :  "+password+
               "\nEmail : "+email+
               "\nGender : "+gender+
               "\nMarital Status : "+maritalStatus;
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("please wait while loading...");
        pd.show();

    }
}
