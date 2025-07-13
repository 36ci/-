package com.example.medcialassistants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BDMap extends BaseActivity {

    private String[][] medicine_details = {
        {"阿司匹林", "解热镇痛药", "用于缓解轻至中度疼痛", "¥15.80", "加入购物车"},
        {"布洛芬", "非甾体抗炎药", "用于缓解疼痛和炎症", "¥12.50", "加入购物车"},
        {"感冒灵颗粒", "感冒药", "用于感冒引起的头痛发热", "¥25.00", "加入购物车"},
        {"板蓝根颗粒", "清热解毒药", "用于风热感冒", "¥18.60", "加入购物车"},
        {"维生素C片", "维生素类", "补充维生素C", "¥8.90", "加入购物车"},
        {"钙片", "矿物质类", "补充钙质", "¥32.00", "加入购物车"},
        {"藿香正气水", "中成药", "用于暑湿感冒", "¥16.50", "加入购物车"},
        {"创可贴", "外用药品", "用于小伤口止血", "¥5.20", "加入购物车"},
        {"云南白药", "跌打损伤药", "用于跌打损伤", "¥45.00", "加入购物车"},
        {"眼药水", "眼科用药", "缓解眼部疲劳", "¥22.80", "加入购物车"}
    };

    private int[] medicine_images = {
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5,
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdmap);

        btnGoToCart = findViewById(R.id.btn_search);
        btnBack = findViewById(R.id.address);
        listView = findViewById(R.id.bmapView);
        tvTitle = findViewById(R.id.tv_title);

        // 修改按钮文本和功能
        btnGoToCart.setText("查看购物车");
        btnBack.setText("返回");
        tvTitle.setText("药品选购");

        // 返回Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BDMap.this, HomeActivity.class));
            }
        });

        // 去购物车界面
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BDMap.this, OrderDetailsActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < medicine_details.length; i++) {
            item = new HashMap<>();
            item.put("line1", medicine_details[i][0]);
            item.put("line2", medicine_details[i][1]);
            item.put("line3", medicine_details[i][2]);
            item.put("line4", medicine_details[i][3]);
            item.put("line5", medicine_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[] {"line1", "line2", "line3", "line4", "line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        listView.setAdapter(sa);

        // 点击某一栏进入药品详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(BDMap.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", medicine_details[position][0]);
                it.putExtra("text2", medicine_details[position][1]);
                it.putExtra("text3", medicine_details[position][2]);
                it.putExtra("text4", medicine_details[position][3]);
                startActivity(it);
            }
        });
    }
}