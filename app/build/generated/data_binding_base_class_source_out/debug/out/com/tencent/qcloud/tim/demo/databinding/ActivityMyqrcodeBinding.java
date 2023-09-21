// Generated by view binder compiler. Do not edit!
package com.tencent.qcloud.tim.demo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tuikit.timcommon.component.TitleBarLayout;
import com.tencent.qcloud.tuikit.timcommon.component.gatherimage.ShadeImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMyqrcodeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TitleBarLayout aboutTitleBar;

  @NonNull
  public final ImageView ivQrcode;

  @NonNull
  public final ShadeImageView selfIcon;

  @NonNull
  public final TextView tvName;

  private ActivityMyqrcodeBinding(@NonNull LinearLayout rootView,
      @NonNull TitleBarLayout aboutTitleBar, @NonNull ImageView ivQrcode,
      @NonNull ShadeImageView selfIcon, @NonNull TextView tvName) {
    this.rootView = rootView;
    this.aboutTitleBar = aboutTitleBar;
    this.ivQrcode = ivQrcode;
    this.selfIcon = selfIcon;
    this.tvName = tvName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMyqrcodeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMyqrcodeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_myqrcode, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMyqrcodeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.about_title_bar;
      TitleBarLayout aboutTitleBar = rootView.findViewById(id);
      if (aboutTitleBar == null) {
        break missingId;
      }

      id = R.id.ivQrcode;
      ImageView ivQrcode = rootView.findViewById(id);
      if (ivQrcode == null) {
        break missingId;
      }

      id = R.id.self_icon;
      ShadeImageView selfIcon = rootView.findViewById(id);
      if (selfIcon == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = rootView.findViewById(id);
      if (tvName == null) {
        break missingId;
      }

      return new ActivityMyqrcodeBinding((LinearLayout) rootView, aboutTitleBar, ivQrcode, selfIcon,
          tvName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
