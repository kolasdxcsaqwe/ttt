// Generated by view binder compiler. Do not edit!
package com.tencent.qcloud.tim.demo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tuikit.timcommon.component.LineControllerView;
import com.tencent.qcloud.tuikit.timcommon.component.TitleBarLayout;
import com.tencent.qcloud.tuikit.timcommon.component.gatherimage.ShadeImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySelfDetailBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout faceUrlArea;

  @NonNull
  public final LineControllerView modifyAccountLv;

  @NonNull
  public final LineControllerView modifyBirthdayLv;

  @NonNull
  public final LineControllerView modifyGenderLv;

  @NonNull
  public final LineControllerView modifyNickNameLv;

  @NonNull
  public final LineControllerView modifySignatureLv;

  @NonNull
  public final TextView modifyUserIconTv;

  @NonNull
  public final LineControllerView myQrcode;

  @NonNull
  public final TitleBarLayout selfDetailTitleBar;

  @NonNull
  public final ShadeImageView selfIcon;

  private ActivitySelfDetailBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout faceUrlArea, @NonNull LineControllerView modifyAccountLv,
      @NonNull LineControllerView modifyBirthdayLv, @NonNull LineControllerView modifyGenderLv,
      @NonNull LineControllerView modifyNickNameLv, @NonNull LineControllerView modifySignatureLv,
      @NonNull TextView modifyUserIconTv, @NonNull LineControllerView myQrcode,
      @NonNull TitleBarLayout selfDetailTitleBar, @NonNull ShadeImageView selfIcon) {
    this.rootView = rootView;
    this.faceUrlArea = faceUrlArea;
    this.modifyAccountLv = modifyAccountLv;
    this.modifyBirthdayLv = modifyBirthdayLv;
    this.modifyGenderLv = modifyGenderLv;
    this.modifyNickNameLv = modifyNickNameLv;
    this.modifySignatureLv = modifySignatureLv;
    this.modifyUserIconTv = modifyUserIconTv;
    this.myQrcode = myQrcode;
    this.selfDetailTitleBar = selfDetailTitleBar;
    this.selfIcon = selfIcon;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySelfDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySelfDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_self_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySelfDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.face_url_area;
      LinearLayout faceUrlArea = rootView.findViewById(id);
      if (faceUrlArea == null) {
        break missingId;
      }

      id = R.id.modify_account_lv;
      LineControllerView modifyAccountLv = rootView.findViewById(id);
      if (modifyAccountLv == null) {
        break missingId;
      }

      id = R.id.modify_birthday_lv;
      LineControllerView modifyBirthdayLv = rootView.findViewById(id);
      if (modifyBirthdayLv == null) {
        break missingId;
      }

      id = R.id.modify_gender_lv;
      LineControllerView modifyGenderLv = rootView.findViewById(id);
      if (modifyGenderLv == null) {
        break missingId;
      }

      id = R.id.modify_nick_name_lv;
      LineControllerView modifyNickNameLv = rootView.findViewById(id);
      if (modifyNickNameLv == null) {
        break missingId;
      }

      id = R.id.modify_signature_lv;
      LineControllerView modifySignatureLv = rootView.findViewById(id);
      if (modifySignatureLv == null) {
        break missingId;
      }

      id = R.id.modify_user_icon_tv;
      TextView modifyUserIconTv = rootView.findViewById(id);
      if (modifyUserIconTv == null) {
        break missingId;
      }

      id = R.id.myQrcode;
      LineControllerView myQrcode = rootView.findViewById(id);
      if (myQrcode == null) {
        break missingId;
      }

      id = R.id.self_detail_title_bar;
      TitleBarLayout selfDetailTitleBar = rootView.findViewById(id);
      if (selfDetailTitleBar == null) {
        break missingId;
      }

      id = R.id.self_icon;
      ShadeImageView selfIcon = rootView.findViewById(id);
      if (selfIcon == null) {
        break missingId;
      }

      return new ActivitySelfDetailBinding((LinearLayout) rootView, faceUrlArea, modifyAccountLv,
          modifyBirthdayLv, modifyGenderLv, modifyNickNameLv, modifySignatureLv, modifyUserIconTv,
          myQrcode, selfDetailTitleBar, selfIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
