// Copyright (c) 2022 NetEase, Inc. All rights reserved.
// Use of this source code is governed by a MIT license that can be
// found in the LICENSE file.

package com.tencent.qcloud.tim.demo.about;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tim.demo.databinding.ActivityAboutBinding;
import com.tencent.qcloud.tim.demo.utils.SPUtils;
import com.tencent.qcloud.tuikit.timcommon.component.interfaces.ITitleBarLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class AboutActivity extends AppCompatActivity {

  private ActivityAboutBinding viewBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewBinding = ActivityAboutBinding.inflate(getLayoutInflater());
    setContentView(viewBinding.getRoot());
    viewBinding.aboutTitleBar.setOnLeftClickListener(v -> onBackPressed());
    viewBinding.aboutTitleBar.setTitle(getString(R.string.about_im), ITitleBarLayout.Position.MIDDLE);
    String detail= SPUtils.getInstance().get(SPUtils.ConfigData,"");
    try {
      JSONObject jsonObject=new JSONObject(detail);
      String content=jsonObject.optString("about","");
      viewBinding.webView.loadData(content,"text/html; charset=UTF-8",null);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

  }
}
