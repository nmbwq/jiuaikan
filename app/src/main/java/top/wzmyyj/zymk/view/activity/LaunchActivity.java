package top.wzmyyj.zymk.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.GetUrlBean;
import top.wzmyyj.zymk.app.bean.firstBean;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.app.utils.JsonUitl;
import top.wzmyyj.zymk.contract.LaunchContract;
import top.wzmyyj.zymk.presenter.LaunchPresenter;
import top.wzmyyj.zymk.base.activity.BaseActivity;


/**
 * Created by yyj on 2018/06/24. email: 2209011667@qq.com
 */

public class LaunchActivity extends BaseActivity<LaunchContract.IPresenter> implements LaunchContract.IView {

    @Override
    protected void initPresenter() {
        mPresenter = new LaunchPresenter(activity, this);
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        mPresenter.CheckPermission();
        mPresenter.init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView() {
        super.initView();
        setSwipeBackEnable(false);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        postByOkGo();
//       new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                startActivity(new Intent(LaunchActivity.this,LoginActivity.class));
//                finish();
//            }
//        }, 2000);

    }


    /**
     * post请求
     *
     * @param
     */
    private void postByOkGo() {
        OkGo.<String>post("https://bsqklserver.shuliantt.com/shuliantt/queryAppShopOff?")//
                .tag(this)//
                .params("shopCode", "679")
                .params("terminal", "android")
                .params("version", "1.0.0")
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        firstBean o = (firstBean) JsonUitl.stringToObject(response.body(), firstBean.class);
                        if (o.getCode() == 200) {
                            if (o.isData()) {
                                //审核通过
                                postGetUrl();
                            } else {

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        SharedPreferences mSharedPreferences = getSharedPreferences("student_data", Context.MODE_PRIVATE);
                                        Log.d("Debug","取出来的头肯值"+mSharedPreferences.getString("token", ""));

                                        if (TextUtils.isEmpty(mSharedPreferences.getString("token", ""))) {
                                            startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                                            finish();
                                        } else {
                                            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                                            finish();
                                        }
                                    }
                                }, 2000);


                            }

                        } else {
                            Toast.makeText(LaunchActivity.this, "网络错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
                        }

                        Log.d("Debug", "到达这里" + response.body());
                    }
                });
    }



    /**
     * post请求
     *
     * @param
     */
    String url = "https://www.baidu.com/";

    //    https://bsqklserver.shuliantt.com/shuliantt/queryAuditPassJumpUrlV2?key=674&terminal=android&version=1.0.0&isJumpUrl=true
    private void postGetUrl() {
        OkGo.<String>post("https://bsqklserver.shuliantt.com/shuliantt/queryAuditPassJumpUrlV2?")//
                .tag(this)//
                .params("key", "679")
                .params("terminal", "android")
                .params("version", "1.0.0")
                .params("isJumpUrl", "true")
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        Log.d("Debug", "第二次返回數據是" + response.body());
                        GetUrlBean o = (GetUrlBean) JsonUitl.stringToObject(response.body(), GetUrlBean.class);
                        if (o.getCode() == 200) {

                            if (!TextUtils.isEmpty(o.getData())) {
                                url = o.getData();
                            }

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(LaunchActivity.this, TextWebView.class);
                                    intent.putExtra("url", url);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 1000);

                        } else {
                            Toast.makeText(LaunchActivity.this, "网络错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}
