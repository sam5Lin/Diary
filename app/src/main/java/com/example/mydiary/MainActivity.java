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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        });

        initTools();
        initDiarys();
    }



    private void deleteDiary(int position){
        //database.delete("diarys","title=?",new String[]{"难过的一天"});
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

        //设置Toolbar菜单点击事件
        mtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.one: {
                        Toast.makeText(MainActivity.this, "我是1", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.two: {
                        Toast.makeText(MainActivity.this, "我是2", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case R.id.three: {
                        Toast.makeText(MainActivity.this, "我是3", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    default:break;
                }
                return true;
            }
        });
        setSupportActionBar(mtoolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
