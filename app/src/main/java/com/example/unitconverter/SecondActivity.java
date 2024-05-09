package com.example.unitconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    EditText input;
    Spinner unit;
    TextView C, F, K;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input = findViewById(R.id.input);
        unit = findViewById(R.id.unit);
        C = findViewById(R.id.C);
        F = findViewById(R.id.F);
        K = findViewById(R.id.K);

        String[] arr = {"°C", "°F", "K"};
        unit.setAdapter(new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1, arr));

        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                update();
            }
        });
    }

    private void update(){
        if(!input.getText().toString().isEmpty() && !unit.getSelectedItem().toString().isEmpty()){
            double in = Double.parseDouble(input.getText().toString());
            switch(unit.getSelectedItem().toString()){
                case "°C":
                    setC(in);
                    break;

                case "°F":
                    setC((in - 32) * 5/9 );
                    break;

                case "K":
                    setC(in - 273.15);
                    break;
            }
        }
    }

    private void setC(double C_in){
        C.setText(String.valueOf(C_in));
        F.setText(String.valueOf(C_in * 9/5 + 32));
        K.setText(String.valueOf(C_in + 273.15));
    }
}