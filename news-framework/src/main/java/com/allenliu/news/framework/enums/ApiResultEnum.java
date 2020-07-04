package com.allenliu.news.framework.enums;

/**
 * 
 * @ClassName: ApiResultCodeEnum
 * @Description: WEB API接口异常
 * @Author: Allen Liu
 * @CreateDate: 2017-11-22 12:28:15
 *
 */
public enum ApiResultEnum {
	/**
	 * 成功
	 */
	SUCCESS("0", "SUCCESS", "成功"),
	/**
	 * 无权访问
	 */
	NO_AUTHENTICATION("300", "NO_AUTHENTICATION", "无权访问"),
	/**
	 * 参数校验
	 */
	ARGUMENT_ERROR("600","ARGUMENT_ERROR","参数为空或格式错误"),
	/**
	 * 调用外部系统出错
	 */
	SYSTEM_ERROR("602","SYSTEM_ERROR","调用外部系统报错"),
	/**
	 * 调用外部系统超时
	 */
	SYSTEM_TIMEOUT("603","SYSTEM_TIMEOUT","调用外部系统超时"),
	/**
	 * 系统内部错误
	 */
	SYSTEM_EXCEPTION("700","SYSTEM_EXCEPTION","系统内部异常"),
	/**
	 * 数据库异常
	 */
	DATABASE_EXCEPTION("701","DATABASE_EXCEPTION","数据库异常"),
	/**
	 * 未知错误
	 */
	UNKNOWN_ERROR("999","UNKNOWN_ERROR","未知错误");

	private String code;
	private String msg;
	private String chineseMessage;


	ApiResultEnum(String code, String msg, String chineseMessage) {
		this.code = code;
		this.msg = msg;
		this.chineseMessage = chineseMessage;
	}

	public String getCode()
	{
		return code;
	}

	public String getMsg()
	{
		return msg;
	}


	/**
	 * 获取中文错误信息
	 *
	 * @return
	 */
	public String getChineseMessage()
	{
		return chineseMessage;
	}

	@Override
	public String toString() {
		return "ApiResultEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + ", chineseMessage='" + chineseMessage + '\'' + '}';
	}
}
