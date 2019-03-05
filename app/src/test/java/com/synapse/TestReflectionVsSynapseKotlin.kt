package com.synapse

import org.junit.Test

import java.lang.reflect.InvocationTargetException


class TestReflectionVsSynapseKotlin {

    @Test
    fun doRegular() {
        val start = System.currentTimeMillis()
        for (i in 0..1000000) {
            FeatureManager.initFeature("com.synapse.MainActivityKotlin.Companion", "functionA", "dfs", 2)
        }
        println(System.currentTimeMillis() - start)
    }



}
