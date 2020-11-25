package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Activity activity; ArrayList<Record> arrayList;
    public CustomAdapter(Activity activity, ArrayList<Record> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }




    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = activity.getLayoutInflater().inflate(R.layout.view_record,parent,false);

        final TextView title = convertView.findViewById(R.id.title);
        final TextView amount=  convertView.findViewById(R.id.amount);
        final TextView balance=  convertView.findViewById(R.id.balance);
        title.setText(arrayList.get(position).getTitle());
        amount.setText(arrayList.get(position).getAmount());
        balance.setText(arrayList.get(position).getBalance());
        if(arrayList.get(position).getType().toLowerCase().equals( "credit")){
            amount.setTextColor(activity.getResources().getColor(R.color.green));
        }else{
            amount.setTextColor(activity.getResources().getColor(R.color.red));
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(activity,ViewFullRecord.class);
                i.putExtra("title",title.getText().toString());
                i.putExtra("amount",amount.getText().toString());
                i.putExtra("balance",balance.getText().toString());
                i.putExtra("ID",arrayList.get(position).getID().toString());
                activity.startActivity(i);
            }
        });
        return convertView;

    }


}
