package com.example.priyanshu1.ui.visi_details;

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
import com.example.priyanshu1.visitior_recy.visitior_adapter;
import com.example.priyanshu1.visitior_recy.visitior_data;

import java.util.ArrayList;
import java.util.List;

public class VisiDetailsFragment extends Fragment {

    RecyclerView recyclerView;
    List<visitior_data> li;

    private VisiDetailsViewModel visiDetailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visiDetailsViewModel =
                ViewModelProviders.of(this).get(VisiDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visi_details, container, false);
        recyclerView=(RecyclerView) root.findViewById(R.id.visitior_recycle);
        li=new ArrayList<>();
        visitior_data data[]={new visitior_data("jethava kaushal","7383846827","21/04/2020","1:20pm","2:20pm","B-102")
                ,new visitior_data("mokariya kaushik","7383846827","21/04/2020","1:40pm","2:40pm","B-102")
                ,new visitior_data("desai priyanshu","7383846827","21/04/2020","2:20pm","3:50pm","C-103")
                ,new visitior_data("bhatt danika","7383846827","20/04/2020","1:20pm","2:20pm","B-108")
                ,new visitior_data("patel shakshi","7383846827","20/04/2020","4:20pm","9:20pm","B-109")};
        for(int i=0;i< data.length;i++){
            li.add(data[i]);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        visitior_adapter vi=new visitior_adapter(getContext(),li);
        recyclerView.setAdapter(vi);


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