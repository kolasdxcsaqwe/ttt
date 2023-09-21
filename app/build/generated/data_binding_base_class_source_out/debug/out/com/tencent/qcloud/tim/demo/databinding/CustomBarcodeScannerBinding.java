// Generated by view binder compiler. Do not edit!
package com.tencent.qcloud.tim.demo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.ViewfinderView;
import com.tencent.qcloud.tim.demo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomBarcodeScannerBinding implements ViewBinding {
  @NonNull
  private final View rootView;

  @NonNull
  public final BarcodeView zxingBarcodeSurface;

  @NonNull
  public final TextView zxingStatusView;

  @NonNull
  public final ViewfinderView zxingViewfinderView;

  private CustomBarcodeScannerBinding(@NonNull View rootView,
      @NonNull BarcodeView zxingBarcodeSurface, @NonNull TextView zxingStatusView,
      @NonNull ViewfinderView zxingViewfinderView) {
    this.rootView = rootView;
    this.zxingBarcodeSurface = zxingBarcodeSurface;
    this.zxingStatusView = zxingStatusView;
    this.zxingViewfinderView = zxingViewfinderView;
  }

  @Override
  @NonNull
  public View getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomBarcodeScannerBinding inflate(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    if (parent == null) {
      throw new NullPointerException("parent");
    }
    inflater.inflate(R.layout.custom_barcode_scanner, parent);
    return bind(parent);
  }

  @NonNull
  public static CustomBarcodeScannerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.zxing_barcode_surface;
      BarcodeView zxingBarcodeSurface = rootView.findViewById(id);
      if (zxingBarcodeSurface == null) {
        break missingId;
      }

      id = R.id.zxing_status_view;
      TextView zxingStatusView = rootView.findViewById(id);
      if (zxingStatusView == null) {
        break missingId;
      }

      id = R.id.zxing_viewfinder_view;
      ViewfinderView zxingViewfinderView = rootView.findViewById(id);
      if (zxingViewfinderView == null) {
        break missingId;
      }

      return new CustomBarcodeScannerBinding(rootView, zxingBarcodeSurface, zxingStatusView,
          zxingViewfinderView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
