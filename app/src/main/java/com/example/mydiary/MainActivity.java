package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydiary.entity.Diary;
import com.example.mydiary.entity.DiaryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Diary> diaryList = new ArrayList<>();
    private FloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.addDiary);
        btn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,AddDiaryActivity.class);
            startActivity(intent);
        });

        initTools();

        initDiarys();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.diary_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DiaryAdapter adapter = new DiaryAdapter(diaryList);
        recyclerView.setAdapter(adapter);

    }

    private void initDiarys() {
        // HH:mm:ss
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());

        Diary diary1 = new Diary("开心的一天",
                simpleDateFormat.format(date),
                "今天是个好日子，美丽的一天又要开始咯，我和同学们出去打球咯");

        Diary diary2 = new Diary("很开心的一天",
                simpleDateFormat.format(date),
                "今天是个好日子，美丽的一天又要开始咯，我和同学们出去打球咯");

        Diary diary3 = new Diary("超级开心的一天",
                simpleDateFormat.format(date),
                "今天是个好日子，美丽的一天又要开始咯，我和同学们出去打球咯");

        diaryList.add(diary1);
        diaryList.add(diary2);
        diaryList.add(diary3);

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
