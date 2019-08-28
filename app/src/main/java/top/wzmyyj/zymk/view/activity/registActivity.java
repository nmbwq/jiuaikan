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
import top.wzmyyj.zymk.app.bean.RegistBean;
import top.wzmyyj.zymk.app.bean.UserBean;
import top.wzmyyj.zymk.app.bean.checkLoginBean;
import top.wzmyyj.zymk.app.utils.ActivityManager;
import top.wzmyyj.zymk.app.utils.JsonUitl;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.contract.SettingContract;
import top.wzmyyj.zymk.presenter.SettingPresenter;


/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */

public class registActivity extends BaseActivity<SettingContract.IPresenter> implements SettingContract.IView {
    @Override
    protected void initPresenter() {
        mPresenter = new SettingPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }


    @BindView(R.id.iv_login_top)
    ImageView ivLoginTop;
    @BindView(R.id.iv_common_close)
    ImageView ivCommonClose;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.pass_et)
    EditText passEt;
    @BindView(R.id.parent_psd)
    RelativeLayout parentPsd;
    @BindView(R.id.pass_et1)
    EditText passEt1;
    @BindView(R.id.parent_psd1)
    RelativeLayout parentPsd1;
    @BindView(R.id.button)
    TextView button;

    @OnClick({R.id.iv_common_close, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_common_close:
                finish();
                break;
            case R.id.button:
                String name = phoneEt.getText().toString().trim();
                String pass = passEt.getText().toString().trim();
                String pass1 = passEt1.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(registActivity.this, "手机号码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(registActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass1)){
                    Toast.makeText(registActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!pass.equals(pass1)){
                    Toast.makeText(registActivity.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
                    return;
                }
                manhuaceshi(name,pass);
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
                                UserBean userBean = new UserBean();
                                userBean.setPhone(name);
                                userBean.setPwd(pwd);
                                String jsonDate = JsonUitl.objectToString(userBean);
                                creatuser(jsonDate);
                            } else {
                                Toast.makeText(registActivity.this, "此账号已注册过", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(registActivity.this, "信息错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
                        }

                        Log.d("Debug", "测试小白平台数据" + response.body());
                    }
                });
    }




    //    https://bsqklserver.shuliantt.com/shuliantt/queryAuditPassJumpUrlV2?key=674&terminal=android&version=1.0.0&isJumpUrl=true
    private void creatuser(final String jsonDate) {
        OkGo.<String>post("http://hn216.api.yesapi.cn/?")//
                .tag(this)//
                .params("service", "App.Table.Create")
                .params("model_name", "manhuaUser")
                .params("data", jsonDate)
                .params("app_key", "A0A30E83923479344A44C6C6D4F38AFE")
                .params("sign", "92B7D208DCAD357FD6FFE05027CA7A4C")
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        RegistBean o = (RegistBean) JsonUitl.stringToObject(response.body(), RegistBean.class);
                        if (o.getRet() == 200) {
                            SharedPreferences mSharedPreferences = getSharedPreferences("student_data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edite = mSharedPreferences.edit();
                            edite.putString("token", "已登录");
                            edite.putString("userid", o.getData().getId() + "");
                            edite.commit();
                            startActivity(new Intent(registActivity.this, MainActivity.class));
                            ActivityManager.getInstance().finishActivity(LoginActivity.class);
                            finish();
                        } else {
                            Toast.makeText(registActivity.this, "信息错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
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
