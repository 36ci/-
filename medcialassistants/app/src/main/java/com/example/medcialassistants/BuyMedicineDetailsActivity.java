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

public class BuyMedicineDetailsActivity extends BaseActivity {

    TextView tvPackageName, tvTotalCost, tvMedicineType;
    EditText edDetails;
    Button btnBack, btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName = findViewById(R.id.textViewBMDPackageName);
        tvTotalCost = findViewById(R.id.textViewBMDTotalCost);
        tvMedicineType = findViewById(R.id.textViewBMDMedicineType);
        edDetails = findViewById(R.id.editTextTextBMDMultiLine);
        edDetails.setKeyListener(null);
        btnBack = findViewById(R.id.buttonBMDGoBackBuy);
        btnAddToCart = findViewById(R.id.buttonAddBMDToCart);
        
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1")); // 药品名称
        tvMedicineType.setText("药品类型: " + intent.getStringExtra("text2")); // 药品类型
        edDetails.setText(intent.getStringExtra("text3")); // 药品用途
        tvTotalCost.setText("¥" + intent.getStringExtra("text4")); // 价格

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        // 添加进购物车
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                try {
                    SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedpreferences.getString("username", "").toString();
                    String product = tvPackageName.getText().toString();
                    String priceStr = intent.getStringExtra("text4");
                    float price = Float.parseFloat(priceStr);

                    System.out.println("准备添加药品: 用户=" + username + ", 产品=" + product + ", 价格=" + price);

                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    if (db.checkCart(username, product) == 1) {
                        Toast.makeText(getApplicationContext(), "药品已在产品清单中", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean success = db.addCart(username, product, price, "medicine");
                        if (success) {
                            Toast.makeText(getApplicationContext(), "已添加至产品清单", Toast.LENGTH_SHORT).show();
                            // 添加调试信息
                            System.out.println("添加药品到购物车成功: " + product + ", 价格: " + price + ", 用户: " + username);
                        } else {
                            Toast.makeText(getApplicationContext(), "添加失败，请重试", Toast.LENGTH_SHORT).show();
                            System.out.println("添加药品到购物车失败");
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "添加失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("添加药品异常: " + e.getMessage());
                    e.printStackTrace();
                }
            };
        });
    }
}