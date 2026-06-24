package dao;

import model.Orders;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {

    private Connection conn;

    public OrdersDao() throws SQLException {
        this.conn = ConnectionFactory.getConnection();
    }

    public void insert(Orders order) throws SQLException {
        String sql = "INSERT INTO orders (ORD_NO, PURCH_AMT, ORD_DATE, CUSTOMER_ID, SALESMAN_ID) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt   (1, order.getOrdNo());
            stmt.setDouble(2, order.getPurchAmt());
            stmt.setDate  (3, new java.sql.Date(order.getOrdDate().getTime()));
            stmt.setInt   (4, order.getCustomerId());
            stmt.setInt   (5, order.getSalesmanId());
            stmt.executeUpdate();
        }
    }

    public void update(Orders order) throws SQLException {
        String sql = "UPDATE orders "
                   + "SET PURCH_AMT = ?, ORD_DATE = ?, CUSTOMER_ID = ?, SALESMAN_ID = ? "
                   + "WHERE ORD_NO = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, order.getPurchAmt());
            stmt.setDate  (2, new java.sql.Date(order.getOrdDate().getTime()));
            stmt.setInt   (3, order.getCustomerId());
            stmt.setInt   (4, order.getSalesmanId());
            stmt.setInt   (5, order.getOrdNo());
            stmt.executeUpdate();
        }
    }

    public void delete(int ordNo) throws SQLException {
        String sql = "DELETE FROM orders WHERE ORD_NO = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordNo);
            stmt.executeUpdate();
        }
    }

    public Orders findById(int ordNo) throws SQLException {
        String sql = "SELECT * FROM orders WHERE ORD_NO = ?";
        Orders order = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordNo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = mapRow(rs);
                }
            }
        }

        return order;
    }

    public List<Orders> findAll() throws SQLException {
        String sql = "SELECT * FROM orders ORDER BY ORD_NO";
        List<Orders> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }

        return list;
    }

    //     Retorna todas as ordens de um cliente específico.

    public List<Orders> findByCustomerId(int customerId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE CUSTOMER_ID = ? ORDER BY ORD_DATE DESC";
        List<Orders> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }

        return list;
    }

    //     Retorna todas as ordens de um vendedor específico.

    public List<Orders> findBySalesmanId(int salesmanId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE SALESMAN_ID = ? ORDER BY ORD_DATE DESC";
        List<Orders> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salesmanId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }

        return list;
    }

    //     Retorna o valor total de vendas de um vendedor (útil para comissão).

    public double getTotalBySalesmanId(int salesmanId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(PURCH_AMT), 0) AS total "
                   + "FROM orders WHERE SALESMAN_ID = ?";
        double total = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salesmanId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble("total");
                }
            }
        }

        return total;
    }

    private Orders mapRow(ResultSet rs) throws SQLException {
        Orders order = new Orders();
        order.setOrdNo     (rs.getInt   ("ORD_NO"));
        order.setPurchAmt  (rs.getDouble("PURCH_AMT"));
        order.setOrdDate   (rs.getDate  ("ORD_DATE"));
        order.setCustomerId(rs.getInt   ("CUSTOMER_ID"));
        order.setSalesmanId(rs.getInt   ("SALESMAN_ID"));
        return order;
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
