package top.wzmyyj.zymk.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.GetUrlBean;
import top.wzmyyj.zymk.app.bean.checkLoginBean;
import top.wzmyyj.zymk.app.utils.JsonUitl;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.contract.SettingContract;
import top.wzmyyj.zymk.presenter.SettingPresenter;


/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */

public class LoginActivity extends BaseActivity<SettingContract.IPresenter> implements SettingContract.IView {
    @Override
    protected void initPresenter() {
        mPresenter = new SettingPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @BindView(R.id.iv_common_close)
    ImageView ivCommonClose;
    @BindView(R.id.tv_account_login)
    TextView tvAccountLogin;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.pass_et)
    EditText passEt;
    @BindView(R.id.parent_psd)
    RelativeLayout parentPsd;
    @BindView(R.id.button)
    TextView button;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @OnClick({R.id.iv_common_close, R.id.button, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_common_close:
                finish();
                break;
            case R.id.button:
                String name = phoneEt.getText().toString();
                String pass = passEt.getText().toString();
                Log.i("TAG", name + "_" + pass);
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginActivity.this, "手机号码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                manhuaceshi(name, pass);

                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,registActivity.class));
                break;
        }
    }


    //    https://bsqklserver.shuliantt.com/shuliantt/queryAuditPassJumpUrlV2?key=674&terminal=android&version=1.0.0&isJumpUrl=true
    private void manhuaceshi(final String name, final String pwd) {
        OkGo.<String>post("http://hn216.api.yesapi.cn/?")//
                .tag(this)//
                .params("service", "App.Table.FreeQuery")
                .params("model_name", "manhuaUser")
                .params("where", "[[\"phone\", \"=\", \"" + name + "\"]]")
                .params("app_key", "A0A30E83923479344A44C6C6D4F38AFE")
                .params("sign", "92B7D208DCAD357FD6FFE05027CA7A4C")
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        checkLoginBean o = (checkLoginBean) JsonUitl.stringToObject(response.body(), checkLoginBean.class);
                        if (o.getRet() == 200) {
                            if (o.getData().getList().size() == 0) {
                                Toast.makeText(LoginActivity.this, "请先注册", Toast.LENGTH_SHORT).show();
                            } else {
                                checkLoginBean.DataBean.ListBean listBean = o.getData().getList().get(0);
                                if (listBean.getPhone().equals(name) && listBean.getPwd().equals(pwd)) {
                                    SharedPreferences mSharedPreferences = getSharedPreferences("student_data", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edite = mSharedPreferences.edit();
                                    edite.putString("token", "已登录");
                                    edite.putString("userid", listBean.getId() + "");
                                    edite.commit();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            Toast.makeText(LoginActivity.this, "信息错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
                        }

                        Log.d("Debug", "测试小白平台数据" + response.body());
                    }
                });
    }


    @Override
    protected void initData() {
        super.initData();

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


}
