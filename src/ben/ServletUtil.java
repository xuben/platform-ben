package ben;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

public class ServletUtil {

	//无错误
	public static final int NO_ERROR = 0;
	//登录-用户名密码错误
	public static final int ERROR_LOGIN_VALIDATION = 1;
	//登录-验证码错误
	public static final int ERROR_LOGIN_AUTHCODE = 2;
	
	public static String getParameterWithDefault(HttpServletRequest request,
			String name, String defaultValue) {
		String value = null;
		if (request != null && name != null) {
			value = request.getParameter(name);
		}
		if (null == value) {
			value = defaultValue;
		}
		if(value != null){
			value = StringEscapeUtils.escapeHtml4(value);
		}
		return value;
	}

	public static Object getSessionAttributeWidthDefault(HttpSession session,
			String name, String defaultValue) {
		String value = null;
		if (session != null && name != null) {
			Object valueObj = session.getAttribute(name);
			if(valueObj != null){
				value = valueObj.toString();
			}
		}
		if (null == value) {
			value = defaultValue;
		}
		if(value != null){
			value = StringEscapeUtils.escapeHtml4(value);
		}
		return value;
	}
	
	public static int getSessionIntAttributeWidthDefault(HttpSession session,
			String name, int defaultValue) {
		Object obj = getSessionAttributeWidthDefault(session, name, String.valueOf(defaultValue));
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 返回错误信息
	 */
	public static String getErrorMsg(int errorCode){
		String errorMsg = "";
		switch(errorCode){
			case NO_ERROR:
				break;
			case ERROR_LOGIN_VALIDATION:
				errorMsg = "error username or password";
				break;
			case ERROR_LOGIN_AUTHCODE:
				errorMsg = "error authcode";
				break;
			default:
				errorMsg = "unknown error";
				break;
		}
		return errorMsg;	
	}
}
