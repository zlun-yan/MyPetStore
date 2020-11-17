package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Order;
import org.csu.petstore.domain.OrderList;
import org.csu.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/viewOrder")
public class ViewOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderIdStr = req.getParameter("orderid");

        // 一般不会有空的情况吧
//        if (orderIdStr == null || orderIdStr.equals("")) {
//
//        }
        orderService = new OrderService();

        int orderId = Integer.parseInt(orderIdStr);

        OrderList tempOrderList = new OrderList();
        List<Order> orders = new ArrayList<>();
        Order order = orderService.getOrderByOrderId(orderId);
        orders.add(order);
        tempOrderList.setOrders(orders);
        tempOrderList.setSubTotal(order.getTotPrice());

        Date date = new Date();

        HttpSession session = req.getSession();
        session.setAttribute("orders", tempOrderList);

        req.setAttribute("address", order.getAddress());
        req.setAttribute("date", date);

        if (order.getState().equals("unpay")) {
            req.setAttribute("unpay", true);
        }

        req.getRequestDispatcher(VIEW_ORDER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
