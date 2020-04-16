package com.bhaire.digiboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawBoard extends AppCompatActivity {
    private Drawing drawBoard;
    Button saveScr,clrScr;
    EditText label;
    RadioGroup radioGroup;
    RadioButton radioType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_board);
        drawBoard = findViewById(R.id.canvas_square);

        clrScr = findViewById(R.id.clr_scr);
        saveScr = findViewById(R.id.save_scr);
        label = findViewById(R.id.et_Label);

        label.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    label.setCursorVisible(true);
                }
                else{
                    label.setCursorVisible(false);
                }
            }
        });

        radioGroup = findViewById(R.id.radioGroup);

        clrScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawBoard.clearCanvas();
            }
        });



        saveScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selection = radioGroup.getCheckedRadioButtonId();
                radioType = findViewById(selection);

                String _label = label.getText().toString().trim();
                String _type = radioType.getText().toString().toLowerCase();

                if (TextUtils.isEmpty(_label)){
                    label.setError("Please provide a valid label");
                    return;
                }
                String filename = _label+getTimeStamp()+".jpg";
                try {
                    drawBoard.saveCanvas(filename,_type);
                    Toast.makeText(DrawBoard.this, filename+" saved!!!", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private String getTimeStamp(){
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

}
