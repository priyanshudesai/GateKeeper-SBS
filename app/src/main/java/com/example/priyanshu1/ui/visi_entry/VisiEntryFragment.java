package com.example.priyanshu1.ui.visi_entry;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.priyanshu1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class VisiEntryFragment extends Fragment {

    EditText e,e1;
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

                v=true;
                if(s.getSelectedItemId()==0){
                    Toast.makeText(getContext(), "Select Block no", Toast.LENGTH_SHORT).show();
                    v=false;
                }
                else if(s1.getSelectedItemId()==0){
                    Toast.makeText(getContext(), "Select Flat no", Toast.LENGTH_SHORT).show();
                    v=false;
                }
                else if(v==true){
                    Toast.makeText(getContext(), "Visitor Entered", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return root;
    }
}