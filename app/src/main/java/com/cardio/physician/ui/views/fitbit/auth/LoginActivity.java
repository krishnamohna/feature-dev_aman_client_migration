package com.cardio.physician.ui.views.fitbit.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cardio.physician.R;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthorizationController;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.ClientCredentials;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;
import com.cardio.physician.databinding.ActivityFitbitLoginBinding;

import java.util.HashSet;
import java.util.Set;


public class LoginActivity extends AppCompatActivity implements AuthenticationHandler {

    public static final String CONFIGURATION_VERSION = "CONFIGURATION_VERSION";
    public static final String AUTHENTICATION_RESULT_KEY = "AUTHENTICATION_RESULT_KEY";
    private static final String CLIENT_CREDENTIALS_KEY = "CLIENT_CREDENTIALS_KEY";
    private static final String EXPIRES_IN_KEY = "EXPIRES_IN_KEY";
    private static final String SCOPES_KEY = "SCOPES_KEY";
    private ActivityFitbitLoginBinding binding;
    private AuthorizationController authorizationController;

    public static Intent createIntent(Context context, @NonNull ClientCredentials clientCredentials, @Nullable Long expiresIn, Set<Scope> scopes) {
        return createIntent(context, null, clientCredentials, expiresIn, scopes);
    }

    public static Intent createIntent(Context context, Integer configVersion, @NonNull ClientCredentials clientCredentials, @Nullable Long expiresIn, Set<Scope> scopes) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(CLIENT_CREDENTIALS_KEY, clientCredentials);
        intent.putExtra(CONFIGURATION_VERSION, configVersion);
        intent.putExtra(EXPIRES_IN_KEY, expiresIn);
        intent.putExtra(SCOPES_KEY, scopes.toArray(new Scope[scopes.size()]));

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fitbit_login);

        ClientCredentials clientCredentials = getIntent().getParcelableExtra(CLIENT_CREDENTIALS_KEY);
        Long expiresIn = getIntent().getLongExtra(EXPIRES_IN_KEY, 604800);
        Parcelable[] scopes = getIntent().getParcelableArrayExtra(SCOPES_KEY);
        Set<Scope> scopesSet = new HashSet<>();
        for (Parcelable scope : scopes) {
            scopesSet.add((Scope) scope);
        }

        binding.loginWebview.getSettings().setJavaScriptEnabled(true);
        binding.loginWebview.getSettings().setLoadsImagesAutomatically(true);
        binding.loginWebview.getSettings().setDomStorageEnabled(true);
        binding.loginWebview.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Mobile Safari/537.36");
        binding.loginWebview.getSettings().setUseWideViewPort(true);
        binding.loginWebview.getSettings().setBuiltInZoomControls(true);
        binding.loginWebview.getSettings().setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.loginWebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        authorizationController = new AuthorizationController(
                binding.loginWebview,
                clientCredentials,
                this);
        if(getIntent().getAction()==null) {
            authorizationController.authenticate(expiresIn, scopesSet, this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getAction().equalsIgnoreCase("android.intent.action.VIEW")){
            authorizationController.onUrlChanged(intent.getDataString());
        }
    }

    @Override
    public void onAuthFinished(AuthenticationResult result) {
        binding.loginWebview.setVisibility(View.GONE);
        Intent resultIntent = new Intent();
        resultIntent.putExtra(AUTHENTICATION_RESULT_KEY, result);
        resultIntent.putExtra(CONFIGURATION_VERSION, getIntent().getIntExtra(CONFIGURATION_VERSION, 0));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
