package com.air.restroom;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Add_info extends Activity {

    EditText label;
    RadioButton opened;
    CheckBox m, w, m_d, w_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_info);

    }

    public void okClicked(View view) {

        int a = 0;

        label = (EditText)findViewById(R.id.label);
        opened = (RadioButton) findViewById(R.id.opened);
//        locked = (RadioButton) findViewById(R.id.locked);
        m = (CheckBox)findViewById(R.id.man);
        w = (CheckBox)findViewById(R.id.woman);
        m_d = (CheckBox)findViewById(R.id.man_disabled);
        w_d = (CheckBox)findViewById(R.id.woman_disabled);

        if ( opened.isChecked() )
            a = 1;

        SharedPreferences info = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor = info.edit();

        editor.putString("label", label.getText().toString());
        editor.putInt("isOpen", a);

        if ( m.isChecked() )
            editor.putInt("man", 1);

        if ( w.isChecked() )
            editor.putInt("woman", 1);

        if ( m_d.isChecked() )
            editor.putInt("man_disabled", 1);

        if ( w_d.isChecked() )
            editor.putInt("woman_disabled", 1);


        editor.commit();

        Toast.makeText(getApplicationContext(), "저장완료!", Toast.LENGTH_SHORT).show();

        Intent _intent = getIntent();
        _intent.putExtra("isEnd", "y");
        setResult(RESULT_OK,_intent);
        finish();
    }

    public void cancelClicked(View view) {

        Intent _intent = getIntent();
        _intent.putExtra("isEnd", "n");
        setResult(RESULT_OK,_intent);
        finish();
    }
}
