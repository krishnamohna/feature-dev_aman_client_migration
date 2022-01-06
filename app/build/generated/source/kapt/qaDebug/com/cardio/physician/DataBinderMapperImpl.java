package com.cardio.physician;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cardio.physician.databinding.ActivityAuthenticateUserBindingImpl;
import com.cardio.physician.databinding.ActivityChangeEmailBindingImpl;
import com.cardio.physician.databinding.ActivityFitbitLoginBindingImpl;
import com.cardio.physician.databinding.ActivityRootBindingImpl;
import com.cardio.physician.databinding.ActivitySplashBindingImpl;
import com.cardio.physician.databinding.ActivityTutorialBindingImpl;
import com.cardio.physician.databinding.DialogProgressBarBindingImpl;
import com.cardio.physician.databinding.FragmentChangePasswordBindingImpl;
import com.cardio.physician.databinding.FragmentEditProfileBindingImpl;
import com.cardio.physician.databinding.FragmentForgotPasswordBindingImpl;
import com.cardio.physician.databinding.FragmentGetProfileBindingImpl;
import com.cardio.physician.databinding.FragmentLoginBindingImpl;
import com.cardio.physician.databinding.FragmentPhoneNumberVerificationBindingImpl;
import com.cardio.physician.databinding.FragmentProfileMenuBindingImpl;
import com.cardio.physician.databinding.FragmentSettingBindingImpl;
import com.cardio.physician.databinding.FragmentSignUpBindingImpl;
import com.cardio.physician.databinding.FragmentSyncHealthDataBindingImpl;
import com.cardio.physician.databinding.FragmentWebViewBindingImpl;
import com.cardio.physician.databinding.GuidelineLeftBindingImpl;
import com.cardio.physician.databinding.GuidelineRightBindingImpl;
import com.cardio.physician.databinding.ItemConnectionBindingImpl;
import com.cardio.physician.databinding.ItemPatientBindingImpl;
import com.cardio.physician.databinding.TutorialSlideLayoutBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYAUTHENTICATEUSER = 1;

  private static final int LAYOUT_ACTIVITYCHANGEEMAIL = 2;

  private static final int LAYOUT_ACTIVITYFITBITLOGIN = 3;

  private static final int LAYOUT_ACTIVITYROOT = 4;

  private static final int LAYOUT_ACTIVITYSPLASH = 5;

  private static final int LAYOUT_ACTIVITYTUTORIAL = 6;

  private static final int LAYOUT_DIALOGPROGRESSBAR = 7;

  private static final int LAYOUT_FRAGMENTCHANGEPASSWORD = 8;

  private static final int LAYOUT_FRAGMENTEDITPROFILE = 9;

  private static final int LAYOUT_FRAGMENTFORGOTPASSWORD = 10;

  private static final int LAYOUT_FRAGMENTGETPROFILE = 11;

  private static final int LAYOUT_FRAGMENTLOGIN = 12;

  private static final int LAYOUT_FRAGMENTPHONENUMBERVERIFICATION = 13;

  private static final int LAYOUT_FRAGMENTPROFILEMENU = 14;

  private static final int LAYOUT_FRAGMENTSETTING = 15;

  private static final int LAYOUT_FRAGMENTSIGNUP = 16;

  private static final int LAYOUT_FRAGMENTSYNCHEALTHDATA = 17;

  private static final int LAYOUT_FRAGMENTWEBVIEW = 18;

  private static final int LAYOUT_GUIDELINELEFT = 19;

  private static final int LAYOUT_GUIDELINERIGHT = 20;

  private static final int LAYOUT_ITEMCONNECTION = 21;

  private static final int LAYOUT_ITEMPATIENT = 22;

  private static final int LAYOUT_TUTORIALSLIDELAYOUT = 23;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(23);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.activity_authenticate_user, LAYOUT_ACTIVITYAUTHENTICATEUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.activity_change_email, LAYOUT_ACTIVITYCHANGEEMAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.activity_fitbit_login, LAYOUT_ACTIVITYFITBITLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.activity_root, LAYOUT_ACTIVITYROOT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.activity_splash, LAYOUT_ACTIVITYSPLASH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.activity_tutorial, LAYOUT_ACTIVITYTUTORIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.dialog_progress_bar, LAYOUT_DIALOGPROGRESSBAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_change_password, LAYOUT_FRAGMENTCHANGEPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_edit_profile, LAYOUT_FRAGMENTEDITPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_forgot_password, LAYOUT_FRAGMENTFORGOTPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_get_profile, LAYOUT_FRAGMENTGETPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_login, LAYOUT_FRAGMENTLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_phone_number_verification, LAYOUT_FRAGMENTPHONENUMBERVERIFICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_profile_menu, LAYOUT_FRAGMENTPROFILEMENU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_setting, LAYOUT_FRAGMENTSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_sign_up, LAYOUT_FRAGMENTSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_sync_health_data, LAYOUT_FRAGMENTSYNCHEALTHDATA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.fragment_web_view, LAYOUT_FRAGMENTWEBVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.guideline_left, LAYOUT_GUIDELINELEFT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.guideline_right, LAYOUT_GUIDELINERIGHT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.item_connection, LAYOUT_ITEMCONNECTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.item_patient, LAYOUT_ITEMPATIENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.cardio.physician.R.layout.tutorial_slide_layout, LAYOUT_TUTORIALSLIDELAYOUT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYAUTHENTICATEUSER: {
          if ("layout/activity_authenticate_user_0".equals(tag)) {
            return new ActivityAuthenticateUserBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_authenticate_user is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCHANGEEMAIL: {
          if ("layout/activity_change_email_0".equals(tag)) {
            return new ActivityChangeEmailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_change_email is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYFITBITLOGIN: {
          if ("layout/activity_fitbit_login_0".equals(tag)) {
            return new ActivityFitbitLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_fitbit_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYROOT: {
          if ("layout/activity_root_0".equals(tag)) {
            return new ActivityRootBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_root is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSPLASH: {
          if ("layout/activity_splash_0".equals(tag)) {
            return new ActivitySplashBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_splash is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYTUTORIAL: {
          if ("layout/activity_tutorial_0".equals(tag)) {
            return new ActivityTutorialBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_tutorial is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGPROGRESSBAR: {
          if ("layout/dialog_progress_bar_0".equals(tag)) {
            return new DialogProgressBarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_progress_bar is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCHANGEPASSWORD: {
          if ("layout/fragment_change_password_0".equals(tag)) {
            return new FragmentChangePasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_change_password is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTEDITPROFILE: {
          if ("layout/fragment_edit_profile_0".equals(tag)) {
            return new FragmentEditProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_edit_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFORGOTPASSWORD: {
          if ("layout/fragment_forgot_password_0".equals(tag)) {
            return new FragmentForgotPasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_forgot_password is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTGETPROFILE: {
          if ("layout/fragment_get_profile_0".equals(tag)) {
            return new FragmentGetProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_get_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGIN: {
          if ("layout/fragment_login_0".equals(tag)) {
            return new FragmentLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPHONENUMBERVERIFICATION: {
          if ("layout/fragment_phone_number_verification_0".equals(tag)) {
            return new FragmentPhoneNumberVerificationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_phone_number_verification is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILEMENU: {
          if ("layout/fragment_profile_menu_0".equals(tag)) {
            return new FragmentProfileMenuBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_menu is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSETTING: {
          if ("layout/fragment_setting_0".equals(tag)) {
            return new FragmentSettingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_setting is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSIGNUP: {
          if ("layout/fragment_sign_up_0".equals(tag)) {
            return new FragmentSignUpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sign_up is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSYNCHEALTHDATA: {
          if ("layout/fragment_sync_health_data_0".equals(tag)) {
            return new FragmentSyncHealthDataBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sync_health_data is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWEBVIEW: {
          if ("layout/fragment_web_view_0".equals(tag)) {
            return new FragmentWebViewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_web_view is invalid. Received: " + tag);
        }
        case  LAYOUT_GUIDELINELEFT: {
          if ("layout/guideline_left_0".equals(tag)) {
            return new GuidelineLeftBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for guideline_left is invalid. Received: " + tag);
        }
        case  LAYOUT_GUIDELINERIGHT: {
          if ("layout/guideline_right_0".equals(tag)) {
            return new GuidelineRightBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for guideline_right is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCONNECTION: {
          if ("layout/item_connection_0".equals(tag)) {
            return new ItemConnectionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_connection is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPATIENT: {
          if ("layout/item_patient_0".equals(tag)) {
            return new ItemPatientBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_patient is invalid. Received: " + tag);
        }
        case  LAYOUT_TUTORIALSLIDELAYOUT: {
          if ("layout/tutorial_slide_layout_0".equals(tag)) {
            return new TutorialSlideLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for tutorial_slide_layout is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "loading");
      sKeys.put(2, "mViewModel");
      sKeys.put(3, "userProfileViewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(23);

    static {
      sKeys.put("layout/activity_authenticate_user_0", com.cardio.physician.R.layout.activity_authenticate_user);
      sKeys.put("layout/activity_change_email_0", com.cardio.physician.R.layout.activity_change_email);
      sKeys.put("layout/activity_fitbit_login_0", com.cardio.physician.R.layout.activity_fitbit_login);
      sKeys.put("layout/activity_root_0", com.cardio.physician.R.layout.activity_root);
      sKeys.put("layout/activity_splash_0", com.cardio.physician.R.layout.activity_splash);
      sKeys.put("layout/activity_tutorial_0", com.cardio.physician.R.layout.activity_tutorial);
      sKeys.put("layout/dialog_progress_bar_0", com.cardio.physician.R.layout.dialog_progress_bar);
      sKeys.put("layout/fragment_change_password_0", com.cardio.physician.R.layout.fragment_change_password);
      sKeys.put("layout/fragment_edit_profile_0", com.cardio.physician.R.layout.fragment_edit_profile);
      sKeys.put("layout/fragment_forgot_password_0", com.cardio.physician.R.layout.fragment_forgot_password);
      sKeys.put("layout/fragment_get_profile_0", com.cardio.physician.R.layout.fragment_get_profile);
      sKeys.put("layout/fragment_login_0", com.cardio.physician.R.layout.fragment_login);
      sKeys.put("layout/fragment_phone_number_verification_0", com.cardio.physician.R.layout.fragment_phone_number_verification);
      sKeys.put("layout/fragment_profile_menu_0", com.cardio.physician.R.layout.fragment_profile_menu);
      sKeys.put("layout/fragment_setting_0", com.cardio.physician.R.layout.fragment_setting);
      sKeys.put("layout/fragment_sign_up_0", com.cardio.physician.R.layout.fragment_sign_up);
      sKeys.put("layout/fragment_sync_health_data_0", com.cardio.physician.R.layout.fragment_sync_health_data);
      sKeys.put("layout/fragment_web_view_0", com.cardio.physician.R.layout.fragment_web_view);
      sKeys.put("layout/guideline_left_0", com.cardio.physician.R.layout.guideline_left);
      sKeys.put("layout/guideline_right_0", com.cardio.physician.R.layout.guideline_right);
      sKeys.put("layout/item_connection_0", com.cardio.physician.R.layout.item_connection);
      sKeys.put("layout/item_patient_0", com.cardio.physician.R.layout.item_patient);
      sKeys.put("layout/tutorial_slide_layout_0", com.cardio.physician.R.layout.tutorial_slide_layout);
    }
  }
}
