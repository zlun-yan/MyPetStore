package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.Address;
import org.csu.petstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {
    private static final String EDIT_ADDRESS_FORM = "/WEB-INF/jsp/account/editAddressForm.jsp";

    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String province = req.getParameter("province");
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String details = req.getParameter("details");
        String receiver = req.getParameter("receiver");
        String phone = req.getParameter("phone");

        if (province == null || province.equals("")) {
            String msg = "Please type in province";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
        }
        if (city == null || city.equals("")) {
            String msg = "Please type in city";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
        }
        if (district == null || district.equals("")) {
            String msg = "Please type in district";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
        }
        if (details == null || details.equals("")) {
            String msg = "Please type in details";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
        }
        if (receiver == null || receiver.equals("")) {
            String msg = "Please type in receiver";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
        }
        if (phone == null || phone.equals("")) {
            String msg = "Please type in phone";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
        }

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        Address address = new Address();
        address.setUserId(account.getUserId());
        address.setProvince(province);
        address.setCity(city);
        address.setDistrict(district);
        address.setDetails(details);
        address.setReceiver(receiver);
        address.setPhone(phone);

        accountService = new AccountService();
        accountService.insertAddress(address);

        account = accountService.updateAddressInSession(account);
        session.setAttribute("account", account);

        req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
    }
}
