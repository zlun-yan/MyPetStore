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

@WebServlet("/editAddress")
public class EditAddressServlet extends HttpServlet {
    private static final String EDIT_ADDRESS_FORM = "/WEB-INF/jsp/account/editAddressForm.jsp";

    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        if (itemId != null) {
            // delete
            if (accountService == null) accountService = new AccountService();
            Address address = new Address();
            address.setId(Integer.parseInt(itemId));
            accountService.deleteAddress(address);

            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");
            account = accountService.updateAddressInSession(account);
            session.setAttribute("account", account);
        }
        req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //update
        if (accountService == null) accountService = new AccountService();
        Address address = new Address();
        address.setId(Integer.parseInt(req.getParameter("itemId")));
        address.setReceiver(req.getParameter("receiver"));
        address.setPhone(req.getParameter("phone"));
        address.setProvince(req.getParameter("province"));
        address.setCity(req.getParameter("city"));
        address.setDistrict(req.getParameter("district"));
        address.setDetails(req.getParameter("details"));

        accountService.updateAddress(address);

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        account = accountService.updateAddressInSession(account);
        session.setAttribute("account", account);
        req.getRequestDispatcher(EDIT_ADDRESS_FORM).forward(req, resp);
    }
}
