package com.gqz.v_bo.ui;

import com.gqz.v_bo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_login);
		
		Intent intent = new Intent(this, AuthActivity.class);
		startActivity(intent);
	}

}
