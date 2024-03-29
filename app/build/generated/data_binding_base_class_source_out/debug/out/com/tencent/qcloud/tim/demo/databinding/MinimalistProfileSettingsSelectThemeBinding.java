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

public final class MinimalistProfileSettingsSelectThemeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MinimalistLineControllerView selectTheme;

  private MinimalistProfileSettingsSelectThemeBinding(@NonNull LinearLayout rootView,
      @NonNull MinimalistLineControllerView selectTheme) {
    this.rootView = rootView;
    this.selectTheme = selectTheme;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MinimalistProfileSettingsSelectThemeBinding inflate(
      @NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MinimalistProfileSettingsSelectThemeBinding inflate(
      @NonNull LayoutInflater inflater, @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.minimalist_profile_settings_select_theme, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MinimalistProfileSettingsSelectThemeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.select_theme;
      MinimalistLineControllerView selectTheme = rootView.findViewById(id);
      if (selectTheme == null) {
        break missingId;
      }

      return new MinimalistProfileSettingsSelectThemeBinding((LinearLayout) rootView, selectTheme);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
