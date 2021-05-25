package com.mtlteam.retrofitweather;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class BaseActivity extends AppCompatActivity implements DialogInterface.OnCancelListener {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Check if {@link ProgressDialog} is visible.
     *
     * @return true/false if {@link ProgressDialog} is visible.
     */
    protected boolean isShowingProgressDialog() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    /**
     * Show {@link ProgressDialog}.
     *
     * @param message of {@link ProgressDialog}.
     */
    protected void showProgressDialog(String message) {
        closeProgressDialog();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.setOnCancelListener(this);
        mProgressDialog.show();
    }

    /**
     * Hide {@link ProgressDialog} if {@link #isShowingProgressDialog()} is true.
     */
    protected void closeProgressDialog() {
        if (isShowingProgressDialog()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {

    }
}
