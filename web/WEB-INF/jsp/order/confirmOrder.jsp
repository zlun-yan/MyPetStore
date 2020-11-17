<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/16
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">
    Please confirm the information below...

    <table>
        <tr>
            <th align="center" colspan="3"><font size="4"><b>Order</b></font>
                <br/>
                <font size="3"><b>
                    <fmt:formatDate value="${requestScope.date}" pattern="yyyy/MM/dd hh:mm:ss" />
                </b></font>
            </th>
        </tr>

        <tr>
            <td>Item</td>
            <td>Quantity</td>
            <td>Total</td>
        </tr>
        <c:forEach var="order" items="${sessionScope.orders.orders}">
            <tr>
                <td>${order.itemId}</td>
                <td>${order.quantity}</td>
                <td>${order.totPrice}</td>
            </tr>
        </c:forEach>

        <tr>
            <th colspan="7">Select Address</th>
        </tr>


<%--        地址--%>
        <tr>
            <td>&nbsp;</td>
            <td>Receiver</td>
            <td>Phone</td>
            <td>Province</td>
            <td>City</td>
            <td>District</td>
            <td>Details</td>
        </tr>

        <form action="addOrderAddress" method="post">
            <c:forEach var="address" items="${sessionScope.account.addressList}">
                <tr>
                    <td><input type="radio" name="addressId" value="${address.id}"></td>
                    <td>${address.receiver}</td>
                    <td>${address.phone}</td>
                    <td>${address.province}</td>
                    <td>${address.city}</td>
                    <td>${address.district}</td>
                    <td>${address.details}</td>
                </tr>
            </c:forEach>
        <tr>
            <td colspan="7"><font color="red">${requestScope.msg}</font> </td>
        </tr>
    </table>
    <input type="submit" value="Confirm">
    </form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>





