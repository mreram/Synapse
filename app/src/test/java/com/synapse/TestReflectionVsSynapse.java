package com.synapse;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestReflectionVsSynapse {

    @Test
    public void doSynapse() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            FeatureManager.initFeature("com.synapse.MainActivityJava", "functionA", "dfs", 2);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void doReflection() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            try {
                Class<?> a = Class.forName("com.synapse.MainActivityJava");
                Object[] values = new Object[]{new Object[]{"dfs", 2}};
                Method bt1click = a.getDeclaredMethod("functionA", Object[].class);
                bt1click.invoke(null, values);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        System.out.println(System.currentTimeMillis() - start);
    }


}
