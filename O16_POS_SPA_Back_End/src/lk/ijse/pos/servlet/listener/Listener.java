package lk.ijse.pos.servlet.listener;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    private static ServletContext servletContext;

    public static ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicDataSource pool=new BasicDataSource();
        pool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        pool.setUrl("jdbc:mysql://localhost:3306/company");
        pool.setUsername("root");
        pool.setPassword("1234");
        pool.setInitialSize(10);
        pool.setMaxTotal(10);

        servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("dbcp",pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
