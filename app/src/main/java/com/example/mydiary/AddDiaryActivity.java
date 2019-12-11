package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydiary.entity.Diary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDiaryActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private Button btn;
    private EditText title;
    private EditText content;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        MyApplication application = (MyApplication)this.getApplication();
        database = application.getDatabase();
        toolBar = findViewById(R.id.writeToolBar);
        toolBar.setTitle("写日记");
        setSupportActionBar(toolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        btn = findViewById(R.id.add_diary_button);
        title = findViewById(R.id.add_diary_title);
        content = findViewById(R.id.add_diary_content);

        btn.setOnClickListener(v -> {
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());


            String title_str = title.getText().toString();
            String content_str = content.getText().toString();
            String time = simpleDateFormat.format(date);

            if(title_str.length() == 0 || " ".equals(title_str) || content_str.length() == 0 || " ".equals(content_str)){
                Toast.makeText(getBaseContext(),"日记内容还没有写完哦", Toast.LENGTH_LONG).show();
            }
            else{
                Diary diary = new Diary(title_str, time, content_str);

                addDiary(diary);
                Toast.makeText(getBaseContext(),"日记已生成", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddDiaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void addDiary(Diary diary){
        ContentValues values=new ContentValues();
        values.put("title",diary.getTitle());
        values.put("date",diary.getDate());
        values.put("content",diary.getContent());
        database.insert("diarys",null,values);
    }
}
