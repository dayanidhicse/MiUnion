package com.example.home.miunion_v3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import dmax.dialog.SpotsDialog;


/**
 * Created by divahar on 9/13/2015.
 */
public class Register extends ActionBarActivity implements View.OnClickListener {
    EditText Name,pass,conform_pass,place,mail,mobile;

    Button buttonRegister;

    private static final String REGISTER_URL = "http://dayahospital.esy.es/UserRegistration/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
      /*  LinearLayout linearLayout = (LinearLayout)findViewById(R.id.bg);
        Resources res = getResources();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Name = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        conform_pass = (EditText) findViewById(R.id.conform);
        place = (EditText) findViewById(R.id.place);
        mail= (EditText) findViewById(R.id.mail);
        mobile = (EditText) findViewById(R.id.mobile);
        buttonRegister = (Button) findViewById(R.id.reg);
        buttonRegister.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            registerUser();
        }

    }
    private void registerUser() {
        String name = Name.getText().toString().trim().toLowerCase();
        String password = pass.getText().toString().trim().toLowerCase();
        String con_password = conform_pass.getText().toString().trim().toLowerCase();
        String Place = place.getText().toString().trim().toLowerCase();
        String mail_id = mail.getText().toString().trim().toLowerCase();
        String mobile_no = mobile.getText().toString().trim().toLowerCase();
        int flag=1;
        if(name.equals("")){
            flag=0;
            Toast.makeText(getApplicationContext(),"Name cannot be empty !",Toast.LENGTH_LONG).show();
        }
        if(!password.equals(con_password) || password.equals("")) {
            flag=0;
            Toast.makeText(getApplicationContext(),"Password does not match !",Toast.LENGTH_LONG).show();
        }
        if(Place.equals("")){
            flag=0;
            Toast.makeText(getApplicationContext(),"Place cannot be empty !",Toast.LENGTH_LONG).show();
        }
        if(!mail_id.contains("@")){
            flag=0;
            Toast.makeText(getApplicationContext(),"Invalid Email !",Toast.LENGTH_LONG).show();
        }
        if(mobile_no.equals("")){
            flag=0;
            Toast.makeText(getApplicationContext(),"Invalid Mobile Number !",Toast.LENGTH_LONG).show();
        }
        if(flag==1){
            register(name,password,Place,mail_id,mobile_no);
        }

    }


    private void register(String name, String Password, String Place,String mail_id, String mobile_no) {
        String urlSuffix = "?name="+name+"&password="+Password+"&place="+Place+"&mail_id="+mail_id+"&mobile_no="+mobile_no;
        class RegisterUser extends AsyncTask<String, Void, String>{

            //ProgressDialog loading;
            AlertDialog dialog = new SpotsDialog(Register.this,R.style.Custom);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
              dialog.show();
              //  loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                //loading.dismiss();

                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                try{
                if(s.equalsIgnoreCase("Successfully Registered")){

                    //Intent intent = new Intent(this, UserProfile.class);

                    Intent intent=new Intent(Register.this,MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Register.this, s, Toast.LENGTH_LONG).show();
                }
                }
                catch (Exception e)
                {
                    Toast.makeText(Register.this, e+"", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }

}
