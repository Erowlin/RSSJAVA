package com.example.jonathan.rss;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Jonathan on 08-Nov-14.
 */
public class Feed implements Parcelable{
    public int id;
    public String title;
    public String link;
    public ArrayList<Item> items = new ArrayList<Item>();

    public Feed(int i, String t, String l)
    {
        id = i;
        title = t;
        link = l;
    }
    public void updateItems() {
        items = NetworkAPI.getInstance().updateFeed(id);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(link);
    }

    public static final Parcelable.Creator<Feed> CREATOR = new Parcelable.Creator<Feed>() {
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    private Feed(Parcel parcel) {
        id = parcel.readInt();
        title = parcel.readString();
        link = parcel.readString();
    }
}
