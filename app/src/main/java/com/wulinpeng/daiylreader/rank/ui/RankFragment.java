package com.wulinpeng.daiylreader.rank.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.net.ReaderApiManager;
import wulinpeng.com.framework.base.ui.BaseFragment;
import com.wulinpeng.daiylreader.bean.RankingInfoResponse;
import com.wulinpeng.daiylreader.rank.adapter.RankingAdapter;
import com.wulinpeng.daiylreader.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wulinpeng
 * @datetime: 17/2/18 下午11:38
 * @description:
 */
public class RankFragment extends BaseFragment {

    @BindView(R.id.male)
    public RecyclerView maleRv;

    @BindView(R.id.female)
    public RecyclerView femaleRv;

    private RankingAdapter maleAdapter, femaleAdapter;

    private List<RankingInfoResponse.Rank> maleRanks = new ArrayList<>();

    private List<RankingInfoResponse.Rank> femaleRanks = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void initViews() {
        maleAdapter = new RankingAdapter(getContext(), maleRanks);
        femaleAdapter = new RankingAdapter(getContext(), femaleRanks);

        maleRv.setLayoutManager(new LinearLayoutManager(getContext()));
        femaleRv.setLayoutManager(new LinearLayoutManager(getContext()));

        maleRv.setAdapter(maleAdapter);
        femaleRv.setAdapter(femaleAdapter);
    }

    @Override
    protected void initData() {
        ReaderApiManager.getInstance().getRankingInfo()
                .compose(RxUtil.rxScheduler())
                .subscribe(rankingInfoResponse -> {
                    maleRanks.clear();
                    maleRanks.addAll(rankingInfoResponse.getMale().subList(0, 5));
                    femaleRanks.clear();
                    femaleRanks.addAll(rankingInfoResponse.getFemale().subList(0, 5));
                    maleAdapter.notifyDataSetChanged();
                    femaleAdapter.notifyDataSetChanged();
                }, throwable -> Toast.makeText(getContext(), "发错错误了", Toast.LENGTH_SHORT).show());
    }
}
