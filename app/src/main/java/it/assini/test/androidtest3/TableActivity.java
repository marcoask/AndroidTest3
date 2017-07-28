package it.assini.test.androidtest3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity {

    EditText peso1;
    EditText txt1;
    EditText peso2;
    EditText txt2;
    TextView txtResult;
    Button btnCalcola;

    double num1,num2,num3,num4,sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        peso1 = (EditText)findViewById(R.id.peso1);
        txt1 = (EditText)findViewById(R.id.txt1);
        peso2 = (EditText)findViewById(R.id.peso2);
        txt2 = (EditText)findViewById(R.id.txt2);
        txtResult = (TextView)findViewById(R.id.txtResult);

        btnCalcola = (Button)findViewById(R.id.btnCalcola);

        btnCalcola.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = Double.parseDouble(peso1.getText().toString());
                num2 = Double.parseDouble(txt1.getText().toString());
                num3 = Double.parseDouble(peso2.getText().toString());
                num4 = Double.parseDouble(txt2.getText().toString());
                sum = ((num1*num2) + (num3*num4)) / (num1+num3);
                txtResult.setText(Double.toString(sum));
            }
        });

    }

    private double NSEWtoDec(String nsew, String X){

        double dec = 0;

        String ns_ew;

        if ("N".equalsIgnoreCase(X) || "S".equalsIgnoreCase(X)){
            ns_ew=nsew.substring(1,12);
        } else if ("E".equalsIgnoreCase(X) || "W".equalsIgnoreCase(X)) {
            ns_ew=nsew.substring(13,24);
        }


        return dec;

    }

    public static void main (String[] args ) {

        System.out.println("***************main***************");

        String nsew ="45°30'22.4\"N 10°19'52.5\"E";

        System.out.println(nsew);
        String substring = nsew.substring(0, 12);
        System.out.println("["+ substring +"]");
        System.out.println("["+nsew.substring(13,25)+"]");

        String replace = substring.replace(".",",");
        System.out.println(replace);
    }
}
