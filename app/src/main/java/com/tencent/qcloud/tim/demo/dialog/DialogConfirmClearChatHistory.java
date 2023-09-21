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


public class DialogConfirmClearChatHistory extends BaseDialog {


    DialogReConfirmBinding binding;
    OnClickConfirmListener onClickConfirmListener;

    public static DialogConfirmClearChatHistory showDialog(FragmentManager fragmentManager)
    {
        DialogConfirmClearChatHistory dialogConfirmClearChatHistory=new DialogConfirmClearChatHistory();
        dialogConfirmClearChatHistory.show(fragmentManager,dialogConfirmClearChatHistory.getClass().getName());
        return dialogConfirmClearChatHistory;
    }

    public void setOnClickConfirmListener(OnClickConfirmListener onClickConfirmListener) {
        this.onClickConfirmListener = onClickConfirmListener;
    }

    @Nullable
    @Override
    protected View getRootView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        binding = DialogReConfirmBinding.inflate(layoutInflater, viewGroup, false);

        binding.tvTitle.setText("清除聊天记录");
        binding.tvContent.setText("确定清除所有聊天记录吗");

        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
                if(onClickConfirmListener!=null)
                {
                    onClickConfirmListener.onCLick();
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
        void onCLick();
    }

}
