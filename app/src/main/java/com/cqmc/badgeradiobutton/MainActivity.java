package com.cqmc.badgeradiobutton;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        mBrbMain0.setBadgeNumber(1);
        mBrbMain1.setBadgeNumber(10).setBadgeColorBackground(Color.GREEN);
        mBrbMain2.setBadgeNumber(100).setBadgeOffX(10).setBadgeColorBadgeText(Color.BLUE);
        mBrbMain3.setBadgeNumber(0).setBadgeOffX(-10).setBadgeOffY(10);
    }
}
