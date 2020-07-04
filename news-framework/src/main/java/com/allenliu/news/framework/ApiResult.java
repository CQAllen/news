package com.allenliu.news.framework;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allenliu.news.framework.enums.ApiResultEnum;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Map;
import com.github.pagehelper.Page;

public class ApiResult<T> implements Serializable {

  private static final long serialVersionUID = -236053672816307102L;

  /** 返回code */
  private String code;

  /** 返回信息 */
  private String message;

  /** 返回数据 */
  private T result;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public static ApiResult build(String code, String msg) {
    ApiResult apiResult = new ApiResult();
    apiResult.setCode(code);
    apiResult.setMessage(msg);
    return apiResult;
  }

  public static ApiResult buildSuccess(String code, String msg, Object data) {
    ApiResult apiResult = new ApiResult();
    apiResult.setCode(code);
    apiResult.setMessage(msg);
    apiResult.setResult(data);
    return apiResult;
  }

  public static ApiResult build(ApiResultEnum resultCodeEnum) {
    ApiResult apiResult = new ApiResult();
    apiResult.setCode(resultCodeEnum.getCode());
    apiResult.setMessage(resultCodeEnum.getChineseMessage());
    return apiResult;
  }

  public static ApiResult build(ApiResultEnum resultCodeEnum, Object data) {
    ApiResult apiResult = new ApiResult();
    apiResult.setCode(resultCodeEnum.getCode());
    apiResult.setMessage(resultCodeEnum.getChineseMessage());
    apiResult.setResult(data);
    return apiResult;
  }

  public static ApiResult buildSuccess(Object data) {
    ApiResult apiResult = new ApiResult();
    apiResult.setCode(ApiResultEnum.SUCCESS.getCode());
    apiResult.setMessage(ApiResultEnum.SUCCESS.getChineseMessage());

    if (data instanceof Page) {
      Page pageData = (Page) data;
      Map<String, Object> result = Maps.newHashMap();
      result.put("pageNum", pageData.getPageNum());
      result.put("pageSize", pageData.getPageSize());
      result.put("total", pageData.getTotal());
      result.put("pageList", pageData.getResult());
      apiResult.setResult(result);
    } else {
      apiResult.setResult(data);
    }
    return apiResult;
  }

  public static ApiResult buildSuccess() {
    return buildSuccess(null);
  }

  public ApiResult(String code, String message, T result) {
    this.code = code;
    this.message = message;
    this.result = result;
  }

  public ApiResult() {}

  public JSONObject toJSONResult() {
    if (result != null) {
      return JSON.parseObject(JSON.toJSONString(this.result));
    } else {
      return null;
    }
  }

  public static boolean isSuccess(ApiResult apiResult) {
    return apiResult != null && ApiResultEnum.SUCCESS.getCode().equals(apiResult.getCode());
  }
}
