package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Cart;
import org.csu.petstore.domain.MyList;
import org.csu.petstore.domain.OrderList;
import org.csu.petstore.service.AccountService;
import org.csu.petstore.service.CartService;
import org.csu.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private static final String VIEW_SIGN_IN = "/WEB-INF/jsp/account/signIn.jsp";
    private static final String VIEW_MAIN = "/WEB-INF/jsp/catalog/main.jsp";

    private AccountService accountService;
    private CartService cartService;
    private OrderService orderService;
    private boolean isVerify;

    private Account account;
    private Cart cart;
    private OrderList orderList;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        //这个是用户输入的验证码
        String code = req.getParameter("verifyCode");
        //这是存在session的验证码
        String verifyCode=(String) req.getSession().getAttribute("verifyCode");

        System.out.println(code + "  " + verifyCode);

        if (userId == null || userId.equals("")) {
            String msg = "please type in username";
            req.setAttribute("msg",msg);
            req.getRequestDispatcher(VIEW_SIGN_IN).forward(req,resp);
        }
        if (password == null || password.equals("")) {
            String msg = "please type in password";
            req.setAttribute("msg",msg);

            req.setAttribute("username", userId);
            req.getRequestDispatcher(VIEW_SIGN_IN).forward(req,resp);
        }
        if(req.getParameter("isVerify") != null && !code.equals(verifyCode)){
            String msg = "wrong verify code";
            req.setAttribute("msg",msg);

            req.setAttribute("username", userId);
            req.setAttribute("password", password);
            req.setAttribute("isVerify", true);
            req.getRequestDispatcher(VIEW_SIGN_IN).forward(req,resp);
        }

        accountService = new AccountService();
        account  = accountService.getAccountByUserIdAndPassword(userId, password);

        if (account == null) {
            String msg = "username or password is wrong";
            req.setAttribute("msg", msg);

            req.setAttribute("isVerify", true);
            req.getRequestDispatcher(VIEW_SIGN_IN).forward(req, resp);
        }

        HttpSession session = req.getSession();
        cartService = new CartService();
        cart = cartService.getCartByUserId(account.getUserId());

        orderService = new OrderService();
        orderList = orderService.getOrderListByUserId(account.getUserId());

        MyList myList = accountService.getMyList(account.getFavCategory());

        session.setAttribute("account", account);
        session.setAttribute("cart", cart);
        session.setAttribute("orderList", orderList);
        session.setAttribute("myList", myList);

        req.getRequestDispatcher(VIEW_MAIN).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW_SIGN_IN).forward(req, resp);
    }
}
