package servlet;

import dao.CustomerDao;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    // -------------------------------------------------------------------------
    // GET → lista todos os clientes (agora também atende edição)
    // -------------------------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "edit":
                edit(req, res);
                break;
            default:
                list(req, res);
                break;
        }
    }

    // -------------------------------------------------------------------------
    // POST → roteador de ações (save / delete)
    // -------------------------------------------------------------------------
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "delete":
                delete(req, res);
                break;
            case "save":
                save(req, res);
                break;
            default:
                list(req, res);
                break;
        }
    }

    // -------------------------------------------------------------------------
    // list – lê todos os clientes e encaminha para a JSP
    // -------------------------------------------------------------------------
    private void list(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        CustomerDao dao = null;
        try {
            dao = new CustomerDao();
            List<Customer> customers = dao.findAll();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/views/customer/list.jsp")
               .forward(req, res);

        } catch (SQLException e) {
            throw new ServletException("Erro ao listar clientes: " + e.getMessage(), e);
        } finally {
            closeDao(dao);
        }
    }

    // -------------------------------------------------------------------------
    // edit – carrega um cliente específico para o formulário
    // -------------------------------------------------------------------------
    private void edit(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        CustomerDao dao = null;
        try {
            int customerId = Integer.parseInt(req.getParameter("customerId"));
            dao = new CustomerDao();
            Customer customer = dao.findById(customerId);
            
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/views/customer/form.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar cliente: " + e.getMessage(), e);
        } finally {
            closeDao(dao);
        }
    }

    // -------------------------------------------------------------------------
    // save – insere novo (auto-gerado ou manual) ou atualiza existente
    // -------------------------------------------------------------------------
    private void save(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        CustomerDao dao = null;
        try {
            Customer customer = buildCustomerFromRequest(req);
            dao = new CustomerDao();

            if (customer.getCustomerId() == 0) {
                // Auto-gera o ID se foi submetido em branco
                List<Customer> all = dao.findAll();
                int nextId = all.isEmpty() ? 1 
                           : all.stream().mapToInt(Customer::getCustomerId).max().getAsInt() + 1;
                customer.setCustomerId(nextId);
                dao.insert(customer);
            } else {
                // Se já existe, atualiza. Se não existe, cria com o ID forçado.
                Customer existing = dao.findById(customer.getCustomerId());
                if (existing == null) {
                    dao.insert(customer);
                } else {
                    dao.update(customer);
                }
            }

            res.sendRedirect(req.getContextPath() + "/customers");

        } catch (SQLException e) {
            throw new ServletException("Erro ao salvar cliente: " + e.getMessage(), e);
        } finally {
            closeDao(dao);
        }
    }

    // -------------------------------------------------------------------------
    // delete – remove o cliente pelo CUSTOMER_ID
    // -------------------------------------------------------------------------
    private void delete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        CustomerDao dao = null;
        try {
            int customerId = Integer.parseInt(req.getParameter("customerId"));
            dao = new CustomerDao();
            dao.delete(customerId);
            res.sendRedirect(req.getContextPath() + "/customers");

        } catch (SQLException e) {
            throw new ServletException("Erro ao deletar cliente. Verifique se ele não possui ordens de venda atreladas: " + e.getMessage(), e);
        } finally {
            closeDao(dao);
        }
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------
    private Customer buildCustomerFromRequest(HttpServletRequest req) {
        Customer customer = new Customer();

        String customerIdParam = req.getParameter("customerId");
        if (customerIdParam != null && !customerIdParam.trim().isEmpty()) {
            customer.setCustomerId(Integer.parseInt(customerIdParam.trim()));
        }

        String custName = req.getParameter("custName");
        if (custName != null) {
            customer.setCustName(custName.trim());
        }

        String city = req.getParameter("city");
        if (city != null) {
            customer.setCity(city.trim());
        }

        String gradeParam = req.getParameter("grade");
        if (gradeParam != null && !gradeParam.trim().isEmpty()) {
            customer.setGrade(Integer.parseInt(gradeParam.trim()));
        }

        String salesmanIdParam = req.getParameter("salesmanId");
        if (salesmanIdParam != null && !salesmanIdParam.trim().isEmpty()) {
            customer.setSalesmanId(Integer.parseInt(salesmanIdParam.trim()));
        }

        return customer;
    }

    private void closeDao(CustomerDao dao) {
        if (dao != null) {
            try { dao.close(); } catch (SQLException ignored) {}
        }
    }
}