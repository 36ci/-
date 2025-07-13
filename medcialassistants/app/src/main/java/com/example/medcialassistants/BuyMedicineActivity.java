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

public class BuyMedicineActivity extends BaseActivity {

    // 枚举药品信息
    private String[][] packages =
            {
                    {"维生素D3 1000IU 胶囊", "增强骨骼健康", "提高免疫力", "改善钙吸收", "50"},
                    {"铬元素 200mcg 胶囊", "调节血糖", "促进新陈代谢", "增强胰岛素敏感性", "305"},
                    {"复合维生素B 胶囊", "缓解疲劳", "维护神经系统", "促进红细胞形成", "448"},
                    {"维生素E 小麦胚芽油胶囊", "抗氧化", "改善皮肤", "保护心血管", "539"},
                    {"布洛芬 650mg 片剂", "缓解疼痛", "退烧消炎", "适用于头痛关节痛", "30"},
                    {"对乙酰氨基酚 650mg 片剂", "退烧止痛", "适合心脏病患者", "温和有效", "50"},
                    {"润喉糖 含片", "缓解咽喉痛", "杀菌消炎", "舒缓喉咙不适", "40"},
                    {"钙片+维生素D3", "强健骨骼", "预防骨质疏松", "促进钙吸收", "30"},
                    {"铁剂 补铁片", "治疗贫血", "补充铁元素", "改善疲劳", "130"},
                    {"维生素C 1000mg 片剂", "增强免疫力", "抗氧化", "促进胶原蛋白合成", "80"},
                    {"鱼油 Omega-3 胶囊", "保护心血管", "改善大脑功能", "抗炎作用", "200"},
                    {"益生菌 胶囊", "调节肠道", "增强消化", "提高免疫力", "150"},
                    {"褪黑素 3mg 片剂", "改善睡眠", "调节生物钟", "缓解失眠", "120"},
                    {"辅酶Q10 100mg 胶囊", "保护心脏", "抗衰老", "提供能量", "180"},
                    {"胶原蛋白 粉剂", "美容养颜", "改善皮肤", "增强关节", "250"},
                    {"叶酸 400mcg 片剂", "预防贫血", "孕妇必备", "促进细胞分裂", "60"},
                    {"锌元素 15mg 胶囊", "增强免疫", "促进伤口愈合", "改善味觉", "90"},
                    {"镁元素 400mg 片剂", "放松肌肉", "改善睡眠", "缓解焦虑", "110"},
                    {"维生素K2 胶囊", "促进钙吸收", "保护骨骼", "预防动脉硬化", "160"},
                    {"葡萄籽提取物 胶囊", "抗氧化", "保护血管", "改善视力", "220"}
            };

    private String[] package_details = {
            "维生素D3有助于增强骨骼和牙齿强度\n" +
                    "减少疲劳和肌肉疼痛\n" +
                    "提高免疫力，增强对感染的抵抗力",
            "铬是重要的微量元素，在帮助胰岛素调节血糖方面发挥重要作用。",
            "缓解维生素B缺乏症状\n" +
                    "帮助红细胞形成\n" +
                    "维护健康的神经系统",
            "促进健康和皮肤益处\n" +
                    "帮助减少皮肤瑕疵和色素沉着\n" +
                    "保护皮肤免受有害的UVA和UVB射线",
            "布洛芬片剂通过阻断某些化学信使的释放来缓解疼痛和发烧。",
            "帮助缓解发烧并降低高温\n" +
                    "适合心脏病或高血压患者",
            "缓解细菌性咽喉感染症状并舒缓恢复过程\n" +
                    "在咽喉痛期间提供温暖舒适的感觉",
            "降低缺钙、佝偻病和骨质疏松的风险\n" +
                    "促进关节的活动性和灵活性",
            "帮助减少由于慢性失血或铁摄入不足导致的缺铁",
            "维生素C是强大的抗氧化剂，能够增强免疫系统功能，促进胶原蛋白合成，帮助伤口愈合。",
            "鱼油富含Omega-3脂肪酸，对心血管健康有益，能够降低甘油三酯，改善大脑功能。",
            "益生菌有助于维持肠道菌群平衡，改善消化功能，增强免疫系统。",
            "褪黑素是天然的睡眠激素，能够调节生物钟，改善睡眠质量，缓解失眠。",
            "辅酶Q10是细胞能量产生的重要物质，对心脏健康特别重要，具有抗衰老作用。",
            "胶原蛋白是皮肤、骨骼和关节的重要组成成分，补充胶原蛋白有助于美容养颜。",
            "叶酸是B族维生素，对预防贫血特别重要，孕妇补充叶酸可以预防胎儿神经管缺陷。",
            "锌是重要的微量元素，对免疫系统、伤口愈合和味觉功能都很重要。",
            "镁有助于肌肉放松，改善睡眠质量，缓解焦虑和压力。",
            "维生素K2有助于钙的吸收和利用，保护骨骼健康，预防动脉硬化。",
            "葡萄籽提取物富含原花青素，具有强大的抗氧化作用，保护血管健康。"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        // 去购物车界面
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
        // 返回Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "总价: " + packages[i][4] + "元");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        // 药品详情页面
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1" , packages[i][0]);  // 药品名称
                it.putExtra("text2" , "药品");  // 药品类型
                it.putExtra("text3" , package_details[i]);  // 药品用途
                it.putExtra("text4" , packages[i][4]);  // 价格
                startActivity(it);
            }
        });
    }
}