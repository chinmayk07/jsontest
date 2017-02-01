package test.example.com.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStates extends AppCompatActivity {

    EditText add_name, add_abbre, add_cap, add_large, add_lang, add_cm;
    Button addStates;
    private String country;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_states);

        addStates = (Button) findViewById(R.id.button_addstates);

        dbManager = new DbManager(this);
        dbManager.open();

        String s = getIntent().getStringExtra("COUNTRY");
        Log.d("TAG", s);

        add_name = (EditText) findViewById(R.id.state_name);
        add_abbre = (EditText) findViewById(R.id.state_abbr);
        add_cap = (EditText) findViewById(R.id.state_capital);
        add_large = (EditText) findViewById(R.id.state_large);
        add_lang = (EditText) findViewById(R.id.state_lang);
        add_cm = (EditText) findViewById(R.id.state_cm);

        country = s;

        addStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(add_cm.toString()=="" && add_name.toString()=="" && add_abbre.toString()=="" &&  add_cap.toString()=="" && add_large.toString()=="" && add_lang.toString()=="") {
                    Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbManager.insert(country,add_name.toString(),add_abbre.toString(),add_cap.toString(),add_large.toString(),add_lang.toString(),add_cm.toString());
                    /*dbManager.close();*/
                }
            }
        });
    }
}
