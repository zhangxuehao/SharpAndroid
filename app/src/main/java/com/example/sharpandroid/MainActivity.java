package com.example.sharpandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ScrollView scvRootContent;
    private Button btnPickImage;
    private Button btnScannerQrImage;

    private void assignViews() {
        scvRootContent = (ScrollView) findViewById(R.id.scv_root_content);
        btnPickImage = (Button) findViewById(R.id.btn_pick_image);
        btnScannerQrImage = (Button) findViewById(R.id.btn_scanner_qr_image);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        findButtonSetOnClickListener(scvRootContent, this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pick_image:
                startActivity(new Intent(this, PickImageActivity.class));
                break;
            case R.id.btn_scanner_qr_image:
                startActivity(new Intent(this,ScannerMainActivity.class));
                break;
        }
    }


    /**
     * 给button类型的控件加上点击事件
     *
     * @param view 根root
     * @param listener this
     */
    private void findButtonSetOnClickListener(View view, View.OnClickListener listener) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                if (child instanceof Button || child instanceof ImageButton) {
                    child.setOnClickListener(listener);
                } else if (child instanceof ViewGroup) {
                    findButtonSetOnClickListener(child, listener);
                }
            }
        }
    }





}
