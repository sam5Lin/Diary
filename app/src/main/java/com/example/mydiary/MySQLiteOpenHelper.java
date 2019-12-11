package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mydiary.entity.Diary;



/*
 * @author sam
 * @date 19-12-8 下午6:27
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String name = "Diary.db";
    private static final int version = 1;

    //数据库方法
    public MySQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    //创建数据库名
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table diarys(_id integer primary key autoincrement,title varchar(20),date varchar(30), content varchar(300))");

    }

    //数据库方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
