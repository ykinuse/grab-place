package com.grabtaxi.grabplace.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.grabtaxi.grabplace.R;
import com.grabtaxi.grabplace.ui.AActivity;
import com.grabtaxi.grabplace.ui.main.MainActivity;
import com.grabtaxi.grabplace.utils.Logger;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class LoginActivity extends AActivity implements FacebookCallback<LoginResult>, GraphRequest.GraphJSONObjectCallback
{
    private static final String       TAG                      = LoginActivity.class.getSimpleName();
    private static final Bundle       FACEBOOK_ME_PERMISSION   = new Bundle();
    private static final List<String> FACEBOOK_READ_PERMISSION = Arrays.asList("email", "public_profile");
    CallbackManager mFacebookCallbackManager;
    LoginButton     mFacebookLogin;

    public static Intent newIntent(final Context context)
    {
        final Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (this.isLogeedIn())
        {
            goToMain();
            return;
        }

        this.mFacebookLogin = (LoginButton) this.findViewById(R.id.facebook_login_button);

        // Add Facebook read permission
        this.mFacebookLogin.setReadPermissions(LoginActivity.FACEBOOK_READ_PERMISSION);

        // Facebook Callback registration
        this.mFacebookCallbackManager = CallbackManager.Factory.create();
        this.mFacebookLogin.registerCallback(this.mFacebookCallbackManager, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        this.mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(final LoginResult loginResult)
    {
        if (loginResult == null || loginResult.getAccessToken() == null)
        {
            return;
        }

        Logger.debug(LoginActivity.TAG, loginResult.getAccessToken().getApplicationId());
        Logger.debug(LoginActivity.TAG, loginResult.getAccessToken().getToken());
        Logger.debug(LoginActivity.TAG, loginResult.getAccessToken().getUserId());

        // Request me
        final GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), this);
        request.setParameters(LoginActivity.FACEBOOK_ME_PERMISSION);
        request.executeAsync();
    }

    @Override
    public void onCancel()
    {
        Logger.debug(LoginActivity.TAG, "onCancel()");
    }

    @Override
    public void onError(final FacebookException error)
    {
        error.printStackTrace();
    }

    @Override
    public void onCompleted(final JSONObject object, final GraphResponse response)
    {
        if (response!=null && response.getError() != null)
        {
            Logger.debug(LoginActivity.TAG, response.getError().toString());
            return;
        }

        Logger.debug(LoginActivity.TAG, object.toString());
        goToMain();
    }

    private void goToMain()
    {
        this.startActivity(MainActivity.newIntent(this));
        this.finish();
    }

    static
    {
        LoginActivity.FACEBOOK_ME_PERMISSION.putString("fields", "id, name, email, gender, birthday");
    }
}
