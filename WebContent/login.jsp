<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
</STYLE>
<script type="text/javascript">
//防止页面后退
	 history.pushState(null, null, document.URL);
	window.addEventListener('popstate', function () {
      history.pushState(null, null, document.URL);
	});
	/* window.history.back(1); */
	/* self.location='login.jsp';  */
</script>
<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>
	
</HEAD>
<BODY>
<FORM id=form1 name=form1 onsubmit="return checkvCode()" action="${pageContext.request.contextPath}/user_login.action" method="post" target="_parent">

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR><!-- 空白行 -->
                <TD style="HEIGHT: 18px"> </TD>
                <TD style="HEIGHT: 18px"> </TD>
                <TD style="HEIGHT: 18px"> </TD>
              </TR>
               <TR><!-- 空白行 -->
                <TD style="HEIGHT: 18px"> </TD>
                <TD style="HEIGHT: 18px"> </TD>
                <TD style="HEIGHT: 18px"> </TD>
              </TR>
              <TR>
              
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150>
                <INPUT id="user_code" style="WIDTH: 130px" name="user_code"></TD>
                <TD style="HEIGHT: 28px" width=370><SPAN 
                  id=RequiredFieldValidator3 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id="user_password" style="WIDTH: 130px" type=password name="user_password"></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">验证码：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT onblur="vvCode()" id="viCode" name="viCode" type="text" style="WIDTH: 130px;" />
                <span id="vcodes" class="vcode"></span>
                
                </TD>

                <TD style="HEIGHT: 28px">&nbsp;</TD></TR>	
              <TR>
              <TR>
                <TD style="HEIGHT: 28px"></TD>
                <TD style="HEIGHT: 28px">
                
             	<img id="vId" onclick="this.src='${pageContext.request.contextPath }/vCode?id='+Math.random()" alt="" src="${pageContext.request.contextPath }/vCode">
                <br/>
                <span style="color:yellow; height:28px; ">${userMsg }${codeMsg } </span> <br/>
                </TD>

                <TD style="HEIGHT: 28px">&nbsp;</TD></TR>
            <!--   <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR> -->
                <TD></TD>
                <TD><INPUT id=btn 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  type=image src="images/login_button.gif" name=btn> 
                  <a href="register.jsp" style="text-decoration:none; color:white;">注册</a>
              </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
<TR>
    <TD><IMG src="images/login_3.jpg" 
border=0></TD></TR></TBODY></TABLE></DIV></DIV>
</FORM></BODY></HTML>
	<%
		session.removeAttribute("userMsg");
		session.removeAttribute("codeMsg");
	%>

</body>
</html>