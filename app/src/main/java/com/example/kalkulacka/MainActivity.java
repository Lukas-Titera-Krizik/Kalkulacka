package com.example.kalkulacka;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerOperation = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.operands,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerOperation.setAdapter(adapter);

    }

    //Funkce pro provedení výpočtu
    public void calculate(View view){

        try{

            double result;

            String operand = spinnerOperation.getSelectedItem().toString();

            //vezme celý edittext a uloží ho do input1
            EditText input1 = (EditText) findViewById(R.id.editTextNumberDecimal);
            //z edittextu v input1 vezme jeho obsah a prevedeho do double, ten se ulozi do number1
            double number1 = Double.parseDouble(input1.getText().toString());

            //vezme celý edittext a uloží ho do input2
            EditText input2 = (EditText) findViewById(R.id.editTextNumberDecimal2);
            //z edittextu v input2 vezme jeho obsah a prevedeho do double, ten se ulozi do number2
            double number2 = Double.parseDouble(input2.getText().toString());

            switch(operand){
                case "+":{
                    result = number1 + number2;
                    break;
                }
                case "-":{
                    result = number1 - number2;
                    break;
                }
                case "*":{
                    result = number1 * number2;
                    break;
                }
                case "/":{
                    result = number1 / number2;
                    break;
                }
                case "%":{
                    result = number1 % number2;
                    break;
                }
                case "ⁿ√":{
                    //Vrátí ntou (number2) odmocninu z number1
                    result = Math.pow(number1, 1/number2);
                    break;
                }
                case "^":{
                    //Vrátí mocninu number1 na number2
                    result = Math.pow(number1, number2);
                /*result = number1;
                for(int i = 1; i <= number2; i++){
                    result *= number1;
                }*/
                    break;
                }
                case "!":{
                    result = 1;
                    for(int i = 1; i <= number1; i++){
                        result *= i;
                    }
                    break;
                }
                default:{
                    result = 0;
                    break;
                }
            }

            //Vloží výsledek výpočtu do textview v aplikaci
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(String.valueOf(result));

        } catch (Exception e) {
            //V případě chyby vypíše "Syntax error"
            TextView textView = (TextView) findViewById(R.id.textView);
            CharSequence text = "Syntax error";
            textView.setText(String.valueOf(text));
        }



    }
}