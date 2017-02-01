package test.example.com.jsontest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditStates extends AppCompatActivity {

    private DbManager dbManager;
    EditText add_name, add_abbre, add_cap, add_large, add_lang, add_cm;
    TextView id;
    Button updateStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_states);

        updateStates = (Button) findViewById(R.id.button_updatestates);

        id = (TextView) findViewById(R.id.state_id);
        add_name = (EditText) findViewById(R.id.state_name);
        add_abbre = (EditText) findViewById(R.id.state_abbr);
        add_cap = (EditText) findViewById(R.id.state_capital);
        add_large = (EditText) findViewById(R.id.state_large);
        add_lang = (EditText) findViewById(R.id.state_lang);
        add_cm = (EditText) findViewById(R.id.state_cm);

        dbManager = new DbManager(this);
        dbManager.open();

        String s = getIntent().getStringExtra("NAMESTATE");
        Log.d("TAG", s);

        Cursor returnednickname = dbManager.returnickname(s);

        returnednickname.moveToFirst();

        id.setText(returnednickname.getString(0));
        add_name.setText(returnednickname.getString(2));
        add_abbre.setText(returnednickname.getString(3));
        add_cap.setText(returnednickname.getString(4));
        add_large.setText(returnednickname.getString(5));
        add_lang.setText(returnednickname.getString(6));
        add_cm.setText(returnednickname.getString(7));

        final String country_nam = returnednickname.getString(1);
        final String Stste_nam = returnednickname.getString(2);
        final String ststeabbr = returnednickname.getString(3);
        final String ststecapi = returnednickname.getString(4);

        final String ststelarge = add_large.getText().toString();
        final String ststelang = add_lang.getText().toString();
        final String ststecm = add_cm.getText().toString();



        final int idState = Integer.parseInt(returnednickname.getString(0));

        Log.d("COUNTRY", country_nam);
        Log.d("MYINT", "value: " + idState);

        updateStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.update(idState ,country_nam,Stste_nam,ststeabbr,ststecapi,ststelarge,ststelang,ststecm);
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT);
                Intent intent = new Intent(EditStates.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
