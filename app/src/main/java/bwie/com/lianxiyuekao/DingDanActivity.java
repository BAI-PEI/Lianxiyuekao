package bwie.com.lianxiyuekao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import bwie.com.lianxiyuekao.fragment.CanceldFragment;
import bwie.com.lianxiyuekao.fragment.PayedFragment;
import bwie.com.lianxiyuekao.fragment.WaitPayFragment;

public class DingDanActivity extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton rbAll;
    private RadioButton rbWait;
    private RadioButton rbPayed;
    private RadioButton rbCancel;
    private ViewPager viewpager;
    private List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);

        initView();
        MyApapter apapter=new MyApapter(getSupportFragmentManager());
        viewpager.setAdapter(apapter);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_wait:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.rb_payed:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.rb_cancel:
                        viewpager.setCurrentItem(2);
                        break;

                }
            }
        });
    }

    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        rbWait = (RadioButton) findViewById(R.id.rb_wait);
        rbPayed = (RadioButton) findViewById(R.id.rb_payed);
        rbCancel = (RadioButton) findViewById(R.id.rb_cancel);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        list = new ArrayList<>();
        list.add(new WaitPayFragment());
        list.add(new PayedFragment());
        list.add(new CanceldFragment());
    }
    class MyApapter extends FragmentPagerAdapter {

        public MyApapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }



}
