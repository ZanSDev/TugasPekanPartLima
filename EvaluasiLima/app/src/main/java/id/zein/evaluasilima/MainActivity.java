package id.zein.evaluasilima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonadd, buttonshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        buttonadd = findViewById(R.id.buttonAdd);
        buttonshow = findViewById(R.id.buttonShow);

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(CreatedbActivity.getActIntent(MainActivity.this));
            }
        });

        buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(ReaddbActivity.getActIntent(MainActivity.this));
            }
        });
    }
}
