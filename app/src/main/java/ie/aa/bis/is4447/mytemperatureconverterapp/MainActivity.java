package ie.aa.bis.is4447.mytemperatureconverterapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etTemp;
    private TextView tvOutput;
    private Spinner spUnitFrom, spUnitTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiating layout contents for later use
        etTemp = findViewById(R.id.etTemp);
        tvOutput = findViewById(R.id.tvOutput);
        Button btnConvertHere = findViewById(R.id.btnConvertHere);
        Button btnConvertSecondAct = findViewById(R.id.btnConvertSecondAct);

        //spinners - from & to
        spUnitFrom = findViewById(R.id.spUnitFrom);
        spUnitTo = findViewById(R.id.spUnitTo);

        //array adapter to read string array then populate the spinner
        ArrayAdapter adapterFrom = ArrayAdapter.createFromResource(this, R.array.units_from, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnitFrom.setAdapter(adapterFrom);

        ArrayAdapter adapterTo = ArrayAdapter.createFromResource(this, R.array.units_to, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnitTo.setAdapter(adapterTo);

        btnConvertHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etEntered = String.valueOf(etTemp.getText());
                Log.wtf("******LOG******", etEntered);
                double convertedVal;
                double finalVal;

                //check text box is not empty
                if(etEntered.isEmpty()){

                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }else{
                    double tempVal = Double.parseDouble(String.valueOf(etEntered.toString()));

                    //nested switch statements to incorporate converting from (outer switch) and converting to (inner switch)
                    switch (spUnitFrom.getSelectedItemPosition()){
                        case 0:
                            //unselected unit
                            Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            //case 1 = celsius
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    //case 0 is unselected unit
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    //call the convert method
                                    convertedVal = convertToFahrenheit(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    tvOutput.setText(etEntered + " in Celsius is: \n " + finalVal + " in Fahrenheit");
                                    break;
                                case 3:
                                case 4:
                                    //not allowing user to convert from temp to distance
                                    Toast.makeText(MainActivity.this,"Can't convert temperature to distance", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;

                        case 2:
                            //fahrenheit
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    convertedVal = convertToCelsius(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    tvOutput.setText(etEntered + " in Fahrenheit is: \n " + finalVal + " in Celsius");
                                    break;
                                case 2:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                case 4:
                                    Toast.makeText(MainActivity.this,"Can't convert temperature to distance", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case 3:
                            //metres
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                case 2:
                                    Toast.makeText(MainActivity.this,"Can't convert distance to temperature", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                                case 4:
                                    convertedVal = convertToFeet(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    tvOutput.setText(etEntered + " in Metres is: \n" +
                                            " " + finalVal + " in Feet");
                                    break;
                            }
                            break;
                        case 4:
                            //feet
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                case 2:
                                    Toast.makeText(MainActivity.this,"Can't convert distance to temperature", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    convertedVal = convertToMetres(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    tvOutput.setText(etEntered + " in Feet is: \n " + finalVal + " in Metres");
                                    break;
                                case 4:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;

                    }
                }
            }
        });

        //exact same logic as the first button but passing the output to another activity
        btnConvertSecondAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etEntered = String.valueOf(etTemp.getText());
                final double convertedVal, finalVal;

                if(etEntered.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter a Temperature", Toast.LENGTH_SHORT).show();
                }else{
                    final double tempVal = Double.parseDouble(String.valueOf(etEntered.toString()));

                    //creating an intent to call other activity
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                    switch (spUnitFrom.getSelectedItemPosition()){
                        case 0:
                            Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    convertedVal = convertToFahrenheit(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    //passing an extra on the intent to be parsed in the second activity
                                    intent.putExtra("convertedExtra", etEntered + " in Fahrenheit is: \n " + finalVal + " in Celsius");
                                    startActivity(intent);
                                    break;
                                case 3:
                                case 4:
                                    Toast.makeText(MainActivity.this,"Can't convert temperature to distance", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case 2:
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    convertedVal = convertToCelsius(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    intent.putExtra("convertedExtra", etEntered + " in Celsius is: \n " + finalVal + " in Fahrenheit");
                                    startActivity(intent);
                                    break;
                                case 2:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                case 4:
                                    Toast.makeText(MainActivity.this,"Can't convert temperature to distance", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case 3:
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                case 2:
                                    Toast.makeText(MainActivity.this,"Can't convert distance to temperature", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                                case 4:
                                    convertedVal = convertToFeet(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    intent.putExtra("convertedExtra", etEntered + " in Metres is: \n " + finalVal + " in Feet");
                                    startActivity(intent);
                                    break;
                            }
                            break;
                        case 4:
                            switch(spUnitTo.getSelectedItemPosition()){
                                case 0:
                                    Toast.makeText(MainActivity.this,"Please Select a Unit", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                case 2:
                                    Toast.makeText(MainActivity.this,"Can't convert distance to temperature", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    convertedVal = convertToMetres(tempVal);
                                    finalVal = Math.round(convertedVal * 100.0)/100.0;
                                    intent.putExtra("convertedExtra", etEntered + " in Feet is: \n " + finalVal + " in Metres");
                                    startActivity(intent);
                                    break;
                                case 4:
                                    Toast.makeText(MainActivity.this,"Why convert identical units?", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                    }
                }
            }
        });

    }

    public double convertToCelsius(double value){
        return (value - 32)/1.8;
    }

    public double convertToFahrenheit(double value){
        return value * 1.8 + 32;
    }

    public double convertToMetres(double value){
        return value * 0.3048;
    }

    public double convertToFeet(double value){
        return value / 0.3048;
    }
}
