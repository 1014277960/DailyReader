package com.wulinpeng.daiylreader;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.ImageView;

import wulinpeng.com.framework.base.ui.BaseActivity;
import com.wulinpeng.daiylreader.category.view.CategoryFragment;
import com.wulinpeng.daiylreader.rank.ui.RankFragment;
import com.wulinpeng.daiylreader.search.view.SearchActivity;
import com.wulinpeng.daiylreader.self.view.SelfFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.img_user)
    public ImageView userImg;

    @BindView(R.id.img_search)
    public ImageView searchImg;

    @BindView(R.id.tab_layout)
    public TabLayout tabLayout;

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter;

    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    public NavigationView navigationView;

    private List<Fragment> tabFragments = new ArrayList<>();

    private String[] tabTitles = {"书架", "分类", "榜单"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        searchImg.setOnClickListener(v -> SearchActivity.startActivity(this));

        tabFragments.add(new SelfFragment());
        tabFragments.add(new CategoryFragment());
        tabFragments.add(new RankFragment());
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
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {}

    @OnClick(R.id.img_user)
    public void showNavigationView(ImageView imageView) {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @OnClick(R.id.img_search)
    public void search(ImageView imageView) {}

}
