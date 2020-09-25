package com.immortals.tw;

import android.Manifest;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.global.sdk.base.Config;
import com.global.sdk.model.LoginItemInfo;
import com.global.sdk.permission.request.CheckPermission;
import com.global.sdk.ui.bridge.CreateOrderInfo;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkArgument;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;

/**
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    final String TAG = this.getClass().getSimpleName();
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void permissionsNoticeTest() throws Exception {

//        ViewInteraction appCompatButton = onView(allOf(withId(R.id.tv_close_game), isDisplayed()));
//        //执行按钮的点击操作
//        appCompatButton.perform(click());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        List<String> list = CheckPermission.checkPermissionDenied(activityTestRule.getActivity(), permissions);
        if (list.size() == 0) {
            Log.i(TAG, "权限-已授权");
        } else {
            Log.i(TAG, "权限-申请开始");
            Espresso.onView(withId(R.id.tv_close_game)).check(matches(isDisplayed()));
            Log.i(TAG, "权限-权限弹窗展示成功");
            onView(withId(R.id.tv_premissions_notices_kf)).perform(click());
            try {
                onView(withText("Allow")).inRoot(isDialog()).perform(click());
            } catch (Exception e) {
                e.printStackTrace();
            }
//        onView(withText("Allow")).perform(click());
            Log.i(TAG, "权限-申请结束");
        }
//        onView(withText("显示绑定页面123")).perform(click());
        //通过id找到textview，并判断是否与文本匹配
        //Espresso.onView(withId(R.id.textView)).check(matches(withText("计算结果：6")));
        //通过文本"Item 0"找到view，并检查是否显示，然后执行点击事件，此时会弹出对话框
//        Espresso.onView(withText("Item 0")).check(matches(isDisplayed())).perform(click());
        //通过文本"Item 2"找到view，并检查是否显示，然后执行点击事件，此时会弹出对话框
//        Espresso.onView(withText("Item 2")).check(matches(isDisplayed())).perform(click());
//        执行点击返回按钮事件，关闭对话框
//        Espresso.pressBack();
        //通过id找到recycleview，然后执行滑动事件，滑动到21项
//        Espresso.onView(ViewMatchers.withId(R.id.recycleview)).perform(RecyclerViewActions.scrollToPosition(21));
        //通过文本"Item 20"找到view，并检查是否显示，然后执行点击事件，此时会弹出对话框
//        Espresso.onView(withText("Item 20")).check(matches(isDisplayed())).perform(click());


        //通过name为"word"找到搜索输入框
      /*  onWebView().withElement(findElement(Locator.NAME, "word"))
                //往输入框中输入字符串"android"
                .perform(DriverAtoms.webKeys("android"))
                //通过id为"index-bn"找到"百度一下"按钮
                .withElement(DriverAtoms.findElement(Locator.ID, "index-bn"))
                //执行点击事件
                .perform(webClick())
                //通过id为"results"找到结果div
                .withElement(DriverAtoms.findElement(Locator.ID, "results"))
                //检查div中是否包含字符串"android"
                .check(WebViewAssertions.webMatches(DriverAtoms.getText(), Matchers.containsString("android")));*/


    }


    @Test
    public void bindAccount() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Espresso.onView(withText("Service agreement")).check(matches(isDisplayed())).perform(click());
        onData(instanceOf(LoginItemInfo.class))
                .inAdapterView(allOf(withId(R.id.gm_login_gv), isDisplayed()))
                .atPosition(0)
                .onChildView(withId(R.id.gm_login_item_icon))
                .perform(click());

        Log.i(TAG, "登录成功");


        Espresso.onView(withText("显示绑定页面")).check(matches(isDisplayed())).perform(click());
        Log.i(TAG, "显示绑定页面");
        String email = getBindAccount("email");
        String facebook = getBindAccount("facebook");
        String google = getBindAccount("google");
        String twitter = getBindAccount("twitter");
        String line = getBindAccount("line");

        boolean isBindEmail;
        boolean isBindTwitter;
        boolean isBindFacebook;
        boolean isBindGoogle;
        boolean isBindLine;
        if (email.isEmpty()) {
            isBindEmail = false;
        } else {
            isBindEmail = true;
        }


        if (isBindEmail) {
            Espresso.onView(allOf(withParent(allOf(withParent(withId(R.id.google_account)), withId(R.id.ll_main_bind_item))),withId(R.id.bind_account))).check(matches(withText("Bound")));
            Log.i(TAG, "账号已绑定");
        } else {
            Log.i(TAG, "账号未绑定");
            Espresso.onView(allOf(withParent(allOf(withParent(withId(R.id.google_account)), withId(R.id.ll_main_bind_item))),withId(R.id.bind_account))).check(matches(withText("Bind>>")));
        }


        if (twitter.isEmpty()) {
            isBindTwitter = false;
        } else {
            isBindTwitter = true;
        }
        if (isBindTwitter) {
            Log.i(TAG, "twitter已绑定");
        } else {
            Log.i(TAG, "twitter未绑定");
        }
        if (facebook.isEmpty()) {
            isBindFacebook = false;
        } else {
            isBindFacebook = true;
        }
        if (isBindFacebook) {
            Log.i(TAG, "facebook已绑定");
        } else {
            Log.i(TAG, "facebook未绑定");
        }
        if (google.isEmpty()) {
            isBindGoogle = false;
        } else {
            isBindGoogle = true;
        }
        if (isBindGoogle) {
            Log.i(TAG, "google已绑定");
        } else {
            Log.i(TAG, "google未绑定");
        }
        if (line.isEmpty()) {
            isBindLine = false;
        } else {
            isBindLine = true;
        }
        if (isBindLine) {
            Log.i(TAG, "line已绑定");
        } else {
            Log.i(TAG, "line未绑定");
        }


    }


    private String getBindAccount(String loginType) {
        for (int i = 0; i < Config.getInstance().getmAccountList().getAccount_list().size(); i++) {
            if (loginType.equals(Config.getInstance().getmAccountList().getAccount_list().get(i).getType())) {
                return Config.getInstance().getmAccountList().getAccount_list().get(i).getInfo();
            }
        }
        return "";
    }


    @Test
    public void paypal() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Espresso.onView(withText("Service agreement")).check(matches(isDisplayed())).perform(click());
        onData(instanceOf(LoginItemInfo.class))
                .inAdapterView(allOf(withId(R.id.gm_login_gv), isDisplayed()))
                .atPosition(0)
                .onChildView(withId(R.id.gm_login_item_icon))
                .perform(click());

        Log.i(TAG, "登录成功");

        Espresso.onView(withId(R.id.game_price)).perform(scrollTo(), replaceText("0.99"), closeSoftKeyboard());
        Log.i(TAG, "输入支付金额0.99美元");
        Espresso.onView(withText("调用支付")).perform(scrollTo(), click());
        Log.i(TAG, "显示调用支付");
