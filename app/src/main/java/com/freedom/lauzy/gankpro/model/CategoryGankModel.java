package com.freedom.lauzy.gankpro.model;

import android.content.Context;

import com.freedom.lauzy.gankpro.function.entity.GankData;
import com.freedom.lauzy.gankpro.function.net.ApiFactory;
import com.freedom.lauzy.gankpro.function.net.LySubscriber;
import com.freedom.lauzy.gankpro.function.net.OnResponse;

/**
 * 分类Model
 * Created by Lauzy on 2017/2/4.
 */

public class CategoryGankModel {
    private static final String LYTAG = CategoryGankModel.class.getSimpleName();
    private Context mContext;

    public CategoryGankModel(Context context) {
        mContext = context;
    }

    public CategoryGankModel(){

    }

    public void getCategoryGankData(String type, int page, final OnResponse<GankData> response) {
        ApiFactory.getInstance().getGankData(new LySubscriber<GankData>(mContext) {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onNext(GankData o) {
                if (o == null) {
                    response.onError(new Throwable("data is null"));
                } else {
                    response.onSuccess(o);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                response.onError(e);
            }
        }, type, page);
    }
}
