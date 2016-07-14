package com.example.sharpandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sharpandroid.framework.widget.scancode.CommonScanActivity;
import com.sharpandroid.framework.widget.scancode.utils.Constant;


public class ScannerMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button createCode;
    private Button scan2code;
    private Button scanBarCode;
    private Button scanCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_main);
        assignViews();
        int mode = getIntent().getIntExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);

    }


    private void assignViews() {
        createCode = (Button) findViewById(R.id.create_code);
        scan2code = (Button) findViewById(R.id.scan_2code);
        scanBarCode = (Button) findViewById(R.id.scan_bar_code);
        scanCode = (Button) findViewById(R.id.scan_code);
        createCode.setOnClickListener(this);
        scan2code.setOnClickListener(this);
        scanBarCode.setOnClickListener(this);
        scanCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.create_code: //生成码
                intent = new Intent(this, CreateCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.scan_2code: //扫描二维码
                intent = new Intent(this, CommonScanActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_QRCODE_MODE);
                startActivity(intent);
                break;
            case R.id.scan_bar_code://扫描条形码
                intent = new Intent(this, CommonScanActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_BARCODE_MODE);
                startActivity(intent);
                break;
            case R.id.scan_code://扫描条形码或者二维码
                intent = new Intent(this, CommonScanActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                startActivity(intent);
                break;
        }
    }


}
