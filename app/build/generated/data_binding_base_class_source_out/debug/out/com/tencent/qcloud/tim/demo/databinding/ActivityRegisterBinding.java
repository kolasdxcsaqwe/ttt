// Generated by view binder compiler. Do not edit!
package com.tencent.qcloud.tim.demo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.tencent.qcloud.tim.demo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText etLoginName;

  @NonNull
  public final EditText etPassword;

  @NonNull
  public final TextView tvLogin;

  @NonNull
  public final TextView tvRegister;

  private ActivityRegisterBinding(@NonNull LinearLayout rootView, @NonNull EditText etLoginName,
      @NonNull EditText etPassword, @NonNull TextView tvLogin, @NonNull TextView tvRegister) {
    this.rootView = rootView;
    this.etLoginName = etLoginName;
    this.etPassword = etPassword;
    this.tvLogin = tvLogin;
    this.tvRegister = tvRegister;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.etLoginName;
      EditText etLoginName = rootView.findViewById(id);
      if (etLoginName == null) {
        break missingId;
      }

      id = R.id.etPassword;
      EditText etPassword = rootView.findViewById(id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.tvLogin;
      TextView tvLogin = rootView.findViewById(id);
      if (tvLogin == null) {
        break missingId;
      }

      id = R.id.tvRegister;
      TextView tvRegister = rootView.findViewById(id);
      if (tvRegister == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((LinearLayout) rootView, etLoginName, etPassword, tvLogin,
          tvRegister);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
