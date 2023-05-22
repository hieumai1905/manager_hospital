package com.example.datlichkhambenh.servlets;

import com.example.datlichkhambenh.DAO.BenhNhan.BenhNhanDAO;
import com.example.datlichkhambenh.DAO.bacsi.BacSiDAO;
import com.example.datlichkhambenh.DAO.khoa.IKhoaDAO;
import com.example.datlichkhambenh.DAO.khoa.KhoaDAO;
import com.example.datlichkhambenh.DAO.lichkham.LichKhamDAO;
import com.example.datlichkhambenh.models.Khoa;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/KhoaServlet", urlPatterns = "/khoas")
public class KhoaServlet extends HttpServlet {

    private IKhoaDAO khoaDAO;
    private SessionFactory sessionFactory;

    public void init() throws ServletException {
        // Lấy SessionFactory từ ServletContext
        sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
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
                hienThiDanhSachKhoa(req, resp);
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
            case "create":
                themKhoa(req, resp);
                break;
            case "edit":
                suaKhoa(req, resp);
                break;
            case "delete":
                xoaKhoa(req, resp);
                break;
            default:
                hienThiDanhSachKhoa(req, resp);
                break;
        }
    }

    private void xoaKhoa(HttpServletRequest req, HttpServletResponse resp) {
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            khoaDAO.delete(id);
            List<Khoa> khoas = khoaDAO.findAll();
            req.setAttribute("khoas", khoas);
            req.setAttribute("message", "Xóa thành công");
            redirectTo(req, resp, "/WEB-INF/views/khoa/list.jsp");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void suaKhoa(HttpServletRequest req, HttpServletResponse resp) {
        Khoa khoa = new Khoa();
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            khoa.setTenK(name);
            khoaDAO.update(id, khoa);
            List<Khoa> khoas = khoaDAO.findAll();
            req.setAttribute("khoas", khoas);
            req.setAttribute("message", "Cập nhật thành công");
            redirectTo(req, resp, "/WEB-INF/views/khoa/list.jsp");
        }catch (Exception e) {}
    }

    private void themKhoa(HttpServletRequest req, HttpServletResponse resp) {
        Khoa khoa = new Khoa();
        String name = req.getParameter("name");
        khoa.setTenK(name);
        khoaDAO.save(khoa);
        List<Khoa> khoas = khoaDAO.findAll();
        req.setAttribute("khoas", khoas);
        req.setAttribute("message", "Thêm thành công");
        redirectTo(req, resp, "/WEB-INF/views/khoa/list.jsp");
    }

    private void hienThiDanhSachKhoa(HttpServletRequest req, HttpServletResponse resp) {
        List<Khoa> khoas = khoaDAO.findAll();
        req.setAttribute("khoas", khoas);
        redirectTo(req, resp, "/WEB-INF/views/khoa/list.jsp");
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
