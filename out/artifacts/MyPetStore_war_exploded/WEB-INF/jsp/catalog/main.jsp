<%--
  Created by IntelliJ IDEA.
  User: ZlunYan
  Date: 2020/11/12
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../common/IncludeTop.jsp"%>

<div id="Welcome">
    <c:if test="${empty sessionScope.account}">
        <div id="WelcomeContent">
            Welcome to PetStore!
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.account}">
        <div id="WelcomeContent">
            <c:if test="${empty sessionScope.account.nickName}">
                Welcome ${sessionScope.account.userId}
            </c:if>
            <c:if test="${not empty sessionScope.account.nickName}">
                Welcome ${sessionScope.account.nickName}
            </c:if>
        </div>
    </c:if>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="viewCategory?categoryId=FISH"><img src="static/images/fish_icon.gif" /></a>

            <br/> Saltwater, Freshwater <br/>
            <a href="viewCategory?categoryId=DOGS"><img src="static/images/dogs_icon.gif" /></a>
            <br /> Various Breeds <br />
            <a href="viewCategory?categoryId=CATS"><img src="static/images/cats_icon.gif" /></a>
            <br /> Various Breeds, Exotic Varieties <br />
            <a href="viewCategory?categoryId=REPTILES"><img src="static/images/reptiles_icon.gif" /></a>
            <br /> Lizards, Turtles, Snakes <br />
            <a href="viewCategory?categoryId=BIRDS><img src="static/images/birds_icon.gif" /></a>
            <br /> Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250" href="viewCategory?categoryId=BIRDS" shape="rect" />
                <area alt="Fish" coords="2,180,72,250" href="viewCategory?categoryId=FISH" shape="rect" />
                <area alt="Dogs" coords="60,250,130,320" href="viewCategory?categoryId=DOGS" shape="rect" />
                <area alt="Reptiles" coords="140,270,210,340" href="viewCategory?categoryId=REPTILES" shape="rect" />
                <area alt="Cats" coords="225,240,295,310" href="viewCategory?categoryId=CATS" shape="rect" />
                <area alt="Birds" coords="280,180,350,250" href="viewCategory?categoryId=BIRDS" shape="rect" />
            </map>
            <img height="355" src="static/images/splash.gif" align="middle" usemap="#estoremap" width="350" />
        </div>
    </div>
    <div id="Separator">&nbsp;</div>
</div>

<%@include file="../common/IncludeBottom.jsp"%>