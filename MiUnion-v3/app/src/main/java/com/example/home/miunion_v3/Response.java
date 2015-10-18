package com.example.home.miunion_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by divahar on 9/14/2015.
 */
public class Response extends ActionBarActivity{

    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    String rest,pg,data3,data4;

    public static final String PAGE_GOT = "PAGE_GOT";
    public static final String RESULT_GOT = "RESULT_GOT";
    public static final String DATA3 = "DATA3";
    public static final String DATA4 = "DATA4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.response);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    try {
        Intent intent = getIntent();
        String UN = intent.getStringExtra(UserProfile.RESULT_GOT);
        String title = intent.getStringExtra(UserProfile.TITLE);
         rest = intent.getStringExtra(UserProfile.DATA1);
         pg = intent.getStringExtra(UserProfile.DATA2);
        data3 = intent.getStringExtra(UserProfile.DATA3);
        data4 = intent.getStringExtra(UserProfile.DATA4);
       // Toast.makeText(getApplicationContext(), rest+"=="+pg, Toast.LENGTH_LONG).show();
        //textView=(TextView) findViewById(R.id.tv);
        setTitle(title);
        tv1 = (TextView) findViewById(R.id.TextView02);
        tv2 = (TextView) findViewById(R.id.TextView04);
        tv3 = (TextView) findViewById(R.id.TextView06);
        tv4 = (TextView) findViewById(R.id.TextView08);
        TextView td ;
        //textView.setText("Welcome User "+ UN);
        String[] am = UN.split("_");
        if (am[0].equalsIgnoreCase("Lab")) {

            td= (TextView) findViewById(R.id.des);
            td.setText("Recommended Lab based on your recent diagnosis.");
            tv5 = (TextView) findViewById(R.id.textView);
            tv5.setText("Lab Name");
            String[] arr = UN.split(",");
            tv1.setText(arr[0]);
            tv2.setText(arr[1]);
            tv3.setText(arr[2]);
            tv4.setText(arr[3]);

        } else if (am[0].equalsIgnoreCase("Camp")) {
            td= (TextView) findViewById(R.id.des);
            td.setText("Recommended Hospital which organises camp based on your diagnosis.");
            tv5 = (TextView) findViewById(R.id.textView);
            tv6 = (TextView) findViewById(R.id.TextView05);
            tv5.setText("Organiser");
            tv6.setText("Camp Name");
            String[] arr = UN.split(",");
            tv1.setText(arr[0]);
            tv2.setText(arr[1]);
            tv3.setText(arr[2]);
            tv4.setText(arr[3]);

        } else {
            td= (TextView) findViewById(R.id.des);
            td.setText("Recommended Hospital based on your recent diseases.");
            String[] arr = UN.split(",");
            tv1.setText(arr[0]);
            tv2.setText(arr[1]);
            tv3.setText(arr[2]);
            tv4.setText(arr[3]);

        }

    }
    catch (Exception e)
    {
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
    }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.about:
                Intent intent1=new Intent(Response.this,About.class);
                intent1.putExtra(RESULT_GOT,rest);
                intent1.putExtra(PAGE_GOT,pg);
                intent1.putExtra(DATA3,data3);
                intent1.putExtra(DATA4,data4);
                startActivity(intent1);
                break;
            // action with ID action_settings was selected
            case R.id.logout:
                Intent intent=new Intent(Response.this,MainActivity.class);
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
