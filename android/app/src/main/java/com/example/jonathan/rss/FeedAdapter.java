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


public class FeedAdapter extends ArrayAdapter<Feed> {
    private Activity myContext;
    private ArrayList<Feed> data;

    static class ViewHolder {
        TextView feedTitle;
    }

    public FeedAdapter(Context context, int textViewResourceId,
                       ArrayList<Feed> objects) {
        super(context, textViewResourceId, objects);
        myContext = (Activity) context;
        data = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = myContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.feed_list, null);
            viewHolder = new ViewHolder();
            viewHolder.feedTitle = (TextView)convertView.findViewById(R.id.feedTitle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.feedTitle.setText(data.get(position).title);
        return convertView;
    }
}