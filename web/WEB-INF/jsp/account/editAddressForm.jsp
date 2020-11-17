<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/15
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">

    <h3>Add New Address</h3>

    <form action="addAddress" method="post">
        <table>
            <tr>
                <td>Province:</td>
                <td><input type="text" name="province"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="city"></td>
            </tr>
            <tr>
                <td>District:</td>
                <td><input type="text" name="district"></td>
            </tr>
            <tr>
                <td>Details:</td>
                <td><input type="text" name="details"></td>
            </tr>
            <tr>
                <td>Receiver Name:</td>
                <td><input type="text" name="receiver"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td colspan="2"><font color="red">${requestScope.msg}</font> </td>
            </tr>
        </table>
        <input type="submit" value="confirm">
    </form>


    <h3>Address Information</h3>

    <table>
        <tr>
            <td>Receiver</td>
            <td>Phone</td>
            <td>Province</td>
            <td>City</td>
            <td>District</td>
            <td>Details</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>

        <c:forEach var="address" items="${sessionScope.account.addressList}">
            <tr>
                <form action="editAddress" method="post">
                    <td><input type="text" name="receiver" value="${address.receiver}"></td>
                    <td><input type="text" name="phone" value="${address.phone}"></td>
                    <td><input type="text" name="province" value="${address.province}"></td>
                    <td><input type="text" name="city" value="${address.city}"></td>
                    <td><input type="text" name="district" value="${address.district}"></td>
                    <td><input type="text" name="details" value="${address.details}"></td>
                    <td>
                        <input type="hidden" name="itemId" value="${address.id}">
                        <input type="submit" value="Update">
                    </td>
                </form>
                <td>
                    <a class="Button" href="editAddress?thisAction=delete&itemId=${address.id}" >Remove</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
