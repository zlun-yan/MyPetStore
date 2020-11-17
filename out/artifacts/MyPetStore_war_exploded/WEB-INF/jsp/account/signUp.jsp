<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/15
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

    <h3>User Information</h3>

    <form action="signUp" method="post">
        <table>
            <tr>
                <td>username:</td>
                <td><input type="text" name="userId"><font color="red">Cannot be changed</font></td>
            </tr>
            <tr>
                <td>password:</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>repeat password:</td>
                <td><input type="text" name="repassword"></td>
            </tr>
            <tr>
                <td>email:</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>phone:</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td align="right" colspan="2">
                    <font color="red">${requestScope.msg}</font>
                </td>
            </tr>
        </table>
        <input type="submit" value="Confirm">
    </form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>