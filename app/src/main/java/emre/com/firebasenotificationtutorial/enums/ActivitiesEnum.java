package emre.com.firebasenotificationtutorial.enums;

/**
 * Created by Emre.Karatas on 16.10.2018.
 */

public enum ActivitiesEnum {
    MainActivity("0"),
    DetailActivity("1");

    private String mValue;

    ActivitiesEnum(String value) {
        this.mValue = value;
    }

    public String getActivityID() {
        return mValue;
    }
}
