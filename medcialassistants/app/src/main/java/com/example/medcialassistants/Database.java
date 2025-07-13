package com.example.medcialassistants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // users表 用户
            String qry1 = "create table users(username text, email text, password text)";
            db.execSQL(qry1);

            // cart表 购物车
            String qry2 = "create table cart(username text, product text, price float, otype text)";
            db.execSQL(qry2);

            // order表 订单
            String qry3 = "create table orderplace(username text, fullname text, address text, contactno text, pincode int, date text, time text, amount float, otype text)";
            db.execSQL(qry3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public boolean addCart(String username, String product, float price, String otype) {
        try {
            // 检查表是否存在
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='cart'", null);
            boolean tableExists = cursor.moveToFirst();
            cursor.close();
            
            if (!tableExists) {
                // 如果表不存在，创建表
                String qry = "create table cart(username text, product text, price float, otype text)";
                db.execSQL(qry);
            }
            
            ContentValues cv = new ContentValues();
            cv.put("username", username);
            cv.put("product", product);
            cv.put("price", price);
            cv.put("otype", otype);
            
            long result = db.insert("cart", null, cv);
            db.close();
            
            System.out.println("数据库操作结果: " + result + ", 用户: " + username + ", 产品: " + product + ", 价格: " + price + ", 类型: " + otype);
            
            return result != -1;
        } catch (Exception e) {
            System.out.println("数据库操作异常: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public int checkCart(String username, String product) {
        int result = 0;
        try {
            String[] str = new String[2];
            str[0] = username;
            str[1] = product;
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
            if (c.moveToFirst()) {
                result = 1;
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void removeCart(String username, String otype) {
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and otype=?", str);
        db.close();
    }

    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if (c.moveToFirst()) {
            do {
                try {
                    // 使用列名来获取数据，避免列索引问题
                    int productIndex = c.getColumnIndex("product");
                    int priceIndex = c.getColumnIndex("price");
                    
                    if (productIndex >= 0 && priceIndex >= 0) {
                        String product = c.getString(productIndex);
                        String price = c.getString(priceIndex);
                        arr.add(product + "$" + price);
                    } else {
                        // 如果列名获取失败，尝试使用列索引
                        String product = c.getString(1); // product列
                        String price = c.getString(2);   // price列
                        arr.add(product + "$" + price);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // 最后的备用方案
                    try {
                        String product = c.getString(1); // product列
                        String price = c.getString(2);   // price列
                        arr.add(product + "$" + price);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    public ArrayList<String> getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + // fullname
                        c.getString(2) + "$" + // address
                        c.getString(3) + "$" + // contactno
                        c.getString(4) + "$" + // pincode
                        c.getString(5) + "$" + // date
                        c.getString(6) + "$" + // time
                        c.getString(7) + "$" + // amount
                        c.getString(8) // otype
                );
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result = 0;
        String[] str = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address=? and contactno=? and date=? and time=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

}