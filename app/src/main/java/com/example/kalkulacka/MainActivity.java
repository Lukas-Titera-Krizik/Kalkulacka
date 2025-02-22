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
            double number1 = 0;
            double number2 = 0;

            String operand = spinnerOperation.getSelectedItem().toString();

            try{

                //vezme celý edittext a uloží ho do input1
                EditText input1 = (EditText) findViewById(R.id.editTextNumberDecimal);
                //z edittextu v input1 vezme jeho obsah a prevedeho do double, ten se ulozi do number1
                number1 = Double.parseDouble(input1.getText().toString());

            } catch (Exception e) {

                TextView textView = (TextView) findViewById(R.id.textView);
                CharSequence text = "Zadejte obě čísla >:(";
                if(operand.equals("!")) text = "Stačí zadat pouze první číslo :)";
                textView.setText(String.valueOf(text));
                return;

            }

            if(!operand.equals("!")){

                try{

                    //vezme celý edittext a uloží ho do input2
                    EditText input2 = (EditText) findViewById(R.id.editTextNumberDecimal2);
                    //z edittextu v input2 vezme jeho obsah a prevedeho do double, ten se ulozi do number2
                    number2 = Double.parseDouble(input2.getText().toString());;

                } catch (Exception e) {

                    TextView textView = (TextView) findViewById(R.id.textView);
                    CharSequence text = "Zadejte obě čísla >:(";
                    textView.setText(String.valueOf(text));
                    return;

                }

            }

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

                    if(number2 == 0){

                        TextView textView = (TextView) findViewById(R.id.textView);
                        CharSequence text = "Nelze dělit nulou :(";
                        textView.setText(String.valueOf(text));
                        return;

                    }else{
                        result = number1 / number2;
                    }

                    break;
                }
                case "%":{
                    result = number1 % number2;
                    break;
                }
                case "ⁿ√":{


                    if (number1 == 0) {
                        TextView textView = (TextView) findViewById(R.id.textView);
                        CharSequence text = "Nultá odmocnina neexistuje :|";
                        textView.setText(String.valueOf(text));
                        return;
                    }else if(number2 < 0){
                        TextView textView = (TextView) findViewById(R.id.textView);
                        CharSequence text = "Neumím imaginární čísla :(";
                        textView.setText(String.valueOf(text));
                        return;
                    }else{
                        //Vrátí ntou (number2) odmocninu z number1
                        result = Math.pow(number2, 1/number1);
                    }

                    break;
                }
                case "^":{
                    //Vrátí mocninu number1 na number2
                    result = Math.pow(number1, number2);
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