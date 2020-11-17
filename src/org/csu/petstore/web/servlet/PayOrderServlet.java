package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Account;
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
import java.util.List;

@WebServlet("/pay")
public class PayOrderServlet extends HttpServlet {
    private static final String VIEW_PAY = "/WEB-INF/jsp/order/payOrder.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/main.jsp";
    private static final String VIEW_ORDER_LIST = "/WEB-INF/jsp/order/orderList.jsp";


    private OrderList tempOrderList;

    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        tempOrderList = (OrderList) session.getAttribute("orders");
        List<Order> orders = tempOrderList.getOrders();

        orderService = new OrderService();
        for (Order order : orders) {
            order.setState("paid");

            orderService.updateOrder(order);
        }

        tempOrderList.setOrders(orders);

        Account account = (Account) session.getAttribute("account");

        OrderList orderList = orderService.getOrderListByUserId(account.getUserId());
        session.setAttribute("orderList", orderList);
        session.setAttribute("orders", tempOrderList);

//        req.getRequestDispatcher(MAIN).forward(req, resp);
        req.getRequestDispatcher(VIEW_ORDER_LIST).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW_PAY).forward(req, resp);
    }
}
