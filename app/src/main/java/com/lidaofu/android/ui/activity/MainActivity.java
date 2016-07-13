package com.lidaofu.android.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lidaofu.android.R;
import com.lidaofu.android.ui.base.ToolbarActivity;
import com.lidaofu.android.ui.fragment.FourFragment;
import com.lidaofu.android.ui.fragment.OneFragment;
import com.lidaofu.android.ui.fragment.ThreeFragment;
import com.lidaofu.android.ui.fragment.TwoFragment;
import com.lidaofu.android.view.MainBarView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LiDaofu on 16/7/11.
 * email: lidaofu_zlx@163.com
 */
public class MainActivity extends ToolbarActivity {


    @Bind(R.id.mainBarView)
    MainBarView mainBarView;

    private FragmentManager manager;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;

    private int[] titles={R.string.str_string_home,R.string.str_string_invest,R.string.str_string_money,R.string.str_string_me};

    @Override
    public void setupView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        mainBarView.setBarViewTitle(titles);
        updateFragment(1);
    }

    @Override
    public void setupListener() {

        mainBarView.setBarViewClick(new MainBarView.BarViewClick() {
            @Override
            public void onClick(View v, int location) {
                updateFragment(location);
            }
        });

    }

    private void updateFragment(int location) {
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        switch (location) {
            case 1:
                toolbar.setText(R.string.str_string_home);
                if (oneFragment == null) {
                    oneFragment = new OneFragment();
                    transaction.add(R.id.main_frag_content, oneFragment);
                } else {
                    transaction.show(oneFragment);
                }
                break;
            case 2:
                toolbar.setText(R.string.str_string_invest);
                if (twoFragment == null) {
                    twoFragment = new TwoFragment();
                    transaction.add(R.id.main_frag_content, twoFragment);
                } else {
                    transaction.show(twoFragment);
                }
                break;
            case 3:
                toolbar.setText(R.string.str_string_money);
                if (threeFragment == null) {
                    threeFragment = new ThreeFragment();
                    transaction.add(R.id.main_frag_content, threeFragment);
                } else {
                    transaction.show(threeFragment);
                }
                break;
            case 4:
                toolbar.setText(R.string.str_string_me);
                if (fourFragment == null) {
                    fourFragment = new FourFragment();
                    transaction.add(R.id.main_frag_content, fourFragment);
                } else {
                    transaction.show(fourFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (oneFragment != null) {
            transaction.hide(oneFragment);
        }
        if (twoFragment != null) {
            transaction.hide(twoFragment);
        }
        if (threeFragment != null) {
            transaction.hide(threeFragment);
        }
        if (fourFragment != null) {
            transaction.hide(fourFragment);
        }
    }

}
