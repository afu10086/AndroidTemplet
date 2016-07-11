package com.lidaofu.android.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidaofu.android.R;
import com.lidaofu.android.presenter.LoginPresenter;
import com.lidaofu.android.presenter.imp.LoginPresenterImp;
import com.lidaofu.android.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginPresenter.LoginView {


    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.edit_pass)
    EditText editPass;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenterImp(this);

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