//        onData(searchMainItemWithName(CreateOrderInfo._PAYTYPE_PAYPAL)).check(matches(not(isDisplayed())));

//        onData(allOf(is(instanceOf(String.class)),is(CreateOrderInfo._PAYTYPE_PAYPAL))).check(doesNotExist());

//        onData(allOf(is(instanceOf(String.class)),is(CreateOrderInfo._PAYTYPE_PAYPAL))).check(matches(isCompletelyDisplayed()));
        //判断listview某一项是否存在
//        onView(withId(R.id.gm_paytype_list)).check(matches(withAdaptedData(withItemPayContent(CreateOrderInfo._PAYTYPE_PAYPAL))));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("PayPal")).check(doesNotExist());
        Log.i(TAG, "小于0.99美元paypal不可见");

        pressBack();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(withId(R.id.game_price)).perform(scrollTo(), replaceText("1"), closeSoftKeyboard());
        Log.i(TAG, "输入支付金额1美元");
        Espresso.onView(withText("调用支付")).perform(scrollTo(), click());
        Log.i(TAG, "显示调用支付");
        //listview存在并且点击该一项
        onData(searchMainItemWithName(CreateOrderInfo._PAYTYPE_PAYPAL)).check(matches(isDisplayed())); //.perform(click());
        Log.i(TAG, "大于0.99美元paypal可见");

    }


    /**
     * @param name 需要搜索的字
     */
    public static Matcher<Object> searchMainItemWithName(final String name) {
        return new BoundedMatcher<Object, String>(String.class) {
            @Override
            protected boolean matchesSafely(String item) {
                return item != null && !TextUtils.isEmpty(item) && item.equals(name);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("SalesOpp has Name: " + name);
            }
        };
    }

    private static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }

                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }

                return false;
            }
        };
    }

    public static Matcher<Object> withContactItemName(String itemText) {
        checkArgument(!(itemText.equals(null)));
        return withContactItemName(equalTo(itemText));
    }


    private DataInteraction onRow(final String name) {

        return onData(new BoundedMatcher<Object, String>(String.class) {


            @Override
            public void describeTo(Description description) {

                description.appendText("Matching to Student");
            }

            @Override
            protected boolean matchesSafely(String item) {


                return name.equals(item);
            }
        });
    }
}
