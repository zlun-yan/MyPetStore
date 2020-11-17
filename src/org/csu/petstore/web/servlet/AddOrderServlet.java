package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.*;
import org.csu.petstore.service.CartService;
import org.csu.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/addOrder")
public class AddOrderServlet extends HttpServlet {
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/cart.jsp";

    private String[] checkedId;
    private OrderList orderList;

    private CartService cartService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CONFIRM_ORDER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkedId = req.getParameterValues("checkedId");
        // 现在是从 cart里面传回来的  还没有 addressId
        if (checkedId != null && checkedId.length > 0) {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");
            orderList = new OrderList();
            orderList.setUserId(account.getUserId());

            cartService = new CartService();

            List<Order> orders = new ArrayList<>();
            List<String> checkedIdList = new ArrayList<>();

            BigDecimal subTotal = new BigDecimal(0);
            for (int i = 0; i < checkedId.length; i++) {
                CartItem cartItem = cartService.getCartItemById(Integer.parseInt(checkedId[i]));
                checkedIdList.add(checkedId[i]);

                Order order = new Order();
                order.setUserId(account.getUserId());

                order.setItemId(cartItem.getItemId());
                order.setQuantity(cartItem.getQuantity());
                order.setTotPrice(cartItem.getTotal().multiply(new BigDecimal(order.getQuantity())));

                orders.add(order);
                subTotal = subTotal.add(order.getTotPrice());
            }

            orderList = new OrderList();
            orderList.setOrders(orders);
            orderList.setSubTotal(subTotal);

            Date date = new Date();

            req.setAttribute("date", date);

            session.setAttribute("orders", orderList);
            session.setAttribute("checkedIdList", checkedIdList);
            req.getRequestDispatcher(CONFIRM_ORDER).forward(req, resp);
        }
        else {
            String msg = "please select the items you like";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_CART).forward(req, resp);
        }
    }
}
