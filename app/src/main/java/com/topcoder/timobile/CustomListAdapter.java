//package com.topcoder.timobile;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.SparseBooleanArray;
//import android.view.View;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
///**
// * Created by akshay on 1/11/17.
// */
//
//public class CustomListAdapter implements View.OnClickListener {
//
//    public void onClick(View v) {
//        SparseBooleanArray checked = listView.getCheckedItemPositions();
//        ArrayList<String> selectedItems = new ArrayList<String>();
//        for (int i = 0; i < checked.size(); i++) {
//            // Item position in adapter
//            int position = checked.keyAt(i);
//            // Add sport if it is checked i.e.) == TRUE!
//            if (checked.valueAt(i))
//                selectedItems.add(adapter.getItem(position));
//        }
//
//        String[] outputStrArr = new String[selectedItems.size()];
//
//        for (int i = 0; i < selectedItems.size(); i++) {
//            outputStrArr[i] = selectedItems.get(i);
//        }
//
//        Intent intent = new Intent(getApplicationContext(),
//                ResultActivity.class);
//
//        // Create a bundle object
//        Bundle b = new Bundle();
//        b.putStringArray("selectedItems", outputStrArr);
//
//        // Add the bundle to the intent.
//        intent.putExtras(b);
//
//        // start the ResultActivity
//        startActivity(intent);
//    }
//}
