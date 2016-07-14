package com.example.sharpandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.sharpandroid.framework.widget.scancode.zxing.encode.EncodingHandler;

import java.io.UnsupportedEncodingException;


/**
 * Created by 刘红亮 on 2015/9/24 14:37.
 */
public class CreateCodeActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etCodeKey;
    private Button btnCreateCode;
    private Button btnCreateCodeAndImg;
    private ImageView iv2Code;
    private ImageView ivBarCode;

    private void assignViews() {
        etCodeKey = (EditText) findViewById(R.id.et_code_key);
        btnCreateCode = (Button) findViewById(R.id.btn_create_code);
        btnCreateCodeAndImg = (Button) findViewById(R.id.btn_create_code_and_img);
        iv2Code = (ImageView) findViewById(R.id.iv_2_code);
        ivBarCode = (ImageView) findViewById(R.id.iv_bar_code);
        btnCreateCode.setOnClickListener(this);
        btnCreateCodeAndImg.setOnClickListener(this);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_code);
        assignViews();
    }


    private Bitmap createBarCode(String key) {
        Bitmap qrCode = null;
        try {
            qrCode = EncodingHandler.createBarCode(key, 600, 300);
            ivBarCode.setImageBitmap(qrCode);
        } catch (Exception e) {
            Toast.makeText(this, "输入的内容条形码不支持！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return qrCode;
    }

    /**
     * 生成二维码
     *
     * @param key
     */
    private Bitmap create2Code(String key) {
        Bitmap qrCode = null;
        try {
            qrCode = EncodingHandler.create2Code(key, 400);
            iv2Code.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return qrCode;
    }

    /**
     * 初始化头像图片
     */
    private Bitmap getHeadBitmap(int size) {
        try {
            // 这里采用从asset中加载图片abc.jpg
            Bitmap portrait = BitmapFactory.decodeResource(getResources(), R.drawable.head);
            // 对原有图片压缩显示大小
            Matrix mMatrix = new Matrix();
            float width = portrait.getWidth();
            float height = portrait.getHeight();
            mMatrix.setScale(size / width, size / height);
            return Bitmap.createBitmap(portrait, 0, 0, (int) width,
                    (int) height, mMatrix, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在二维码上绘制头像
     */
    private void createQRCodeBitmapWithPortrait(Bitmap qr, Bitmap portrait) {
        // 头像图片的大小
        int portrait_W = portrait.getWidth();
        int portrait_H = portrait.getHeight();

        // 设置头像要显示的位置，即居中显示
        int left = (qr.getWidth() - portrait_W) / 2;
        int top = (qr.getHeight() - portrait_H) / 2;
        int right = left + portrait_W;
        int bottom = top + portrait_H;
        Rect rect1 = new Rect(left, top, right, bottom);

        // 取得qr二维码图片上的画笔，即要在二维码图片上绘制我们的头像
        Canvas canvas = new Canvas(qr);

        // 设置我们要绘制的范围大小，也就是头像的大小范围
        Rect rect2 = new Rect(0, 0, portrait_W, portrait_H);
        // 开始绘制
        canvas.drawBitmap(portrait, rect2, rect1, null);
    }

    @Override
    public void onClick(View v) {
        String key = etCodeKey.getText().toString();
        switch (v.getId()) {
            case R.id.btn_create_code: //生成码
                if (TextUtils.isEmpty(key)) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    create2Code(key);
                    createBarCode(key);
                }
                break;
            case R.id.btn_create_code_and_img: //生成码
                Bitmap bitmap = create2Code(key);
                Bitmap headBitmap = getHeadBitmap(60);
                if (bitmap != null && headBitmap != null) {
                    createQRCodeBitmapWithPortrait(bitmap, headBitmap);
                }
                break;
        }
    }
}
