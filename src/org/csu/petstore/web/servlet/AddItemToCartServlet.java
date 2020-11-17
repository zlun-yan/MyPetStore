package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Cart;
import org.csu.petstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addItemToCart")
public class AddItemToCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/cart.jsp";

    private String workingItemId;
    private Cart cart;

    private CartService cartService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cartService = new CartService();
        workingItemId = req.getParameter("workingItemId");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (cartService.buyItemByUserIdAndItemIdAndQuantity(account.getUserId(), workingItemId, 1)) {
            cart = cartService.getCartByUserId(account.getUserId());
            session.setAttribute("cart", cart);
            req.getRequestDispatcher(VIEW_CART).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
