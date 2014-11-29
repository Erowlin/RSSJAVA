package com.example.jonathan.rss;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Jonathan on 16-Nov-14.
 */


public class ItemAdapter extends ArrayAdapter<Item> {
    private Activity myContext;
    private ArrayList<Item> data;

    static class ViewHolder {
        TextView itemTitle;
        TextView itemDate;
    }

    public ItemAdapter(Context context, int textViewResourceId,
                       ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        myContext = (Activity) context;
        data = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = myContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_list, null);
            viewHolder = new ViewHolder();
            viewHolder.itemTitle = (TextView)convertView.findViewById(R.id.itemTitle);
            viewHolder.itemDate = (TextView)convertView.findViewById(R.id.itemDate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.itemTitle.setText(data.get(position).title);
        viewHolder.itemDate.setText(data.get(position).pubDate);
        return convertView;
    }
}