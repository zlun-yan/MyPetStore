<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/15
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<h3>Account Information</h3>

<table>
    <tr>
        <td>Nickname:</td>
        <td><input type="text" name="nickname" value="${sessionScope.account.nickName}"></td>
    </tr>
    <tr>
        <td>Realname:</td>
        <td><input type="text" name="realname" value="${sessionScope.account.realName}"></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><input type="text" name="email" value="${sessionScope.account.email}"></td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td><input type="text" name="phone" value="${sessionScope.account.phone}"></td>
    </tr>
</table>

<h3>Profile Information</h3>

<table>
    <tr>
        <td>Language Preference:</td>
        <td>
<%--            <stripes:select name="account.languagePreference">--%>
<%--            <stripes:options-collection collection="${actionBean.languages}" />--%>
<%--        </stripes:select>--%>
            <select name="langpref">
                <c:if test="${sessionScope.account.languagePrefer eq 'english'}">
                    <option value="english" selected>english</option>
                </c:if>
                <c:if test="${sessionScope.account.languagePrefer ne 'english'}">
                    <option value="english">english</option>
                </c:if>

                <c:if test="${sessionScope.account.languagePrefer eq 'chinese'}">
                    <option value="chinese" selected>chinese</option>
                </c:if>
                <c:if test="${sessionScope.account.languagePrefer ne 'chinese'}">
                    <option value="chinese">chinese</option>
                </c:if>
            </select>
        </td>
    </tr>
    <tr>
        <td>Favourite Category:</td>
        <td>
            <select name="favcategory">
                <c:if test="${empty sessionScope.account.favCategory}">
                    <option>&nbsp;</option>
                </c:if>
                <c:if test="${sessionScope.account.favCategory eq 'BIRDS'}">
                    <option value="BIRDS" selected>BIRDS</option>
                </c:if>
                <c:if test="${sessionScope.account.favCategory ne 'BIRDS'}">
                    <option value="BIRDS">BIRDS</option>
                </c:if>

                <c:if test="${sessionScope.account.favCategory eq 'CATS'}">
                    <option value="CATS" selected>CATS</option>
                </c:if>
                <c:if test="${sessionScope.account.favCategory ne 'CATS'}">
                    <option value="CATS">CATS</option>
                </c:if>

                <c:if test="${sessionScope.account.favCategory eq 'DOGS'}">
                    <option value="DOGS" selected>DOGS</option>
                </c:if>
                <c:if test="${sessionScope.account.favCategory ne 'DOGS'}">
                    <option value="DOGS">DOGS</option>
                </c:if>

                <c:if test="${sessionScope.account.favCategory eq 'FISH'}">
                    <option value="FISH" selected>FISH</option>
                </c:if>
                <c:if test="${sessionScope.account.favCategory ne 'FISH'}">
                    <option value="FISH">FISH</option>
                </c:if>

                <c:if test="${sessionScope.account.favCategory eq 'REPTILES'}">
                    <option value="REPTILES" selected>REPTILES</option>
                </c:if>
                <c:if test="${sessionScope.account.favCategory ne 'REPTILES'}">
                    <option value="REPTILES">REPTILES</option>
                </c:if>
            </select>
        </td>
    </tr>
    <tr>
        <td>Enable MyList</td>
        <td>
<%--            这个checkbox传值是什么样的--%>
            <c:if test="${sessionScope.account.listOption}">
                <input type="checkbox" name="mylistopt" checked>
            </c:if>
            <c:if test="${not sessionScope.account.listOption}">
                <input type="checkbox" name="mylistopt">
            </c:if>
        </td>
    </tr>
    <tr>
        <td>Enable MyBanner</td>
        <td>
            <c:if test="${sessionScope.account.bannerOption}">
                <input type="checkbox" name="banneropt" checked>
            </c:if>
            <c:if test="${not sessionScope.account.bannerOption}">
                <input type="checkbox" name="banneropt">
            </c:if>
        </td>
    </tr>

</table>
