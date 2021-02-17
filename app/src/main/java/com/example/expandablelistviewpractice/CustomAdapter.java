package com.example.expandablelistviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> headerData;
    HashMap<String,List<String>> childData;

    CustomAdapter(Context context, List<String> headerData, HashMap<String, List<String>> childData ){
        this.context = context;
        this.headerData = headerData;
        this.childData = childData;
    }

    @Override
    public int getGroupCount() {
        return headerData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childData.get(headerData.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return headerData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childData.get(headerData.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTextSet = (String) getGroup(i);
        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.groupe_layout,null);
        }
        TextView textView = (TextView)view.findViewById(R.id.header_id);
        textView.setText(headerTextSet);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childText = (String) getChild(i, i1);
        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_layout,null);
        }
        TextView textView = (TextView)view.findViewById(R.id.child_id);
        textView.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
