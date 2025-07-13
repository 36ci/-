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

public class DoctorDetailsActivity extends BaseActivity {

    // 枚举每个页面医生的信息，各有五个信息
    private String[][] doctor_details1 =
            {
                    {"医生姓名 : 张医生", "医院地址 : 北京协和医院", "经验 : 15年", "联系电话:13800138000", "600"},
                    {"医生姓名 : 李医生", "医院地址 : 上海瑞金医院", "经验 : 12年", "联系电话:13900139000", "800"},
                    {"医生姓名 : 王医生", "医院地址 : 广州中山医院", "经验 : 10年", "联系电话:13700137000", "700"},
                    {"医生姓名 : 刘医生", "医院地址 : 深圳人民医院", "经验 : 8年", "联系电话:13600136000", "500"},
                    {"医生姓名 : 陈医生", "医院地址 : 杭州浙医一院", "经验 : 20年", "联系电话:13500135000", "900"}
            };
    private String[][] doctor_details2 =
            {
                    {"医生姓名 : 赵营养师", "医院地址 : 北京营养研究所", "经验 : 8年", "联系电话:13800138001", "400"},
                    {"医生姓名 : 钱营养师", "医院地址 : 上海营养中心", "经验 : 10年", "联系电话:13900139001", "500"},
                    {"医生姓名 : 孙营养师", "医院地址 : 广州营养科", "经验 : 6年", "联系电话:13700137001", "350"},
                    {"医生姓名 : 周营养师", "医院地址 : 深圳营养科", "经验 : 12年", "联系电话:13600136001", "600"},
                    {"医生姓名 : 吴营养师", "医院地址 : 杭州营养科", "经验 : 15年", "联系电话:13500135001", "700"}
            };
    private String[][] doctor_details3 =
            {
                    {"医生姓名 : 郑牙医", "医院地址 : 北京口腔医院", "经验 : 12年", "联系电话:13800138002", "800"},
                    {"医生姓名 : 王牙医", "医院地址 : 上海口腔医院", "经验 : 15年", "联系电话:13900139002", "1000"},
                    {"医生姓名 : 冯牙医", "医院地址 : 广州口腔医院", "经验 : 10年", "联系电话:13700137002", "700"},
                    {"医生姓名 : 陈牙医", "医院地址 : 深圳口腔医院", "经验 : 8年", "联系电话:13600136002", "600"},
                    {"医生姓名 : 褚牙医", "医院地址 : 杭州口腔医院", "经验 : 18年", "联系电话:13500135002", "1200"}
            };
    private String[][] doctor_details4 =
            {
                    {"医生姓名 : 卫外科医生", "医院地址 : 北京外科医院", "经验 : 20年", "联系电话:13800138003", "1500"},
                    {"医生姓名 : 蒋外科医生", "医院地址 : 上海外科医院", "经验 : 18年", "联系电话:13900139003", "1400"},
                    {"医生姓名 : 沈外科医生", "医院地址 : 广州外科医院", "经验 : 15年", "联系电话:13700137003", "1200"},
                    {"医生姓名 : 韩外科医生", "医院地址 : 深圳外科医院", "经验 : 12年", "联系电话:13600136003", "1000"},
                    {"医生姓名 : 杨外科医生", "医院地址 : 杭州外科医院", "经验 : 25年", "联系电话:13500135003", "1800"}
            };
    private String[][] doctor_details5 =
            {
                    {"医生姓名 : 朱心脏病专家", "医院地址 : 北京心脏中心", "经验 : 25年", "联系电话:13800138004", "2000"},
                    {"医生姓名 : 秦心脏病专家", "医院地址 : 上海心脏中心", "经验 : 22年", "联系电话:13900139004", "1800"},
                    {"医生姓名 : 尤心脏病专家", "医院地址 : 广州心脏中心", "经验 : 20年", "联系电话:13700137004", "1600"},
                    {"医生姓名 : 许心脏病专家", "医院地址 : 深圳心脏中心", "经验 : 18年", "联系电话:13600136004", "1400"},
                    {"医生姓名 : 何心脏病专家", "医院地址 : 杭州心脏中心", "经验 : 30年", "联系电话:13500135004", "2500"}
            };
    private String[][] doctor_details6 =
            {
                    {"医生姓名 : 吕皮肤科医生", "医院地址 : 北京皮肤医院", "经验 : 15年", "联系电话:13800138005", "800"},
                    {"医生姓名 : 施皮肤科医生", "医院地址 : 上海皮肤医院", "经验 : 12年", "联系电话:13900139005", "700"},
                    {"医生姓名 : 张皮肤科医生", "医院地址 : 广州皮肤医院", "经验 : 10年", "联系电话:13700137005", "600"},
                    {"医生姓名 : 孔皮肤科医生", "医院地址 : 深圳皮肤医院", "经验 : 8年", "联系电话:13600136005", "500"},
                    {"医生姓名 : 曹皮肤科医生", "医院地址 : 杭州皮肤医院", "经验 : 18年", "联系电话:13500135005", "900"}
            };
    private String[][] doctor_details7 =
            {
                    {"医生姓名 : 严眼科医生", "医院地址 : 北京眼科医院", "经验 : 20年", "联系电话:13800138006", "1200"},
                    {"医生姓名 : 华眼科医生", "医院地址 : 上海眼科医院", "经验 : 18年", "联系电话:13900139006", "1100"},
                    {"医生姓名 : 金眼科医生", "医院地址 : 广州眼科医院", "经验 : 15年", "联系电话:13700137006", "900"},
                    {"医生姓名 : 魏眼科医生", "医院地址 : 深圳眼科医院", "经验 : 12年", "联系电话:13600136006", "800"},
                    {"医生姓名 : 陶眼科医生", "医院地址 : 杭州眼科医院", "经验 : 22年", "联系电话:13500135006", "1300"}
            };
    private String[][] doctor_details8 =
            {
                    {"医生姓名 : 姜神经科医生", "医院地址 : 北京神经医院", "经验 : 25年", "联系电话:13800138007", "1800"},
                    {"医生姓名 : 戚神经科医生", "医院地址 : 上海神经医院", "经验 : 22年", "联系电话:13900139007", "1600"},
                    {"医生姓名 : 谢神经科医生", "医院地址 : 广州神经医院", "经验 : 20年", "联系电话:13700137007", "1400"},
                    {"医生姓名 : 邹神经科医生", "医院地址 : 深圳神经医院", "经验 : 18年", "联系电话:13600136007", "1200"},
                    {"医生姓名 : 喻神经科医生", "医院地址 : 杭州神经医院", "经验 : 28年", "联系电话:13500135007", "2000"}
            };
    private String[][] doctor_details9 =
            {
                    {"医生姓名 : 柏精神科医生", "医院地址 : 北京精神医院", "经验 : 20年", "联系电话:13800138008", "1500"},
                    {"医生姓名 : 水精神科医生", "医院地址 : 上海精神医院", "经验 : 18年", "联系电话:13900139008", "1300"},
                    {"医生姓名 : 窦精神科医生", "医院地址 : 广州精神医院", "经验 : 15年", "联系电话:13700137008", "1100"},
                    {"医生姓名 : 章精神科医生", "医院地址 : 深圳精神医院", "经验 : 12年", "联系电话:13600136008", "900"},
                    {"医生姓名 : 云精神科医生", "医院地址 : 杭州精神医院", "经验 : 25年", "联系电话:13500135008", "1700"}
            };
    private String[][] doctor_details10 =
            {
                    {"医生姓名 : 苏儿科医生", "医院地址 : 北京儿童医院", "经验 : 18年", "联系电话:13800138009", "1000"},
                    {"医生姓名 : 潘儿科医生", "医院地址 : 上海儿童医院", "经验 : 15年", "联系电话:13900139009", "900"},
                    {"医生姓名 : 葛儿科医生", "医院地址 : 广州儿童医院", "经验 : 12年", "联系电话:13700137009", "800"},
                    {"医生姓名 : 奚儿科医生", "医院地址 : 深圳儿童医院", "经验 : 10年", "联系电话:13600136009", "700"},
                    {"医生姓名 : 范儿科医生", "医院地址 : 杭州儿童医院", "经验 : 20年", "联系电话:13500135009", "1100"}
            };
    private String[][] doctor_details11 =
            {
                    {"医生姓名 : 彭妇科医生", "医院地址 : 北京妇产医院", "经验 : 20年", "联系电话:13800138010", "1200"},
                    {"医生姓名 : 鲁妇科医生", "医院地址 : 上海妇产医院", "经验 : 18年", "联系电话:13900139010", "1100"},
                    {"医生姓名 : 韦妇科医生", "医院地址 : 广州妇产医院", "经验 : 15年", "联系电话:13700137010", "900"},
                    {"医生姓名 : 昌妇科医生", "医院地址 : 深圳妇产医院", "经验 : 12年", "联系电话:13600136010", "800"},
                    {"医生姓名 : 马妇科医生", "医院地址 : 杭州妇产医院", "经验 : 22年", "联系电话:13500135010", "1300"}
            };
    private String[][] doctor_details12 =
            {
                    {"医生姓名 : 苗骨科医生", "医院地址 : 北京骨科医院", "经验 : 22年", "联系电话:13800138011", "1400"},
                    {"医生姓名 : 凤骨科医生", "医院地址 : 上海骨科医院", "经验 : 20年", "联系电话:13900139011", "1300"},
                    {"医生姓名 : 花骨科医生", "医院地址 : 广州骨科医院", "经验 : 18年", "联系电话:13700137011", "1100"},
                    {"医生姓名 : 方骨科医生", "医院地址 : 深圳骨科医院", "经验 : 15年", "联系电话:13600136011", "1000"},
                    {"医生姓名 : 俞骨科医生", "医院地址 : 杭州骨科医院", "经验 : 25年", "联系电话:13500135011", "1600"}
            };
    private String[][] doctor_details13 =
            {
                    {"医生姓名 : 任消化科医生", "医院地址 : 北京消化医院", "经验 : 18年", "联系电话:13800138012", "1100"},
                    {"医生姓名 : 袁消化科医生", "医院地址 : 上海消化医院", "经验 : 16年", "联系电话:13900139012", "1000"},
                    {"医生姓名 : 柳消化科医生", "医院地址 : 广州消化医院", "经验 : 14年", "联系电话:13700137012", "900"},
                    {"医生姓名 : 酆消化科医生", "医院地址 : 深圳消化医院", "经验 : 12年", "联系电话:13600136012", "800"},
                    {"医生姓名 : 鲍消化科医生", "医院地址 : 杭州消化医院", "经验 : 20年", "联系电话:13500135012", "1200"}
            };
    private String[][] doctor_details14 =
            {
                    {"医生姓名 : 史内分泌科医生", "医院地址 : 北京内分泌医院", "经验 : 20年", "联系电话:13800138013", "1300"},
                    {"医生姓名 : 唐内分泌科医生", "医院地址 : 上海内分泌医院", "经验 : 18年", "联系电话:13900139013", "1200"},
                    {"医生姓名 : 费内分泌科医生", "医院地址 : 广州内分泌医院", "经验 : 16年", "联系电话:13700137013", "1000"},
                    {"医生姓名 : 廉内分泌科医生", "医院地址 : 深圳内分泌医院", "经验 : 14年", "联系电话:13600136013", "900"},
                    {"医生姓名 : 岑内分泌科医生", "医院地址 : 杭州内分泌医院", "经验 : 22年", "联系电话:13500135013", "1400"}
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};

