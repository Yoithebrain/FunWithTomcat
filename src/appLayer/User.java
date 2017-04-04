package appLayer;
import dataLayer.*;
/**
 * Created by jakob on 21-03-2017.
 */
public class User {

    public boolean isValidUserCredentials(String sUsername, String sUserPassword){
        userDB userDB = new userDB();

        return userDB.isValidUserLogin(sUsername, sUserPassword);
    }

}
