package com.example.medcialassistants;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends BaseActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;

    Button btnAddToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewLDPackageName);
        tvTotalCost = findViewById(R.id.textViewLDTotalCost);
        edDetails = findViewById(R.id.editTextLDTextMultiLine);
        btnAddToCart = findViewById(R.id.buttonLDAddToCart);
        btnBack = findViewById(R.id.buttonLDBack);

        edDetails.setKeyListener(null);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("总花费:" + intent.getStringExtra("text3") + "/-");

        // 返回上一级
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        // 添加进购物车
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "产品已在清单中", Toast.LENGTH_SHORT).show();
                } else {
                    boolean success = db.addCart(username, product, price, "lab");
                    if (success) {
                        Toast.makeText(getApplicationContext(), "已添加至产品清单", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "添加失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}