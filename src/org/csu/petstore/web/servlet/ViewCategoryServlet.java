package org.csu.petstore.web.servlet;

import org.csu.petstore.domain.Category;
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

@WebServlet("/viewCategory")
public class ViewCategoryServlet extends HttpServlet {
    private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/category.jsp";
    private String categoryId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryId = req.getParameter("categoryId");
        CategoryService categoryService = new CategoryService();
        Category category = categoryService.getCategory(categoryId);
        List<Product> productList = categoryService.getProductListByCategory(categoryId);

        HttpSession session = req.getSession();
        session.setAttribute("category", category);
        session.setAttribute("productList", productList);
        req.getRequestDispatcher(VIEW_CATEGORY).forward(req, resp);
    }
}
