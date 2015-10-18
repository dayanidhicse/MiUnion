package com.example.home.miunion_v3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import dmax.dialog.SpotsDialog;

/**
 * Created by divahar on 9/26/2015.
 */
public class Hospital extends ActionBarActivity {
    TextView textView;

    ListView lv;
    ArrayAdapter aa;
    ArrayList al= new ArrayList();
    String username,id1;
    String na;
    Button show;
    String item1;
    String input;
    String rest,pg;
    public static final String DATA1 = "DATA1";
    public static final String DATA2 = "DATA2";
    public static final String DATA3 = "DATA3";
    public static final String DATA4 = "DATA4";

    public static final String RESULT_GOT = "RESULT_GOT";
    public static final String PAGE_GOT = "PAGE_GOT";
    private static final String REGISTER_URL = "http://dayahospital.esy.es/UserRegistration/hospital.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital);
     /*   LinearLayout linearLayout = (LinearLayout)findViewById(R.id.bg);
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

        Intent intent = getIntent();

        username = intent.getStringExtra(MainActivity.ID);
        id1 = intent.getStringExtra(MainActivity.ID1);
        textView=(TextView) findViewById(R.id.textViewUser);
        textView.setText(username);

        lv=(ListView) findViewById(R.id.list);
        aa=new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
        aboutUser();
        Product[] items = {
                new Product("History"),
                new Product("Remote"),
                new Product("Location"),

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
                showInputDialog();
                // Toast.makeText(getBaseContext(),item , Toast.LENGTH_LONG).show();
            }
        });


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
                //Intent intent1=new Intent(Hospital.this,About.class);
                //startActivity(intent1);
                //aboutUser();
                Intent intent=new Intent(Hospital.this,About.class);
                intent.putExtra(RESULT_GOT,rest);
                intent.putExtra(PAGE_GOT,pg);
                intent.putExtra(DATA3,id1);
                intent.putExtra(DATA4,username);
                startActivity(intent);
                break;
            case R.id.logout:
                Intent intent1=new Intent(Hospital.this,MainActivity.class);
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

    private void registerUser() {
        register(na,id1);
    }


    private void register(String name,String id) {
        String urlSuffix;
        urlSuffix = "?name="+name+"&id="+id+"&input="+input;
        class RegisterUser extends AsyncTask<String, Void, String> {

           // ProgressDialog loading;

            AlertDialog dialog = new SpotsDialog(Hospital.this,R.style.Custom);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.show();
                //loading = ProgressDialog.show(Hospital.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                // loading.dismiss();
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
                        if(na.equals("History")&&!result.equalsIgnoreCase("No Data Found"))
                        {

                            Intent intent=new Intent(Hospital.this,History.class);
                            intent.putExtra(RESULT_GOT,result);
                            intent.putExtra(DATA1,rest);
                            intent.putExtra(DATA2,pg);
                            intent.putExtra(DATA3,id1);
                            intent.putExtra(DATA4,username);
                            startActivity(intent);
                        }

                        else if(na.equals("Remote")&&!result.equalsIgnoreCase("No Data Found"))
                        {
                            Intent intent=new Intent(Hospital.this,Remote.class);
                            intent.putExtra(RESULT_GOT,result);
                            intent.putExtra(DATA1,rest);
                            intent.putExtra(DATA2,pg);
                            intent.putExtra(DATA3,id1);
                            intent.putExtra(DATA4,username);
                            startActivity(intent);
                          //  finish();
                        }
                        else if(na.equals("Location")&&!result.equalsIgnoreCase("No Data Found"))
                        {
                            Intent intent=new Intent(Hospital.this,Location.class);
                            intent.putExtra(RESULT_GOT,result);
                            intent.putExtra(DATA1,rest);
                            intent.putExtra(DATA2,pg);
                            intent.putExtra(DATA3,id1);
                            intent.putExtra(DATA4,username);
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
        LayoutInflater layoutInflater = LayoutInflater.from(Hospital.this);
        if(na.equals("Location"))
        {
            View promptView = layoutInflater.inflate(R.layout.input_dialog3, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Hospital.this);
            alertDialogBuilder.setView(promptView);

            final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
            Spinner sp=(Spinner) promptView.findViewById(R.id.spinner1);
            TextView tv=(TextView) promptView.findViewById(R.id.dialogtitle);
            TextView tv1=(TextView) promptView.findViewById(R.id.dialog);



            ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.option,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    item1 = ((TextView)view).getText().toString();

                    //Toast.makeText(getApplicationContext(),item1, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            tv.setText("Location");
            tv1.setText("Enter The Location");
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           // input= sp.getText().toString().trim().toLowerCase();
                          //  input=String.valueOf(sp.getSelectedItem());
                            input=item1;
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
        else
        {
            View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Hospital.this);
            alertDialogBuilder.setView(promptView);

            final EditText editText = (EditText) promptView.findViewById(R.id.edittext);

            TextView tv=(TextView) promptView.findViewById(R.id.dialogtitle);
            TextView tv1=(TextView) promptView.findViewById(R.id.dialog);
            tv.setText(na);
            tv1.setText("Enter The UserID");
            editText.setHint("Enter UserID here...");
            // setup a dialog window
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            input= editText.getText().toString().trim().toLowerCase();
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
     }

    private void mostrar_alertdialog_spinners() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("Selecciona un archivo:");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.rgb(0, 153, 204));
        title.setTextSize(23);
        builder.setCustomTitle(title);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout_spinners = inflater.inflate(R.layout.input_dialog3,null);
        Spinner sp_titulos_carpetas = (Spinner) layout_spinners.findViewById(R.id.spinner1);
        builder.setView(layout_spinners);
        builder.setCancelable(false);
        builder.show();
        ArrayList<String> lista_k = new ArrayList<String>();
        lista_k.add("Puducherry");
        ArrayAdapter<String> carpetas = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, lista_k);
        carpetas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_titulos_carpetas.setAdapter(carpetas);
       sp_titulos_carpetas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private static final String REGISTER_URLAA = "http://dayahospital.esy.es/UserRegistration/hospitalabout.php";

    private void aboutUser() {
        userLogin(id1);
    }
    private void userLogin(final String username) {
        class UserLoginClass extends AsyncTask<String, Void, String> {
           // ProgressDialog loading;
            AlertDialog dialog = new SpotsDialog(Hospital.this,R.style.Custom);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.show();
                //loading = ProgressDialog.show(Hospital.this, "Please Wait", null, true, true);
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
                    pg="hospital";
                }
                else
                {
                    Toast toast = Toast.makeText(Hospital.this, s, Toast.LENGTH_LONG);
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
