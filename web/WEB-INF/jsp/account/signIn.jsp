<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/15
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

    <h3>User Information</h3>

    <form action="signIn" method="post">
        <table>
            <tr>
                <td>username:</td>
                <td><input type="text" name="userId" value="${requestScope.username}"></td>
            </tr>
            <tr>
                <td>password:</td>
                <td><input type="text" name="password" value="${requestScope.password}"></td>
            </tr>
            <c:if test="${requestScope.isVerify}">
                <tr>
                    <td>verify code:</td>
                    <td><input type="text" name="verifyCode"></td>
                    <td><img src="verifyCodeImage"> </td>
                </tr>
                <input type="hidden" name="isVerify" value="true">
            </c:if>
            <tr>
                <td colspan="2">
                    <font color="red">${requestScope.msg}</font>
                </td>
            </tr>
        </table>
        <input type="submit" value="Login">
    </form>
    Need a user name and password?
    <a href="signUp">Register Now!</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>