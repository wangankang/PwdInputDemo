Password-Input-View
----
a password input view

<img src="https://github.com/wangankang/PwdInputView/blob/master/Screenshot_20160619-095112.png" width="216" height="384"/>

**Usage**
----
Add thd code in your build.gradle

    dependencies {
        compile fileTree(dir: 'libs', include: ['*.jar'])
        compile 'com.cornor.passwordview:passwordview:1.0.0'
    }

支持属性:

    length:密码长度
    outBorderColor：外边框颜色
    inBorderColor：内边框颜色
    contentHidden：内容是否隐藏
    textSize：字体大小
    placeholderText：占位符文字(一位)


布局文件：

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        tools:context="com.example.cornor.pwdinputdemo.MainActivity">

            <com.cornor.passwordview.PwdInputView
                android:id="@+id/pwdInpuView"
                android:layout_width="match_parent"
                android:layout_height="80dp"
                app:length="4"
                app:textSize="14sp"
                app:contentHidden="true"
                app:inBorderColor="@color/colorPrimary"
                app:outBorderColor="@color/colorPrimary"
                app:placeholderText="▪"
            >

        </com.cornor.passwordview.PwdInputView>


        <com.cornor.passwordview.NumInputView
            android:layout_below="@+id/pwdInpuView"
            android:id="@+id/numInpuView"
            android:layout_marginTop="5dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
        >

        </com.cornor.passwordview.NumInputView>

    </RelativeLayout>




