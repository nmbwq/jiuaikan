package top.wzmyyj.zymk.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.wzm_sdk.widget.CircleImageView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.SelectOneBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.app.utils.JsonUitl;
import top.wzmyyj.zymk.base.fragment.BaseFragment;
import top.wzmyyj.zymk.contract.MineContract;
import top.wzmyyj.zymk.presenter.MinePresenter;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 * 第四页。
 */

public class MineFragment extends BaseFragment<MineContract.IPresenter> implements MineContract.IView {


    @BindView(R.id.img_me)
    CircleImageView imgMe;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.bt_star)
    Button btStar;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.ll_4)
    LinearLayout ll4;
    Unbinder unbinder;

    @Override
    protected void initPresenter() {
        mPresenter = new MinePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_4;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
//        addPanels(new MineNestedScrollPanel(context,mPresenter));
    }

//    @BindView(R.id.fl_panel)
//    FrameLayout fl_panel;

    @BindView(R.id.v_top)
    View v;

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v);
//        fl_panel.addView(getPanelView(0));
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("student_data", Context.MODE_PRIVATE);
         String    userid = mSharedPreferences.getString("userid", "");
         if (!TextUtils.isEmpty(userid)){
             selectsomeOne(userid);
         }

    }

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
                    public void onSuccess(Response<String> response) {
                        SelectOneBean o = (SelectOneBean) JsonUitl.stringToObject(response.body(), SelectOneBean.class);
                        if (o.getRet() == 200) {
                            SelectOneBean.DataBeanX.DataBean data = o.getData().getData();
                            if (!TextUtils.isEmpty(data.getUsername())){
                                tvName.setText(data.getUsername());
                            }
                            if (!TextUtils.isEmpty(data.getDescription())){
                                tvDescription.setText(data.getDescription());
                            }

                        } else {
                            Toast.makeText(getActivity(), "信息错误，请稍后尝试！", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("Debug", "测试小白平台数据c查詢數據" + response.body());
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_info, R.id.ll_1, R.id.ll_2, R.id.ll_3, R.id.ll_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                I.toMyinfo(getActivity());
                break;
            case R.id.ll_1:
                break;
            case R.id.ll_2:
                I.toBrowser(getActivity(), Urls.ZYMK_Activity);
                break;
            case R.id.ll_3:
                I.toBrowser(getActivity(), Urls.ZYMK_Tmall);
                break;
            case R.id.ll_4:
                I.toSettingActivity(getActivity());
                break;
        }
    }
}
