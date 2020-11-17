<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/15
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

    <h3>User Information</h3>

    <form action="editAccount" method="post">
        <table>
            <tr>
                <td>User ID:</td>
                <td>${sessionScope.account.userId}</td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repassword"></td>
            </tr>
            <tr>
                <td><font color="red">${requestScope.msg}</font> </td>
            </tr>
        </table>
        <%@ include file="IncludeAccountFields.jsp"%>

        <input type="submit" value="Save Account Information">
        <a class="Button" href="editAddress">Edit Address</a>
    </form>
    <a href="viewOrderList" class="Button">My Orders</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
