// Generated by view binder compiler. Do not edit!
package com.example.parkingresarvation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.parkingresarvation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView arrowback;

  @NonNull
  public final EditText confirmepassword;

  @NonNull
  public final EditText email;

  @NonNull
  public final EditText nom;

  @NonNull
  public final EditText password;

  @NonNull
  public final EditText prenom;

  @NonNull
  public final Button register;

  @NonNull
  public final ProgressBar registerprogress;

  @NonNull
  public final EditText telephone;

  @NonNull
  public final TextView textView2;

  private FragmentRegisterBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView arrowback,
      @NonNull EditText confirmepassword, @NonNull EditText email, @NonNull EditText nom,
      @NonNull EditText password, @NonNull EditText prenom, @NonNull Button register,
      @NonNull ProgressBar registerprogress, @NonNull EditText telephone,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.arrowback = arrowback;
    this.confirmepassword = confirmepassword;
    this.email = email;
    this.nom = nom;
    this.password = password;
    this.prenom = prenom;
    this.register = register;
    this.registerprogress = registerprogress;
    this.telephone = telephone;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.arrowback;
      ImageView arrowback = rootView.findViewById(id);
      if (arrowback == null) {
        break missingId;
      }

      id = R.id.confirmepassword;
      EditText confirmepassword = rootView.findViewById(id);
      if (confirmepassword == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = rootView.findViewById(id);
      if (email == null) {
        break missingId;
      }

      id = R.id.nom;
      EditText nom = rootView.findViewById(id);
      if (nom == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = rootView.findViewById(id);
      if (password == null) {
        break missingId;
      }

      id = R.id.prenom;
      EditText prenom = rootView.findViewById(id);
      if (prenom == null) {
        break missingId;
      }

      id = R.id.register;
      Button register = rootView.findViewById(id);
      if (register == null) {
        break missingId;
      }

      id = R.id.registerprogress;
      ProgressBar registerprogress = rootView.findViewById(id);
      if (registerprogress == null) {
        break missingId;
      }

      id = R.id.telephone;
      EditText telephone = rootView.findViewById(id);
      if (telephone == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      return new FragmentRegisterBinding((ConstraintLayout) rootView, arrowback, confirmepassword,
          email, nom, password, prenom, register, registerprogress, telephone, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
