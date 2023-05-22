package com.example.datlichkhambenh.servlets;

import com.example.datlichkhambenh.DAO.BenhNhan.BenhNhanDAO;
import com.example.datlichkhambenh.DAO.BenhNhan.IBenhNhanDAO;
import com.example.datlichkhambenh.DAO.account.AccountDAO;
import com.example.datlichkhambenh.DAO.bacsi.BacSiDAO;
import com.example.datlichkhambenh.DAO.bacsi.IBacSiDAO;
import com.example.datlichkhambenh.DAO.khoa.KhoaDAO;
import com.example.datlichkhambenh.DAO.lichkham.ILichKhamDAO;
import com.example.datlichkhambenh.DAO.lichkham.LichKhamDAO;
import com.example.datlichkhambenh.models.BacSi;
import com.example.datlichkhambenh.models.BenhNhan;
import com.example.datlichkhambenh.models.LichKham;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "/LichKhamServlet", urlPatterns = "/lich-khams")
public class LichKhamServlet extends HttpServlet {
    private ILichKhamDAO lichKhamDAO;
    private IBacSiDAO bacSiDAO;
    private IBenhNhanDAO benhNhanDAO;
    private SessionFactory sessionFactory;

    public void init() throws ServletException {
        // Lấy SessionFactory từ ServletContext
        sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        lichKhamDAO = new LichKhamDAO(sessionFactory);
        bacSiDAO = new BacSiDAO(sessionFactory);
        benhNhanDAO = new BenhNhanDAO(sessionFactory);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "getByBacSi":
                layLichKhamBenhCuaBacSi(req, resp);
                break;
            case "getLichKhamInTime":
                layLichKhamTrongThoiGian(req, resp);
                break;
            case "layLichKhamCuaBenhNhan":
                layLichKhamCuaBenhNhan(req, resp);
                break;
            default:
                hienThiDanhSachLichKham(req, resp);
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
                themLichKham(req, resp);
                break;
            case "edit":
                suaLichKham(req, resp);
                break;
            case "delete":
                xoaLichKham(req, resp);
                break;
            default:
                hienThiDanhSachLichKham(req, resp);
                break;
        }
    }


    private void themLichKham(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Date ngayKham = Date.valueOf(req.getParameter("ngayKham"));
        int maBn = Integer.parseInt(req.getParameter("maBn"));
        String noiDung = req.getParameter("noiDung");
        int maBacSi = Integer.parseInt(req.getParameter("maBacSi"));
        int maBenhNhan = Integer.parseInt(req.getParameter("maBenhNhan"));
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setMabn(maBenhNhan);
        BacSi bacSi = new BacSi();
        bacSi.setMabs(maBacSi);
        LichKham lichKham = new LichKham(ngayKham, noiDung, bacSi, benhNhan);
        lichKhamDAO.save(lichKham);

        req.setAttribute("message", "Đăng ký lịch khám thàn công!");
//        resp.sendRedirect("/lich-khams");
        List<BacSi> bacSis = bacSiDAO.findAll();
        req.setAttribute("bacSis", bacSis);
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        List<LichKham> lichKhams = lichKhamDAO.findAll();
        req.setAttribute("lichKhams", lichKhams);
        redirectTo(req, resp, "/WEB-INF/views/lich-kham/list.jsp");
    }

    private void suaLichKham(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int maLichKham = Integer.parseInt(req.getParameter("maLichKham"));
        Date ngayKham = Date.valueOf(req.getParameter("ngayKham"));
        String noiDung = req.getParameter("noiDung");
        int maBacSi = Integer.parseInt(req.getParameter("maBacSi"));
        int maBenhNhan = Integer.parseInt(req.getParameter("maBenhNhan"));
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setMabn(maBenhNhan);
        BacSi bacSi = new BacSi();
        bacSi.setMabs(maBacSi);
        LichKham lichKham = new LichKham(maLichKham, ngayKham, noiDung, bacSi, benhNhan);
        lichKhamDAO.update(maLichKham, lichKham);

        req.setAttribute("message", "Cập nhật thành công lịch khám!");
        resp.sendRedirect("/lich-khams");
    }

    private void xoaLichKham(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int maLichKham = Integer.parseInt(req.getParameter("maLichKham"));
        lichKhamDAO.delete(maLichKham);
        req.setAttribute("message", "Xóa thành công lịch khám!");
        resp.sendRedirect("/lich-khams");
    }

    private void layLichKhamBenhCuaBacSi(HttpServletRequest req, HttpServletResponse resp) {
        List<BacSi> bacSis = bacSiDAO.findAll();
        req.setAttribute("bacSis", bacSis);
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        int maBacSi = Integer.parseInt(req.getParameter("maBacSi"));
        List<LichKham> lichKhams = lichKhamDAO.layDanhSachLichKhamCuaBacSi(maBacSi);
        req.setAttribute("lichKhams", lichKhams);
        redirectTo(req, resp, "/WEB-INF/views/lich-kham/list.jsp");
    }

    private void layLichKhamCuaBenhNhan(HttpServletRequest req, HttpServletResponse resp) {
        List<BacSi> bacSis = bacSiDAO.findAll();
        req.setAttribute("bacSis", bacSis);
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        int maBenhNhan = Integer.parseInt(req.getParameter("maBenhNhan"));
        List<LichKham> lichKhams = lichKhamDAO.layDanhSachLichKhamCuaBenhNhan(maBenhNhan);
        req.setAttribute("lichKhams", lichKhams);
        redirectTo(req, resp, "/WEB-INF/views/lich-kham/list.jsp");
    }

    private void layLichKhamTrongThoiGian(HttpServletRequest req, HttpServletResponse resp) {
        String ngayBatDau = req.getParameter("ngayBatDau");
        String ngayKetThuc = req.getParameter("ngayKetThuc");
        List<LichKham> lichKhams = lichKhamDAO.layDanhSachLichKhamTrongKhoang(ngayBatDau, ngayKetThuc);
        List<BacSi> bacSis = bacSiDAO.findAll();
        req.setAttribute("bacSis", bacSis);
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        req.setAttribute("lichKhams", lichKhams);
        redirectTo(req, resp, "/WEB-INF/views/lich-kham/list.jsp");
    }

    private void hienThiDanhSachLichKham(HttpServletRequest req, HttpServletResponse resp) {
        List<BacSi> bacSis = bacSiDAO.findAll();
        req.setAttribute("bacSis", bacSis);
        List<BenhNhan> benhNhans = benhNhanDAO.findAll();
        req.setAttribute("benhNhans", benhNhans);
        List<LichKham> lichKhams = lichKhamDAO.findAll();
        req.setAttribute("lichKhams", lichKhams);
        redirectTo(req, resp, "/WEB-INF/views/lich-kham/list.jsp");
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
