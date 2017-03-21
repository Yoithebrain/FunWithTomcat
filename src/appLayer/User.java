package appLayer;

/**
 * Created by jakob on 21-03-2017.
 */
public class User {

    public boolean isValidUserCredentials(String sUsername, String sUserPassword){
        if (sUsername.equals("yoi") && sUserPassword.equals("toda")) {
            return true;
        }
        return false;
    }
}
