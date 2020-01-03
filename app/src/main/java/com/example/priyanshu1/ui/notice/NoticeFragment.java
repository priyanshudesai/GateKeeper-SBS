package com.example.priyanshu1.ui.notice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.priyanshu1.R;
import com.example.priyanshu1.notice_recycle.notice_adapter;
import com.example.priyanshu1.notice_recycle.notice_data;

import java.util.ArrayList;
import java.util.List;

public class NoticeFragment extends Fragment {

    RecyclerView recyclerView;
    List<notice_data> li;
    private NoticeViewModel noticeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        noticeViewModel =
                ViewModelProviders.of(this).get(NoticeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notice, container, false);


        li=new ArrayList<>();
        recyclerView=(RecyclerView) root.findViewById(R.id.noticerecycle);
        notice_data data2[]={new notice_data("Last Notice for Maintenance Due","Defaulters post Oct 12th will be levied a final of 10%","K.L.Mokariya","14 min.ago,11:20 AM")
                ,new notice_data("Lift Maintenance","Inform that lifts are under maintanance on 10/2/2019 from 3 to 5 pm kindly cooperat.","P.D.Desai","14 min.ago,11:20 AM")
                ,new notice_data("Upcoming Road Maintenance","Walkway before block A will be closed down for annual maintenance.","K.J.Jethva","14 min.ago,11:20 AM")
                ,new notice_data("Upcoming Road Maintenance","Walkway before block A will be closed down for annual maintenance.","K.J.Jethva","14 min.ago,11:20 AM")
                ,new notice_data("Upcoming Road Maintenance","Walkway before block A will be closed down for annual maintenance.","K.J.Jethva","14 min.ago,11:20 AM")};


        for(int i=0;i< data2.length;i++){
            li.add(data2[i]);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notice_adapter ada=new notice_adapter(getContext(),li);
        recyclerView.setAdapter(ada);



        return root;
    }
    private void runLayoutanimation (RecyclerView recyclerView)
    {
        Context context=recyclerView.getContext();
        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anmimation_fall_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}