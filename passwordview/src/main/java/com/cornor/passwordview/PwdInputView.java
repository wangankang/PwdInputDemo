package com.cornor.passwordview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Desc:
 * User:cornor
 * Date:16/6/14
 */
public class PwdInputView extends LinearLayout {
    private int length;
    private int outBorderColor = Color.BLACK;
    private int inBorderColor= Color.BLACK;
    private boolean isPwdHidden = true;//密码默认隐藏
    private int textSize = 16;
    private OnInputListener inputListener;
    private StringBuilder password;
    public static final String DEFAULT_HOLDER_TEXT = "●";
    private String placeholderText = DEFAULT_HOLDER_TEXT;
    private ArrayList<TextView> textViewList;
    public interface OnInputListener{
        void onInputComplete(String content);
    }

    private OnInputListener listener;
    public PwdInputView(Context context) {
        this(context,null);
    }

    public PwdInputView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PwdInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textViewList = new ArrayList<>();
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PwdInputView,defStyleAttr,0);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = array.getIndex(i);
            if(index ==R.styleable.PwdInputView_length){
                length = array.getInt(index,6);
            }else if(index ==  R.styleable.PwdInputView_contentHidden){
                isPwdHidden = array.getBoolean(index,true);
            }else if(index == R.styleable.PwdInputView_inBorderColor){
                inBorderColor = array.getColor(index, Color.BLACK);
            }else if(index ==  R.styleable.PwdInputView_outBorderColor){
                outBorderColor = array.getColor(index,Color.BLACK);
            }else if(index ==  R.styleable.PwdInputView_textSize){
                textSize = array.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            }else if(index == R.styleable.PwdInputView_placeholderText){
                placeholderText = array.getString(index);
                if(!TextUtils.isEmpty(placeholderText) && placeholderText.length() > 1){
                    placeholderText = placeholderText.substring(0,1);
                }else if(TextUtils.isEmpty(placeholderText)){
                    placeholderText = DEFAULT_HOLDER_TEXT;
                }
            }
        }
        array.recycle();
        init(context);
    }

    public void setListener(OnInputListener listener) {
        this.listener = listener;
    }

    private void init(Context context){
        setOrientation(HORIZONTAL);
        password = new StringBuilder();
        float roundRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,context.getResources().getDisplayMetrics());
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1,context.getResources().getDisplayMetrics());
        int strokeWidth = padding;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.WHITE);
        gradientDrawable.setCornerRadius(roundRadius);
        gradientDrawable.setStroke(strokeWidth,outBorderColor);
        if(Build.VERSION.SDK_INT >= 16){
            setBackground(gradientDrawable);
        }else {
            setBackgroundDrawable(gradientDrawable);
        }
        for (int i = 0; i < length; i++) {
            final TextView txtV = new TextView(context);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            if(i > 0){
                params.leftMargin = 1;//px
            }
            txtV.setLayoutParams(params);
            txtV.setMaxEms(1);
            txtV.setTextSize(textSize);
            txtV.setGravity(Gravity.CENTER);
            addView(txtV);
            textViewList.add(txtV);
            if (i != length - 1){
                View view = new View(context);
                view.setBackgroundColor(inBorderColor);
                LayoutParams borderParams = new LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(borderParams);
                addView(view);
            }
        }
    }

    public void appendText(String text){
        if(password.length() == length){
            return;
        }
        TextView textView = textViewList.get(password.length());
        if(isPwdHidden){
            textView.setText(placeholderText);
        }else {
            textView.setText(text);
        }
        password.append(text);
        if(password.length() == length){
            if(null != listener){
                listener.onInputComplete(getPassword());
            }
        }
    }

    public void clear(){
        for (int i = 0; i < password.length(); i++) {
            textViewList.get(i).setText("");
        }
        password.delete(0,password.length());
    }

    public void del(){
        if(password.length() == 0){
            return;
        }
        int index = password.length() - 1;
        textViewList.get(index).setText("");
        password.delete(password.length()-1,password.length());
    }

    public String getPassword(){
        return password.toString();
    }

}
