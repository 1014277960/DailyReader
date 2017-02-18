package com.wulinpeng.daiylreader.category.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.base.BaseFragment;
import com.wulinpeng.daiylreader.category.adapter.CatAdapter;
import com.wulinpeng.daiylreader.category.contract.ICategoryPresenter;
import com.wulinpeng.daiylreader.category.contract.ICategoryView;
import com.wulinpeng.daiylreader.category.presenter.CategoryPresenterImpl;
import com.wulinpeng.daiylreader.entity.CatResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wulinpeng
 * @datetime: 17/2/18 下午11:38
 * @description:
 */
public class CategoryFragment extends BaseFragment implements ICategoryView {

    @BindView(R.id.male)
    public RecyclerView maleRecyclerView;

    @BindView(R.id.female)
    public RecyclerView femaleRecyclerView;

    @BindView(R.id.press)
    public RecyclerView pressRecyclerView;

    private CatAdapter maleAdapter;

    private CatAdapter femaleAdapter;

    private CatAdapter pressAdapter;

    private List<CatResponse.Cat> maleDate = new ArrayList<>();

    private List<CatResponse.Cat> femaleDate = new ArrayList<>();

    private List<CatResponse.Cat> pressDate = new ArrayList<>();

    private ICategoryPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initViews() {
        presenter = new CategoryPresenterImpl(this);

        maleRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        femaleRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        pressRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        maleAdapter = new CatAdapter(getContext(), maleDate);
        femaleAdapter = new CatAdapter(getContext(), femaleDate);
        pressAdapter = new CatAdapter(getContext(), pressDate);

        maleRecyclerView.setAdapter(maleAdapter);
        femaleRecyclerView.setAdapter(femaleAdapter);
        pressRecyclerView.setAdapter(pressAdapter);
    }

    @Override
    protected void initData() {
        presenter.getCatData();
    }

    @Override
    public void onCatDataLoad(CatResponse response) {
        maleDate.clear();
        maleDate.addAll(response.getMale());
        maleAdapter.notifyDataSetChanged();

        femaleDate.clear();
        femaleDate.addAll(response.getFemale());
        femaleAdapter.notifyDataSetChanged();

        pressDate.clear();
        pressDate.addAll(response.getPress());
        pressAdapter.notifyDataSetChanged();
    }
}
