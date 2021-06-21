package com.yogeeswar.converter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ConverterUtils con = new ConverterUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclickBtn(View view) {
        Spinner from_opt = (Spinner) findViewById(R.id.from_opt);
        Spinner to_opt = (Spinner) findViewById(R.id.to_opt);

        String from = String.valueOf(from_opt.getSelectedItem());
        String to = String.valueOf(to_opt.getSelectedItem());

        EditText value = (EditText) findViewById(R.id.input);
        TextView output = (TextView) findViewById(R.id.ans);
        String data = value.getText().toString();
        String out;
        if(!from.equals(to)) {
            if(from.equals("decimal") && to.equals("binary"))
                out = con.dectobin(Integer.parseInt(data));
            else if(from.equals("decimal") && to.equals("hexadecimal"))
                out = con.dectohex(Integer.parseInt(data));
            else if(from.equals("binary") && to.equals("decimal"))
                out = con.bintodec(data);
            else if(from.equals("binary") && to.equals("hexadecimal"))
                out = con.bintohex(data);
            else if(from.equals("hexadecimal") && to.equals("decimal"))
                out = con.hextodec(data);
            else
               out = con.hextobin(data);
            output.setText(out);
        }
        else
            output.setText("select from and to properly");
    }
}