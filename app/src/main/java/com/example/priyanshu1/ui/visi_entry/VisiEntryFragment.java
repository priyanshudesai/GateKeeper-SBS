package com.example.priyanshu1.ui.visi_entry;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.priyanshu1.R;
import com.example.priyanshu1.apiinterface.Api;
import com.example.priyanshu1.apiinterface.ApiClient;
import com.example.priyanshu1.apiinterface.CommanResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiEntryFragment extends Fragment {

    EditText e,e1,fname,mobno,nov,email,vno,pv;
    boolean v=true;
    Spinner s,s1;
    private VisiEntryViewModel visiEntryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visiEntryViewModel =
                ViewModelProviders.of(this).get(VisiEntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visitor_entry, container, false);
        e=(EditText) root.findViewById(R.id.entertimevisi_entry);
        e1=(EditText) root.findViewById(R.id.enterdatevisi_entry);
        fname=(EditText) root.findViewById(R.id.entery_fname);
        mobno=(EditText) root.findViewById(R.id.entery_mobno);
        nov=(EditText) root.findViewById(R.id.entery_nov);
        email=(EditText) root.findViewById(R.id.entery_email);
        vno=(EditText) root.findViewById(R.id.entery_vno);
        pv=(EditText) root.findViewById(R.id.entery_purvisiting);

        s=(Spinner) root.findViewById(R.id.spinner_blk_no);
        s1=(Spinner) root.findViewById(R.id.spinner_flt_no);
        e1.setText(new SimpleDateFormat("DD-MM-yyyy", Locale.US).format(new Date()));
        e.setText(new SimpleDateFormat("hh:mm a", Locale.US).format(new Date()));
        e.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){




                        final Calendar c = Calendar.getInstance();



                    // Get Current Time

                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

//                                    if (hourOfDay>13){
//
//                                        e.setText((hourOfDay-12) + ":" + minute+" PM");
//                                    }
//                                    else {
//                                        if(hourOfDay==0){
//                                            hourOfDay=12;
//                                        }
//                                        e.setText(hourOfDay + ":" + minute+" AM");
//
//                                    }

                                    boolean isPM = (hourOfDay >= 12);
                                    e.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();





                }
            }
        });
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){



                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);




                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    e1.setText(String.format("%02d-%02d-%04d",dayOfMonth,monthOfYear+1,year));
                                    //e1.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-"+year);

                                }

                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();




                }
            }
        });


        root.findViewById(R.id.save_btn_visi_entry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String s7 = fname.getText().toString();
                String s2 = nov.getText().toString();
                String s6 = vno.getText().toString();
                String s3 = mobno.getText().toString();
                String s4 = email.getText().toString();
                String s5 = pv.getText().toString();
                String s8,s9=e1.getText().toString(),s10=e.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String MobilePattern = "[0-9]{10}";
                v=true;

                if (s7.isEmpty()) {
                    fname.setError("Enter Full Name");
                    v = false;
                }
                if (s2.isEmpty()) {
                    nov.setError("Enter No of Visitors");
                    v = false;
                }
                if (s3.isEmpty() || !s3.matches(MobilePattern)) {

                    mobno.setError("Enter Mobile Number");
                    v = false;
                }
                if(!s4.isEmpty()) {
                    if (!s4.matches(emailPattern)) {
                        email.setError("Enter Email");
                        v = false;
                    }
                }

                if (s6.isEmpty()) {
                    vno.setError("Enter Vehicle no");
                    v = false;
                }

                if(s.getSelectedItemId()==0){
                    Toast.makeText(getContext(), "Select Block no", Toast.LENGTH_SHORT).show();
                    v=false;
                }
                if(s1.getSelectedItemId()==0){
                    Toast.makeText(getContext(), "Select Flat no", Toast.LENGTH_SHORT).show();
                    v=false;
                }
                s8=s.getSelectedItem()+"-"+s1.getSelectedItem();
                if(v==true){



                    Api api= ApiClient.getClient().create(Api.class);
                    Call<CommanResponse> call=api.gatekvisientry("gatekvisientry",s7
                            ,s2,s6,s3,s4,s8,s5,s9,s10);
                    call.enqueue(new Callback<CommanResponse>() {
                        @Override
                        public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                            if (response.body().getSuccess()==200) {

                                Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<CommanResponse> call, Throwable t) {
                            Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                    });





                }
            }
        });


        return root;
    }
}