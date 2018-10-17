package emre.com.firebasenotificationtutorial.utility;

/**
 * Created by Emre.Karatas on 16.10.2018.
 */

public class ControlManager {
    public static boolean isIntegerValue(String value) {
        return value.matches("\\d+(?:\\.\\d+)?");
    }
}
