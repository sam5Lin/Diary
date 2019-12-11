package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddDiaryActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private Button btn;
    private EditText title;
    private EditText content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        toolBar = findViewById(R.id.writeToolBar);
        toolBar.setTitle("写日记");
        setSupportActionBar(toolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        btn = findViewById(R.id.add_diary_button);
        title = findViewById(R.id.add_diary_title);
        content = findViewById(R.id.add_diary_content);

        btn.setOnClickListener(v -> {
            String title_str = title.getText().toString();
            String content_str = content.getText().toString();
            String time = 
        });
    }
}
