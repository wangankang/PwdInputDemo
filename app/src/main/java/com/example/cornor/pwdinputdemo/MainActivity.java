package com.example.cornor.pwdinputdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cornor.passwordview.NumInputView;
import com.cornor.passwordview.PwdInputView;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    private PwdInputView pwdInputView;
    private NumInputView numInputView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pwdInputView = (PwdInputView) findViewById(R.id.pwdInpuView);
        numInputView = (NumInputView) findViewById(R.id.numInpuView);
        pwdInputView.setListener(new PwdInputView.OnInputListener() {
            @Override
            public void onInputComplete(String content) {
                Toast.makeText(MainActivity.this,"输入完成,输入的密码是"+content,Toast.LENGTH_LONG).show();
            }
        });
        numInputView.setKeyboardClickListener(new NumInputView.onKeyboardClickListener() {
            @Override
            public void onNumClick(String content) {
                pwdInputView.appendText(content);
            }

            @Override
            public void onClearClick() {
                pwdInputView.clear();
            }

            @Override
            public void onDelClick() {
                pwdInputView.del();
            }
        });

    }
}
