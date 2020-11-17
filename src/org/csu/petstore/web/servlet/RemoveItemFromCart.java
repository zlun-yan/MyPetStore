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

@WebServlet("/removeItemFromCart")
public class RemoveItemFromCart extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/cart.jsp";

    private String itemId;
    private Cart cart;

    private CartService cartService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemId = req.getParameter("itemId");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        cartService = new CartService();
        if (cartService.deleteCartItemByUserIdAndItemId(account.getUserId(), itemId)) {
            cart = cartService.getCartByUserId(account.getUserId());
            session.setAttribute("cart", cart);
        }

        req.getRequestDispatcher(VIEW_CART).forward(req, resp);
    }
}
