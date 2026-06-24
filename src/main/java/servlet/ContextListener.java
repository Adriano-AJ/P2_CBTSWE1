package servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import utils.DbInit;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[ContextListener] Aplicação iniciando. Inicializando banco de dados...");
        // Dispara a criação automática
        DbInit.initialize();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[ContextListener] Aplicação finalizada.");
    }
}