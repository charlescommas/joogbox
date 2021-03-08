package id.ac.umn.joogbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView textAbout;
    //private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }
}
