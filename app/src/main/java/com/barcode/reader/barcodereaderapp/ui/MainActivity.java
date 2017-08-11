package com.barcode.reader.barcodereaderapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.barcode.reader.barcodereaderapp.R;
import com.barcode.reader.barcodereaderapp.productDetail.ui.ProductDetailActivity;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mStatusMsg;
    private TextView mBarCodeValue;
    private Button mReadBarCode;
    private static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
    }

    private void initViews() {
        mStatusMsg = (TextView) findViewById(R.id.status_message);
        mBarCodeValue = (TextView) findViewById(R.id.barcode_value);
        mReadBarCode = (Button) findViewById(R.id.read_barcode);
        mReadBarCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Barcode barcode = data.getParcelableExtra("barcode");
//                mBarCodeValue.setText("barCode Value :" + barcode.displayValue);
                displayProductDetails(barcode);
            } else {
                Toast.makeText(this, "No Barcodes Found", Toast.LENGTH_SHORT).show();
            }


        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void displayProductDetails(Barcode barcode) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("barcode", barcode);
        startActivity(intent);
        finish();
    }
}
