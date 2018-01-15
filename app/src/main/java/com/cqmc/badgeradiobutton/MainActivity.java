package com.cqmc.badgeradiobutton;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.cqmc.badgeradiobutton.widget.BadgeRadioButton;

public class MainActivity extends AppCompatActivity {

    /** 首页 */
    private BadgeRadioButton mBrbMain0;
    /** 首页 */
    private BadgeRadioButton mBrbMain1;
    /** 首页 */
    private BadgeRadioButton mBrbMain2;
    /** 首页 */
    private BadgeRadioButton mBrbMain3;
    /** 首页 */
    private BadgeRadioButton mBrbMain4;
    /** 首页 */
    private BadgeRadioButton mBrbMain5;
    /** 首页 */
    private BadgeRadioButton mBrbMain6;
    /** 首页 */
    private BadgeRadioButton mBrbMain7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBrbMain0 = (BadgeRadioButton) findViewById(R.id.brb_main_0);
        mBrbMain1 = (BadgeRadioButton) findViewById(R.id.brb_main_1);
        mBrbMain2 = (BadgeRadioButton) findViewById(R.id.brb_main_2);
        mBrbMain3 = (BadgeRadioButton) findViewById(R.id.brb_main_3);
        mBrbMain4 = (BadgeRadioButton) findViewById(R.id.brb_main_4);
        mBrbMain5 = (BadgeRadioButton) findViewById(R.id.brb_main_5);
        mBrbMain6 = (BadgeRadioButton) findViewById(R.id.brb_main_6);
        mBrbMain7 = (BadgeRadioButton) findViewById(R.id.brb_main_7);

        mBrbMain0.setBadgeNumber(1);
        mBrbMain1.setBadgeNumber(10).setBadgeColorBackground(Color.GREEN);
        mBrbMain2.setBadgeNumber(100).setBadgeOffX(10).setBadgeColorBadgeText(Color.BLUE);
        mBrbMain3.setBadgeNumber(0).setBadgeOffX(-10).setBadgeOffY(10);
        mBrbMain4.setBadgeText("new");
        mBrbMain5.setBadgeText("免费");
        mBrbMain6.setBadgeText("free").setBadgeGravity(Gravity.LEFT);
        mBrbMain7.setBadgeText("free").setBadgeGravity(Gravity.RIGHT);
    }
}
