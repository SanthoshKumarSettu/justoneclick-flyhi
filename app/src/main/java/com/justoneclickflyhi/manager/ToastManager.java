package com.justoneclickflyhi.manager;

import android.content.Context;
import android.widget.Toast;

public class ToastManager {

	public ToastManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static void showToast(Context context, String toastMessage){
		
		Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
		
		
		
		
	}

}
