package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Account;
import org.csu.petstore.domain.MyList;
import org.csu.petstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editAccount")
public class EditAccountServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/editAccountForm.jsp";
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        Account account = (Account) session.getAttribute("account");
        if (password != null && !password.equals("")) {
            if (!password.equals(repassword)) {
                String msg = "Two input password must be consistent";
                req.setAttribute("msg", msg);
                req.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(req, resp);
            }

            account.setPassword(password);
        }
        account.setNickName(req.getParameter("nickname"));
        account.setRealName(req.getParameter("realname"));
        account.setEmail(req.getParameter("email"));
        account.setPhone(req.getParameter("phone"));

        account.setLanguagePrefer(req.getParameter("langpref"));
        account.setFavCategory(req.getParameter("favcategory"));

        //还有两个checkbox中的值 但是不知道checkbox传值是如何处理的
        String listOpt = req.getParameter("mylistopt");
        if (listOpt != null) account.setListOption(true);
        else account.setListOption(false);
        String bannerOpt = req.getParameter("banneropt");
        if (bannerOpt != null) account.setBannerOption(true);
        else account.setBannerOption(false);

        accountService = new AccountService();
        accountService.updateInfo(account);

        MyList myList = accountService.getMyList(account.getFavCategory());

        session.setAttribute("account", account);
        session.setAttribute("myList", myList);

        // 修改信息之后是否需要转发来刷新这个页面?
        req.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(req, resp);
    }
}
