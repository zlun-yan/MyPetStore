<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/12
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">
    <h2>${sessionScope.category.name}</h2>
<%--    选择的种类的名称 记得再修改下 servlet的代码--%>
    <table>
        <tr>
            <th>Product ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var="product" items="${sessionScope.productList}">
            <tr>
                <td>
                    <a href="viewProduct?productId=${product.productId}">${product.productId}</a>
                </td>
                <td>${product.name}</td>
            </tr>
        </c:forEach>
    </table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>


