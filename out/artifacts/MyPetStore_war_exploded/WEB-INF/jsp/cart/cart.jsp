<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/15
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">

        <h2>Shopping Cart</h2>
        <form action="addOrder" method="post" id="order"/>
        <form action="updateCart" method="post" id="updateCart"/>
        <table>
            <tr>
                <th>&nbsp;</th>
                <th><b>Item ID</b></th>
                <th><b>Product ID</b></th>
                <th><b>Description</b></th>
                <th><b>In Stock?</b></th>
                <th><b>Quantity</b></th>
                <th><b>List Price</b></th>
                <th><b>Total Cost</b></th>
                <th>&nbsp;</th>
            </tr>

            <c:if test="${sessionScope.cart.numberOfItems == 0}">
                <tr>
                    <td colspan="9"><b>Your cart is empty.</b></td>
                </tr>
            </c:if>

            <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                <tr>
                    <td><input type="checkbox" name="checkedId" value="${cartItem.id}" form="order"></td>
                    <td>
                        <a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                    </td>
                    <td>${cartItem.item.product.productId}</td>
                    <td>
                            ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                            ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                            ${cartItem.item.attribute5} ${cartItem.item.product.name}
                    </td>
                    <td>${cartItem.inStock}</td>
                    <td>
                        <input type="text" name="${cartItem.item.itemId}" value="${cartItem.quantity}" size="3" form="updateCart">
                    </td>
                    <td><fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" /></td>
                    <td><fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" /></td>
                    <td>
                        <a class="Button" href="removeItemFromCart?itemId=${cartItem.item.itemId}" >Remove</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="8">
                    Sub Total:
                    <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                </td>
                <td><input type="submit" name="updateCartQuantities" value="Update Cart" form="updateCart"></td>
            </tr>
            <tr>
                <td colspan="9"><font color="red">${requestScope.msg}</font> </td>
            </tr>
        </table>
        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <input type="submit" value="Proceed to Checkout" form="order">
        </c:if>
    </div>


    <div id="MyList">
        <c:if test="${sessionScope.account != null}">
            <c:if test="${!empty sessionScope.account.listOption}">
                <%@ include file="includeMyList.jsp"%>
            </c:if>
        </c:if>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>