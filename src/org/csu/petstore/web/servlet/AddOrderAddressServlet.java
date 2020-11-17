package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Cart;
import org.csu.petstore.domain.Order;
import org.csu.petstore.domain.OrderList;
import org.csu.petstore.persistence.UserDAO;
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
import java.util.Date;
import java.util.List;

@WebServlet("/addOrderAddress")
public class AddOrderAddressServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrder.jsp";

    private String addressIdStr;
    private OrderList tempOrderList;
    private List<String> checkedIdList;

    private OrderService orderService;
    private CartService cartService;
    private AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        addressIdStr = req.getParameter("addressId");
        tempOrderList = (OrderList) session.getAttribute("orders");
        checkedIdList = (List<String>) session.getAttribute("checkedIdList");

        // 现在是从 confirmOrder里面传过来的了 现在这个里面确认的就是地址
        if (addressIdStr == null || addressIdStr.equals("")) {
            String msg = "please choose a address";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(CONFIRM_ORDER).forward(req, resp);
        }
        else  {
            int addressId = Integer.parseInt(addressIdStr);

            orderService = new OrderService();
            cartService = new CartService();

            List<Order> orders = tempOrderList.getOrders();
            Date date = new Date();
            for (Order order : orders) {

                order.setOrderDate(date);
                order.setState("unpay");
                order.setAddressId(addressId);

                order.setOrderId(orderService.insertOrder(order));
            }
            tempOrderList.setOrders(orders);
            // 确认订单之后 那么购物车之内的商品就应该取出来了?
            for (String checkedId : checkedIdList) cartService.deleteCartItemById(Integer.parseInt(checkedId));

            //然后就应该 更新在session中的 cart 和 orderList
            Account account = (Account) session.getAttribute("account");
            Cart cart = cartService.getCartByUserId(account.getUserId());
            OrderList orderList = orderService.getOrderListByUserId(account.getUserId());

            session.setAttribute("cart", cart);
            session.setAttribute("orderList", orderList);

            // 然后销毁暂时存在session中的 orders对象和 checkedIdList 对象
//            session.removeAttribute("orders");
            session.removeAttribute("checkedIdList");

            accountService = new AccountService();

            req.setAttribute("address", accountService.getAddressById(addressId));
//            req.setAttribute("orders", tempOrderList);
            req.setAttribute("date", date);
            req.setAttribute("unpay", true);

            session.setAttribute("orders", tempOrderList);
            req.getRequestDispatcher(VIEW_ORDER).forward(req, resp);
        }
    }
}
