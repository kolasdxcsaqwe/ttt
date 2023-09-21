// Generated by view binder compiler. Do not edit!
package com.tencent.qcloud.tim.demo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tuikit.timcommon.component.UnreadCountTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MainMinimalistBottomTabItemLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout conversationBtnGroup;

  @NonNull
  public final ImageView tabIcon;

  @NonNull
  public final TextView tabText;

  @NonNull
  public final UnreadCountTextView unreadView;

  private MainMinimalistBottomTabItemLayoutBinding(@NonNull RelativeLayout rootView,
      @NonNull RelativeLayout conversationBtnGroup, @NonNull ImageView tabIcon,
      @NonNull TextView tabText, @NonNull UnreadCountTextView unreadView) {
    this.rootView = rootView;
    this.conversationBtnGroup = conversationBtnGroup;
    this.tabIcon = tabIcon;
    this.tabText = tabText;
    this.unreadView = unreadView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MainMinimalistBottomTabItemLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MainMinimalistBottomTabItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.main_minimalist_bottom_tab_item_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MainMinimalistBottomTabItemLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      RelativeLayout conversationBtnGroup = (RelativeLayout) rootView;

      id = R.id.tab_icon;
      ImageView tabIcon = rootView.findViewById(id);
      if (tabIcon == null) {
        break missingId;
      }

      id = R.id.tab_text;
      TextView tabText = rootView.findViewById(id);
      if (tabText == null) {
        break missingId;
      }

      id = R.id.unread_view;
      UnreadCountTextView unreadView = rootView.findViewById(id);
      if (unreadView == null) {
        break missingId;
      }

      return new MainMinimalistBottomTabItemLayoutBinding((RelativeLayout) rootView,
          conversationBtnGroup, tabIcon, tabText, unreadView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}