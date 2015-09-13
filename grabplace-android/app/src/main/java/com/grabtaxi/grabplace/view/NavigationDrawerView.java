package com.grabtaxi.grabplace.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.facebook.login.LoginManager;
import com.grabtaxi.grabplace.R;
import com.grabtaxi.grabplace.ui.login.LoginActivity;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class NavigationDrawerView extends ScrollView implements View.OnClickListener
{
    public NavigationDrawerView(final Context context)
    {
        this(context, null);
    }

    public NavigationDrawerView(final Context context, final AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public NavigationDrawerView(final Context context, final AttributeSet attrs, final int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context)
    {
        if (this.isInEditMode())
        {
            return;
        }

        inflate(context, R.layout.view_navigation_list, this);
        this.findViewById(R.id.action_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view)
    {
        switch (view.getId())
        {
            case R.id.action_logout:
            {
                LoginManager.getInstance().logOut();
                this.getContext().startActivity(LoginActivity.newIntent(this.getContext()));
                ((Activity) this.getContext()).finish();
                break;
            }
        }
    }
}
