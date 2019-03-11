package com.synapse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.synapse_annotations.Feature;

public class MainActivityJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvContent = findViewById(R.id.tvContent);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> startActivity(new Intent(this, MainActivityKotlin.class)));
        btn.setText(getString(R.string.switch_on, "Kotlin"));

        tvContent.append("return of functionA value: " + FeatureManager.initFeature(getClass().getCanonicalName(), "functionA", "dfs", 2));
        tvContent.append("\nreturn of functionB value: " + FeatureManager.initFeature(getClass().getCanonicalName(), "functionB", "dfs", 2));
    }

    @Feature
    static void functionA(Object... param) {
        System.out.println("functionA");
    }

    @Feature
    static String functionB(Object... param) {
        System.out.println("functionB");
        return "Java";
    }


}
