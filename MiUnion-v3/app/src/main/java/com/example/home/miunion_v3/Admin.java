package com.example.home.miunion_v3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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



public class Admin extends ActionBarActivity {
    String id1;
    TextView textView;
    ListView lv;
    ArrayAdapter aa;
    ArrayList al = new ArrayList();
    String username, input;


    ListView list;
    String[] web = {
            "Epidemic",
            "Welfare",
            "Age"
    } ;
    Integer[] imageId = {
            R.drawable.epidemic,
            R.drawable.wefare,
            R.drawable.age

    };

    public static final String DATA1 = "DATA1";
    public static final String DATA2 = "DATA2";
    public static final String DATA3 = "DATA3";
    public static final String DATA4 = "DATA4";

    public static final String PAGE_GOT = "PAGE_GOT";
    public static final String RESULT_GOT = "RESULT_GOT";
    String na,rest,pg;
    private static final String REGISTER_URL = "http://dayahospital.esy.es/UserRegistration/admin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
//start
        CustomList adapter1 = new
                CustomList(Admin.this, web, imageId);
        list=(ListView)findViewById(R.id.list12);
        list.setAdapter(adapter1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               // Toast.makeText(Admin.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                String item = web[+ position].toString();
                na=item;
                // Toast.makeText(getBaseContext(),item , Toast.LENGTH_LONG).show();
                if (na.equals("Epidemic")) {
                    showInputDialog();

                } else {
                    input="";
                    registerUser();
                }
            }
        });

        //end
        Intent intent = getIntent();

        username = intent.getStringExtra(MainActivity.USER_NAME);
        id1 = intent.getStringExtra(MainActivity.ID1);
        textView = (TextView) findViewById(R.id.textViewUser1);
        textView.setText(id1);
        aboutUser();/*
        lv = (ListView) findViewById(R.id.list);
        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        final Product[] items = {
                new Product("Epidemic"),
                new Product("Welfare"),
                new Product("Age"),

        };
        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this,
                android.R.layout.simple_list_item_1, items);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String item = ((TextView) view).getText().toString();
                na = item;
                if (na.equals("Epidemic")) {
                    showInputDialog();

                } else {
                    input="";
                    registerUser();
                }
            }
        });*/

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
                //Intent intent1=new Intent(Admin.this,About.class);
                //startActivity(intent1);
                //aboutUser();
                Intent intent=new Intent(Admin.this,About.class);
                intent.putExtra(RESULT_GOT,rest);
                intent.putExtra(PAGE_GOT,pg);
                intent.putExtra(DATA3,username);
                intent.putExtra(DATA4,id1);
                startActivity(intent);

                break;
            // action with ID action_settings was selected
            case R.id.logout:
                Intent intent1=new Intent(Admin.this,MainActivity.class);
                startActivity(intent1);
                finishAffinity();
                //finish();
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

    private void registerUser() {
        register(na,id1);
    }


    private void register(String name,String id) {
        String urlSuffix;
        urlSuffix = "?name="+name+"&id="+id+"&input="+input;
        class RegisterUser extends AsyncTask<String, Void, String> {

           // ProgressDialog loading;
            AlertDialog dialog = new SpotsDialog(Admin.this,R.style.Custom);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.show();
             //   loading = ProgressDialog.show(Admin.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                dialog.dismiss();
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
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
                        if(na.equals("Epidemic")&&!result.equalsIgnoreCase("No Data Found"))
                        {

                            Intent intent=new Intent(Admin.this,Epidemic.class);
                            intent.putExtra(RESULT_GOT,result);
                            intent.putExtra(DATA1,rest);
                            intent.putExtra(DATA2,pg);
                            intent.putExtra(DATA3,username);
                            intent.putExtra(DATA4,id1);

                            startActivity(intent);
                            //finish();

                        }

                        else if(na.equals("Welfare")&&!result.equalsIgnoreCase("No Data Found"))
                        {
                            Intent intent=new Intent(Admin.this,Welfare.class);
                            intent.putExtra(RESULT_GOT,result);
                            intent.putExtra(DATA1,rest);
                            intent.putExtra(DATA2,pg);
                            intent.putExtra(DATA3,username);
                            intent.putExtra(DATA4,id1);

                            startActivity(intent);
                            //  finish();
                        }
                        else if(na.equals("Age")&&!result.equalsIgnoreCase("No Data Found"))
                        {
                            Intent intent=new Intent(Admin.this,Age.class);
                            intent.putExtra(RESULT_GOT,result);
                            intent.putExtra(DATA1,rest);
                            intent.putExtra(DATA2,pg);
                            intent.putExtra(DATA3,username);
                            intent.putExtra(DATA4,id1);

                            startActivity(intent);
                            //finish();
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

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(Admin.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog2, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Admin.this);
        alertDialogBuilder.setView(promptView);


        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        final EditText editText1 = (EditText) promptView.findViewById(R.id.edittext1);
        TextView tv = (TextView) promptView.findViewById(R.id.dialogtitle);
        TextView tv1 = (TextView) promptView.findViewById(R.id.dialog);
        tv.setText("Epidemic");
        tv1.setText("Enter The Date");

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String st = editText.getText().toString().trim().toLowerCase();
                        String end = editText1.getText().toString().trim().toLowerCase();
                        input = st + "to" + end;
                        registerUser();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
       }
    private static final String REGISTER_URLAA = "http://dayahospital.esy.es/UserRegistration/adminabout.php";

    private void aboutUser() {
        userLogin(id1);
    }
    private void userLogin(final String username) {
        class UserLoginClass extends AsyncTask<String, Void, String> {
           // ProgressDialog loading;
            AlertDialog dialog = new SpotsDialog(Admin.this,R.style.Custom);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               dialog.show();
               // loading = ProgressDialog.show(Admin.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {

                super.onPostExecute(s);
                dialog.dismiss();
                //loading.dismiss();
                if(!s.equalsIgnoreCase("No Data Found"))
                {
                  //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    rest=s;
                    pg="admin";
                }
                else
                {
                    Toast toast = Toast.makeText(Admin.this, s, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

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
