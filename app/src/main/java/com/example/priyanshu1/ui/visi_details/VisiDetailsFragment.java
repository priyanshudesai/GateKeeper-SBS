package com.example.priyanshu1.ui.visi_details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.priyanshu1.R;
import com.example.priyanshu1.apiinterface.Api;
import com.example.priyanshu1.apiinterface.ApiClient;
import com.example.priyanshu1.apiinterface.responce.visidetail_responce;
import com.example.priyanshu1.apiinterface.responce_get_set.visi_de;
import com.example.priyanshu1.visitior_recy.visitior_adapter;
import com.example.priyanshu1.visitior_recy.visitior_data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiDetailsFragment extends Fragment {

    RecyclerView recyclerView;
    List<visi_de> li;
    TextView t;
    SwipeRefreshLayout swipe;
    visitior_adapter vi;
    private VisiDetailsViewModel visiDetailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visiDetailsViewModel =
                ViewModelProviders.of(this).get(VisiDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visi_details, container, false);

        recyclerView=(RecyclerView) root.findViewById(R.id.visitior_recycle);
        swipe=(SwipeRefreshLayout) root.findViewById(R.id.visiswipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               visiload();
                swipe.setRefreshing(false);
            }
        });

//        li=new ArrayList<>();
//        visitior_data data[]={new visitior_data("jethava kaushal","7383846827","21/04/2020","1:20pm","2:20pm","B-102")
//                ,new visitior_data("mokariya kaushik","7383846827","21/04/2020","1:40pm","2:40pm","B-102")
//                ,new visitior_data("desai priyanshu","7383846827","21/04/2020","2:20pm","3:50pm","C-103")
//                ,new visitior_data("bhatt danika","7383846827","20/04/2020","1:20pm","2:20pm","B-108")
//                ,new visitior_data("patel shakshi","7383846827","20/04/2020","4:20pm","9:20pm","B-109")};
//        for(int i=0;i< data.length;i++){
//            li.add(data[i]);
//        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       visiload();

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
    public void  visiload()
    {
        Api api= ApiClient.getClient().create(Api.class);
        Call<visidetail_responce> call= api.visidetail("gatekvisidetail");
        call.enqueue(new Callback<visidetail_responce>() {
            @Override
            public void onResponse(Call<visidetail_responce> call, Response<visidetail_responce> response) {
                li=response.body().getDe();
                vi=new visitior_adapter(getContext(),li);
                recyclerView.setAdapter(vi);
            }

            @Override
            public void onFailure(Call<visidetail_responce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });


    }
}