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
    private String mTitle;

    public MySpinnerAdapter(Context context, int resource, String[] data, String title) {
        super(context, resource, data);

        mContext = context;
        mLayoutResource = resource;
        mData = new ArrayList<String>(Arrays.asList(data));
        mTitle = title;
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

        // Always use the title.
        holder.mTitle.setText(mTitle);

        // You don't have to use the .replace() call I just thought the subtitle would be too
        // long otherwise.
        holder.mSubtitle.setText(mData.get(position).replace("Sort by ", "").toUpperCase());

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

        // Get the string for the dropdown item.
        holder.mTitle.setText(mData.get(position));

        return convertView;
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
