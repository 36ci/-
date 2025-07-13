package com.example.medcialassistants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

public class FindDoctorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_find_doctor);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "页面加载失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 返回Home页面
        CardView exit = null;
        try {
            exit = findViewById(R.id.cardFDBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (exit != null) {
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(FindDoctorActivity.this, "返回失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // 接下来都是点击某个医生进入医生详情页面，查看指定医生的详细信息
        // 进入相关详情页面时，会携带一个各个页面的title
        CardView familyPhysician = null;
        try {
            familyPhysician = findViewById(R.id.cardFDFamilyPhysician); // 家庭医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (familyPhysician != null) {
            familyPhysician.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                        it.putExtra("title", "家庭医生");
                        startActivity(it);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(FindDoctorActivity.this, "跳转失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        CardView dietician = null;
        try {
            dietician = findViewById(R.id.cardFDDietician); // 营养师
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dietician != null) {
            dietician.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                        it.putExtra("title", "营养师");
                        startActivity(it);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(FindDoctorActivity.this, "跳转失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        CardView dentist = null;
        try {
            dentist = findViewById(R.id.cardFDDentist); // 牙医
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dentist != null) {
            dentist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                        it.putExtra("title", "牙医");
                        startActivity(it);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(FindDoctorActivity.this, "跳转失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        CardView surgeon = null;
        try {
            surgeon = findViewById(R.id.cardFDSurgeon); // 外科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (surgeon != null) {
            surgeon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                        it.putExtra("title", "外科医生");
                        startActivity(it);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(FindDoctorActivity.this, "跳转失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        CardView cardiologists = null;
        try {
            cardiologists = findViewById(R.id.cardFDCardiologists); // 心脏病专家
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cardiologists != null) {
            cardiologists.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                        it.putExtra("title", "心脏病专家");
                        startActivity(it);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(FindDoctorActivity.this, "跳转失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // 新增医生类别
        CardView dermatologist = null;
        try {
            dermatologist = findViewById(R.id.cardFDDermatologist); // 皮肤科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dermatologist != null) {
            dermatologist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "皮肤科医生");
                    startActivity(it);
                }
            });
        }

        CardView ophthalmologist = null;
        try {
            ophthalmologist = findViewById(R.id.cardFDOphthalmologist); // 眼科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ophthalmologist != null) {
            ophthalmologist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "眼科医生");
                    startActivity(it);
                }
            });
        }

        CardView neurologist = null;
        try {
            neurologist = findViewById(R.id.cardFDNeurologist); // 神经科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (neurologist != null) {
            neurologist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "神经科医生");
                    startActivity(it);
                }
            });
        }

        CardView psychiatrist = null;
        try {
            psychiatrist = findViewById(R.id.cardFDPsychiatrist); // 精神科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (psychiatrist != null) {
            psychiatrist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "精神科医生");
                    startActivity(it);
                }
            });
        }

        CardView pediatrician = null;
        try {
            pediatrician = findViewById(R.id.cardFDPediatrician); // 儿科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pediatrician != null) {
            pediatrician.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "儿科医生");
                    startActivity(it);
                }
            });
        }

        CardView gynecologist = null;
        try {
            gynecologist = findViewById(R.id.cardFDGynecologist); // 妇科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gynecologist != null) {
            gynecologist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "妇科医生");
                    startActivity(it);
                }
            });
        }

        CardView orthopedist = null;
        try {
            orthopedist = findViewById(R.id.cardFDOrthopedist); // 骨科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (orthopedist != null) {
            orthopedist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "骨科医生");
                    startActivity(it);
                }
            });
        }

        CardView gastroenterologist = null;
        try {
            gastroenterologist = findViewById(R.id.cardFDGastroenterologist); // 消化科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gastroenterologist != null) {
            gastroenterologist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "消化科医生");
                    startActivity(it);
                }
            });
        }

        CardView endocrinologist = null;
        try {
            endocrinologist = findViewById(R.id.cardFDEndocrinologist); // 内分泌科医生
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (endocrinologist != null) {
            endocrinologist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                    it.putExtra("title", "内分泌科医生");
                    startActivity(it);
                }
            });
        }
    }
}