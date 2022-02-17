package classes;

public class UserInformation {
    static public String apiKey = "";
    static public GeoCoder userPosition;
    static public double latitude = 91;
    static public double longitude = 91;
    static public String userProbleme = "";
    static public String userCity = "";

    static public void resetInformation(){
        userProbleme = "";
        userCity = "";
        latitude = 91;
        longitude = 91;
    }
}
