package com.example.home.miunion_v3;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import dmax.dialog.SpotsDialog;


public class UserProfile extends ActionBarActivity implements View.OnClickListener {

    private TextView textView,tt;

    ListView lv;
    ArrayAdapter aa;
    ArrayList al= new ArrayList();
    String username;
    String id;
    public static final String PAGE_GOT = "PAGE_GOT";
    public static final String RESULT_GOT = "RESULT_GOT";
    public static final String TITLE = "TITLE";
    public static final String DATA1 = "DATA1";
    public static final String DATA2 = "DATA2";
    public static final String DATA3 = "DATA3";
    public static final String DATA4 = "DATA4";

    ListView list;
    String[] web = {
            "Hospital",
            "Lab",
            "Camp"
    } ;
    Integer[] imageId = {
            R.drawable.hospitalicon11,
            R.drawable.lab,
            R.drawable.camp

           };

    String na,rest,pg;
    private static final String REGISTER_URL = "http://dayahospital.esy.es/UserRegistration/patient.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
           MenuInflater inflater = getMenuInflater();
           inflater.inflate(R.menu.menu_main, menu);
//start
        CustomList adapter1 = new
                CustomList(UserProfile.this, web, imageId);
        list=(ListView)findViewById(R.id.list12);
        list.setAdapter(adapter1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
              //  Toast.makeText(UserProfile.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                String item = web[+ position].toString();
                na=item;
                // Toast.makeText(getBaseContext(),item , Toast.LENGTH_LONG).show();
                registerUser();
            }
        });

 //end
        textView = (TextView) findViewById(R.id.textViewUserName);
        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USER_NAME);
        id = intent.getStringExtra(MainActivity.ID1);
        textView.setText(username);
        aboutUser();
       /* lv=(ListView) findViewById(R.id.list);
        aa=new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
        aboutUser();
        Product[] items = {
                new Product("Hospital"),
                new Product("Lab"),
                new Product("Camp"),

        };
        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this,
                android.R.layout.simple_list_item_1, items);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String item = ((TextView)view).getText().toString();
              na=item;
               // Toast.makeText(getBaseContext(),item , Toast.LENGTH_LONG).show();
                registerUser();
            }
        });

         */
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.about:
                Intent intent=new Intent(UserProfile.this,About.class);
                intent.putExtra(RESULT_GOT,rest);
                intent.putExtra(PAGE_GOT,pg);
                intent.putExtra(DATA3,username);
                intent.putExtra(DATA4,id);
                startActivity(intent);

                break;
            // action with ID action_settings was selected
            case R.id.logout:
                Intent intent1=new Intent(UserProfile.this,MainActivity.class);
                startActivity(intent1);
                finishAffinity();
                return true;
            default:
                break;
        }

        return true;
    }
    class Product {
        private String name;


        public Product(){
            super();
        }

        public Product( String name) {
            super();
            this.name = name;

        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    @Override
    public void onClick(View v) {

    }


    private void registerUser() {
        register(na,id);
    }


    private void register(String name,String id1) {

        String urlSuffix = "?name="+name+"&id="+id1;
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            AlertDialog dialog = new SpotsDialog(UserProfile.this,R.style.Custom);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.show();
               // loading = ProgressDialog.show(UserProfile.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                dialog.dismiss();
               // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
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

                    if (!result.equals(""))//result.equalsIgnoreCase("sucess"))
                    {
                        if(!result.equalsIgnoreCase("No Data Found"))
                        {
                            Intent intent=new Intent(UserProfile.this,Response.class);
                            intent.putExtra(RESULT_GOT,result);
                            intent.putExtra(TITLE,na);
                            intent.putExtra(DATA1,rest);
                            intent.putExtra(DATA2,pg);
                            intent.putExtra(DATA3,username);
                            intent.putExtra(DATA4,id);

                            startActivity(intent);
                           // finish();
                        }
                        else if(result.equalsIgnoreCase("No Data Found"))
                        {
                            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Network Problem", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    }
                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
    private static final String REGISTER_URLAA = "http://dayahospital.esy.es/UserRegistration/userabout.php";

    private void aboutUser() {
        userLogin(id);
    }
    private void userLogin(final String username) {
        class UserLoginClass extends AsyncTask<String, Void, String> {
            //ProgressDialog loading;
            AlertDialog dialog = new SpotsDialog(UserProfile.this,R.style.Custom);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.show();
              //  loading = ProgressDialog.show(UserProfile.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {

                super.onPostExecute(s);
                dialog.dismiss();
                //loading.dismiss();
                    if(!s.equalsIgnoreCase("No Data Found"))
                    {
                        //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                        rest=s;
                        pg="user";
                    }
                else
                    {
                        Toast toast = Toast.makeText(UserProfile.this, s, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    /*Toast toast = Toast.makeText(UserProfile.this, s, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();*/

            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<>();
                data.put("id", params[0]);
                RegisterUserClass ruc = new RegisterUserClass();
                String result = ruc.sendPostRequest(REGISTER_URLAA, data);
                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username);
    }
}
