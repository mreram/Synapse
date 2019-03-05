package com.synapse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.synapse_annotations.Feature
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityKotlin : AppCompatActivity() {

    companion object {


        @Feature
        fun functionA(vararg param: Any) {
            System.out.println("functionA")
        }

        @Feature
        fun functionB(vararg param: Any): Int {
            System.out.println("functionB")
            return 2
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvContent.append("return of functionA value: " + FeatureManager.initFeature("functionA", "dfs", 2))
        tvContent.append("\nreturn of functionB value: " + FeatureManager.initFeature("functionB", "dfs", 2))
    }
}
