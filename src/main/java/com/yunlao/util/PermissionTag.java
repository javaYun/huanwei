//package com.yunlao.util;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import com.sczh.platform.po.MenuInfo;
//import com.sczh.platform.po.UserInfo;
//
//public class PermissionTag extends TagSupport {
//	private static final long serialVersionUID = 4592227792811389132L;
//
//	private String module;// 属性名必须与JSP自定义标签的属性名一样
//
//	private String code;
//
//	public String getModule() {
//		return module;
//	}
//
//	public void setModule(String module) {
//		this.module = module;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	@Override
//	public int doStartTag() throws JspException {
//		boolean result = false;
//		HttpServletRequest request = (HttpServletRequest) this.pageContext
//				.getRequest();// 通过成员变量获取HttpServletRequest对象
//		UserInfo admin = (UserInfo) request.getSession().getAttribute("admin");// 获取登录到系统的用户
//		if (admin != null) {
//			if ("1".equals(String.valueOf(admin.getGroupid()))) {// 超级管理员
//				result = true;
//			} else {
//				DynamicParams params = new DynamicParams();
//				params.put("id", String.valueOf(admin.getId()));
//				params.put("module", this.module);
//				params.put("code", this.code);
//				AuthManager authManager = SpringContextHolder
//						.getBean(AuthManager.class);
//				List<MenuInfo> userRoleAuths = authManager
//						.findUserRoleAuthList(params);
//				if (userRoleAuths != null && userRoleAuths.size() > 0) {
//					result = true;
//				}
//			}
//		}
//		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
//	}
//}
