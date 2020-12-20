package com.example.test_kao.interfaces;

import com.example.test_kao.bean.CarBean;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.bean.TypeBean;

public interface ICar {
    interface View extends IBaseView{
        void getResult(CarBean carBean);
        void getResult(RelateBean relateBean);
    }
    interface Presenter extends IBasePresenter<ICar.View>{
        void getCar(int id);
        void getrelated(int id);
    }
    interface Model extends IBaseModel{
        void getCar(int id,Callback callback);
        void getrelated(int id,Callback callback);
    }
}
