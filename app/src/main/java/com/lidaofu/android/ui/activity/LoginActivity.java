package com.lidaofu.android.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidaofu.android.R;
import com.lidaofu.android.presenter.LoginPresenter;
import com.lidaofu.android.presenter.imp.LoginPresenterImp;
import com.lidaofu.android.ui.base.ToolbarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends ToolbarActivity implements LoginPresenter.LoginView {


    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.edit_pass)
    EditText editPass;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    private LoginPresenter presenter;

    @Override
    public void setupView() {
        setContentView(R.layout.activity_login);
        toolbar.setText("登录");
        ButterKnife.bind(this);
        presenter = new LoginPresenterImp(this);

    }

    @Override
    public void setupListener() {

        //登录
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginByHttp();
            }
        });

    }


    @Override
    public String getUserName() {
        return editName.getText().toString();
    }

    @Override
    public String getUserPass() {
        return editPass.getText().toString();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
