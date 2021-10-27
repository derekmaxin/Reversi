package com.example.dsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.dsapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.dsapp.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String name1 = editText.getText().toString();
        String name2 = editText2.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, name1);
        intent.putExtra(EXTRA_MESSAGE2, name2);
        startActivity(intent);
    }


}