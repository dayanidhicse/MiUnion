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
public class Remote extends ActionBarActivity {
    Button show;
    CardView cv1,cv2;
    TextView tv1,tv2;
    String rest,pg,data3,data4;

    public static final String PAGE_GOT = "PAGE_GOT";
    public static final String RESULT_GOT = "RESULT_GOT";
    public static final String DATA3 = "DATA3";
    public static final String DATA4 = "DATA4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote);
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
        try {
            cv1 = (CardView) findViewById(R.id.card_view1);
            cv2 = (CardView) findViewById(R.id.card_view2);

            Intent intent = getIntent();
            String UN = intent.getStringExtra(Hospital.RESULT_GOT);
            //textView=(TextView) findViewById(R.id.tv);
            rest = intent.getStringExtra(Hospital.DATA1);
            pg = intent.getStringExtra(Hospital.DATA2);
            data3 =intent.getStringExtra(Hospital.DATA3);
            data4 =intent.getStringExtra(Hospital.DATA4);

            tv1 = (TextView) findViewById(R.id.TextView02);
            tv2 = (TextView) findViewById(R.id.TextView04);

            UN = UN.replace("--", ",");
            String[] arr = UN.split(",");

            tv1.setText(arr[0]);
            tv2.setText(arr[1]);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
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
                Intent intent1=new Intent(Remote.this,About.class);
                intent1.putExtra(RESULT_GOT,rest);
                intent1.putExtra(PAGE_GOT,pg);
                intent1.putExtra(DATA3,data3);
                intent1.putExtra(DATA4,data4);
                startActivity(intent1);
                break;
            // action with ID action_settings was selected
            case R.id.logout:
                Intent intent=new Intent(Remote.this,MainActivity.class);
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
