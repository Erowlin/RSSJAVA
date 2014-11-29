package com.example.jonathan.rss;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FeedList extends Activity {
    private ArrayList<Feed> feeds = new ArrayList<Feed>();
    private FeedAdapter feedAdapter;

    private class refreshFeeds extends AsyncTask<Void, Integer, ArrayList<Feed>> {

        @Override
        protected ArrayList<Feed> doInBackground(Void... voids) {
            NetworkAPI.getInstance().updateFeeds();
            return NetworkAPI.getInstance().feeds;
        }

        @Override
        protected void onPostExecute(ArrayList<Feed> f) {
            feeds.clear();
            feeds.addAll(f);
            //feedAdapter.clear();
            //feedAdapter.addAll(f);
            feedAdapter.notifyDataSetChanged();
        }
    }


    private class addFeed extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            NetworkAPI.getInstance().newFeed(urls[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            new refreshFeeds().execute();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_view);
        feedAdapter = new FeedAdapter(this, R.layout.feed_list, feeds);
        this.setTitle("Rss Reader");
        ListView listView = (ListView)this.findViewById(R.id.itemsList);
        listView.setAdapter(feedAdapter);
        listView.setOnItemClickListener(onItemClickListener);
        new refreshFeeds().execute();
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Feed data = feeds.get(arg2);
            Intent feedViewIntent = new Intent(getApplicationContext(), FeedView.class);
            feedViewIntent.putExtra("feed", data);
            startActivity(feedViewIntent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed_list, menu);
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
        } else if (id == R.id.action_add) {
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
            new AlertDialog.Builder(FeedList.this)
                    .setTitle("Add Feed")
                    .setMessage("Feed URL")
                    .setView(input)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable value = input.getText();
                            new addFeed().execute(value.toString());
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Do nothing.
                }
            }).show();
        }
        return true;
    }
}
