package com.example.expandablelistviewpractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder alertBuilder;
    private ExpandableListView expandableListView;
    private int lastExpandableGroup = -1;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
        prepearData();
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),listDataHeader,listDataChild);
        expandableListView.setAdapter(customAdapter);


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String groupName = listDataHeader.get(i);
                Toast.makeText(getApplicationContext(),"Group Name is : "+groupName,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                String groupName = listDataHeader.get(i);
                Toast.makeText(getApplicationContext(),groupName+" is collapsed",Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String childName = listDataChild.get(listDataHeader.get(i)).get(i1);
                Toast.makeText(getApplicationContext(),"Child Name is : "+childName,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                String lastExpandableGroupName = listDataHeader.get(i);
                Toast.makeText(getApplicationContext(),"Last ExpandableGroup Name "+lastExpandableGroupName+" "+i,Toast.LENGTH_SHORT).show();
                if (lastExpandableGroup != -1 && lastExpandableGroup != i){
                    expandableListView.collapseGroup(lastExpandableGroup);
                    Toast.makeText(getApplicationContext(),"Collapse"+lastExpandableGroupName+" "+lastExpandableGroup,Toast.LENGTH_SHORT).show();
                }
                lastExpandableGroup = i;
                Toast.makeText(getApplicationContext(),"I Value of last expandable group"+lastExpandableGroup,Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void prepearData(){
        String [] dataHeader = getResources().getStringArray(R.array.list_view_header);
        String [] dataChild = getResources().getStringArray(R.array.child_string);
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        for (int i = 0; i<dataHeader.length;i++){
            listDataHeader.add(dataHeader[i]);
            List<String> child = new ArrayList<>();
            child.add(dataChild[i]);
            listDataChild.put(listDataHeader.get(i),child);
        }
    }

    public void alertDilougeRun(){
        alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setIcon(R.drawable.aleart_icon);
        alertBuilder.setTitle("Warning");
        alertBuilder.setMessage("Are You Sure ?");
        alertBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertBuilder.create().show();
    }



    @Override
    public void onBackPressed() {
        alertDilougeRun();
    }
}