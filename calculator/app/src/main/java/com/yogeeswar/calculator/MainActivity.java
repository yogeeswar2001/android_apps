package com.yogeeswar.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClinckdatabtn (View view) {
        Button data = (Button) view;
        TextView exp = (TextView) findViewById(R.id.exp);
        exp.setText(exp.getText() + data.getText().toString());
    }
    public void onClickclrbtn (View view) {
        TextView exp = (TextView) findViewById(R.id.exp);
        TextView ans = (TextView) findViewById(R.id.ans);
        exp.setText("");
        ans.setText("");
    }
    public void onClickansbtn (View view) {
        ExpEval e = new ExpEval();
        TextView exp = (TextView) findViewById(R.id.exp);
        TextView ans = (TextView) findViewById(R.id.ans);

        String data = e.cal_exp(exp.getText().toString());
        ans.setText(String.valueOf(data));
    }
    public void onClickdelbtn(View view) {
        TextView exp = (TextView) findViewById(R.id.exp);
        String data = exp.getText().toString();
        if(!data.equals(""))
            exp.setText(data.substring(0,data.length()-1));
    }
}