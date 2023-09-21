// Generated by view binder compiler. Do not edit!
package com.tencent.qcloud.tim.demo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tuikit.timcommon.component.MinimalistLineControllerView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MinimalistProfileSettingsAllowTypeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MinimalistLineControllerView modifyAllowType;

  private MinimalistProfileSettingsAllowTypeBinding(@NonNull LinearLayout rootView,
      @NonNull MinimalistLineControllerView modifyAllowType) {
    this.rootView = rootView;
    this.modifyAllowType = modifyAllowType;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MinimalistProfileSettingsAllowTypeBinding inflate(
      @NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MinimalistProfileSettingsAllowTypeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.minimalist_profile_settings_allow_type, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MinimalistProfileSettingsAllowTypeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.modify_allow_type;
      MinimalistLineControllerView modifyAllowType = rootView.findViewById(id);
      if (modifyAllowType == null) {
        break missingId;
      }

      return new MinimalistProfileSettingsAllowTypeBinding((LinearLayout) rootView,
          modifyAllowType);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
