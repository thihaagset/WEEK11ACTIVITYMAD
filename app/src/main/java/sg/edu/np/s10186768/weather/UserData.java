package sg.edu.np.s10186768.weather;

public class UserData {

    private String MyUsername;
    private String MyPassword;



    public UserData(){

    }

    public UserData(String myUsername,String myPassword){

        MyUsername=myUsername;
        MyPassword=myPassword;





    }


    //getter setter
    public String getMyUsername() {
        return MyUsername;
    }

    public void setMyUsername(String myUsername) {
        MyUsername = myUsername;
    }

    public String getMyPassword() {
        return MyPassword;
    }

    public void setMyPassword(String myPassword) {
        MyPassword = myPassword;
    }




}
