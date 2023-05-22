package com.example.datlichkhambenh.servlets;

import com.example.datlichkhambenh.DAO.BenhNhan.BenhNhanDAO;
import com.example.datlichkhambenh.DAO.BenhNhan.IBenhNhanDAO;
import com.example.datlichkhambenh.DAO.khoa.KhoaDAO;
import com.example.datlichkhambenh.models.BenhNhan;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/BenhNhanServlet", urlPatterns = "/benh-nhans")
public class BenhNhanServlet extends HttpServlet {
    private IBenhNhanDAO benhNhanDAO;
    private SessionFactory sessionFactory;

    public void init() throws ServletException {
        // Lấy SessionFactory từ ServletContext
        sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        benhNhanDAO = new BenhNhanDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "laybenhnhantheoma":
                layBenhNhanTheoMa(req, resp);
                break;
            default:
                hienThiDanhSachBenhNhan(req, resp);
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
                themBenhNhan(req, resp);
                break;
            case "edit":
                suaBenhNhan(req, resp);
                break;
            case "delete":
                xoaBenhNhan(req, resp);
                break;
            default:
                hienThiDanhSachBenhNhan(req, resp);
                break;
        }
    }

    private void themBenhNhan(HttpServletRequest req, HttpServletResponse resp) {
        String tenBenhNhan = req.getParameter("tenBenhNhan");
        Date ngaySinh = Date.valueOf(req.getParameter("ngaySinh"));
        int gioiTinh = Integer.parseInt(req.getParameter("gioiTinh"));

        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setTenbn(tenBenhNhan);
        benhNhan.setNgaysinh(ngaySinh);
        benhNhan.setGioitinh(gioiTinh);
        benhNhanDAO.save(benhNhan);

        req.setAttribute("message", "Thêm mới thành công Bệnh nhân!");
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        redirectTo(req, resp, "/WEB-INF/views/benh-nhan/list.jsp");
    }

    private void suaBenhNhan(HttpServletRequest req, HttpServletResponse resp) {
        int maBenhNhan = Integer.parseInt(req.getParameter("maBenhNhan"));
        String tenBenhNhan = req.getParameter("tenBenhNhan");
        Date ngaySinh = Date.valueOf(req.getParameter("ngaySinh"));
        int gioiTinh = Integer.parseInt(req.getParameter("gioiTinh"));

        BenhNhan benhNhan = new BenhNhan(maBenhNhan, tenBenhNhan, ngaySinh, gioiTinh);
        benhNhanDAO.update(maBenhNhan, benhNhan);

        req.setAttribute("message", "Cập nhật thành công Bệnh nhân!");
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        redirectTo(req, resp, "/WEB-INF/views/benh-nhan/list.jsp");

    }

    private void layBenhNhanTheoMa(HttpServletRequest req, HttpServletResponse resp) {
        int maBenhNhan = Integer.parseInt(req.getParameter("maBenhNhan"));
        BenhNhan benhNhan = benhNhanDAO.findById(maBenhNhan);
        List<BenhNhan> benhNhans = new ArrayList<>();
        benhNhans.add(benhNhan);
        req.setAttribute("benhNhans", benhNhans);
        redirectTo(req, resp, "/WEB-INF/views/benh-nhan/list.jsp");
    }

    private void xoaBenhNhan(HttpServletRequest req, HttpServletResponse resp) {
        int maBenhNhan = Integer.parseInt(req.getParameter("maBenhNhan"));
        benhNhanDAO.delete(maBenhNhan);

        req.setAttribute("message", "Xóa thành công Bệnh nhân!");
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        redirectTo(req, resp, "/WEB-INF/views/benh-nhan/list.jsp");
    }

    private void hienThiDanhSachBenhNhan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        redirectTo(req, resp, "/WEB-INF/views/benh-nhan/list.jsp");
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
