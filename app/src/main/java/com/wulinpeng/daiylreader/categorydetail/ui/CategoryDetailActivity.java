package com.wulinpeng.daiylreader.categorydetail.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.base.BaseActivity;
import com.wulinpeng.daiylreader.categorydetail.view.CatDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午2:07
 * @description:
 */
public class CategoryDetailActivity extends BaseActivity {

    @BindView(R.id.common_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.tab_layout)
    public TabLayout tabLayout;

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter;

    private List<Fragment> tabFragments = new ArrayList<>();

    private String[] tabTitles = {"最热", "最新", "好评", "完结"};

    private String cat;

    public static void startActivity(Context context, String cat, String gender) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra("cat", cat);
        intent.putExtra("gender", gender);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected void initViews() {
        String cat = getIntent().getStringExtra("cat");

        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle(cat);

        String gender = getIntent().getStringExtra("gender");
        tabFragments.add(CatDetailFragment.newInstance(cat, gender, "hot"));
        tabFragments.add(CatDetailFragment.newInstance(cat, gender, "new"));
        tabFragments.add(CatDetailFragment.newInstance(cat, gender, "reputation"));
        tabFragments.add(CatDetailFragment.newInstance(cat, gender, "over"));
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return tabFragments.get(position);
            }

            @Override
            public int getCount() {
                return tabFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles[position];
            }
        };
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}
