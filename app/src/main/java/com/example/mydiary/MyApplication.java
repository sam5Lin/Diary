package com.example.mydiary;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;



/*
 * @author sam
 * @date 19-12-11 下午2:44
 */

public class MyApplication extends Application {
    private NotificationManager manager ;//实例化通知
    private MySQLiteOpenHelper helper ;//实例化数据库
    private SQLiteDatabase database ;//获取一个可读写的数据库对象


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        super.onCreate();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        helper = new MySQLiteOpenHelper(this);
        database = helper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }
}
