package de.kurtfromqlb.nichtraucherersparnis;

/**
 * Created by Kurt Balle on 21.03.2018.
 */

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class edit extends AppCompatActivity implements View.OnClickListener{

    Calendar myCalendar = Calendar.getInstance();
    private static final String SHARED_PREF_NAME = "mysharedpref";
    private static final String KEY_NAME = "keyname";
    private int mYear, mMonth, mDay;
    private EditText datum;
    EditText etpartperday;
    TextView tvpartperdayoutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        etpartperday = findViewById(R.id.etpartperday);
        tvpartperdayoutput = findViewById(R.id.tvpartperdayoutput);
        datum = (EditText) findViewById(R.id.etdate);
        datum.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if (v == datum) {
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener()

                    { @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) { datum.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year); } }, mYear, mMonth, mDay);

            dpd.show();

        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveName();
                displayName();
            }
        });
    }
    private void saveName() {
        String name = etpartperday.getText().toString();

        if (name.isEmpty()) {
            etpartperday.setError("Anzahl eintragen");
            etpartperday.requestFocus();
            return;
        }
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(KEY_NAME, name);
        editor.apply();

        etpartperday.setText("");
    }
    private void displayName() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sp.getString(KEY_NAME, null);
        if (name != null) {
            tvpartperdayoutput.setText( name);
        }
    }

}