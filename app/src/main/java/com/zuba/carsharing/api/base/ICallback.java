package com.zuba.carsharing.api.base;

/**
 * Created by bang.chang on 2018/6/15.
 */

public interface ICallback<T> {
    void iSuccess(T t);
    void iFail(String errorBody);
}
