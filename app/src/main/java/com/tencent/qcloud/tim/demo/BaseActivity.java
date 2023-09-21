package com.tencent.qcloud.tim.demo;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.tencent.qcloud.tim.demo.dialog.LoadingDialog;


public class BaseActivity extends FragmentActivity {

    LoadingDialog loadingDialog;

    private int derectionAnimation = LinearLayout.HORIZONTAL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (LinearLayout.HORIZONTAL == getAnimation()) {
//            overridePendingTransition(R.anim.h_enter, R.anim.donothing);
//        } else if (LinearLayout.VERTICAL == getAnimation()) {
//            overridePendingTransition(R.anim.v_enter, R.anim.donothing);
//        }
    }

    public int getAnimation() {
        return derectionAnimation;
    }

    public void showLoading()
    {
        if(!isFinishing() && !isDestroyed()){
            if(loadingDialog==null)
            {
                loadingDialog=new LoadingDialog(this);
                loadingDialog.show();
            }
            else
            {
                if(!loadingDialog.isShowing())
                {
                    loadingDialog.show();
                }
            }
        }
    }

    public void dismissLoading()
    {
        if(loadingDialog!=null)
        {
            loadingDialog.dismiss();
            loadingDialog=null;
        }
    }

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.h_exit, R.anim.donothing);
//    }
}
