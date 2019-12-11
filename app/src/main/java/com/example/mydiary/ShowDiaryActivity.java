package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowDiaryActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private String time;
    private SQLiteDatabase database;
    private Button modifyButton;
    private Button deleteButton;
    private  Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);

        toolBar=findViewById(R.id.showToolBar);
        toolBar.setTitle("查看日记");
        setSupportActionBar(toolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        MyApplication application = (MyApplication)this.getApplication();
        database = application.getDatabase();

        title = findViewById(R.id.show_diary_title);
        content = findViewById(R.id.show_diary_content);
        modifyButton = findViewById(R.id.show_diary_modify_button);
        deleteButton = findViewById(R.id.show_diary_delete_button);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        time = bundle.getString("time");

        Cursor cursor = database.query("diarys", null, "date=?", new String[]{time}, null, null, null);
        cursor.moveToFirst();
        title.setText(cursor.getString(1));
        content.setText(cursor.getString(3));

        modifyButton.setOnClickListener(v->{
            ContentValues values=new ContentValues();
            values.put("title",title.getText().toString());
            values.put("content", content.getText().toString());
            database.update("diarys",values,"date=?",new String[]{time});

            Toast.makeText(getBaseContext(),"日记已修改", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(ShowDiaryActivity.this, MainActivity.class);
            startActivity(intent1);


        });

        deleteButton.setOnClickListener(v->{
            database.delete("diarys","date=?",new String[]{time});
            Toast.makeText(getBaseContext(),"日记已删除", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(ShowDiaryActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }
}
