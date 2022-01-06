// Generated by data binding compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cardio.physician.R;
import com.cardio.physician.ui.views.auth.signup.SignUpViewModel;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentSignUpBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton btnSignup;

  @NonNull
  public final AppCompatCheckBox chkBoxAcceptPolicy;

  @NonNull
  public final ConstraintLayout confirmPasswordContainer;

  @NonNull
  public final ConstraintLayout confirmPasswordValContainer;

  @NonNull
  public final AppCompatTextView countryCode;

  @NonNull
  public final View countryCodeSeprator;

  @NonNull
  public final AppCompatEditText edtConfirmPassword;

  @NonNull
  public final AppCompatEditText edtEmailId;

  @NonNull
  public final AppCompatEditText edtFirstName;

  @NonNull
  public final AppCompatEditText edtLastName;

  @NonNull
  public final AppCompatEditText edtPassword;

  @NonNull
  public final AppCompatEditText edtPhoneNumber;

  @NonNull
  public final ConstraintLayout emailValContainer;

  @NonNull
  public final ConstraintLayout firstNameValContainer;

  @NonNull
  public final ToolbarBinding headerView;

  @NonNull
  public final AppCompatImageView iconSignupForward;

  @NonNull
  public final AppCompatImageView imgConfirmShowPassword;

  @NonNull
  public final ShapeableImageView imgProfilePic;

  @NonNull
  public final AppCompatImageView imgShowPassword;

  @NonNull
  public final ImageView ivPasswordnfo;

  @NonNull
  public final ConstraintLayout lastNameValContainer;

  @NonNull
  public final ConstraintLayout passwordContainer;

  @NonNull
  public final ConstraintLayout passwordValContainer;

  @NonNull
  public final ConstraintLayout phoneNumberContainer;

  @NonNull
  public final ConstraintLayout phoneNumberValContainer;

  @NonNull
  public final AppCompatTextView titleSignup;

  @NonNull
  public final AppCompatTextView tvAgree;

  @NonNull
  public final AppCompatTextView tvAnd;

  @NonNull
  public final AppCompatTextView tvConfirmPasswordError;

  @NonNull
  public final AppCompatTextView tvEmailError;

  @NonNull
  public final AppCompatTextView tvFirstNameError;

  @NonNull
  public final AppCompatTextView tvLastName;

  @NonNull
  public final AppCompatTextView tvPasswordError;

  @NonNull
  public final AppCompatTextView tvPhoneNoError;

  @NonNull
  public final AppCompatTextView tvPrivacyPolicy;

  @NonNull
  public final AppCompatTextView tvTermCondition;

  @NonNull
  public final AppCompatTextView txtConfirmPassword;

  @NonNull
  public final AppCompatTextView txtEmailAddress;

  @NonNull
  public final AppCompatTextView txtFirstName;

  @NonNull
  public final AppCompatTextView txtLastName;

  @NonNull
  public final AppCompatTextView txtPassword;

  @NonNull
  public final AppCompatTextView txtPhoneNumber;

  @NonNull
  public final AppCompatTextView txtSignin;

  @NonNull
  public final AppCompatTextView txtTitleSignup;

  @Bindable
  protected SignUpViewModel mMViewModel;

  protected FragmentSignUpBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatButton btnSignup, AppCompatCheckBox chkBoxAcceptPolicy,
      ConstraintLayout confirmPasswordContainer, ConstraintLayout confirmPasswordValContainer,
      AppCompatTextView countryCode, View countryCodeSeprator, AppCompatEditText edtConfirmPassword,
      AppCompatEditText edtEmailId, AppCompatEditText edtFirstName, AppCompatEditText edtLastName,
      AppCompatEditText edtPassword, AppCompatEditText edtPhoneNumber,
      ConstraintLayout emailValContainer, ConstraintLayout firstNameValContainer,
      ToolbarBinding headerView, AppCompatImageView iconSignupForward,
      AppCompatImageView imgConfirmShowPassword, ShapeableImageView imgProfilePic,
      AppCompatImageView imgShowPassword, ImageView ivPasswordnfo,
      ConstraintLayout lastNameValContainer, ConstraintLayout passwordContainer,
      ConstraintLayout passwordValContainer, ConstraintLayout phoneNumberContainer,
      ConstraintLayout phoneNumberValContainer, AppCompatTextView titleSignup,
      AppCompatTextView tvAgree, AppCompatTextView tvAnd, AppCompatTextView tvConfirmPasswordError,
      AppCompatTextView tvEmailError, AppCompatTextView tvFirstNameError,
      AppCompatTextView tvLastName, AppCompatTextView tvPasswordError,
      AppCompatTextView tvPhoneNoError, AppCompatTextView tvPrivacyPolicy,
      AppCompatTextView tvTermCondition, AppCompatTextView txtConfirmPassword,
      AppCompatTextView txtEmailAddress, AppCompatTextView txtFirstName,
      AppCompatTextView txtLastName, AppCompatTextView txtPassword,
      AppCompatTextView txtPhoneNumber, AppCompatTextView txtSignin,
      AppCompatTextView txtTitleSignup) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnSignup = btnSignup;
    this.chkBoxAcceptPolicy = chkBoxAcceptPolicy;
    this.confirmPasswordContainer = confirmPasswordContainer;
    this.confirmPasswordValContainer = confirmPasswordValContainer;
    this.countryCode = countryCode;
    this.countryCodeSeprator = countryCodeSeprator;
    this.edtConfirmPassword = edtConfirmPassword;
    this.edtEmailId = edtEmailId;
    this.edtFirstName = edtFirstName;
    this.edtLastName = edtLastName;
    this.edtPassword = edtPassword;
    this.edtPhoneNumber = edtPhoneNumber;
    this.emailValContainer = emailValContainer;
    this.firstNameValContainer = firstNameValContainer;
    this.headerView = headerView;
    this.iconSignupForward = iconSignupForward;
    this.imgConfirmShowPassword = imgConfirmShowPassword;
    this.imgProfilePic = imgProfilePic;
    this.imgShowPassword = imgShowPassword;
    this.ivPasswordnfo = ivPasswordnfo;
    this.lastNameValContainer = lastNameValContainer;
    this.passwordContainer = passwordContainer;
    this.passwordValContainer = passwordValContainer;
    this.phoneNumberContainer = phoneNumberContainer;
    this.phoneNumberValContainer = phoneNumberValContainer;
    this.titleSignup = titleSignup;
    this.tvAgree = tvAgree;
    this.tvAnd = tvAnd;
    this.tvConfirmPasswordError = tvConfirmPasswordError;
    this.tvEmailError = tvEmailError;
    this.tvFirstNameError = tvFirstNameError;
    this.tvLastName = tvLastName;
    this.tvPasswordError = tvPasswordError;
    this.tvPhoneNoError = tvPhoneNoError;
    this.tvPrivacyPolicy = tvPrivacyPolicy;
    this.tvTermCondition = tvTermCondition;
    this.txtConfirmPassword = txtConfirmPassword;
    this.txtEmailAddress = txtEmailAddress;
    this.txtFirstName = txtFirstName;
    this.txtLastName = txtLastName;
    this.txtPassword = txtPassword;
    this.txtPhoneNumber = txtPhoneNumber;
    this.txtSignin = txtSignin;
    this.txtTitleSignup = txtTitleSignup;
  }

  public abstract void setMViewModel(@Nullable SignUpViewModel mViewModel);

  @Nullable
  public SignUpViewModel getMViewModel() {
    return mMViewModel;
  }

  @NonNull
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentSignUpBinding>inflateInternal(inflater, R.layout.fragment_sign_up, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentSignUpBinding>inflateInternal(inflater, R.layout.fragment_sign_up, null, false, component);
  }

  public static FragmentSignUpBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentSignUpBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentSignUpBinding)bind(component, view, R.layout.fragment_sign_up);
  }
}
