<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/16
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <th colspan="2">Address</th>
        </tr>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <td>Receiver</td>
                        <td>Phone</td>
                        <td>Province</td>
                        <td>City</td>
                        <td>District</td>
                        <td>Details</td>
                    </tr>
                    <tr>
                        <td>${requestScope.address.receiver}</td>
                        <td>${requestScope.address.phone}</td>
                        <td>${requestScope.address.province}</td>
                        <td>${requestScope.address.city}</td>
                        <td>${requestScope.address.district}</td>
                        <td>${requestScope.address.details}</td>
                    </tr>
                </table>
            </td>
        </tr>

        <tr>
            <th colspan="2">Details</th>
        </tr>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <th>Item ID</th>
                        <th>State</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Cost</th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:forEach var="order" items="${sessionScope.orders.orders}">
                        <tr>
                            <td>
                                <a href="viewItem">${order.item.itemId}</a>
                            </td>
                            <td>
                                ${order.state}
                            </td>
                            <td>
                                <c:if test="${order.item != null}">
                                    ${order.item.attribute1}
                                    ${order.item.attribute2}
                                    ${order.item.attribute3}
                                    ${order.item.attribute4}
                                    ${order.item.attribute5}
                                    ${order.item.product.name}
                                </c:if>

                                <c:if test="${item == null}">
                                    <i>{description unavailable}</i>
                                </c:if>
                            </td>

                            <td>${order.quantity}</td>
                            <td>
                                <fmt:formatNumber value="${order.item.listPrice}" pattern="$#,##0.00" />
                            </td>
                            <td>
                                <fmt:formatNumber value="${order.totPrice}" pattern="$#,##0.00" />
                            </td>
                            <c:if test="${order.state eq 'unpay'}">
                                <td>
                                    <a class="Button" href="pay">go to pay</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>

                    <c:if test="${requestScope.unpay}">
                        <tr>
                            <th colspan="6">
                                Total: <fmt:formatNumber value="${requestScope.orders.subTotal}" pattern="$#,##0.00" />
                            </th>
                            <th>
                                <a class="Button" href="pay">go to pay</a>
                            </th>

                        </tr>
                    </c:if>
                </table>
            </td>
        </tr>

    </table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
