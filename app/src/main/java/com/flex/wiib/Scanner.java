package com.flex.wiib;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scanner extends AppCompatActivity{


    public static final int PERMISSION_REQUEST_CAMERA = 0;

    int empId,LineaID,EstacionID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

       empId= getIntent().getExtras().getInt("empId");
       LineaID= getIntent().getExtras().getInt("LineaID");
       EstacionID= getIntent().getExtras().getInt("EstacionID");

       if(empId ==0){
           if (Build.VERSION.SDK_INT < 23)
               new IntentIntegrator(Scanner.this).initiateScan();

           else{ // si no, se debe checar el permiso
               int writePermission = checkSelfPermission(Manifest.permission.CAMERA);
               if (writePermission != PackageManager.PERMISSION_GRANTED) { //si el permiso es diferente de aceptado
                   requestPermission(); // se realiza una solicitud de permiso
               } else { //si no, se inicia el escaner
                   new IntentIntegrator(Scanner.this).initiateScan();
               }
           }
       }



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission() {
        //shouldShowRequestPermissionRationale es verdadero solamente si ya se había mostrado
        //anteriormente el dialogo de permisos y el usuario lo negó
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            //toast;
        } else {
            //si es la primera vez se solicita el permiso directamente
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        empId = Integer.parseInt(result.getContents());
        if(result != null) {
            if(result.getContents() == null) {
                //Toast.makeText(n4Escaner.this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show();
            } else {
                Intent main = new Intent(this, MainActivity.class);
                main.putExtra("LineaID", LineaID);
                main.putExtra("EstacionID", EstacionID);
                main.putExtra("empId", empId);
                startActivity(main);
                //Toast.makeText(this, "Emp ID: "+empId, Toast.LENGTH_SHORT).show();
                //codigo = Integer.parseInt(result.getContents());

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }




}
