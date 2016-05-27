package com.example.farooqezhar.chickenriceapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button cook;
    private EditText temperature;
    private Boolean gingerIngredient = false;
    private Boolean chiliIngredient = false;
    private ChickenRice cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonClickListener();
        cr = new ChickenRice();
    }

    public void onButtonClickListener() {
        cook = (Button)findViewById(R.id.cookButton);
        cook.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        temperature = (EditText)findViewById(R.id.editText);

                        if(TextUtils.isEmpty(temperature.getText().toString())) {
                            temperature.setError("Enter temperature");
                            return;
                        }else{
                            int temp = Integer.parseInt(temperature.getText().toString());
                            //check status of chicken rice
                            String status = cr.cook(temp);

                            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                            a_builder.setMessage("Do you want to eat now ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //check chicken rice has ingredient
                                            if(cr.eat(chiliIngredient,gingerIngredient)){
                                                Toast.makeText(MainActivity.this, "Tasty !", Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(MainActivity.this, "Burppp", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    })
                                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }) ;
                            AlertDialog alert = a_builder.create();
                            alert.setTitle(status);
                            alert.show();
                        }

                    }
                }
        );
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.gingerCheck:
                if (checked){
                    gingerIngredient = true;
                }else{
                    gingerIngredient = false;
                }
                break;
            case R.id.chiliCheck:
                if (checked){
                    chiliIngredient = true;
                }else{
                    chiliIngredient = false;
                }
                break;
        }
    }
}
