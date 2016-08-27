package com.air.restroom;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Popup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

    }

    public void yesClicked(View view) {

        Intent _intent = getIntent();
        _intent.putExtra("isEnd", "y");
        setResult(RESULT_OK,_intent);
        finish();
    }

    public void noClicked(View view) {

        Intent _intent = getIntent();
        _intent.putExtra("isEnd", "n");
        setResult(RESULT_OK,_intent);
        finish();
    }
}
