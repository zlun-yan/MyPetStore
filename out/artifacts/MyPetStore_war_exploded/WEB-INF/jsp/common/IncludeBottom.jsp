<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/12
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
</div>

<div id="Footer">

    <div id="PoweredBy">
        &nbsp<a href="https://blog.csdn.net/i_want_to_studyi">@&nbspZlunYan</a>
    </div>

    <div id="Banner">
        <c:if test="${not empty sessionScope.account && !(sessionScope.account eq null)}">
            <c:if test="${sessionScope.account.bannerOption}">
                <c:if test="${not empty sessionScope.account.favCategory && !(sessionScope.account.favCategory eq null)}">
                    ${sessionScope.account.favCategoryURL}
                </c:if>
            </c:if>
        </c:if>
    </div>

</div>

</body>
</html>