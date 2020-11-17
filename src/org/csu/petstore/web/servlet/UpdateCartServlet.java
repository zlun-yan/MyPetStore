package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Cart;
import org.csu.petstore.domain.CartItem;
import org.csu.petstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/cart.jsp";

    private Cart cart;
    private Account account;

    private CartService cartService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        account = (Account) session.getAttribute("account");
        cart = (Cart) session.getAttribute("cart");
        cartService = new CartService();

        for (CartItem cartItem : cart.getCartItems()) {
            int quantity = Integer.parseInt(req.getParameter(cartItem.getItemId()));
            if (quantity != cartItem.getQuantity()) {
                cartService.updateCartItem(cartItem.getUserId(), cartItem.getItemId(), quantity);
            }
        }

        cart = cartService.getCartByUserId(account.getUserId());
        session.setAttribute("cart", cart);
        req.getRequestDispatcher(VIEW_CART).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
