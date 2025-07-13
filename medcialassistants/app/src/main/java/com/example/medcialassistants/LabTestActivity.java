package com.example.medcialassistants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends BaseActivity {

    // 枚举的实验测试信息
    private String[][] packages =
            {
                    {"套餐1 : 全身健康检查", "全面体检", "基础项目", "适合成年人", "999" },
                    {"套餐2 : 血糖检测", "空腹血糖", "糖化血红蛋白", "糖尿病筛查", "299" },
                    {"套餐3: 新冠病毒检测", "核酸检测", "抗体检测", "快速筛查", "899" },
                    {"套餐4 : 甲状腺功能检查", "T3、T4、TSH", "甲状腺抗体", "内分泌评估", "499" },
                    {"套餐5 : 免疫力检查", "免疫球蛋白", "补体检测", "免疫功能评估", "699" },
                    {"套餐6 : 心血管检查", "心电图", "血脂四项", "心脏功能评估", "799" },
                    {"套餐7 : 肝功能检查", "转氨酶", "胆红素", "肝脏功能评估", "399" },
                    {"套餐8 : 肾功能检查", "肌酐", "尿素氮", "肾脏功能评估", "399" },
                    {"套餐9 : 肿瘤标志物", "AFP、CEA", "PSA、CA125", "癌症筛查", "1200" },
                    {"套餐10 : 骨密度检查", "双能X线", "骨代谢指标", "骨质疏松评估", "600" },
                    {"套餐11 : 女性健康检查", "妇科检查", "乳腺检查", "女性专项", "800" },
                    {"套餐12 : 男性健康检查", "前列腺检查", "性激素", "男性专项", "700" },
                    {"套餐13 : 儿童体检", "生长发育", "疫苗接种", "儿童专项", "500" },
                    {"套餐14 : 老年体检", "心脑血管", "骨关节", "老年专项", "900" },
                    {"套餐15 : 孕前检查", "遗传病筛查", "传染病检测", "优生优育", "1000" }
            };

    private String[] package_details = {
            "空腹血糖\n" +
                    " 完整血常规\n" +
                    "糖化血红蛋白\n" +
                    " 铁含量\n" +
                    "肾功能检测\n" +
                    "乳酸脱氢酶\n" +
                    "血脂四项\n" +
                    "肝功能检测\n" +
                    "尿常规\n" +
                    "心电图",
            "空腹血糖检测\n" +
                    "糖化血红蛋白检测\n" +
                    "胰岛素检测",
            "新冠病毒核酸检测\n" +
                    "新冠病毒抗体检测\n" +
                    "快速抗原检测",
            "甲状腺总剖面图（T3、T4和TSH超灵敏）\n" +
                    "甲状腺抗体检测\n" +
                    "甲状腺超声检查",
            "免疫球蛋白检测\n" +
                    "补体检测\n" +
                    "C反应蛋白定量\n" +
                    "铁含量\n" +
                    "肾功能检测\n" +
                    "维生素D总量\n" +
                    "肝功能测试\n" +
                    "血脂检测",
            "心电图检查\n" +
                    "血脂四项检测\n" +
                    "心肌酶检测\n" +
                    "血压监测\n" +
                    "心脏超声检查",
            "谷丙转氨酶\n" +
                    "谷草转氨酶\n" +
                    "总胆红素\n" +
                    "直接胆红素\n" +
                    "碱性磷酸酶\n" +
                    "γ-谷氨酰转肽酶",
            "血肌酐\n" +
                    "尿素氮\n" +
                    "尿酸\n" +
                    "肾小球滤过率\n" +
                    "尿蛋白定量",
            "甲胎蛋白(AFP)\n" +
                    "癌胚抗原(CEA)\n" +
                    "前列腺特异性抗原(PSA)\n" +
                    "糖类抗原125(CA125)\n" +
                    "糖类抗原199(CA199)",
            "双能X线骨密度检测\n" +
                    "骨代谢指标检测\n" +
                    "维生素D检测\n" +
                    "钙磷代谢检测",
            "妇科常规检查\n" +
                    "宫颈细胞学检查\n" +
                    "乳腺超声检查\n" +
                    "性激素六项\n" +
                    "HPV检测",
            "前列腺特异性抗原\n" +
                    "性激素检测\n" +
                    "前列腺超声检查\n" +
                    "精液常规检查",
            "身高体重测量\n" +
                    "生长发育评估\n" +
                    "疫苗接种情况\n" +
                    "视力听力检查\n" +
                    "血常规检查",
            "心脑血管检查\n" +
                    "骨关节检查\n" +
                    "认知功能评估\n" +
                    "营养状况评估\n" +
                    "跌倒风险评估",
            "遗传病筛查\n" +
                    "传染病检测\n" +
                    "染色体检查\n" +
                    "优生优育咨询\n" +
                    "营养状况评估"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.buttonBMCartCheckout);
        btnBack = findViewById(R.id.buttonBMCartBack);
        listView = findViewById(R.id.listViewCartBM);

        // 返回Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "总花费:" + packages[i][4] + "元");
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

        // 点击某一栏进入实验测试详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                it.putExtra("text1", packages[position][0]);
                it.putExtra("text2", package_details[position]);
                it.putExtra("text3", packages[position][4]);
                startActivity(it);
            }
        });

        // 去购物车界面
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, OrderDetailsActivity.class));
            }
        });
    }
}