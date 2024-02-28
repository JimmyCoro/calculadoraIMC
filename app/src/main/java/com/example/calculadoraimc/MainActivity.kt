package com.example.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etPeso: EditText = findViewById(R.id.etPeso)
        val etEstatura: EditText = findViewById(R.id.etEstatura)
        val cdFemenino: CheckBox = findViewById(R.id.cbFemenino)
        val cdMasculino: CheckBox = findViewById(R.id.cbMasculino)
        val btIMC: Button = findViewById(R.id.btnIMC)
        val btBorrar: Button = findViewById(R.id.btnBorrar)
        var etResultado: EditText = findViewById(R.id.etResultado)
        val btIdeal:Button = findViewById(R.id.btnPeso)

        btIMC.setOnClickListener {

            if (etPeso.getText().toString().trim().length == 0) {
                etPeso.requestFocus();

                Toast.makeText(this, "Favor de ingresar el peso.", Toast.LENGTH_LONG).show();

            } else if (etEstatura.getText().toString().trim().length == 0) {

                etEstatura.requestFocus();

                Toast.makeText(this, "Favor de ingresar la estatura.", Toast.LENGTH_LONG).show();

            } else {
                var PesoKg: Float = etPeso.getText().toString().toFloat();
                var Altm: Float = etEstatura.getText().toString().toFloat();
                var bmi: Float = (PesoKg / (Altm * Altm));

                if (bmi < 18.8) {

                    Toast.makeText(this, "Tu inc es:" + bmi + "\n" + "Baio peso", Toast.LENGTH_LONG)
                        .show();
                    etResultado.setText(bmi.toString());

                } else {

                    if (bmi < 24.9) {

                        Toast.makeText(
                            this,
                            "Tu inc es:" + bmi + "\n" + "Peso normal",
                            Toast.LENGTH_LONG
                        ).show();
                        etResultado.setText(bmi.toString());

                    } else {
                        if (bmi < 29.9) {
                            Toast.makeText(
                                this,
                                "Tu inc es:" + bmi + "\n" + "Sobre peso",
                                Toast.LENGTH_LONG
                            ).show();
                            etResultado.setText(bmi.toString());
                        } else {
                            if (bmi < 34.9) {
                                Toast.makeText(
                                    this,
                                    "Tu inc es:" + bmi + "\n" + "Obecidad",
                                    Toast.LENGTH_LONG
                                ).show();
                                etResultado.setText(bmi.toString());
                            } else {
                                if (bmi > 35) {
                                    Toast.makeText(
                                        this,
                                        "Tu inc es:" + bmi + "\n" + "Muy Obeso",
                                        Toast.LENGTH_LONG
                                    ).show();
                                    etResultado.setText(bmi.toString());
                                }
                            }

                        }
                    }
                }
            }
        }
        btBorrar.setOnClickListener {
            etPeso.setText("");
            etPeso.requestFocus();
            etEstatura.setText("");
            etResultado.setText("");
            cdFemenino.setEnabled(true);
            cdMasculino.setEnabled(true);
            cdFemenino.setChecked(false);
            cdMasculino.setChecked(false);
        }

        btIdeal.setOnClickListener {
            var PesoKg: Float = etPeso.getText().toString().toFloat();
            var Altm: Float = etEstatura.getText().toString().toFloat();
            val f1=2.25;
            val f2=45;
            val m1=2.7;
            val m2=47.75;
            var pi: Float = 0.0f
            var piup:Float;
            var pidown:Float;
            var bandera:Boolean = true;

            if (etPeso.getText().toString().trim().length == 0) {
                etPeso.requestFocus();

                Toast.makeText( this, "Favor de ingresar el peso.", Toast.LENGTH_LONG).show();
                bandera = false;

            } else if (etEstatura.getText().toString().trim().length == 0) {etEstatura.requestFocus();

                Toast.makeText( this, "Favor de ingresar la estatura.", Toast.LENGTH_LONG).show()
                bandera = false;

            } else if(cdMasculino.isChecked() == false && cdFemenino.isChecked() == false) {

                Toast.makeText( this, "Favor de seleccionar un genero.", Toast.LENGTH_LONG).show();
                bandera = false;

            }

            if(cdFemenino.isChecked() == true) {

                cdMasculino.setEnabled(false);

                pi = (((((100*Altm)-152.4) / 2.54)*f1) + f2).toFloat();

            }else if(cdMasculino.isChecked() == true) {

                cdFemenino.setEnabled(false);
                pi = (((((100*Altm)- 152.4) / 2.54) *m1) + m2).toFloat();

            }
            if (bandera) {

                piup = ((pi * 0.10) + pi).toFloat();

                Toast.makeText(this, "Resultado del peso ideal" +pi+ "\n", Toast.LENGTH_LONG).show();
                    etResultado.setText((pi.toString()));
                    pidown = ((pi- (pi *0.10)).toFloat());



                if (PesoKg > piup) {

                    Toast.makeText( this, "Estás arriba de tu peso Ideal", Toast.LENGTH_LONG).show();

                } else if (PesoKg <= piup && PesoKg >= pidown) {

                    Toast.makeText( this, "Estás en tu peso Ideal", Toast.LENGTH_LONG).show();

                } else if (PesoKg < pidown) {

                    Toast.makeText( this,"Estás abajo de tu peso Ideal", Toast.LENGTH_LONG).show();

                }

            }

        }




    }
}