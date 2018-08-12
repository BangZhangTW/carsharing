package com.zuba.carsharing.api.model;

/**
 * Created by panda.hsiao on 2017/10/27.
 */

public class ApiResult<T> {
    public T items;
    public int error_code = 0;
    public String message = "";
}
