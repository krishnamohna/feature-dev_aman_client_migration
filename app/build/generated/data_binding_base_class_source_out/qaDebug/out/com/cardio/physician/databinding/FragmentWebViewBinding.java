// Generated by data binding compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cardio.physician.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentWebViewBinding extends ViewDataBinding {
  @NonNull
  public final ToolbarBinding headerView;

  @NonNull
  public final WebView webView;

  protected FragmentWebViewBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ToolbarBinding headerView, WebView webView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.headerView = headerView;
    this.webView = webView;
  }

  @NonNull
  public static FragmentWebViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentWebViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentWebViewBinding>inflateInternal(inflater, R.layout.fragment_web_view, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentWebViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentWebViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentWebViewBinding>inflateInternal(inflater, R.layout.fragment_web_view, null, false, component);
  }

  public static FragmentWebViewBinding bind(@NonNull View view) {
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
  public static FragmentWebViewBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentWebViewBinding)bind(component, view, R.layout.fragment_web_view);
  }
}
