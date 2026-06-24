package servlet;

import dao.SalesManDao;
import model.SalesMan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/salesman")
public class SalesManServlet extends HttpServlet {

    // ─── GET ─────────────────────────────────────────────────────────────────
    //
    //  Ações via parâmetro "action":
    //    (nenhum)  → lista todos os vendedores
    //    "edit"    → carrega um vendedor para edição no formulário
    //    "delete"  → remove o vendedor e redireciona para a lista
    //

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null) {
            list(req, resp);
            return;
        }

        switch (action) {
            case "edit":
                edit(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            default:
                list(req, resp);
        }
    }

    // ─── POST ────────────────────────────────────────────────────────────────
    //
    //  Ações via parâmetro "action":
    //    "save"  → insere ou atualiza (se salesmanId já existir)
    //

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("save".equals(action)) {
            save(req, resp);
        } else {
            list(req, resp);
        }
    }

    // ─── LIST ────────────────────────────────────────────────────────────────

    private void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            SalesManDao dao = new SalesManDao();
            List<SalesMan> salesmen = dao.findAll();
            dao.close();

            req.setAttribute("salesmen", salesmen);
            req.getRequestDispatcher("/views/salesman/list.jsp")
               .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Erro ao listar vendedores: " + e.getMessage(), e);
        }
    }

    // ─── EDIT (carrega dados para o formulário) ───────────────────────────────

    private void edit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            SalesManDao dao = new SalesManDao();
            SalesMan salesMan = dao.findById(id);
            dao.close();

            req.setAttribute("salesMan", salesMan);
            req.getRequestDispatcher("/views/salesman/form.jsp")
               .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar vendedor: " + e.getMessage(), e);
        }
    }

    // ─── SAVE (insert ou update) ──────────────────────────────────────────────

    private void save(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Lê os parâmetros do formulário
            String idParam = req.getParameter("salesmanId");
            String name       = req.getParameter("name");
            String city       = req.getParameter("city");
            double commission = Double.parseDouble(req.getParameter("commission"));

            SalesManDao dao = new SalesManDao();

            if (idParam == null || idParam.isEmpty()) {
                // ─ INSERT: novo vendedor sem ID definido
                //   Gera o próximo ID com base no maior existente
                List<SalesMan> all = dao.findAll();
                int nextId = all.isEmpty() ? 1
                           : all.stream()
                                .mapToInt(SalesMan::getSalesManId)
                                .max()
                                .getAsInt() + 1;

                SalesMan salesMan = new SalesMan(nextId, name, city, commission);
                dao.insert(salesMan);

            } else {
                // ─ UPDATE: vendedor já existente
                int id = Integer.parseInt(idParam);
                SalesMan salesMan = new SalesMan(id, name, city, commission);
                dao.update(salesMan);
            }

            dao.close();

            // Redireciona para a lista após salvar
            resp.sendRedirect(req.getContextPath() + "/salesman");

        } catch (SQLException e) {
            throw new ServletException("Erro ao salvar vendedor: " + e.getMessage(), e);
        }
    }

    // ─── DELETE ──────────────────────────────────────────────────────────────

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            SalesManDao dao = new SalesManDao();
            dao.delete(id);
            dao.close();

            resp.sendRedirect(req.getContextPath() + "/salesman");

        } catch (SQLException e) {
            throw new ServletException("Erro ao deletar vendedor: " + e.getMessage(), e);
        }
    }
}