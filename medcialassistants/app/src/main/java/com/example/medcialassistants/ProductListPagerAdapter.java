package com.example.medcialassistants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductListPagerAdapter extends PagerAdapter {

    private Context context;
    private String[][] medicine_details;
    private String[][] lab_details;
    private String[][] appointment_details;

    public ProductListPagerAdapter(Context context, String[][] medicine_details, String[][] lab_details, String[][] appointment_details) {
        this.context = context;
        this.medicine_details = medicine_details;
        this.lab_details = lab_details;
        this.appointment_details = appointment_details;
    }

    @Override
    public int getCount() {
        return 3; // 三个页面：药品、体检、预约
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_list_page, container, false);
        
        ListView listView = view.findViewById(R.id.listViewProduct);
        
        String[][] currentData;
        switch (position) {
            case 0: // 药品页面
                currentData = medicine_details;
                break;
            case 1: // 体检页面
                currentData = lab_details;
                break;
            case 2: // 预约页面
                currentData = appointment_details;
                break;
            default:
                currentData = new String[0][];
        }
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < currentData.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", currentData[i][0]);
            item.put("line2", currentData[i][1]);
            item.put("line3", currentData[i][2]);
            item.put("line4", currentData[i][3]);
            item.put("line5", "");
            list.add(item);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(context, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        
        listView.setAdapter(adapter);
        
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
} 