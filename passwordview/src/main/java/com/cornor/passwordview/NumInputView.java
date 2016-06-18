package com.cornor.passwordview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;

/**
 * Desc:
 * User:cornor
 * Date:16/6/15
 */
public class NumInputView extends GridLayout implements View.OnClickListener{

    public interface onKeyboardClickListener{
        void onNumClick(String content);
        void onClearClick();
        void onDelClick();
    }

    public onKeyboardClickListener keyboardClickListener;

    public NumInputView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_num_input,this);
        setListener();
    }

    public NumInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_num_input,this);
        setListener();
    }

    public NumInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_num_input,this);
        setListener();
    }

    public void setKeyboardClickListener(onKeyboardClickListener keyboardClickListener) {
        this.keyboardClickListener = keyboardClickListener;
    }

    private void setListener(){
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
        int count = gridLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            gridLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getTag() != null){
            String tag = (String) v.getTag();
            if(!TextUtils.isEmpty(tag)){
                switch (tag){
                    case "clear":{
                        if(null != keyboardClickListener){
                            keyboardClickListener.onClearClick();
                        }
                        break;
                    }
                    case "del":{
                        if(null != keyboardClickListener){
                            keyboardClickListener.onDelClick();
                        }
                        break;
                    }
                    default:{
                        if(null != keyboardClickListener){
                            keyboardClickListener.onNumClick(tag);
                        }
                        break;
                    }
                }
            }
        }
    }
}
