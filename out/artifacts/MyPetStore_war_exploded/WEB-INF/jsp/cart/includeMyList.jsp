<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/16
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${not empty sessionScope.myList}">
    <p>Pet Favorites <br/>
        Shop for more of your favorite pets here.</p>
    <ul>
        <c:forEach var="product" items="${sessionScope.myList.productList}">
            <li>
                <a href="viewProduct?productId=${product.productId}">${product.name}</a>
                (${product.productId})
            </li>
        </c:forEach>
    </ul>

</c:if>
