package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Item;
import org.csu.petstore.domain.Product;
import org.csu.petstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewProduct")
public class ViewProductServlet extends HttpServlet {
    private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/product.jsp";
    private String productId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productId = req.getParameter("productId");
        CategoryService categoryService = new CategoryService();
        Product product = categoryService.getProduct(productId);
        List<Item> itemList = categoryService.getItemListByProduct(productId);

        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);

        req.getRequestDispatcher(VIEW_PRODUCT).forward(req, resp);
    }
}

