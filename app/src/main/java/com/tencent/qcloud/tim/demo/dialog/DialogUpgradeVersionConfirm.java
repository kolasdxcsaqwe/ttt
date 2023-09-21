package com.tencent.qcloud.tim.demo.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.tencent.qcloud.tim.demo.databinding.DialogReConfirmBinding;
import com.tencent.qcloud.tim.demo.utils.ScreenUtils;


public class DialogUpgradeVersionConfirm  extends BaseDialog {


    DialogReConfirmBinding binding;
    OnClickConfirmListener onClickConfirmListener;

    public static DialogUpgradeVersionConfirm showDialog(FragmentManager fragmentManager)
    {
        DialogUpgradeVersionConfirm dialogUpgradeVersionConfirm=new DialogUpgradeVersionConfirm();
        dialogUpgradeVersionConfirm.show(fragmentManager,dialogUpgradeVersionConfirm.getClass().getName());
        return dialogUpgradeVersionConfirm;
    }

    public void setOnClickConfirmListener(OnClickConfirmListener onClickConfirmListener) {
        this.onClickConfirmListener = onClickConfirmListener;
    }

    @Nullable
    @Override
    protected View getRootView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        binding = DialogReConfirmBinding.inflate(layoutInflater, viewGroup, false);

        binding.tvTitle.setText("版本更新");
        binding.tvContent.setText("有新的版本可以更新！");

        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
                if(onClickConfirmListener!=null)
                {
                    onClickConfirmListener.onCLick(false);
                }
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
                if(onClickConfirmListener!=null)
                {
                    onClickConfirmListener.onCLick(true);
                }

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int width=(int)(ScreenUtils.getScreenWH(view.getContext()).x*0.8f);
        binding.llMain.getLayoutParams().width=width;
    }

    public interface OnClickConfirmListener
    {
        void onCLick(boolean isConfirm);
    }

}
