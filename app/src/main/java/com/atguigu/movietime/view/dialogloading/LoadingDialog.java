package com.atguigu.movietime.view.dialogloading;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.movietime.R;
import com.atguigu.movietime.utils.BaseKeyConstants;


/**
 * 自定义laoding dialog
 * 
 * @author itlanbao
 *
 */
public class LoadingDialog extends Dialog {
	
	private IOnBackCancelListener listener;
	
	private String sMessage = "";
	private TextView messageView;
	private ImageView loadingcompleteImg,deleteImg ;
	private ProgressBar progressBar ;
	/**是否加载完成**/
	private boolean isCompleted = false ;
	private RelativeLayout bglayout ;
	private Activity mActivity = null;
	
	public LoadingDialog(Activity context) {
	    super(context, R.style.alert_dialog);
	    this.mActivity = context;
	    sMessage = context.getResources().getString(R.string.loading_text);
	   
	}
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	if (savedInstanceState != null) {
    		sMessage = savedInstanceState.getString(BaseKeyConstants.KEY_NOTIFMESSAGE);
    	}
    	
    	setContentView(R.layout.loading_dialog_layout);
    	
    	progressBar = (ProgressBar) findViewById(R.id.loading_dialog_progressBar);
    	loadingcompleteImg = (ImageView) findViewById(R.id.loading_dialog_complete);
    	deleteImg = (ImageView) findViewById(R.id.delete_img);
    	messageView = (TextView) findViewById(R.id.loading_message);
	    bglayout = (RelativeLayout) findViewById(R.id.bg_layout);
    	
	    deleteImg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 if (listener != null) {
		            listener.onBackCancel(LoadingDialog.this);
		        }
				getSuperDismiss();
			}
		});
    }
    
    public void setMessage(String message) {
    	this.sMessage = message;
    	setLoadingText();
	}
    
    /***
     * 加载完成的状态设置
     * @param message
     * @param drawable
     */
    public void onLoadingComplete(String message,Drawable drawable){
    	this.sMessage = message;
    	if (bglayout != null) {
    	    bglayout.setBackgroundResource(R.drawable.toast_bg);
    	    
    	}
    	if (deleteImg != null) {
    	    deleteImg.setVisibility(View.GONE);
    	}
    	setLoadingText();
    	setLoadingDrawable(drawable);
    	isCompleted = true ;
    	dismiss();
    }
    
    private void setLoadingDrawable(Drawable drawable){
    	if(drawable != null && loadingcompleteImg != null){
    		loadingcompleteImg.setImageDrawable(drawable);
    		loadingcompleteImg.setVisibility(View.VISIBLE);
    		if (progressBar != null) {
    		    progressBar.setVisibility(View.GONE);
    		}
    	}
    	
    }
    
    private void setLoadingText(){
    	if(messageView != null && !TextUtils.isEmpty(sMessage)){
    		messageView.setText(sMessage);
    		messageView.setVisibility(View.VISIBLE);
    	}
    }
    
    @Override
    public void dismiss() {
        if (isShowing()) {
            try {
            	if(isCompleted) {
            	    new Handler().postDelayed(new Runnable() {
                        
                        @Override
                        public void run() {
                            getSuperDismiss();
                        }
                    }, 1000);
            	}else {
            		getSuperDismiss();
            	}
            } catch (Exception e) {
            	e.printStackTrace();
            	getSuperDismiss();
            }
        }
    }
    
    private void getSuperDismiss(){
    	if (mActivity != null && !mActivity.isFinishing()) {
    	    try {
                super.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 禁止触摸屏幕取消加载框
            setCancelable(false);
        }
        return super.onTouchEvent(event);
    }
    
 
    @Override
    public void onBackPressed() {
        setCancelable(true);
        if (listener != null) {
            listener.onBackCancel(this);
        }
        
        getSuperDismiss();
        super.onBackPressed();
    }
    
    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        if (progressBar != null) {
        	progressBar.setVisibility(View.VISIBLE);
            setLoadingText();
        }
        
        if(loadingcompleteImg != null ){
        	loadingcompleteImg.setVisibility(View.GONE);
        }
        
    }
    
    /**
     * 点击back键取消loading提示框监听
     * 
     * @param listener
     */
    public void setOnBackCancelListener(IOnBackCancelListener listener) {
    	this.listener = listener;
    }
    
    public interface IOnBackCancelListener {
    	public void onBackCancel(DialogInterface dialog);
    }
    
}
