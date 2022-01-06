package com.cardio.physician.ui;

import com.cardio.physician.di.ActivityModule;
import com.cardio.physician.di.ApplicationModule;
import com.cardio.physician.di.FragmentModule;
import com.cardio.physician.di.NetworkModule;
import com.cardio.physician.di.RepositaryModule;
import com.cardio.physician.di.ServiceModule;
import com.cardio.physician.di.ViewModelModule;
import com.cardio.physician.ui.common.base.activity.BaseActivity_GeneratedInjector;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel_HiltModules;
import com.cardio.physician.ui.service.SyncHeathDataService_GeneratedInjector;
import com.cardio.physician.ui.views.add_patient.AddPatientActivity_GeneratedInjector;
import com.cardio.physician.ui.views.add_patient.AddPatientViewModel_HiltModules;
import com.cardio.physician.ui.views.auth.AuthenticateUserActivity_GeneratedInjector;
import com.cardio.physician.ui.views.auth.login.LoginFragment_GeneratedInjector;
import com.cardio.physician.ui.views.auth.login.LoginViewModel_HiltModules;
import com.cardio.physician.ui.views.auth.phone_verification.PhoneNumberVerificationFragment_GeneratedInjector;
import com.cardio.physician.ui.views.auth.phone_verification.PhoneVerificationViewModel_HiltModules;
import com.cardio.physician.ui.views.auth.signup.SignUpFragment_GeneratedInjector;
import com.cardio.physician.ui.views.auth.signup.SignUpViewModel_HiltModules;
import com.cardio.physician.ui.views.change_email.ChangeEmailActivity_GeneratedInjector;
import com.cardio.physician.ui.views.change_email.ChangeEmailViewModel_HiltModules;
import com.cardio.physician.ui.views.dashboard.DashboardActivity_GeneratedInjector;
import com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment_GeneratedInjector;
import com.cardio.physician.ui.views.dashboard.fragment.DashboardViewModel_HiltModules;
import com.cardio.physician.ui.views.dashboard.fragment.PatientDashboardFragment_GeneratedInjector;
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivityViewModel_HiltModules;
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity_GeneratedInjector;
import com.cardio.physician.ui.views.diagnosis.EditDiagnosisActivity_GeneratedInjector;
import com.cardio.physician.ui.views.diagnosis.step1.DiagnosisFragmentStep1_GeneratedInjector;
import com.cardio.physician.ui.views.diagnosis.step1.DiagnosisViewStep1ViewModel_HiltModules;
import com.cardio.physician.ui.views.diagnosis.step2.DiagnosisFragmentStep2_GeneratedInjector;
import com.cardio.physician.ui.views.diagnosis.step2.DiagnosisStep2ViewModel_HiltModules;
import com.cardio.physician.ui.views.diagnosis.step3.DiagnosisFragmentStep3ViewModel_HiltModules;
import com.cardio.physician.ui.views.diagnosis.step3.DiagnosisFragmentStep3_GeneratedInjector;
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel_HiltModules;
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4_GeneratedInjector;
import com.cardio.physician.ui.views.forgot_password.ForgotPasswordFragment_GeneratedInjector;
import com.cardio.physician.ui.views.forgot_password.ForgotPasswordViewModel_HiltModules;
import com.cardio.physician.ui.views.healthlogs.HealthLogsActivity_GeneratedInjector;
import com.cardio.physician.ui.views.healthlogs.HealthLogsViewModel_HiltModules;
import com.cardio.physician.ui.views.illness.IllnessActivity_GeneratedInjector;
import com.cardio.physician.ui.views.notifications.NotificationsActivity_GeneratedInjector;
import com.cardio.physician.ui.views.notifications.NotificatonsViewModel_HiltModules;
import com.cardio.physician.ui.views.profile.change_password.ChangePasswordFragment_GeneratedInjector;
import com.cardio.physician.ui.views.profile.change_password.ChangePasswordViewModel_HiltModules;
import com.cardio.physician.ui.views.profile.editprofile.EditProfileFragment_GeneratedInjector;
import com.cardio.physician.ui.views.profile.editprofile.EditUserProfileViewModel_HiltModules;
import com.cardio.physician.ui.views.profile.profile.GetProfileFragment_GeneratedInjector;
import com.cardio.physician.ui.views.profile.profile.UserProfileViewModel_HiltModules;
import com.cardio.physician.ui.views.profile.profile_menu.ProfileMenuFragment_GeneratedInjector;
import com.cardio.physician.ui.views.profile.setting.SettingFragment_GeneratedInjector;
import com.cardio.physician.ui.views.profile.setting.SettingViewModel_HiltModules;
import com.cardio.physician.ui.views.profile.web_view.WebViewFragment_GeneratedInjector;
import com.cardio.physician.ui.views.splash.SplashActivity_GeneratedInjector;
import com.cardio.physician.ui.views.sync_health_data.SyncHealthDataFragment_GeneratedInjector;
import com.cardio.physician.ui.views.sync_health_data.SyncHealthViewModel_HiltModules;
import com.cardio.physician.ui.views.sync_health_data.activity.SyncHealthActivty_GeneratedInjector;
import com.cardio.physician.ui.views.tutorial.TutorialActivity_GeneratedInjector;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.inject.Singleton;

