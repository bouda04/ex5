package com.example.uibycode;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);

		RelativeLayout.LayoutParams rlp;

		rlp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,toPixels(200));

		LinearLayout layout1 = new LinearLayout(this);
		layout1.setId(R.id.sublayout1);
		layout1.setOrientation(LinearLayout.VERTICAL);
		layout1.setBackgroundResource(R.drawable.border);
		rlp.addRule(RelativeLayout.BELOW, R.id.IDIntro);
		rlp.setMargins(0,toPixels(20),0,0);
		mainLayout.addView(layout1, rlp);


		TextView tvIntro = new TextView(this);
		tvIntro.setText(R.string.title1);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		llp.gravity = Gravity.CENTER;
		layout1.addView(tvIntro,llp);

		LinearLayout llCheckBoxes = new LinearLayout(this);
		llCheckBoxes.setId(View.generateViewId());
		llCheckBoxes.setOrientation(LinearLayout.HORIZONTAL);

		CheckBox cb1 = new CheckBox(this);
		cb1.setText(R.string.cb1);
		CheckBox cb2 = new CheckBox(this);
		cb2.setText(R.string.cb2);

		llCheckBoxes.addView(cb1);
		llCheckBoxes.addView(cb2);

		llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		llp.gravity = Gravity.CENTER;

		llp.setMargins(0, toPixels(50), 0, 0);
		layout1.addView(llCheckBoxes, llp);


		LinearLayout layoutFromXml =  (LinearLayout)LayoutInflater.from(MainActivity.this).inflate(R.layout.sublayout2, mainLayout, false);
		rlp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,toPixels(180));
		rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		rlp.setMargins(0, toPixels(120), 0, 0);
		mainLayout.addView(layoutFromXml,rlp);

	}
	
	private int toPixels(int dp){
	    float scale = this.getResources().getDisplayMetrics().densityDpi/ DisplayMetrics.DENSITY_DEFAULT;
		return (int)(dp * scale);

	}

	private class CheckBoxListener implements CheckBox.OnCheckedChangeListener {
		int layoutID;
		
		public CheckBoxListener(int layoutID){
			this.layoutID =  layoutID;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			ViewGroup mainLayout = (ViewGroup) findViewById(R.id.main_layout);
			if (isChecked){
		       	LinearLayout layoutFromXml =  (LinearLayout)LayoutInflater.from(MainActivity.this).inflate(this.layoutID, mainLayout, false);  
		       	RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,toPixels(180));
				if (layoutID == R.layout.sublayout1)
					lp.addRule(RelativeLayout.BELOW, 0);
				else 
					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				lp.setMargins(0, toPixels(120), 0, 0);
				mainLayout.addView(layoutFromXml,lp);  					
			}
			else{
				View v2Delete = findViewById(layoutID == R.layout.sublayout1?R.id.sublayout1:R.id.sublayout2);
				mainLayout.removeView(v2Delete);
				mainLayout.invalidate();
			}
		
		}
	
	}
}
