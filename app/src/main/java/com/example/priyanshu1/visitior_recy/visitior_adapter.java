package com.example.priyanshu1.visitior_recy;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.priyanshu1.R;

import java.util.List;

public class visitior_adapter extends RecyclerView.Adapter<visitior_adapter.ViewHolder> {
    Dialog mydialog;
    Context mcontext;
    public visitior_adapter(Context mcontext,List<visitior_data> visitior_data){
        this.visitior_data=visitior_data;
        this.mcontext=mcontext;
    }
   private List<visitior_data> visitior_data;
    @NonNull
    @Override
    public visitior_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.visitior_layout,null);
        ViewHolder viewHolder=new ViewHolder(itemview);
        mydialog =new Dialog(mcontext);
        mydialog.setContentView(R.layout.pop_layout_visit_details);
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                EditText di_exit=(EditText) mydialog.findViewById(R.id.pop_exit);
                Button b=(Button)mydialog.findViewById(R.id.pop_ok);
                String s=di_exit.getText().toString();
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      viewHolder.ve_exit.setText(di_exit.getText().toString());
                      mydialog.dismiss();
                    }
                });
                mydialog.show();
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull visitior_adapter.ViewHolder viewHolder, int i) {
        visitior_data d=visitior_data.get(i);
        viewHolder.ve_name.setText(d.v_name);
        viewHolder.ve_mobile.setText(d.v_mobile);
        viewHolder.ve_date.setText(d.v_date);
        viewHolder.ve_entery.setText(d.v_entry);
        viewHolder.ve_exit.setText(d.v_exit);
        viewHolder.ve_flat.setText(d.v_flat);
    }

    @Override
    public int getItemCount() {
        return visitior_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView ve_name,ve_mobile,ve_date,ve_entery,ve_exit,ve_flat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ve_name=(TextView) itemView.findViewById(R.id.visitior_name);
            ve_mobile=(TextView) itemView.findViewById(R.id.visitior_mobile);
            ve_date=(TextView) itemView.findViewById(R.id.visitior_date);
            ve_entery=(TextView) itemView.findViewById(R.id.visitior_entry);
            ve_exit=(TextView) itemView.findViewById(R.id.visitior_exit);
            ve_flat=(TextView) itemView.findViewById(R.id.visitior_flatno);

        }
    }
}
