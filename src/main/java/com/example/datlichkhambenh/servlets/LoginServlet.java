package com.example.datlichkhambenh.servlets;

import com.example.datlichkhambenh.DAO.account.AccountDAO;
import com.example.datlichkhambenh.DAO.account.IAccountDAO;
import com.example.datlichkhambenh.DAO.khoa.IKhoaDAO;
import com.example.datlichkhambenh.DAO.khoa.KhoaDAO;
import com.example.datlichkhambenh.models.Account;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private IAccountDAO accountDAO;
    private IKhoaDAO khoaDAO;
    private SessionFactory sessionFactory;

    public void init() throws ServletException {
        // Lấy SessionFactory từ ServletContext
        sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        accountDAO = new AccountDAO(sessionFactory);
        khoaDAO = new KhoaDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showFormLogin(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "login":
                loginAccount(req, resp);
                break;
            case "register":
                registerAccount(req, resp);
                break;
            default:
                showFormLogin(req, resp);
                break;
        }

    }


    private void loginAccount(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Account account = accountDAO.login(username, password);
        if (account != null) {
            req.getSession().setAttribute("account", account);
            req.setAttribute("account", account);
            req.setAttribute("khoas", khoaDAO.findAll());
            redirectTo(req, resp, "/WEB-INF/views/khoa/list.jsp");
        } else {
            try {
                resp.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void registerAccount(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRegister = accountDAO.register(username, password);
        if (isRegister) {
            try {
                resp.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showFormLogin(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "login.jsp");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
