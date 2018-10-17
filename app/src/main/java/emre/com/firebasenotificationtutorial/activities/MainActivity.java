package emre.com.firebasenotificationtutorial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import emre.com.firebasenotificationtutorial.R;
import emre.com.firebasenotificationtutorial.enums.ActivitiesEnum;

public class MainActivity extends AppCompatActivity {

    String whichActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            whichActivity = getIntent().getExtras().getString("EXTRAS_ACTIVITY");

            if (whichActivity == ActivitiesEnum.DetailActivity.getActivityID()) {
                startActivity(new Intent(this, DetailActivity.class));
            }
        }
    }
}
