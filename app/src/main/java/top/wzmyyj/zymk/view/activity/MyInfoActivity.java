package top.wzmyyj.zymk.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.RegistBean;
import top.wzmyyj.zymk.app.bean.SelectOneBean;
import top.wzmyyj.zymk.app.bean.UserBean;
import top.wzmyyj.zymk.app.utils.JsonUitl;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.contract.SettingContract;
import top.wzmyyj.zymk.presenter.SettingPresenter;


/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */

public class MyInfoActivity extends BaseActivity<SettingContract.IPresenter> implements SettingContract.IView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_qianming)
    EditText tvQianming;
    @BindView(R.id.ll_qianming)
    LinearLayout llQianming;
    @BindView(R.id.tv_esx)
    TextView tvEsx;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_birstday)
    TextView tvBirstday;
    @BindView(R.id.ll_birstday)
    LinearLayout llBirstday;

    String userid="";

    @Override
    protected void initPresenter() {
        mPresenter = new SettingPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mainfo;
    }


    @Override
    protected void initData() {
        super.initData();

        SharedPreferences mSharedPreferences = getSharedPreferences("student_data", Context.MODE_PRIVATE);
         userid = mSharedPreferences.getString("userid", "");
        if (!TextUtils.isEmpty(userid)){
            selectsomeOne(userid);
        }

    }

    @Override
    public void setCache(String s) {
    }

    @Override
    public void setCue(boolean is) {

    }

    @Override
    public void setVersion(String s) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.tv_commit, R.id.ll_sex, R.id.ll_birstday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_commit:

                UserBean userBean = new UserBean();
                String s = tvName.getText().toString();
                String s1 = tvQianming.getText().toString();
                String s2 = tvEsx.getText().toString();
                String s3 = tvBirstday.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    userBean.setUsername(s);
                }
                if (!TextUtils.isEmpty(s1)){
                    userBean.setDescription(s1);
                }
                if (!TextUtils.isEmpty(s2)){
                    userBean.setSex(s2);
                }
                if (!TextUtils.isEmpty(s3)){
                    userBean.setXingzuo(s3);
                }
                String jsonDate = JsonUitl.objectToString(userBean);
                updatesomeOne(userid,jsonDate);
                break;
            case R.id.ll_sex:
                selectsex();
                break;
            case R.id.ll_birstday:
                onConstellationPicker();
                break;
        }
    }



    public void onConstellationPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        top.wzmyyj.zymk.view.SinglePicker<String> picker = new top.wzmyyj.zymk.view.SinglePicker<>(this,
                isChinese ? new String[]{
                        "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
                        "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"
                } : new String[]{
                        "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"
                });
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFFEE0000);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                showToast("index=" + index + ", item=" + item);
                tvBirstday.setText(item);
            }
        });
        picker.show();
    }


    public void selectsex() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        top.wzmyyj.zymk.view.SinglePicker<String> picker = new top.wzmyyj.zymk.view.SinglePicker<>(this,
                isChinese ? new String[]{
                        "男", "女"
                } : new String[]{
                        "man", "woman"
                });
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFFEE0000);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(0);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                showToast("index=" + index + ", item=" + item);
                tvEsx.setText(item);
            }
        });
        picker.show();
    }


    //    https://bsqklserver.shuliantt.com/shuliantt/queryAuditPassJumpUrlV2?key=674&terminal=android&version=1.0.0&isJumpUrl=true
    private void selectsomeOne(final String userid) {
        OkGo.<String>post("http://hn216.api.yesapi.cn/?")//
                .tag(this)//
                .params("service", "App.Table.Get")
                .params("model_name", "manhuaUser")
                .params("id", userid)
                .params("app_key", "A0A30E83923479344A44C6C6D4F38AFE")
                .params("sign", "92B7D208DCAD357FD6FFE05027CA7A4C")
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        SelectOneBean o = (SelectOneBean) JsonUitl.stringToObject(response.body(), SelectOneBean.class);
                        if (o.getRet() == 200) {
                            SelectOneBean.DataBeanX.DataBean data = o.getData().getData();
                            if (!TextUtils.isEmpty(data.getUsername())){
                                tvName.setText(data.getUsername());
                            }else {
                                tvName.setText("就爱看1518667");
                            }
                            if (!TextUtils.isEmpty(data.getDescription())){
                                tvQianming.setText(data.getDescription());
                            }else {
                                tvQianming.setText("缤纷动漫,创意未来");
                            }
                            if (!TextUtils.isEmpty(data.getSex())){
                                tvEsx.setText(data.getSex());
                            }
                            if (!TextUtils.isEmpty(data.getXingzuo())){
                                tvBirstday.setText(data.getXingzuo());
                            }
                        } else {
                            Toast.makeText(MyInfoActivity.this, "信息错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("Debug", "测试小白平台数据c查詢數據" + response.body());
                    }
                });
    }



    private void updatesomeOne(final String userid,final String jsonDate) {
        OkGo.<String>post("http://hn216.api.yesapi.cn/?")//
                .tag(this)//
                .params("service", "App.Table.Update")
                .params("model_name", "manhuaUser")
                .params("id", userid)
                .params("data", jsonDate)
                .params("app_key", "A0A30E83923479344A44C6C6D4F38AFE")
                .params("sign", "92B7D208DCAD357FD6FFE05027CA7A4C")
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        RegistBean o = (RegistBean) JsonUitl.stringToObject(response.body(), RegistBean.class);
                        if (o.getRet() == 200) {
                            Toast.makeText(MyInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(MyInfoActivity.this, "信息错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("Debug", "测试小白平台数据c修改数据" + response.body());
                    }
                });
    }


}
