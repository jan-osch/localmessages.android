package me.grzesik.localmessages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button button = (Button) findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Api.getInstance().getResponse(new GetResponseCallback() {
                    @Override
                    public void onSuccess(String result) {
                        TextView textView = (TextView) findViewById(R.id.result_label);
                        textView.setText(result);
                    }
                });
            }
        });
    }


}
