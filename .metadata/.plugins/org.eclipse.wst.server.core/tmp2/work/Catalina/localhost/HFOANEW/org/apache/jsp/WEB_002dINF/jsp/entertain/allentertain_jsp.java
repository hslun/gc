/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.90
 * Generated at: 2018-12-14 07:56:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.entertain;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class allentertain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("<title>已驳回事后登记列表</title>\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/img/entertainImg/favicon.ico\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/jqueryui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/jqueryui/themes/icon.css\">\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/PublicStyle.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/DataImport.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/main.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/UserManage.css\">\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/CSS/jquery.form.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar es = \"新建\";\r\n");
      out.write("\t//提交action 的url\r\n");
      out.write("\tvar url = \"\";\r\n");
      out.write("\tvar idd = 1;\r\n");
      out.write("\t//返回json对象的长度\r\n");
      out.write("\tfunction getJsonObjLength(jsonObj) {\r\n");
      out.write("\t\tvar Length = 0;\r\n");
      out.write("\t\tfor ( var item in jsonObj) {\r\n");
      out.write("\t\t\tLength++;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn Length;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//格式化json\r\n");
      out.write("\tfunction formatJson(json) {\r\n");
      out.write("\t\tvar jsobj = eval(json);\r\n");
      out.write("\t\treturn '{\"total\":' + jsobj.bingdings.length + ',\"rows\":' + json + \"}\";\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\tfunction search() {\r\n");
      out.write("\t\t$('#searchUser').dialog('open').dialog('setTitle', '查询');\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("/* \tfunction approve(){\r\n");
      out.write("\t\t$('#dlg').dialog('open').dialog('setTitle', '审核');\r\n");
      out.write("\t} */\r\n");
      out.write("\t\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t//$(param.llbid) 传递过来得履历本id\r\n");
      out.write("\t\t//汉化 datagrid 翻页\r\n");
      out.write("\t\t$(\"#llbinfo\").datagrid({\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/entertain/wRGetRegisterEntertain',\r\n");
      out.write("\t\t\tmethod : 'post',\r\n");
      out.write("\t\t\tpageSize : 20,\r\n");
      out.write("\t\t\trownumbers : true,\r\n");
      out.write("\t\t\tsingleSelect : true,\r\n");
      out.write("\t\t\tshowFooter:true, // 增添脚注行\r\n");
      out.write("\t\t\tpagination : true,\r\n");
      out.write("\t\t\t/* toolbar : [{\r\n");
      out.write("\t\t\t\ttext : '查询',\r\n");
      out.write("\t\t\t\ticonCls : 'icon-search',\r\n");
      out.write("\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\tsearch();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t}], */\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tonLoadSuccess:function(data){  \r\n");
      out.write("\t\t\t\tconsole.log(data);\r\n");
      out.write("\t\t\t\t$('.preScan').linkbutton({text:'查看明细',plain:true,iconCls:'icon-search'}); \r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\tvar pager = $('#llbinfo').datagrid().datagrid('getPager');\r\n");
      out.write("\t\tpager.pagination({\r\n");
      out.write("\t\t\tbeforePageText : \"转到\",\r\n");
      out.write("\t\t\tafterPageText : \"共{pages}页\",\r\n");
      out.write("\t\t\tdisplayMsg : '当前显示从{from}到{to}  共{total}记录',\r\n");
      out.write("\t\t\tonBeforeRefresh : function(pageNumber, pageSize) {\r\n");
      out.write("\t\t\t\t$(this).pagination('loading');\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t$(this).pagination('loaded');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t// 审核通过按钮--只是将状态改为通过\r\n");
      out.write("\t\t$(\"#btn_ok\").click(\r\n");
      out.write("\t\t\tfunction() {\r\n");
      out.write("\t\t\tvar row = $('#llbinfo').datagrid('getSelected');\r\n");
      out.write("\t\t\tvar number = row.number;\r\n");
      out.write("\t\t\tvar param=\"number=\"+number;\r\n");
      out.write("\t\t\t\t$.post(\r\n");
      out.write("\t\t\t\t\t\t\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/entertain/approveX\",\r\n");
      out.write("\t\t\t\t\t\tparam, function(result) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#dlg\").dialog(\"close\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\talert(\"事后登记已通过审核！\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//window.location.reload(); \r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#llbinfo\").datagrid({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/entertain/wRGetAllApproved',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmethod : 'post',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tpageSize : 20,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\trownumbers : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tsingleSelect : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowFooter:true, // 增添脚注行\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tpagination : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttoolbar : [ /* {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\ttext : '查询',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\ticonCls : 'icon-search',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tsearch();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t} */],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tonLoadSuccess:function(data){  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t $('.preScan').linkbutton({text:'审核',plain:true,iconCls:'icon-search'}); \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t// 驳回事后登记\r\n");
      out.write("\t\t$(\"#btn_cancel\").click(function() {\r\n");
      out.write("\t\t\tvar row = $('#llbinfo').datagrid('getSelected');\r\n");
      out.write("\t\t\tvar uid = row.number;\r\n");
      out.write("\t\t\tvar param = \"applyId=\"+ uid;\r\n");
      out.write("\t\t\t$.post(\r\n");
      out.write("\t\t\t\t\t\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/register/notThrough\",\r\n");
      out.write("\t\t\t\t\tparam, function(result) {\r\n");
      out.write("\t\t\t\t\t\t\t\t$(\"#dlg\").dialog(\"close\");\r\n");
      out.write("\t\t\t\t\t\t\t\tconsole.info(result);\r\n");
      out.write("\t\t\t\t\t\t\t\talert(\"事后登记未通过审核！\");\r\n");
      out.write("\t\t\t\t\t\t\t\t$(\"#llbinfo\").datagrid({\r\n");
      out.write("\t\t\t\t\t\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/entertain/wRGetAllApproved',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmethod : 'post',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tpageSize : 20,\r\n");
      out.write("\t\t\t\t\t\t\t\t\trownumbers : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tsingleSelect : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tshowFooter:true, // 增添脚注行\r\n");
      out.write("\t\t\t\t\t\t\t\t\tpagination : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttoolbar : [ /* {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttext : '查询',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ticonCls : 'icon-search',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tsearch();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t} */],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\tonLoadSuccess:function(data){  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t $('.preScan').linkbutton({text:'审核',plain:true,iconCls:'icon-search'}); \r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t// 上传取消按钮\r\n");
      out.write("\t\t$(\"#uploadFileCancel\").click(function() {\r\n");
      out.write("\t\t\tdocument.getElementById('fileImport').value = null;\r\n");
      out.write("\t\t\t$('#import-excel').window('close')\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//查询用户\r\n");
      out.write("\t\t$(\"#search_ok\").click(\r\n");
      out.write("\t\t\t\t\t\tfunction() {\r\n");
      out.write("\t\t\t\t\t\t\tvar param = \"department=\"+ $(\"adepartment\").textbox(\"getText\").trim()\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ \"&manager=\"+ $(\"#amanager\").textbox(\"getText\").trim()\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ \"&startTime=\" + $(\"#astarttime\").textbox(\"getText\")\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ \"&endTime=\"+ $(\"#aendtime\").textbox(\"getText\")\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ \"&entertainObject=\"+ $(\"#aentertainobject\").textbox(\"getText\").trim();\r\n");
      out.write("\t\t\t\t\t\t\t$.post(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/entertain/searchApply\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparam, function(result) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(\"#searchUser\").dialog(\"close\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t$(\"#llbinfo\").datagrid(\"loadData\", result);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t$(\"#search_cancel\").click(function() {\r\n");
      out.write("\t\t\t$(\"#searchUser\").dialog(\"close\");\r\n");
      out.write("\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction fixWidth(percent) {\r\n");
      out.write("\t\treturn ($(\".mdiv\").width() - 30) * percent;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 操作列\r\n");
      out.write("\tfunction operation(value,row,rowIndex){\r\n");
      out.write("\t\t//console.info(row.id);\r\n");
      out.write("\t\treturn '<a class=\"preScan\" href=\"#\" iconCls=\"icon-search\" onclick=\"detail('+ row.id +','+rowIndex+')\">查看明细</a>';\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t// 显示明细表页面\r\n");
      out.write("\tfunction detail(id,index) {\r\n");
      out.write("\t\tvar row = $('#llbinfo').datagrid('getData').rows[index];\r\n");
      out.write("\t\tvar playOfpath='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/view/entertain/detail.jsp?number='+row.number+'&flag=1';//跳转到明细页面\r\n");
      out.write("\t\twindow.open(playOfpath,'newwindow','fullscreen=yes,channelmode=yes,resizable=yes,menubar=no,status=no,scrollbars=yes,');\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//增添事后表单\r\n");
      out.write("\tfunction approve(id,index){\r\n");
      out.write("\t\tvar now = new Date();\r\n");
      out.write("\t    var year = now.getFullYear();       //年\r\n");
      out.write("\t    var month = now.getMonth() + 1;     //月\r\n");
      out.write("\t    var day = now.getDate();            //日\r\n");
      out.write("\t\tvar clock = year + \"-\";\r\n");
      out.write("\t    var cmonth=\"\";\r\n");
      out.write("\t    var cday=\"\";\r\n");
      out.write("        \r\n");
      out.write("        if(month < 10){\r\n");
      out.write("        \tcmonth=\"0\"+month;\r\n");
      out.write("            clock += month+ \"-\";}//保持不变\r\n");
      out.write("        else{cmonth=month;\r\n");
      out.write("        clock += month + \"-\";}\r\n");
      out.write("        \r\n");
      out.write("        //if(day < 10)\r\n");
      out.write("            //clock += \"0\";\r\n");
      out.write("            \r\n");
      out.write("        clock += day;\r\n");
      out.write("        \r\n");
      out.write("\t\tvar row = $('#llbinfo').datagrid('getData').rows[index];\r\n");
      out.write("\t\t$('#dlg').dialog('open').dialog('setTitle', '审核单号'+row.number);\r\n");
      out.write("\t\t$(\"#upaidtime\").textbox(\"setText\", clock);\r\n");
      out.write("\t\t$(\"#uvouchernum1\").textbox(\"setValue\", year);\r\n");
      out.write("\t\t$(\"#uvouchernum2\").textbox(\"setValue\", cmonth);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#studentInfo\").datagrid({\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/entertain/registerDetail?number='+row.number,\r\n");
      out.write("\t\t\tmethod : 'post',\r\n");
      out.write("\t\t\trownumbers : true,\r\n");
      out.write("\t\t\tsingleSelect : true,});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"mdiv\" style=\"width:100%;height:94%;\">\r\n");
      out.write("\t\t<table id=\"llbinfo\" class=\"easyui-datagrid\" title=\"已驳回招待列表\"\r\n");
      out.write("\t\t\tstyle=\"width: auto; height:100%;\">\r\n");
      out.write("\t\t\t<thead>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t \t\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'number',width:fixWidth(0.08),align:'center'\">审批单号</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'department',width:fixWidth(0.08),align:'center'\">部门</th>\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'manager',width:fixWidth(0.06),align:'center'\">经办人</th>\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'approver',width:fixWidth(0.06),align:'center'\">审批人</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'entertainObject',width:fixWidth(0.18),align:'center'\">招待对象</th>\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'entertainCategory',width:fixWidth(0.06),align:'center'\">招待类别</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'entertainReason',width:fixWidth(0.1),align:'center'\">招待事由</th> \r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'entertainNum',width:fixWidth(0.06),align:'center'\">招待人数</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'accompanyNum',width:fixWidth(0.06),align:'center'\">陪同人数</th>\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'masterBudget',width:fixWidth(0.06),align:'center'\">总预算</th>\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'applyTime',width:fixWidth(0.1),align:'center'\">申请时间</th>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'operation',width:fixWidth(0.1),formatter:operation,align:'center'\">操作</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</thead>\r\n");
      out.write("\t\t</table>  \r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 审核登记对话框 -->\r\n");
      out.write("\t<div id=\"dlg\" class=\"easyui-dialog\"\r\n");
      out.write("\t\tstyle=\"width: 90%; height: 350px; padding: 0px 0px\" closed=\"true\"\r\n");
      out.write("\t\tbuttons=\"#dlg-buttons\">\r\n");
      out.write("\t\t<!--<div>用户编辑</div>  -->\r\n");
      out.write("\t\t<!-- <form id=\"fm\" method=\"post\">\r\n");
      out.write("\t\t\t<div style=\"text-align: center;\">\r\n");
      out.write("\t\t\t\t<table style=\"margin: auto;\" cellspacing=\"10\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>审批单号</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"unumber\" class=\"easyui-textbox\" disabled=\"disabled\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>发票开具日期</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uinvoicedate\" class=\"easyui-textbox\" disabled=\"disabled\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>开票内容</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uinvoicecontent\" class=\"easyui-textbox\" disabled=\"disabled\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>发票金额</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uinvoicesum\" class=\"easyui-textbox\" disabled=\"disabled\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>发票张数</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uinvoicenum\" class=\"easyui-textbox\" disabled=\"disabled\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>发票开具单位</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uinvoiceunit\" class=\"easyui-textbox\" disabled=\"disabled\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>报销时间</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"upaidtime\" class=\"easyui-textbox\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>凭证号</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uvouchernum1\" class=\"easyui-textbox\" style=\"width: 40px;\"></input>年\r\n");
      out.write("\t\t\t\t\t\t<input id=\"uvouchernum2\" class=\"easyui-textbox\" style=\"width: 25px;\"></input>月\r\n");
      out.write("\t\t\t\t\t\t&nbsp;第<input id=\"uvouchernum3\" class=\"easyui-textbox\" style=\"width: 40px;\"></input>号</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>备注</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uremark\" class=\"easyui-textbox\" disabled=\"disabled\" style=\"width: 160px;\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div> -->\r\n");
      out.write("\t\t\t<table id=\"studentInfo\" class=\"easyui-datagrid\" title=\"事后登记表单\"\r\n");
      out.write("\t\t\tstyle=\"width: 100%; height: 230px;\">\r\n");
      out.write("\t\t\t<thead>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'number',width:fixWidth(0.1),align:'center'\">审批单号</th> \r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'invoiceDate',width:fixWidth(0.15),align:'center'\">发票开具日期</th>\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'invoiceContent',width:fixWidth(0.15),align:'center'\">开票内容</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'invoiceNumber',width:fixWidth(0.1),align:'center'\">发票号</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'invoiceSum',width:fixWidth(0.1),align:'center'\">发票金额</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'invoiceNum',width:fixWidth(0.1),align:'center'\">发票张数</th>\r\n");
      out.write("\t\t\t\t\t<th \r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'invoiceUnit',width:fixWidth(0.2),align:'center'\">发票开具单位</th>\r\n");
      out.write("\t\t\t\t\t<!-- <th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'paidTime',width:fixWidth(0.1),align:'center'\">报销时间</th> -->\r\n");
      out.write("\t\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"field:'remark',width:fixWidth(0.1),align:'center'\">备注</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</thead>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t\t<div style=\"text-align: center; margin-top: 20px;\">\r\n");
      out.write("\t\t\t\t<a href=\"#\" id=\"btn_ok\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\tdata-options=\"iconCls:'icon-save'\" style=\"width: 20%;\">通过</a> \r\n");
      out.write("\t\t\t\t<a href=\"#\" id=\"btn_cancel\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\tdata-options=\"iconCls:'icon-cancel'\" style=\"width: 20%;\">驳回</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("<!-- 查询信息对话框 -->\r\n");
      out.write("\t<div id=\"searchUser\" class=\"easyui-dialog\"\r\n");
      out.write("\t\tstyle=\"width: 400px; height: 320px; padding: 10px 20px\" closed=\"true\">\r\n");
      out.write("\t\t<form id=\"sfm\" method=\"post\">\r\n");
      out.write("\t\t\t<div style=\"text-align: center;\">\r\n");
      out.write("\t\t\t\t<table style=\"margin: auto;\" cellspacing=\"10\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>审批单号</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uunumber\" class=\"easyui-textbox\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>发票开具日期</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"uuinvoicedate\" class=\"easyui-textbox\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>开票内容</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"astarttime\" class=\"easyui-datebox\"\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"sharedCalendar:'#cc'\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>发票金额</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"aendtime\" class=\"easyui-datebox\"\r\n");
      out.write("\t\t\t\t\t\tdata-options=\"sharedCalendar:'#cc'\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>招待对象</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"aentertainobject\" class=\"easyui-textbox\"></input></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<div id=\"cc\" class=\"easyui-calendar\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"text-align: center; bottom: 15px; margin-top: 20px;\">\r\n");
      out.write("\t\t\t\t<a href=\"#\" id=\"search_ok\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\tdata-options=\"iconCls:'icon-search'\" style=\"width: 20%;\">查询</a> <a\r\n");
      out.write("\t\t\t\t\thref=\"#\" id=\"search_cancel\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\t\t\tdata-options=\"iconCls:'icon-cancel'\" style=\"width: 20%;\">取消</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}