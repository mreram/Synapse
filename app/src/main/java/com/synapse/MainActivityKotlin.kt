package com.synapse

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.synapse_annotations.Feature
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityKotlin : AppCompatActivity() {

    companion object {


        @Feature
        fun functionA(vararg param: Any) {
            System.out.println("functionA")
        }

        @Feature
        fun functionB(vararg param: Any): String {
            System.out.println("functionB")
            return "Kotlin"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener { startActivity(Intent(this, MainActivityJava::class.java)) }
        btn.text = getString(R.string.switch_on, "Java")

        tvContent.append(
            "return of functionA value: " + FeatureManager.initFeature(
                "${javaClass.canonicalName}.Companion",
                "functionA",
                "dfs",
                2
            )
        )
        tvContent.append(
            "\nreturn of functionB value: " + FeatureManager.initFeature(
                "${javaClass.canonicalName}.Companion",
                "functionB",
                "dfs",
                2
            )
        )
    }
}
