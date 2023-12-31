// Generated by view binder compiler. Do not edit!
package com.example.parkingresarvation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.airbnb.lottie.LottieAnimationView;
import com.example.parkingresarvation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySplashBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LottieAnimationView lottieAnimationView;

  @NonNull
  public final TextView nameApp;

  @NonNull
  public final TextView textVersion;

  private ActivitySplashBinding(@NonNull ConstraintLayout rootView,
      @NonNull LottieAnimationView lottieAnimationView, @NonNull TextView nameApp,
      @NonNull TextView textVersion) {
    this.rootView = rootView;
    this.lottieAnimationView = lottieAnimationView;
    this.nameApp = nameApp;
    this.textVersion = textVersion;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_splash, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySplashBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.lottieAnimationView;
      LottieAnimationView lottieAnimationView = rootView.findViewById(id);
      if (lottieAnimationView == null) {
        break missingId;
      }

      id = R.id.name_app;
      TextView nameApp = rootView.findViewById(id);
      if (nameApp == null) {
        break missingId;
      }

      id = R.id.text_version;
      TextView textVersion = rootView.findViewById(id);
      if (textVersion == null) {
        break missingId;
      }

      return new ActivitySplashBinding((ConstraintLayout) rootView, lottieAnimationView, nameApp,
          textVersion);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
