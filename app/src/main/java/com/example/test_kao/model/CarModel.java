package com.example.test_kao.model;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.CarBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.ICar;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class CarModel extends BaseModel implements ICar.Model {
    @Override
    public void getCar(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getCar(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<CarBean>(callback) {
            @Override
            public void onNext(CarBean carBean) {
                callback.Scuess(carBean);
            }
        }));
    }

    @Override
    public void getrelated(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getrelated(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RelateBean>(callback) {
                    @Override
                    public void onNext(RelateBean relateBean) {
                        callback.Scuess(relateBean);
                    }
                }));
    }
}
