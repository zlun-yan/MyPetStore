<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/16
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

    <h2>My Orders</h2>

    <table>
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Total Price</th>
        </tr>

        <c:forEach var="order" items="${sessionScope.orderList.orders}">
            <tr>
                <td>
                    <a href="viewOrder?orderid=${order.orderId}">${order.orderId}</a>
                </td>
                <td>
                    <fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
                </td>
                <td>
                    <fmt:formatNumber value="${order.totPrice}" pattern="$#,##0.00" />
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>