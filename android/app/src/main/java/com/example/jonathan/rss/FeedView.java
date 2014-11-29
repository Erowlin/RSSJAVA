package com.example.jonathan.rss;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;

import java.util.ArrayList;

public class FeedView extends Activity {
    private Feed feed;
    private ItemAdapter itemAdapter;

    private class refreshFeed extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            feed.updateItems();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            itemAdapter.clear();
            itemAdapter.addAll(feed.items);
            itemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_view);

        feed = getIntent().getParcelableExtra("feed");
        itemAdapter = new ItemAdapter(this, R.layout.item_list, feed.items);
        this.setTitle(feed.title);
        ListView listView = (ListView)this.findViewById(R.id.itemsList);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(onItemClickListener);
        new refreshFeed().execute();
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Item data = feed.items.get(arg2);
            Bundle itemInfo = new Bundle();
            itemInfo.putString("content", data.description);
            itemInfo.putString("title", data.title);

            Intent itemViewIntent = new Intent(getApplicationContext(), ItemView.class);
            itemViewIntent.putExtras(itemInfo);
            startActivity(itemViewIntent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            NetworkAPI.getInstance().logout();
            Intent loginViewIntent = new Intent(getApplicationContext(), LoginView.class);
            startActivity(loginViewIntent);
        } else if (id == R.id.action_refresh) {
            new refreshFeed().execute();
        }
        return true;
    }
}
