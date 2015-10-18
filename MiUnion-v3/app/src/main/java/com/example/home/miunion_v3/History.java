package com.example.home.miunion_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by divahar on 9/26/2015.
 */
public class History extends ActionBarActivity {
    Button show;
    CardView cv1,cv2,cv3;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12;
    String rest,pg,data3,data4;

    public static final String PAGE_GOT = "PAGE_GOT";
    public static final String RESULT_GOT = "RESULT_GOT";
    public static final String DATA3 = "DATA3";
    public static final String DATA4 = "DATA4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* LinearLayout linearLayout = (LinearLayout)findViewById(R.id.bg);
        Resources res = getResources();
        Drawable portrait = res.getDrawable(R.drawable.bg);
        Drawable landscape = res.getDrawable(R.drawable.bg1);

        WindowManager window = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display display = window.getDefaultDisplay();
        int num = display.getRotation();
        if (num == 0){
            linearLayout.setBackgroundDrawable(portrait);
        }else if (num == 1 || num == 3){
            linearLayout.setBackgroundDrawable(landscape);
        }else{
            linearLayout.setBackgroundDrawable(portrait);
        }*/
        try
        {
            Intent intent = getIntent();
            String UN =intent.getStringExtra(Admin.RESULT_GOT);
            //textView=(TextView) findViewById(R.id.tv);
            rest = intent.getStringExtra(UserProfile.DATA1);
            pg = intent.getStringExtra(UserProfile.DATA2);
            data3 =intent.getStringExtra(Hospital.DATA3);
            data4 =intent.getStringExtra(Hospital.DATA4);

            tv1=(TextView) findViewById(R.id.t5);
            tv2=(TextView) findViewById(R.id.t6);
            tv3=(TextView) findViewById(R.id.t7);
            tv4=(TextView) findViewById(R.id.t8);

            tv5=(TextView) findViewById(R.id.t51);
            tv6=(TextView) findViewById(R.id.t61);
            tv7=(TextView) findViewById(R.id.t71);
            tv8=(TextView) findViewById(R.id.t81);

            tv9=(TextView) findViewById(R.id.t52);
            tv10=(TextView) findViewById(R.id.t62);
            tv11=(TextView) findViewById(R.id.t72);
            tv12=(TextView) findViewById(R.id.t82);
            UN=UN.replace("--", ",");
            String[] arr= UN.split(",");

            tv1.setText(arr[0]);
            tv2.setText(arr[1]);
            tv3.setText(arr[2]);

            tv4.setText(arr[3]);
            tv5.setText(arr[4]);
            tv6.setText(arr[5]);

            tv7.setText(arr[6]);
            tv8.setText(arr[7]);
            tv9.setText(arr[8]);

            tv10.setText(arr[9]);
            tv11.setText(arr[10]);
            tv12.setText(arr[11]);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.about:
                Intent intent1=new Intent(History.this,About.class);
                intent1.putExtra(RESULT_GOT,rest);
                intent1.putExtra(PAGE_GOT,pg);
                intent1.putExtra(DATA3,data3);
                intent1.putExtra(DATA4,data4);

                startActivity(intent1);
                break;
            // action with ID action_settings was selected
            case R.id.logout:
               Intent intent=new Intent(History.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }

        return true;
    }
}
