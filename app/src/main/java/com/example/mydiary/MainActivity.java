package com.example.mydiary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import android.widget.Toast;

import com.example.mydiary.entity.Diary;
import com.example.mydiary.entity.DiaryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Diary> diaryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton btn;
    private SQLiteDatabase database;//数据库
    private LinearLayout diary_empty;
    static private  Intent musicIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 启动服务播放背景音乐
        musicIntent = new Intent(MainActivity.this, musicService.class);
        String action = musicService.ACTION_MUSIC;
        // 设置action
        musicIntent.setAction(action);
        startService(musicIntent);



        MyApplication application = (MyApplication)this.getApplication();
        database = application.getDatabase();

        diary_empty = findViewById(R.id.diary_empty);

        recyclerView = (RecyclerView) findViewById(R.id.diary_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        btn = findViewById(R.id.addDiary);
        btn.setOnClickListener(v->{
            Intent intent = new Intent(this,AddDiaryActivity.class);
            startActivity(intent);

            stopService(musicIntent);
        });

        initTools();
        initDiarys();
    }

    static public Intent getMusicIntent() {
        return musicIntent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicIntent != null){
            // 对于intentService，这一步可能是多余的
            stopService(musicIntent);
        }
    }


    private void initDiarys() {

        Cursor cursor = database.query("diarys", null, null, null, null, null, "_id desc");//创建查询，返回结果集
        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Diary diary = new Diary(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                diaryList.add(diary);//取出所有学生姓名，填充到列表
            }while (cursor.moveToNext());

        }


        DiaryAdapter adapter = new DiaryAdapter(this, diaryList);

        recyclerView.setAdapter(adapter);

        if(adapter.getItemCount() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            diary_empty.setVisibility(View.GONE);

        }
        else{
            recyclerView.setVisibility(View.GONE);
            diary_empty.setVisibility(View.VISIBLE);

        }


    }


    private void initTools() {
        Toolbar mtoolbar=findViewById(R.id.toolbar);

        setSupportActionBar(mtoolbar);
    }




}
