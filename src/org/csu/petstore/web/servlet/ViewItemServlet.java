package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Item;
import org.csu.petstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/viewItem")
public class ViewItemServlet extends HttpServlet {
    private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/item.jsp";
    private String itemId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemId = req.getParameter("itemId");
        CategoryService categoryService = new CategoryService();
        Item item = categoryService.getItem(itemId);

        HttpSession session = req.getSession();
        session.setAttribute("item", item);

        req.getRequestDispatcher(VIEW_ITEM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
