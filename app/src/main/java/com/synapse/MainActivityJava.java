package com.synapse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.synapse_annotations.Feature;

public class MainActivityJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvContent = findViewById(R.id.tvContent);

        tvContent.append("return of functionA value: " + FeatureManager.initFeature("functionA", "dfs", 2));
        tvContent.append("\nreturn of functionB value: " + FeatureManager.initFeature("functionB", "dfs", 2));
    }

    @Feature
    static void functionA(Object... param) {
        System.out.println("functionA");
    }

    @Feature
    static int functionB(Object... param) {
        System.out.println("functionB");
        return 2;
    }


}
