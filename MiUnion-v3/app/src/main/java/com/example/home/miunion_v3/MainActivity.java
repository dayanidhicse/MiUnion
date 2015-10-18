package com.example.home.miunion_v3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import dmax.dialog.SpotsDialog;


public class MainActivity extends Activity implements View.OnClickListener {


    public static final String USER_NAME = "USER_NAME";
    public static final String ID = "ID";
    public static final String ID1 = "ID1";
    public static final String PASSWORD = "PASSWORD";

    private static final String LOGIN_URL = "http://dayahospital.esy.es/UserRegistration/login.php";
    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button buttonLogin,playButton;
    Button bt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt=(Button) findViewById(R.id.Re);
        bt.setOnClickListener(this);

        editTextUserName = (EditText) findViewById(R.id.uname);
        editTextPassword = (EditText) findViewById(R.id.upass);
        buttonLogin = (Button) findViewById(R.id.login);
        buttonLogin.setOnClickListener(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    private void login(){
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        userLogin(username,password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            AlertDialog dialog = new SpotsDialog(MainActivity.this,R.style.Custom);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.show();
               // loading = ProgressDialog.show(MainActivity.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {

                super.onPostExecute(s);
                dialog.dismiss();
               // loading.dismiss();
                if(!s.equalsIgnoreCase("invalid"))
                {
                    String[] arr= s.split("_");
                    if(arr[0].equalsIgnoreCase("Hospital"))
                    {

                        Intent intent=new Intent(MainActivity.this,Hospital.class);
                        intent.putExtra(USER_NAME,username);
                        intent.putExtra(ID1,arr[0]+"_"+arr[1]);
                        intent.putExtra(ID,arr[2]);
                        startActivity(intent);
                        finishAffinity();

                    }
                    else if(arr[0].equalsIgnoreCase("admin"))
                    {

                        Intent intent=new Intent(MainActivity.this,Admin.class);
                        intent.putExtra(USER_NAME,username);
                        intent.putExtra(ID1,arr[0]+"_"+arr[1]);
                        startActivity(intent);
                        finishAffinity();
                    }
                    else if(arr[0].equalsIgnoreCase("user"))
                    {
                       Intent intent=new Intent(MainActivity.this,UserProfile.class);
                        intent.putExtra(USER_NAME,username);
                        intent.putExtra(ID1,arr[1]);
                        startActivity(intent);
                        finishAffinity();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Network problem", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);
                RegisterUserClass ruc = new RegisterUserClass();
                String result = ruc.sendPostRequest(LOGIN_URL,data);
                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
        }
        else
        {
            Intent intent=new Intent(this,Register.class);
            startActivity(intent);

        }
    }

}
