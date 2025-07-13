package com.example.medcialassistants;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OrderDetailsActivity extends BaseActivity {

    private String[][] medicine_details = {};
    private String[][] lab_details = {};
    private String[][] appointment_details = {};
    private String[][] order_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lstMedicine, lstLab, lstAppointment;
    Button btnBack, btnCheckout, btnViewOrders;
    TextView tvTitle, tvTotalCost, tvPageIndicator;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton;
    private float totalAmount = 0;
    private ViewPager viewPager;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btnBack = findViewById(R.id.buttonODBack);
        btnCheckout = findViewById(R.id.buttonCheckout);
        btnViewOrders = findViewById(R.id.buttonViewOrders);
        tvTitle = findViewById(R.id.textViewODTitle);
        tvTotalCost = findViewById(R.id.textViewTotalCost);
        tvPageIndicator = findViewById(R.id.textViewPageIndicator);
        dateButton = findViewById(R.id.buttonDate);
        viewPager = findViewById(R.id.viewPager);

        // 初始化日期选择器
        initDatePicker();

        // 返回主页
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });

        // 查看订单历史
        btnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderHistory();
            }
        });

        // 统一结算
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalAmount > 0) {
                    showCheckoutDialog();
                } else {
                    Toast.makeText(getApplicationContext(), "产品清单为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 日期选择
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        // 设置ViewPager页面切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                updatePageIndicator();
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        // 加载产品清单数据
        loadProductListData();
    }

    private void loadProductListData() {
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "");

        // 获取购物车数据
        ArrayList<String> medicineCart = db.getCartData(username, "medicine");
        ArrayList<String> labCart = db.getCartData(username, "lab");
        ArrayList<String> appointmentCart = db.getCartData(username, "appointment");

        totalAmount = 0;

        // 处理药品数据
        medicine_details = new String[medicineCart.size()][];
        for (int i = 0; i < medicineCart.size(); i++) {
            medicine_details[i] = new String[5];
            String arrData = medicineCart.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            medicine_details[i][0] = strData[0]; // 商品名称
            medicine_details[i][1] = "药品"; // 类型
            medicine_details[i][2] = "¥" + strData[1]; // 价格
            medicine_details[i][3] = "待配送"; // 状态
            medicine_details[i][4] = "medicine"; // 类型标识
            totalAmount += Float.parseFloat(strData[1]);
        }

        // 处理体检数据
        lab_details = new String[labCart.size()][];
        for (int i = 0; i < labCart.size(); i++) {
            lab_details[i] = new String[5];
            String arrData = labCart.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            lab_details[i][0] = strData[0]; // 商品名称
            lab_details[i][1] = "体检"; // 类型
            lab_details[i][2] = "¥" + strData[1]; // 价格
            lab_details[i][3] = "待预约"; // 状态
            lab_details[i][4] = "lab"; // 类型标识
            totalAmount += Float.parseFloat(strData[1]);
        }

        // 处理预约数据
        appointment_details = new String[appointmentCart.size()][];
        for (int i = 0; i < appointmentCart.size(); i++) {
            appointment_details[i] = new String[5];
            String arrData = appointmentCart.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            appointment_details[i][0] = strData[0]; // 商品名称
            appointment_details[i][1] = "预约"; // 类型
            appointment_details[i][2] = "¥" + strData[1]; // 价格
            appointment_details[i][3] = "待确认"; // 状态
            appointment_details[i][4] = "appointment"; // 类型标识
            totalAmount += Float.parseFloat(strData[1]);
        }

        tvTotalCost.setText("总价: ¥" + totalAmount);
        updatePageIndicator();

        // 设置ViewPager适配器
        ProductListPagerAdapter pagerAdapter = new ProductListPagerAdapter(this, medicine_details, lab_details, appointment_details);
        viewPager.setAdapter(pagerAdapter);
    }

    private void updatePageIndicator() {
        String[] pageNames = {"药品", "体检", "预约"};
        tvPageIndicator.setText((currentPage + 1) + "/3 - " + pageNames[currentPage]);
    }

    private void showOrderHistory() {
        if (tvTitle.getText().equals("订单历史")) {
            // 如果当前显示的是订单历史，则切换回产品清单
            loadProductListData();
            tvTitle.setText("产品清单");
            btnViewOrders.setText("订单历史");
            viewPager.setVisibility(View.VISIBLE);
            tvPageIndicator.setVisibility(View.VISIBLE);
            
            // 隐藏订单历史ListView
            ListView orderListView = findViewById(R.id.listViewOrderHistory);
            if (orderListView != null) {
                orderListView.setVisibility(View.GONE);
            }
        } else {
            // 如果当前显示的是产品清单，则切换到订单历史
            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
            SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedpreferences.getString("username", "");
            ArrayList<String> dbData = db.getOrderData(username);

            order_details = new String[dbData.size()][];
            for (int i = 0; i < order_details.length; i++) {
                order_details[i] = new String[5];
                String arrData = dbData.get(i).toString();
                String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
                order_details[i][0] = strData[0]; // fullname
                order_details[i][1] = strData[1]; // address
                if (strData[7].compareTo("medicine") == 0) {
                    order_details[i][3] = "配送:" + strData[4]; // date
                } else {
                    order_details[i][3] = "预约: " + strData[4] + " " + strData[5]; // date + time
                }
                order_details[i][2] = "¥" + strData[6]; // amount
                order_details[i][4] = strData[7]; // otype
            }

            list = new ArrayList<>();
            for (int i = 0; i < order_details.length; i++) {
                item = new HashMap<String, String>();
                item.put("line1", order_details[i][0]);
                item.put("line2", order_details[i][1]);
                item.put("line3", order_details[i][2]);
                item.put("line4", order_details[i][3]);
                item.put("line5", order_details[i][4]);
                list.add(item);
            }

            sa = new SimpleAdapter(this, list,
                    R.layout.multi_lines,
                    new String[]{"line1", "line2", "line3", "line4", "line5"},
                    new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
            
            // 隐藏ViewPager，显示订单历史
            viewPager.setVisibility(View.GONE);
            tvPageIndicator.setVisibility(View.GONE);
            
            // 显示订单历史的ListView
            ListView orderListView = findViewById(R.id.listViewOrderHistory);
            if (orderListView != null) {
                orderListView.setVisibility(View.VISIBLE);
                orderListView.setAdapter(sa);
            }

            tvTitle.setText("订单历史");
            btnViewOrders.setText("查看产品清单");
        }
    }

    private void showCheckoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("统一结算");
        builder.setMessage("总金额: ¥" + totalAmount + "\n\n确认要结算所有产品吗？");
        builder.setPositiveButton("确认", (dialog, which) -> {
            // 执行统一结算
            performUnifiedCheckout();
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    private void performUnifiedCheckout() {
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "");

        // 获取购物车数据
        ArrayList<String> medicineCart = db.getCartData(username, "medicine");
        ArrayList<String> labCart = db.getCartData(username, "lab");
        ArrayList<String> appointmentCart = db.getCartData(username, "appointment");

        // 处理药品订单
        for (int i = 0; i < medicineCart.size(); i++) {
            String arrData = medicineCart.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            db.addOrder(username, strData[0], "药品配送", "系统配送", 0, dateButton.getText().toString(), "", Float.parseFloat(strData[1]), "medicine");
        }

        // 处理体检订单
        for (int i = 0; i < labCart.size(); i++) {
            String arrData = labCart.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            db.addOrder(username, strData[0], "体检中心", "系统预约", 0, dateButton.getText().toString(), "09:00", Float.parseFloat(strData[1]), "lab");
        }

        // 处理预约订单
        for (int i = 0; i < appointmentCart.size(); i++) {
            String arrData = appointmentCart.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            db.addOrder(username, strData[0], "医院", "系统预约", 0, dateButton.getText().toString(), "10:00", Float.parseFloat(strData[1]), "appointment");
        }

        // 清空购物车
        db.removeCart(username, "medicine");
        db.removeCart(username, "lab");
        db.removeCart(username, "appointment");

        Toast.makeText(getApplicationContext(), "统一结算成功！总金额: ¥" + totalAmount, Toast.LENGTH_LONG).show();
        
        // 重新加载产品清单数据
        loadProductListData();
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateButton.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 86400000);
    }
}