    ArrayList list;
    SimpleAdapter sa;
    HashMap<String, String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.titleViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("家庭医生") == 0) {
            doctor_details = doctor_details1;
        } else if (title.compareTo("营养师") == 0) {
            doctor_details = doctor_details2;
        } else if (title.compareTo("牙医") == 0) {
            doctor_details = doctor_details3;
        } else if (title.compareTo("外科医生") == 0) {
            doctor_details = doctor_details4;
        } else if (title.compareTo("心脏病专家") == 0) {
            doctor_details = doctor_details5;
        } else if (title.compareTo("皮肤科医生") == 0) {
            doctor_details = doctor_details6;
        } else if (title.compareTo("眼科医生") == 0) {
            doctor_details = doctor_details7;
        } else if (title.compareTo("神经科医生") == 0) {
            doctor_details = doctor_details8;
        } else if (title.compareTo("精神科医生") == 0) {
            doctor_details = doctor_details9;
        } else if (title.compareTo("儿科医生") == 0) {
            doctor_details = doctor_details10;
        } else if (title.compareTo("妇科医生") == 0) {
            doctor_details = doctor_details11;
        } else if (title.compareTo("骨科医生") == 0) {
            doctor_details = doctor_details12;
        } else if (title.compareTo("消化科医生") == 0) {
            doctor_details = doctor_details13;
        } else if (title.compareTo("内分泌科医生") == 0) {
            doctor_details = doctor_details14;
        }

        // 返回上一个页面
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "咨询费:" + doctor_details[i][4] + "元");
            list.add(item);
        }
        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        ListView listView = findViewById(R.id.listViewDD);
        listView.setAdapter(sa);

        // 点击某一个行跳转到某个医生的详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[position][0]);
                it.putExtra("text3", doctor_details[position][1]);
                it.putExtra("text4", doctor_details[position][3]);
                it.putExtra("text5", doctor_details[position][4]);
                startActivity(it);
            }
        });
    }
}