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


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private static final String VIEW_SIGN_UP = "/WEB-INF/jsp/account/signUp.jsp";
    private static final String VIEW_MAIN = "/WEB-INF/jsp/catalog/main.jsp";

    private AccountService accountService;
    private CartService cartService;
    private OrderService orderService;

    private Account account;
    private Cart cart;
    private OrderList orderList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if (userId == null || userId.equals("")) {
            String msg = "Please type in your username";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
        }

        accountService = new AccountService();
        if (accountService.getAccountById(userId) != null) {
            String msg = "username already exists";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
        }

        if (password == null || password.equals("")) {
            String msg = "Please type in your password";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
        }
        if (repassword == null || repassword.equals("")) {
            String msg = "Please repeat your username";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
        }
        if (email == null || email.equals("")) {
            String msg = "Please type in your email";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
        }
        if (phone == null || phone.equals("")) {
            String msg = "Please type in your phone";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
        }
        if (!password.equals(repassword)) {
            String msg = "Two input password must be consistent";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_SIGN_UP).forward(req, resp);
        }

        account = new Account();
        account.setUserId(userId);
        account.setPassword(password);
        account.setEmail(email);
        account.setPhone(phone);
        account.setLanguagePrefer("english");
        account.setListOption(true);
        account.setBannerOption(true);

        accountService.SignUp(account);

        cartService = new CartService();
        cart = cartService.getCartByUserId(account.getUserId());

        orderService = new OrderService();
        orderList = orderService.getOrderListByUserId(account.getUserId());

        MyList myList = accountService.getMyList(account.getFavCategory());

        HttpSession session = req.getSession();
        session.setAttribute("account", account);
        session.setAttribute("cart", cart);
        session.setAttribute("orderList", orderList);
        session.setAttribute("myList", myList);

        req.getRequestDispatcher(VIEW_MAIN).forward(req, resp);
    }
}
