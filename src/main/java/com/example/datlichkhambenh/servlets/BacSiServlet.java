package com.example.datlichkhambenh.servlets;

import com.example.datlichkhambenh.DAO.account.AccountDAO;
import com.example.datlichkhambenh.DAO.bacsi.BacSiDAO;
import com.example.datlichkhambenh.DAO.bacsi.IBacSiDAO;
import com.example.datlichkhambenh.DAO.khoa.IKhoaDAO;
import com.example.datlichkhambenh.DAO.khoa.KhoaDAO;
import com.example.datlichkhambenh.models.BacSi;
import com.example.datlichkhambenh.models.Khoa;
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

@WebServlet(name = "/BacSiServlet", urlPatterns = "/bac-sis")
public class BacSiServlet extends HttpServlet {
    private IBacSiDAO bacSiDAO;
    private IKhoaDAO khoaDAO;
    private SessionFactory sessionFactory;

    public void init() throws ServletException {
        // Lấy SessionFactory từ ServletContext
        sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        bacSiDAO = new BacSiDAO(sessionFactory);
        khoaDAO = new KhoaDAO(sessionFactory);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "getByKhoa":
                layBacSiThuocKhoa(req, resp);
                break;
            default:
                hienThiDanhSachBacSi(req, resp);
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
                themBacSi(req, resp);
                break;
            case "edit":
                suaBacSi(req, resp);
                break;
            case "delete":
                xoaBacSi(req, resp);
                break;
            case "layTheoKhoa":
                layBacSiThuocKhoa(req, resp);
                break;
            default:
                hienThiDanhSachBacSi(req, resp);
                break;
        }
    }


    private void themBacSi(HttpServletRequest req, HttpServletResponse resp) {
//        int maBacSi = Integer.parseInt(req.getParameter("maBacSi"));
        String tenBacSi = req.getParameter("tenBacSi");
        Date ngaySinh = Date.valueOf(req.getParameter("ngaySinh"));
        int maKhoa = Integer.parseInt(req.getParameter("maKhoa"));

        BacSi bacSi = new BacSi();
        bacSi.setTenbs(tenBacSi);
        Khoa khoa = new Khoa();
        khoa.setMaK(maKhoa);
        bacSi.setKhoa(khoa);
        bacSi.setNgaysinh(ngaySinh);
        bacSiDAO.save(bacSi);

        req.setAttribute("message", "Thêm mới thành công Bác sĩ!");
        List<BacSi> bacSis = bacSiDAO.findAll();
        List<Khoa> khoas = khoaDAO.findAll();
        req.setAttribute("khoas", khoas);
        req.setAttribute("bacSis", bacSis);
        redirectTo(req, resp, "/WEB-INF/views/bac-si/list.jsp");
    }

    private void suaBacSi(HttpServletRequest req, HttpServletResponse resp) {
        int maBacSi = Integer.parseInt(req.getParameter("maBacSi"));
        String tenBacSi = req.getParameter("tenBacSi");
        Date ngaySinh = Date.valueOf(req.getParameter("ngaySinh"));
        int maKhoa = Integer.parseInt(req.getParameter("maKhoa"));
        Khoa khoa = new Khoa();
        khoa.setMaK(maKhoa);
        BacSi bacSi = new BacSi(maBacSi, tenBacSi, ngaySinh, khoa);
        bacSiDAO.update(maBacSi, bacSi);

        req.setAttribute("message", "Cập nhật thành công Bác sĩ!");
        List<BacSi> bacSis = bacSiDAO.findAll();
        List<Khoa> khoas = khoaDAO.findAll();
        req.setAttribute("khoas", khoas);
        req.setAttribute("bacSis", bacSis);
        redirectTo(req, resp, "/WEB-INF/views/bac-si/list.jsp");
    }

    private void xoaBacSi(HttpServletRequest req, HttpServletResponse resp) {
        int maBacSi = Integer.parseInt(req.getParameter("maBacSi"));
        bacSiDAO.delete(maBacSi);

        req.setAttribute("message", "Xóa thành công Bác sĩ!");
        List<BacSi> bacSis = bacSiDAO.findAll();
        List<Khoa> khoas = khoaDAO.findAll();
        req.setAttribute("khoas", khoas);
        req.setAttribute("bacSis", bacSis);
        redirectTo(req, resp, "/WEB-INF/views/bac-si/list.jsp");
    }

    private void layBacSiThuocKhoa(HttpServletRequest req, HttpServletResponse resp) {
        int maKhoa = Integer.parseInt(req.getParameter("maKhoa"));
        List<BacSi> bacSis = bacSiDAO.layBacSiTheoKhoa(maKhoa);
        List<Khoa> khoas = khoaDAO.findAll();
        req.setAttribute("khoas", khoas);
        req.setAttribute("bacSis", bacSis);
        redirectTo(req, resp, "/WEB-INF/views/bac-si/list.jsp");
    }

    private void hienThiDanhSachBacSi(HttpServletRequest req, HttpServletResponse resp) {
        List<BacSi> bacSis = bacSiDAO.findAll();
        List<Khoa> khoas = khoaDAO.findAll();
        req.setAttribute("khoas", khoas);
        req.setAttribute("bacSis", bacSis);
        redirectTo(req, resp, "/WEB-INF/views/bac-si/list.jsp");
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
