package com.atguigu.movietime.view.dialogloading;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class DialogTools {
    
    private static DialogTools mDialogTools;
    private LoadingDialog mLoadingDialog = null;
    
    public static DialogTools getInstance() {
        if (mDialogTools == null) {
            mDialogTools = new DialogTools();
        }
        return mDialogTools;
    }
    
    /**
     * 获取LoadingDialog对象
     * 
     * @return
     */
    public LoadingDialog getLoadingDialog() {
        return mLoadingDialog;
    }
    
    /**
     * 取消loading对话框
     */
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
    
    public void onCompleteLoadingDialog(String message,Drawable drawable){
    	if (mLoadingDialog != null) {
            mLoadingDialog.onLoadingComplete(message, drawable);
        }
    }
    /**
     * 显示loading对话框
     * @param message
     */
    public void showLoadingDialog(Activity activity, String message) {
        try {// 取消上一个加载框
            dismissLoadingDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mLoadingDialog = new LoadingDialog(activity);
        mLoadingDialog.setMessage(message);
        mLoadingDialog.show();
    }
    
    /**
     * 设置dialog点击back键取消时监听
     * 
     * @param listener
     */
    public void setOnBackCancelListener(LoadingDialog.IOnBackCancelListener listener) {
        if (mLoadingDialog != null) {
            mLoadingDialog.setOnBackCancelListener(listener);
        }
    }
}
