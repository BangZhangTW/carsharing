package com.zuba.carsharing.api.model;

/**
 * Created by panda.hsiao on 2017/10/27.
 */

public class ApiResult<T> {
    public T ReturnData;
    public String ReturnCode = "";
    public String ErrorMessage = "";
    public String ReturnTime = "";
}
