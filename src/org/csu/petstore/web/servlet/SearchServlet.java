package org.csu.petstore.web.servlet;

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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final String SEARCH_PRODUCT = "/WEB-INF/jsp/catalog/searchProduct.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        CategoryService categoryService = new CategoryService();
        List<Product> productList = categoryService.searchProductList(keyword);

        HttpSession session = req.getSession();
        session.setAttribute("productList", productList);
        req.getRequestDispatcher(SEARCH_PRODUCT).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
