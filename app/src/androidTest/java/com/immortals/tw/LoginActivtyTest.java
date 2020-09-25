package com.immortals.tw;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by lhy on 2020/3/2.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivtyTest {
    final String TAG = this.getClass().getSimpleName();

    @Test
    public void bindAccount() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(withText("Service agreement")).check(matches(isDisplayed())).perform(click());


    }





}


