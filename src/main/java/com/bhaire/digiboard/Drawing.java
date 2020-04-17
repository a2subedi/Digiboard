package com.bhaire.digiboard;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Drawing extends View {

    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    private Paint mPaint;
    Context context;
    private float mx, my;
    private static float THRESHOLD = 5;

    public Drawing(Context context, AttributeSet attributesSet) {
        super(context, attributesSet);

        this.context = context;
        path = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(75f);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(240,245,240));
        canvas.drawPath(path, mPaint);

    }

    private void startDrawing(float x, float y) {
        path.moveTo(x, y);
        mx = x;
        my = y;
    }

    public void clearCanvas() {
        path.reset();
        invalidate();
    }

    public void saveCanvas(String filename,String imageType) throws FileNotFoundException {
        try {
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/digiboard/"+imageType);
            dir.mkdirs();
            String fullpath = dir.getAbsolutePath()+"/"+ filename;

            OutputStream stream = new FileOutputStream(fullpath);
            getBitmap().compress(Bitmap.CompressFormat.JPEG, 50, stream);
            stream.close();
            clearCanvas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upTouch() {
        path.lineTo(mx, my);
    }

    private void moveTouch(float x, float y) {
        float fx = Math.abs(x - mx);
        float fy = Math.abs(y - my);
        if (fx >= THRESHOLD || fy >= THRESHOLD) {
            path.quadTo(mx, my, (x + mx) / 2, (y + my) / 2);
            mx = x;
            my = y;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startDrawing(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
        }
        return true;
    }

    public Bitmap getBitmap()
    {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);

        return bmp;
    }
}

