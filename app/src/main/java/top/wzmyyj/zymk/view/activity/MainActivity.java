package top.wzmyyj.zymk.view.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.base.activity.BaseMainActivity;
import top.wzmyyj.zymk.view.fragment.HomeFragment;
import top.wzmyyj.zymk.view.fragment.MineFragment;
import top.wzmyyj.zymk.view.fragment.FindFragment;
import top.wzmyyj.zymk.view.fragment.TypeFragment;

/**
 * Created by yyj on 2018/06/24. email: 2209011667@qq.com
 */

public class MainActivity extends BaseMainActivity {

    @Override
    protected void initFTs(List<FT> fts) {
        fts.add(new FT(new HomeFragment(), "主页", R.mipmap.svg_tab_bar_main, R.mipmap.svg_tab_bar_main_hl));
        fts.add(new FT(new TypeFragment(), "分类", R.mipmap.svg_tab_bar_kind, R.mipmap.svg_tab_bar_kind_hl));
        fts.add(new FT(new FindFragment(), "足迹", R.mipmap.svg_tab_bar_find, R.mipmap.svg_tab_bar_find_hl));
        fts.add(new FT(new MineFragment(), "我的", R.mipmap.svg_tab_bar_mine, R.mipmap.svg_tab_bar_mine_hl));
    }
    /**
     * 用于优雅的退出程序(当从其他地方退出应用时会先返回到此页面再执行退出)
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra("TAG_EXIT", false);
            if (isExit) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        }
    }
    //手机连续按返回键两次的间隔时间
    private long mExitTime;
    //按系统返回键时出发
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出就爱看", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
