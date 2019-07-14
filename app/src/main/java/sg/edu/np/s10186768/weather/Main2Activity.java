package sg.edu.np.s10186768.weather;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {


    private static final String TAG="Main2Activity.java";


    SharedPreferences sharedPreferences;
    public static final String MY_GLOBAL_PREFS="MyPrefs";
    public static final String MY_USERNAME="MyUsername";
    public static final String MY_PASSWORD="MyPassword";

    MyDBHandler dbHandler =new MyDBHandler(this,null,null,1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    public void onCreateClick(View v){



        final EditText etPassword=(EditText) findViewById(R.id.pwCreate);
        final EditText etUsername=(EditText) findViewById(R.id.userCreate);

        if (isValidPassword(etPassword.getText().toString()) && isValidUser(etUsername.getText().toString()))
        {
            /*sharedPreferences=getSharedPreferences(MY_GLOBAL_PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(MY_USERNAME,etUsername.getText().toString());
            editor.putString(MY_PASSWORD,etPassword.getText().toString());
            editor.commit();*/


            UserData dbData=dbHandler.findUser(etUsername.getText().toString());

            if(dbData==null){
                String dbUsername=etUsername.getText().toString();
                String dbPassword=etPassword.getText().toString();

                UserData dbUserData=new UserData();

                dbUserData.setMyUsername(dbUsername);
                dbUserData.setMyPassword(dbPassword);
                dbHandler.addUser(dbUserData);

                Toast.makeText(Main2Activity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(Main2Activity.this, "User Already Exists.\nPlease Try Again.", Toast.LENGTH_SHORT).show();
            }

            //Toast.makeText(Main2Activity.this, "Valid User Created", Toast.LENGTH_LONG).show();
            finish();

        }

        else {

            Toast.makeText(Main2Activity.this, "Invalid User Created\n Please try again.", Toast.LENGTH_LONG).show();
        }
    }


    public boolean isValidPassword(String password){

        Pattern passwordPattern;
        Matcher passwordMatcher;

       final String PASSWORD_PATTERN="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%]).{6,12}$";

        passwordPattern=Pattern.compile(PASSWORD_PATTERN);
        passwordMatcher=passwordPattern.matcher(password);


        Log.v(TAG, "Create Password: " + passwordMatcher.matches());

        return passwordMatcher.matches();

    }

    public boolean isValidUser(String username){

        Pattern usernamePattern;
        Matcher usernameMatcher;

        final String USERNAME_PATTERN="^(?=.*[0-9])(?=.*[a-zA-Z]).{6,12}$" ;

        usernamePattern=Pattern.compile(USERNAME_PATTERN);
        usernameMatcher=usernamePattern.matcher(username);

        Log.v(TAG, "Create Username: " + usernameMatcher.matches());

        return usernameMatcher.matches();

    }

}
