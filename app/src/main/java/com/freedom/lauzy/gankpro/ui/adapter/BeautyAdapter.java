package com.freedom.lauzy.gankpro.ui.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freedom.lauzy.gankpro.R;
import com.freedom.lauzy.gankpro.common.utils.DensityUtils;
import com.freedom.lauzy.gankpro.function.entity.GankData;
import com.freedom.lauzy.gankpro.ui.activity.DailyActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.freedom.lauzy.gankpro.function.ValueConstants.ImageValue.IMAGE_URL;
import static com.freedom.lauzy.gankpro.function.ValueConstants.ImageValue.PUBLISH_DATE;

/**
 * BaseQuickAdapter方便添加header，footer，emptyView，以及加载更多
 * 设置多类型item时需数据设置itemType
 * Created by Lauzy on 2017/1/22.
 */

public class BeautyAdapter extends BaseQuickAdapter<GankData.ResultsBean, BaseViewHolder> {

    public BeautyAdapter(int layoutResId, List<GankData.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GankData.ResultsBean item) {
        //(ImageView) helper.getView(R.id.img_beauty_item)
        Picasso.with(mContext).load(item.getUrl())
                .into((ImageView) helper.getView(R.id.img_beauty_item));

        /*CollectionEntityDao entityDao = GankApp.getGankApp().getDaoSession().getCollectionEntityDao();
        CollectionEntity entity = new CollectionEntity((long) helper.getAdapterPosition(), item.getUrl(), item.getDesc(), item.getDesc());
        entityDao.insertOrReplace(entity);*/
       /* if (helper.getLayoutPosition() == 0) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    DensityUtils.dp2px(mContext,200));
            params.setMargins(6, DensityUtils.dp2px(mContext, 65), 6, 4);
            helper.getView(R.id.cv_beauty_item).setLayoutParams(params);
        }else {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    DensityUtils.dp2px(mContext,200));
            params.setMargins(6, 4, 6, 4);
            helper.getView(R.id.cv_beauty_item).setLayoutParams(params);
        }*/
        helper.getView(R.id.img_beauty_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DailyActivity.class);
                intent.putExtra(IMAGE_URL, item.getUrl());
                intent.putExtra(PUBLISH_DATE, item.getPublishedAt());
                if (Build.VERSION.SDK_INT >= 21) {
                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, v, "transitionImg").toBundle());
                } else {
                    mContext.startActivity(intent);
                }
            }
        });
    }
}