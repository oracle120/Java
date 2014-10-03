package com.gqz.v_bo.ui;

import com.gqz.v_bo.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

public class AuthActivity extends Activity{

	private Dialog dialog;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_auth);
		
		View diaView=View.inflate(this, R.layout.authorize_dialog, null);

		dialog=new Dialog(this,R.style.dialog);
		dialog.setContentView(diaView);
		dialog.show();
	}

}
