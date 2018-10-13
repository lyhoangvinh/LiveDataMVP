package com.dev.lyhoangvinh.livedata.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import com.dev.lyhoangvinh.livedata.R;
import com.dev.lyhoangvinh.livedata.base.fragment.BaseFragment;

//@SuppressWarnings({"unchecked"})
public abstract class BaseSingleFragmentActivity<T extends BaseFragment> extends BaseActivity {

    @Override
    public void initialize(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(getContainerId(), createFragment(), null);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.container;
    }

    protected abstract T createFragment();

    @Nullable
    public T getFragment() {
        // noinspection unchecked
        return (T) getSupportFragmentManager().findFragmentById(getContainerId());
    }

    @IdRes
    protected int getContainerId() {
        return R.id.container;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        T fragment = getFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        T fragment = getFragment();
        if (fragment != null) {
            if (!fragment.onBackPressed()) {
                finishWithAnimation();
            }
        }
    }
}
