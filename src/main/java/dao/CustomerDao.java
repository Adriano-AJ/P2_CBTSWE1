package dao;

import model.Customer;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private Connection conn;

    public CustomerDao() throws SQLException {
        this.conn = ConnectionFactory.getConnection();
    }

    public void insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (CUSTOMER_ID, CUST_NAME, CITY, GRADE, SALESMAN_ID) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt   (1, customer.getCustomerId());
            stmt.setString(2, customer.getCustName());
            stmt.setString(3, customer.getCity());
            stmt.setInt   (4, customer.getGrade());
            stmt.setInt   (5, customer.getSalesmanId());
            stmt.executeUpdate();
        }
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE customer "
                   + "SET CUST_NAME = ?, CITY = ?, GRADE = ?, SALESMAN_ID = ? "
                   + "WHERE CUSTOMER_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getCustName());
            stmt.setString(2, customer.getCity());
            stmt.setInt   (3, customer.getGrade());
            stmt.setInt   (4, customer.getSalesmanId());
            stmt.setInt   (5, customer.getCustomerId());
            stmt.executeUpdate();
        }
    }

    public void delete(int customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE CUSTOMER_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
    }

    public Customer findById(int customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE CUSTOMER_ID = ?";
        Customer customer = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = mapRow(rs);
                }
            }
        }

        return customer;
    }

    // ─── FIND ALL ────────────────────────────────────────────────────────────

    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM customer ORDER BY CUSTOMER_ID";
        List<Customer> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }

        return list;
    }

 //     Retorna todos os clientes vinculados a um vendedor específico.

    public List<Customer> findBySalesmanId(int salesmanId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE SALESMAN_ID = ? ORDER BY CUSTOMER_ID";
        List<Customer> list = new ArrayList<>();

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

    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId (rs.getInt   ("CUSTOMER_ID"));
        customer.setCustName   (rs.getString("CUST_NAME"));
        customer.setCity       (rs.getString("CITY"));
        customer.setGrade      (rs.getInt   ("GRADE"));
        customer.setSalesmanId (rs.getInt   ("SALESMAN_ID"));
        return customer;
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
