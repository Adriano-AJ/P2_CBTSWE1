package servlet;

import dao.OrdersDao;
import model.Orders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    // -------------------------------------------------------------------------
    // GET  → lista todas as ordens (ou filtra por customerId / salesmanId)
    // -------------------------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        list(req, res);
    }

    // -------------------------------------------------------------------------
    // POST → roteador de ações (save / delete)
    // -------------------------------------------------------------------------
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) action = "";

        switch (action) {
            case "delete":
                delete(req, res);
                break;
            default:          // "save" (insert ou update)
                save(req, res);
                break;
        }
    }

    // -------------------------------------------------------------------------
    // list – lê todas as ordens e encaminha para a JSP
    // -------------------------------------------------------------------------
    private void list(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        OrdersDao dao = null;
        try {
            dao = new OrdersDao();
            List<Orders> orders = dao.findAll();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/views/orders/list.jsp")
               .forward(req, res);

        } catch (SQLException e) {
            throw new ServletException("Erro ao listar ordens: " + e.getMessage(), e);
        } finally {
            closeDao(dao);
        }
    }

    // -------------------------------------------------------------------------
    // save – insere uma nova ordem ou atualiza uma existente
    // -------------------------------------------------------------------------
    private void save(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        OrdersDao dao = null;
        try {
            Orders order = buildOrderFromRequest(req);
            dao = new OrdersDao();

            // Se ordNo == 0 é insert; caso contrário é update
            if (order.getOrdNo() == 0) {
                dao.insert(order);
            } else {
                dao.update(order);
            }

            res.sendRedirect(req.getContextPath() + "/orders");

        } catch (SQLException e) {
            throw new ServletException("Erro ao salvar ordem: " + e.getMessage(), e);
        } catch (ParseException e) {
            throw new ServletException("Formato de data inválido. Use yyyy-MM-dd.", e);
        } finally {
            closeDao(dao);
        }
    }

    // -------------------------------------------------------------------------
    // delete – remove a ordem pelo ORD_NO
    // -------------------------------------------------------------------------
    private void delete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        OrdersDao dao = null;
        try {
            int ordNo = Integer.parseInt(req.getParameter("ordNo"));
            dao = new OrdersDao();
            dao.delete(ordNo);
            res.sendRedirect(req.getContextPath() + "/orders");

        } catch (SQLException e) {
            throw new ServletException("Erro ao deletar ordem: " + e.getMessage(), e);
        } finally {
            closeDao(dao);
        }
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Constrói um objeto Orders a partir dos parâmetros do request. */
    private Orders buildOrderFromRequest(HttpServletRequest req) throws ParseException {
        Orders order = new Orders();

        String ordNoParam = req.getParameter("ordNo");
        if (ordNoParam != null && !ordNoParam.trim().isEmpty()) {
            order.setOrdNo(Integer.parseInt(ordNoParam.trim()));
        }

        String purchAmtParam = req.getParameter("purchAmt");
        if (purchAmtParam != null && !purchAmtParam.trim().isEmpty()) {
            order.setPurchAmt(Double.parseDouble(purchAmtParam.trim().replace(",", ".")));
        }

        String ordDateParam = req.getParameter("ordDate");
        if (ordDateParam != null && !ordDateParam.trim().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = sdf.parse(ordDateParam.trim());
            order.setOrdDate(parsed);
        }

        String customerIdParam = req.getParameter("customerId");
        if (customerIdParam != null && !customerIdParam.trim().isEmpty()) {
            order.setCustomerId(Integer.parseInt(customerIdParam.trim()));
        }

        String salesmanIdParam = req.getParameter("salesmanId");
        if (salesmanIdParam != null && !salesmanIdParam.trim().isEmpty()) {
            order.setSalesmanId(Integer.parseInt(salesmanIdParam.trim()));
        }

        return order;
    }

    /** Fecha o DAO com segurança para evitar connection leak. */
    private void closeDao(OrdersDao dao) {
        if (dao != null) {
            try { dao.close(); } catch (SQLException ignored) {}
        }
    }
}