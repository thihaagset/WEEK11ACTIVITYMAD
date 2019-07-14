package sg.edu.np.s10186768.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG="MainActivity.java";
    private TextView tv_NewUser;



   /* SharedPreferences sharedPreferences;
    public static final String MY_GLOBAL_PREFS="MyPrefs";
    public static final String MY_USERNAME="MyUsername";
    public static final String MY_PASSWORD="MyPassword";*/
    MyDBHandler dbHandler =new MyDBHandler(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_NewUser=(TextView) findViewById(R.id.registerTv);
        tv_NewUser.setOnTouchListener(this);

    }


    public boolean onTouch(View v, MotionEvent event){

        Log.v(TAG,"Touch Start");

        Intent intent = new Intent (MainActivity.this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;



    }

    public void onClick(View v){

        final EditText etPassword = (EditText) findViewById(R.id.pwCreate);
        final EditText etUsername=(EditText) findViewById(R.id.userCreate);


        Log.v(TAG, "Log in with: " + etUsername.getText().toString() + ", " + etPassword.getText().toString());
        //if(isValidUser(etUsername.getText().toString()) && isValidPassword(etPassword.getText().toString())){
        if(isValid(etUsername.getText().toString(),etPassword.getText().toString())){

            Intent intent = new Intent(MainActivity.this, Main3Activity.class);
            Toast.makeText(MainActivity.this, "Valid user", Toast.LENGTH_LONG).show();
            startActivity(intent);

        }

        else{

            Toast.makeText(MainActivity.this, "Invalid user", Toast.LENGTH_LONG).show();

        }

    }


    public boolean isValid(String username, String password){
        if (dbHandler.findUser(username)!=null){
            UserData dbData=dbHandler.findUser(username);
            Log.v(TAG,"Running Checks......"+dbData.getMyUsername()+": "+dbData.getMyPassword()+" VS "+ username+": "+password);



            if((dbData.getMyUsername().equals(username))&&(dbData.getMyPassword().equals(password))){
                return true;
            }


            else
            {
                return false;
            }
        }

        else{
            return false;
        }
    }

   /* public boolean isValidPassword(String password){

        sharedPreferences=getSharedPreferences(MY_GLOBAL_PREFS,MODE_PRIVATE);
        String SharedPassword= sharedPreferences.getString(MY_PASSWORD,"");

        if(SharedPassword.equals(password))
        {
            return true;
        }

        return false;

    }

    public boolean isValidUser(String username){

        sharedPreferences=getSharedPreferences(MY_GLOBAL_PREFS,MODE_PRIVATE);
        String SharedUsername= sharedPreferences.getString(MY_USERNAME,"");

        if(SharedUsername.equals(username))
        {
            return true;
        }

        return false;

    }*/

}