public final class AppCardioPatient_HiltComponents {
  private AppCardioPatient_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          ApplicationContextModule.class,
          ApplicationModule.class,
          NetworkModule.class,
          RepositaryModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements AppCardioPatient_GeneratedInjector,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent {
  }

  @Subcomponent(
      modules = ServiceModule.class
  )
  @ServiceScoped
  public abstract static class ServiceC implements SyncHeathDataService_GeneratedInjector,
      ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AddPatientViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          BaseAuthViewModel_HiltModules.KeyModule.class,
          ChangeEmailViewModel_HiltModules.KeyModule.class,
          ChangePasswordViewModel_HiltModules.KeyModule.class,
          DashboardViewModel_HiltModules.KeyModule.class,
          DiagnosisActivityViewModel_HiltModules.KeyModule.class,
          DiagnosisFragmentStep3ViewModel_HiltModules.KeyModule.class,
          DiagnosisFragmentStep4ViewModel_HiltModules.KeyModule.class,
          DiagnosisStep2ViewModel_HiltModules.KeyModule.class,
          DiagnosisViewStep1ViewModel_HiltModules.KeyModule.class,
          EditUserProfileViewModel_HiltModules.KeyModule.class,
          ForgotPasswordViewModel_HiltModules.KeyModule.class,
          HealthLogsViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          LoginViewModel_HiltModules.KeyModule.class,
          NotificatonsViewModel_HiltModules.KeyModule.class,
          PhoneVerificationViewModel_HiltModules.KeyModule.class,
          SettingViewModel_HiltModules.KeyModule.class,
          SignUpViewModel_HiltModules.KeyModule.class,
          SyncHealthViewModel_HiltModules.KeyModule.class,
          UserProfileViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ActivityModule.class,
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements BaseActivity_GeneratedInjector,
      AddPatientActivity_GeneratedInjector,
      AuthenticateUserActivity_GeneratedInjector,
      ChangeEmailActivity_GeneratedInjector,
      DashboardActivity_GeneratedInjector,
      DiagnosisActivity_GeneratedInjector,
      EditDiagnosisActivity_GeneratedInjector,
      HealthLogsActivity_GeneratedInjector,
      IllnessActivity_GeneratedInjector,
      NotificationsActivity_GeneratedInjector,
      SplashActivity_GeneratedInjector,
      SyncHealthActivty_GeneratedInjector,
      TutorialActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AddPatientViewModel_HiltModules.BindsModule.class,
          BaseAuthViewModel_HiltModules.BindsModule.class,
          ChangeEmailViewModel_HiltModules.BindsModule.class,
          ChangePasswordViewModel_HiltModules.BindsModule.class,
          DashboardViewModel_HiltModules.BindsModule.class,
          DiagnosisActivityViewModel_HiltModules.BindsModule.class,
          DiagnosisFragmentStep3ViewModel_HiltModules.BindsModule.class,
          DiagnosisFragmentStep4ViewModel_HiltModules.BindsModule.class,
          DiagnosisStep2ViewModel_HiltModules.BindsModule.class,
          DiagnosisViewStep1ViewModel_HiltModules.BindsModule.class,
          EditUserProfileViewModel_HiltModules.BindsModule.class,
          ForgotPasswordViewModel_HiltModules.BindsModule.class,
          HealthLogsViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          LoginViewModel_HiltModules.BindsModule.class,
          NotificatonsViewModel_HiltModules.BindsModule.class,
          PhoneVerificationViewModel_HiltModules.BindsModule.class,
          SettingViewModel_HiltModules.BindsModule.class,
          SignUpViewModel_HiltModules.BindsModule.class,
          SyncHealthViewModel_HiltModules.BindsModule.class,
          UserProfileViewModel_HiltModules.BindsModule.class,
          ViewModelModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ViewWithFragmentCBuilderModule.class,
          FragmentModule.class
      }
  )
  @FragmentScoped
  public abstract static class FragmentC implements LoginFragment_GeneratedInjector,
      PhoneNumberVerificationFragment_GeneratedInjector,
      SignUpFragment_GeneratedInjector,
      DashboardFragment_GeneratedInjector,
      PatientDashboardFragment_GeneratedInjector,
      DiagnosisFragmentStep1_GeneratedInjector,
      DiagnosisFragmentStep2_GeneratedInjector,
      DiagnosisFragmentStep3_GeneratedInjector,
      DiagnosisFragmentStep4_GeneratedInjector,
      ForgotPasswordFragment_GeneratedInjector,
      ChangePasswordFragment_GeneratedInjector,
      EditProfileFragment_GeneratedInjector,
      GetProfileFragment_GeneratedInjector,
      ProfileMenuFragment_GeneratedInjector,
      SettingFragment_GeneratedInjector,
      WebViewFragment_GeneratedInjector,
      SyncHealthDataFragment_GeneratedInjector,
      FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
