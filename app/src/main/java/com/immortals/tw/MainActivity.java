package com.immortals.tw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.global.sdk.ADSDK;
import com.global.sdk.GMSDK;
import com.global.sdk.base.GMActionCode;
import com.global.sdk.listenner.GlobalCallback;
import com.global.sdk.manager.GMCallback;
import com.global.sdk.model.TranslationBean;
import com.global.sdk.ui.dialog.DialogPresenter;
import com.global.sdk.util.ConfigManager;
import com.gm88.gmutils.SDKLog;
import com.gm88.gmutils.ToastHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

//import com.crashlytics.android.Crashlytics;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView mTvEnterQufu, mTvEnterRole, mTvEnterGame, mTvDoPay, mTvShare, mLogin, mTvToCustomer,
            mTvCreateRole, mTvOverBegin, mTvLevel, mFBReLoading, mTvShowBind, mTvSendEvent;
    private TextView systemShare, mTvTranslation, mTvPayList, mTvSwitchAccount;
    private EditText mEtOrderName;
    private EditText mEtOrderPrice;
    private EditText mEtProductId;
    private EditText mEtTranslation;
    private Spinner mEtEvent;
    private FrameLayout mMain;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();

    }


    private void init() {
        GMSDK.setCallBack(new GMCallback() {
            @Override
            public void onCallBack(final Message msg) {
                switch (msg.what) {
                    case GMActionCode.ACTION_INIT_SUCC://初始化成功
                        ToastHelper.toast(MainActivity.this, "初始化完成");
                        GMSDK.doLogin();
                        break;
                    case GMActionCode.ACTION_INIT_FAILED://初始化失败
                        finish();
                        break;
                    case GMActionCode.ACTION_LOGIN_SUCC://登录成功

                        ConfigManager.getInstance().getFcmToken();
                        //ADSDK.getInstance().loadAd();
                        String spot = "{\"spotType\":4,\"extra\":{\"roleName\":\"IleanaJudd\",\"vipLevel\":3,\"serverName\":1服,\"roleServer\":55,\"roleLevel\":1,\"roleId\":1234567" + "}}";
                        GMSDK.doSpot(spot);
                        JSONObject result = (JSONObject) msg.obj;
                        Log.e(TAG, "登录成功" + result.toString());
                        ToastHelper.toast(MainActivity.this, result.toString());
                        break;
                    case GMActionCode.ACTION_LOGIN_CANCEL://退出登录
//                        toast("退出登录");
                        break;
                    case GMActionCode.ACTION_LOGIN_FAILED://登录失败
                        ToastHelper.toast(MainActivity.this, "登录失败" + msg.obj);
                        break;
                    case GMActionCode.ACTION_LOGOUT_SUCC://登出成功
//                        toast("登出成功");
                        break;
                    case GMActionCode.ACTION_GAME_EXIT://退出游戏
                        finish();
                        break;
                    case GMActionCode.ACTION_LOGOUT_FAILED://登出失败，一般不会出现，出现代表有问题
                        break;
                    case GMActionCode.ACTION_PAY_SUCC://支付成功
//                        toast("支付成功");
                        break;
                    case GMActionCode.ACTION_PAY_CANCEL://用户退出支付
//                        toast("用户退出支付");
                        break;
                    case GMActionCode.ACTION_TRANSLATION_SUCCESS://翻译成功
                        TranslationBean translationBean = (TranslationBean) msg.obj;
                        ToastHelper.toast(MainActivity.this, "翻译成功\n" + translationBean.getTarget_text() + "\n" + translationBean.getExtra());
                        break;
                    case GMActionCode.ACTION_TRANSLATION_FAILED://翻译失败
                        ToastHelper.toast(MainActivity.this, "翻译失败\n" + String.valueOf(msg.obj));
                        break;
                    default:
                        break;
                }
            }
        });
        GMSDK.initMainActivity(MainActivity.this);

    }

    private void initView() {

        mLogin = findViewById(R.id.game_login);
        mMain = findViewById(R.id.fl_main);
        mTvEnterQufu = findViewById(R.id.game_qufu);
        mTvEnterRole = findViewById(R.id.game_createrole);
        mTvEnterGame = findViewById(R.id.game_in);
        mTvDoPay = findViewById(R.id.game_pay);
        mTvShare = findViewById(R.id.game_share);
        mTvCreateRole = findViewById(R.id.game_create);
        mTvOverBegin = findViewById(R.id.game_tutorial);
        mTvLevel = findViewById(R.id.game_level);
        mTvShowBind = findViewById(R.id.game_showbind);
        mTvPayList = findViewById(R.id.game_get_pay_list);

        mTvSendEvent = findViewById(R.id.game_send_affb);
        mFBReLoading = findViewById(R.id.game_load_fb_initad);

        mTvTranslation = findViewById(R.id.game_translation);
        mTvSwitchAccount = findViewById(R.id.game_switch_account);
        mEtTranslation = findViewById(R.id.game_translation_targettext);
        mEtEvent = findViewById(R.id.even_sp);
        systemShare = findViewById(R.id.tv_systemshare);
        mTvToCustomer = findViewById(R.id.game_create_deeplink);
        mTvTranslation.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        findViewById(R.id.game_show_ad).setOnClickListener(this);
        mTvPayList.setOnClickListener(this);
        mTvShowBind.setOnClickListener(this);
        mTvSendEvent.setOnClickListener(this);
        mTvSwitchAccount.setOnClickListener(this);
        mTvToCustomer.setOnClickListener(this);
        mTvEnterQufu.setOnClickListener(this);
        mTvEnterRole.setOnClickListener(this);
        mTvEnterGame.setOnClickListener(this);
        mTvDoPay.setOnClickListener(this);
        mTvShare.setOnClickListener(this);
        mTvCreateRole.setOnClickListener(this);
        mTvOverBegin.setOnClickListener(this);
        mTvLevel.setOnClickListener(this);
        mFBReLoading.setOnClickListener(this);

        mEtOrderName = findViewById(R.id.game_order);
        mEtOrderPrice = findViewById(R.id.game_price);
        mEtProductId = findViewById(R.id.game_productid);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        GMSDK.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.game_login:
                SDKLog.e(TAG, "点击登录");
                GMSDK.doLogin();
                break;
            case R.id.game_pay:
                SDKLog.e(TAG, "点击支付");
                Map<String, String> payinfo = new HashMap<>();
                if (mEtOrderName.getText().toString().trim().isEmpty()) {
                    payinfo.put("productName", "1001-60元寶");
                } else {
                    payinfo.put("productName", mEtOrderName.getText().toString().trim());
                }
                if (mEtOrderPrice.getText().toString().trim().isEmpty()) {
                    payinfo.put("productPrice", "0.99");
                } else {
                    payinfo.put("productPrice", mEtOrderPrice.getText().toString().trim());
                }
                if (mEtProductId.getText().toString().trim().isEmpty()) {
                    payinfo.put("productId", "1001");
                } else {
                    payinfo.put("productId", mEtProductId.getText().toString().trim());
                }
                payinfo.put("roleId", "1");
                payinfo.put("roleName", "1");
                payinfo.put("serverId", "1");
                payinfo.put("serverName", "1");
                payinfo.put("notifyUrl", "");
                payinfo.put("extra", System.currentTimeMillis() / 1000 + "");
                GMSDK.doPay(payinfo);
                break;
            case R.id.game_share:

                JSONObject jsonObject1 = new JSONObject();
                try {
                    jsonObject1.put("shareID", "1");
                    jsonObject1.put("shareName", "分享");
                    jsonObject1.put("uName", "11");
                    jsonObject1.put("server", "2");
                    jsonObject1.put("code", "3");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                GMSDK.doShare(jsonObject1.toString());
                break;
            case R.id.game_showbind:
                GMSDK.showBind();
                break;
            case R.id.game_show_ad:
                ADSDK.getInstance().setShow(true);
                if (DialogPresenter.loadingDialog(MainActivity.this) != null && !DialogPresenter.loadingDialog(MainActivity.this).isShowing())
                    DialogPresenter.loadingDialog(MainActivity.this).show();
                String extra = "{\"adType\":\"13\",\"info\":\"asdfasdf\"}";
                ADSDK.getInstance().doShowAD(extra);
                break;
            case R.id.game_get_pay_list:
                GMSDK.getPurchaseList(new GlobalCallback() {
                    @Override
                    public void onSuccess(String o) {
                        ToastHelper.toast(MainActivity.this, o);
                        SDKLog.d(TAG, "doPurchaseListDone=" + o);
                    }

                    @Override
                    public void onFailed(String msg) {
                        SDKLog.d(TAG, "doPurchaseListDone=" + msg);
                    }
                });
                break;
            case R.id.game_translation:
                GMSDK.translation2Text("111", mEtTranslation.getText().toString().isEmpty() ? "hello" : mEtTranslation.getText().toString());
                break;
            case R.id.game_switch_account:
                GMSDK.showLogin();
                break;
            case R.id.game_send_affb:
                GMSDK.doEventInfo(mEtEvent.getSelectedItem().toString());
                break;
            case R.id.game_create_deeplink:
                GMSDK.showServiceCenter();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        GMSDK.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        GMSDK.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GMSDK.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GMSDK.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GMSDK.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GMSDK.onActivityResult(requestCode, resultCode, data);
    }

}
