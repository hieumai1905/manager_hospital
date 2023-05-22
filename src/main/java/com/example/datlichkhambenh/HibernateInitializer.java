package com.example.datlichkhambenh;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateInitializer implements ServletContextListener {

    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Đường dẫn đến tệp cấu hình Hibernate
        String configPath = "hibernate.cfg.xml";

        // Tạo đối tượng Configuration và cấu hình từ tệp cấu hình
        Configuration configuration = new Configuration();
        configuration.configure(configPath);

        // Tạo SessionFactory từ Configuration
        sessionFactory = configuration.buildSessionFactory();

        // Lưu SessionFactory trong ServletContext để sử dụng trong các Servlet khác
        sce.getServletContext().setAttribute("sessionFactory", sessionFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Đóng SessionFactory khi ứng dụng web bị hủy
        sessionFactory.close();
    }
}
