package com.example.home.miunion_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class About extends ActionBarActivity {
    String result,name,data3,data4;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    public static final String USER_NAME = "USER_NAME";
    public static final String ID = "ID";
    public static final String ID1 = "ID1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.bg);
      /*  Resources res = getResources();
        Drawable portrait = res.getDrawable(R.drawable.bgg22);
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
         try {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Intent intent = getIntent();
                result=intent.getStringExtra(Hospital.RESULT_GOT);
                name=intent.getStringExtra(Hospital.PAGE_GOT);
                data3=intent.getStringExtra(Hospital.DATA3);
                data4=intent.getStringExtra(Hospital.DATA4);
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
             if(name.equalsIgnoreCase("user")) {
                 t1 = (TextView) findViewById(R.id.id1);
                 t2 = (TextView) findViewById(R.id.age1);
                 t3 = (TextView) findViewById(R.id.gender1);
                 t4 = (TextView) findViewById(R.id.dob1);
                 t5 = (TextView) findViewById(R.id.col1);
                 t6 = (TextView) findViewById(R.id.matst1);
                 t7 = (TextView) findViewById(R.id.lang1);
                 t8 = (TextView) findViewById(R.id.loc1);

                 String[] arr = result.split("_");
                 t1.setText(arr[0]);
                 t2.setText(arr[1]);
                 t3.setText(arr[2]);
                 t4.setText(arr[3]);
                 t5.setText(arr[4]);
                 t6.setText(arr[5]);
                 t7.setText(arr[6]);
                 t8.setText(arr[7]);
             }
             else if(name.equalsIgnoreCase("hospital"))
             {
                 TextView t9,t10,t11,t12,t13,t14,t15,t16;
                 t1 = (TextView) findViewById(R.id.id1);
                 t2 = (TextView) findViewById(R.id.age1);
                 t3 = (TextView) findViewById(R.id.gender1);
                 t4 = (TextView) findViewById(R.id.dob1);
                 t5 = (TextView) findViewById(R.id.col1);
                 t6 = (TextView) findViewById(R.id.matst1);
                 t7 = (TextView) findViewById(R.id.lang1);
                 t8 = (TextView) findViewById(R.id.loc1);

                 t9 = (TextView) findViewById(R.id.id);
                 t10 = (TextView) findViewById(R.id.age);
                 t11 = (TextView) findViewById(R.id.gender);
                 t12 = (TextView) findViewById(R.id.dob);
                 t13 = (TextView) findViewById(R.id.col);
                 t14 = (TextView) findViewById(R.id.matst);
                 t15 = (TextView) findViewById(R.id.lang);
                 t16 = (TextView) findViewById(R.id.loc);
                 t9.setText("Hospital ID");
                 t10.setText("Hospital Name");
                 t11.setText("Location");
                 t12.setText("Offer");
                 t13.setText("Offer Period");
                 t14.setText("Hospital Type");
                 t15.setText("Camps");
                 t16.setText("Camp Disease");
                 String[] arr = result.split("_");
                 t1.setText(arr[0]);
                 t2.setText(arr[1]);
                 t3.setText(arr[2]);
                 t4.setText(arr[3]);
                 t5.setText(arr[4]);
                 t6.setText(arr[5]);
                 t7.setText(arr[6]);
                 t8.setText(arr[7]);
             }
             else
             {
                 TextView t9,t10,t11,t12,t13,t14,t15,t16;
                 t1 = (TextView) findViewById(R.id.id1);
                 t2 = (TextView) findViewById(R.id.age1);
                 t3 = (TextView) findViewById(R.id.gender1);
                 t4 = (TextView) findViewById(R.id.dob1);
                 t5 = (TextView) findViewById(R.id.col1);
                 t6 = (TextView) findViewById(R.id.matst1);
                 t7 = (TextView) findViewById(R.id.lang1);
                 t8 = (TextView) findViewById(R.id.loc1);

                 t9 = (TextView) findViewById(R.id.id);
                 t10 = (TextView) findViewById(R.id.age);
                 t11 = (TextView) findViewById(R.id.gender);
                 t12 = (TextView) findViewById(R.id.dob);
                 t13 = (TextView) findViewById(R.id.col);
                 t14 = (TextView) findViewById(R.id.matst);
                 t15 = (TextView) findViewById(R.id.lang);
                 t16 = (TextView) findViewById(R.id.loc);
                 t9.setText("Admin ID");
                 t10.setText("Admin Name");
                 t11.setText("Location");
                 t12.setText("Mail Id");
                 t13.setText("Mobile");
                 t14.setText("");
                 t15.setText("");
                 t16.setText("");

                 String[] arr = result.split("__");
                 t1.setText(arr[0]);
                 t2.setText(arr[1]);
                 t3.setText(arr[2]);
                 t4.setText(arr[3]);
                 t5.setText(arr[4]);
                 t6.setText("");
                 t7.setText("");
                 t8.setText("");
             }

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
        inflater.inflate(R.menu.about_menu, menu);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.home:
                if(name.equalsIgnoreCase("user"))
                {
                    Intent intent1=new Intent(About.this,UserProfile.class);
                    intent1.putExtra(USER_NAME,data3);
                    intent1.putExtra(ID1,data4);
                    startActivity(intent1);
                }
                else if(name.equalsIgnoreCase("hospital"))
                {
                    Intent intent1=new Intent(About.this,Hospital.class);
                    intent1.putExtra(ID1,data3);
                    intent1.putExtra(ID,data4);
                    startActivity(intent1);
                }
                else
                {
                    Intent intent1=new Intent(About.this,Admin.class);
                    intent1.putExtra(USER_NAME,data3);
                    intent1.putExtra(ID1,data4);
                    startActivity(intent1);
                }

                break;
            // action with ID action_settings was selected
            case R.id.logout:
                Intent intent=new Intent(About.this,MainActivity.class);
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
