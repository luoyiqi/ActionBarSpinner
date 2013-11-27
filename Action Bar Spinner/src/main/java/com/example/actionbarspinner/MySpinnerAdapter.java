package com.example.actionbarspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MySpinnerAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int mLayoutResource;
    private ArrayList<String> mData;

    public MySpinnerAdapter(Context context, int resource, String[] data) {
        super(context, resource, data);

        mContext = context;
        mLayoutResource = resource;
        mData = new ArrayList<String>(Arrays.asList(data));
    }

    // This method will return the top item of the spinner, so you want to
    // define a layout that has both a title and subtitle
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopViewHolder holder = null;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);

            holder = new TopViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.textView_spinner_dropdown_title);
            holder.mSubtitle = (TextView) convertView.findViewById(R.id.textView_spinner_dropdown_subtitle);

            convertView.setTag(holder);
        } else {
            holder = (TopViewHolder) convertView.getTag();
        }

        // Always use the first item in the list because that is our title.
        holder.mTitle.setText(mData.get(0));

        // You don't have to use the .replace() call I just thought the subtitle would be too
        // long otherwise.
        //
        // "position + 1" because position 0 is our title, and you don't want
        // that in the dropdown.
        holder.mSubtitle.setText(mData.get(position + 1).replace("Sort by ", "").toUpperCase());

        return convertView;
    }

    // This method will return the dropdown items of the spinner. For our purpose we only
    // need a layout that has a simple TextView, this could be customized quite easily.
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        DropDownViewHolder holder = null;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.spinner_top_item, parent, false);

            holder = new DropDownViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.textView_spinner_top_title);

            convertView.setTag(holder);
        } else {
            holder = (DropDownViewHolder) convertView.getTag();
        }

        // "position + 1" because position 0 is our title, and you don't want
        // that in the dropdown.
        String title = mData.get(position + 1);

        holder.mTitle.setText(title);

        return convertView;
    }

    @Override
    public int getCount() {
        // Our cheat is that the first item in the data is supposed to
        // always be in the top view, so we actually have one less piece
        // of data to show in the dropdown list.
        //
        // Make sure the class knows it.
        return mData.size() - 1;
    }

    // I use two different ViewHolder classes to keep track of
    // what item I'm trying to create.
    static class DropDownViewHolder {
        TextView mTitle;
    }

    static class TopViewHolder {
        TextView mTitle;
        TextView mSubtitle;
    }
}
