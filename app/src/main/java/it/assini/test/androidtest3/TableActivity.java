package it.assini.test.androidtest3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    //EditText peso1;
    //EditText txt1;
    //EditText peso2;
    //EditText txt2;

    TextView txtResult;

    Button btnCalcola;
    TableLayout tl;

    //double peso1_double, txt1_double, peso2_double, txt2_double,sum;
    //String txt1_string, txt2_string;
    String result;

    ArrayList<String> latLngArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //peso1 = (EditText)findViewById(R.id.peso1);
        //txt1 = (EditText)findViewById(R.id.txt1);
        //peso2 = (EditText)findViewById(R.id.peso2);
        //txt2 = (EditText)findViewById(R.id.txt2);

        txtResult = (TextView)findViewById(R.id.txtResult);
        btnCalcola = (Button)findViewById(R.id.btnCalcola);

        tl = (TableLayout)findViewById(R.id.tl);

        btnCalcola.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //peso1_double = Double.parseDouble(peso1.getText().toString());
                //txt1_double = Double.parseDouble(txt1.getText().toString());
                //txt1_string = txt1.getText().toString();

                //peso2_double = Double.parseDouble(peso2.getText().toString());
                //txt2_double = Double.parseDouble(txt2.getText().toString());
                //txt2_string = txt2.getText().toString();
                
                //sum = ((peso1_double * txt1_double) + (peso2_double * txt2_double)) / (peso1_double + peso2_double);

                int rowCount = tl.getChildCount();
                int columCount = ((TableRow)tl.getChildAt(1)).getChildCount();

                ArrayList<String> pesoArray = new ArrayList<>();
                for (int i = 1; i < rowCount-1; i++) {
                        EditText et = (EditText)((TableRow)tl.getChildAt(i)).getChildAt(1);
                        pesoArray.add(et.getText().toString());
                }

                double pesiSommatoria=0;
                for (int j = 0; j<pesoArray.size(); j++){
                    pesiSommatoria=pesiSommatoria+Double.parseDouble(pesoArray.get(j));
                }

                ArrayList<String> txtArray = new ArrayList<>();
                for (int i = 1; i < rowCount-1; i++) {
                    EditText et = (EditText)((TableRow)tl.getChildAt(i)).getChildAt(2);
                    txtArray.add(et.getText().toString());

                }

                double decNS_sommatoria=0;
                double decEW_sommatoria=0;
                for (int k = 0; k<txtArray.size(); k++){
                    decNS_sommatoria = decNS_sommatoria + (NSEWtoDec(txtArray.get(k), "N")*Double.parseDouble(pesoArray.get(k)));
                    decEW_sommatoria = decEW_sommatoria + (NSEWtoDec(txtArray.get(k), "E")*Double.parseDouble(pesoArray.get(k)));
                }


                latLngArrayList.add("45.50622222, 10.33125");
                latLngArrayList.add("45.54030556, 10.31736");


                //double txt1_decNS = NSEWtoDec(txt1_string, "N");
                //double txt1_decEW = NSEWtoDec(txt1_string, "E");

                //double txt2_decNS = NSEWtoDec(txt2_string, "N");
                //double txt2_decEW = NSEWtoDec(txt2_string, "E");

                //double decNS_pesato = (txt1_decNS*peso1_double + txt2_decNS*peso2_double)/(pesiSommatoria);
                //double decEW_pesato = (txt1_decEW*peso1_double + txt2_decEW*peso2_double)/(pesiSommatoria);

                double decNS_pesato = decNS_sommatoria/(pesiSommatoria);
                double decEW_pesato = decEW_sommatoria/(pesiSommatoria);

                result = DectoNSEW(decNS_pesato,"N") +" " + DectoNSEW(decEW_pesato,"E");
                
                //txtResult.setText(Double.toString(sum));
                txtResult.setText(result);

                Context context = getApplicationContext();
                CharSequence text = "row: "+rowCount+"; col: "+columCount+"; "+pesiSommatoria;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

    }

    private double NSEWtoDec(String nsew, String X){
        String ns_ew ="";

        if ("N".equalsIgnoreCase(X) || "S".equalsIgnoreCase(X)){
            ns_ew=nsew.substring(0,12);
        } else if ("E".equalsIgnoreCase(X) || "W".equalsIgnoreCase(X)) {
            ns_ew=nsew.substring(13,25);
        }

        String replace = ns_ew.replace(".",".");

        int deg = Integer.parseInt(replace.substring(0,2));

        double min = Double.parseDouble(replace.substring(3,5));

        double sec =  Double.parseDouble(replace.substring(6,10));
        //String sec =  (replace.substring(6,10));

        return deg + ((min*60)+sec)/3600;

    }

    private String DectoNSEW(double dec, String X){
        int deg = parteIntera(dec);

        int min = parteIntera((dec-deg)*60);

        double sec =  round((dec-deg-((double)min/60))*3600,1);

        return (deg+"°"+min+"'"+sec+"''"+X);

    }



    private static int parteIntera(double doubleNumber) {
        String doubleString = String.valueOf(doubleNumber);
        int index = doubleString.indexOf(".");
        return (Integer.parseInt(doubleString.substring(0, index)));

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /** Called when the user taps the button */
    public void openMapsWithPoints(View view) {

        Intent intent=new Intent(this, MapsActivity.class);

        Bundle b=new Bundle();
        b.putStringArray("ARRAY", latLngArrayList.toArray(new String[latLngArrayList.size()]));
        intent.putExtras(b);

        intent.putExtra("POINTS", "Test Extra");

        startActivity(intent);
    }

    public static void main (String[] args ) {

        System.out.println("***************main***************");

        String nsew ="45°30'22.4\"N 10°19'52.5\"E";

        System.out.println(nsew);
        String substring = nsew.substring(0, 12);
        System.out.println("["+ substring +"]");

        System.out.println("["+nsew.substring(13,25)+"]");

        String replace = substring.replace(".",".");
        System.out.println(replace);

        int deg = Integer.parseInt(replace.substring(0,2));
        System.out.println("["+deg+"]");

        double min = Double.parseDouble(replace.substring(3,5));
        System.out.println("["+min+"]");

        double sec =  Double.parseDouble(replace.substring(6,10));
        //String sec =  (replace.substring(6,10));
        System.out.println("["+sec+"]");

        System.out.println("["+deg+"]["+min+"]["+sec+"]");

        double dec = deg + ((min*60)+sec)/3600;
        System.out.println("["+dec+"]");

        System.out.println("**********");

        int deg2 = parteIntera(dec);
        System.out.println("["+deg2+"]");

        int min2 = parteIntera((dec-deg2)*60);
        System.out.println("["+min2+"]");

        double sec2 =  round((dec-deg2-((double)min2/60))*3600,1);
        System.out.println("["+sec2+"]");

        String N = deg2+"°"+min2+"'"+sec2+"''N";
        System.out.println("["+N+"]");
    }

}
