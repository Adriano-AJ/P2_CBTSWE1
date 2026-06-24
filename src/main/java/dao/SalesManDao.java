package dao;

import model.SalesMan;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesManDao {

    private Connection conn;

    public SalesManDao() throws SQLException {
        this.conn = ConnectionFactory.getConnection();
    }

    public void insert(SalesMan salesMan) throws SQLException {
        String sql = "INSERT INTO salesman (SALESMAN_ID, NAME, CITY, COMMISSION) "
                   + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt   (1, salesMan.getSalesManId());
            stmt.setString(2, salesMan.getName());
            stmt.setString(3, salesMan.getCity());
            stmt.setDouble(4, salesMan.getCommission());
            stmt.executeUpdate();
        }
    }

    public void update(SalesMan salesMan) throws SQLException {
        String sql = "UPDATE salesman "
                   + "SET NAME = ?, CITY = ?, COMMISSION = ? "
                   + "WHERE SALESMAN_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salesMan.getName());
            stmt.setString(2, salesMan.getCity());
            stmt.setDouble(3, salesMan.getCommission());
            stmt.setInt   (4, salesMan.getSalesManId());
            stmt.executeUpdate();
        }
    }

    public void delete(int salesmanId) throws SQLException {
        String sql = "DELETE FROM salesman WHERE SALESMAN_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salesmanId);
            stmt.executeUpdate();
        }
    }

     public SalesMan findById(int salesmanId) throws SQLException {
        String sql = "SELECT * FROM salesman WHERE SALESMAN_ID = ?";
        SalesMan salesMan = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salesmanId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    salesMan = mapRow(rs);
                }
            }
        }

        return salesMan;
    }

      public List<SalesMan> findAll() throws SQLException {
        String sql = "SELECT * FROM salesman ORDER BY SALESMAN_ID";
        List<SalesMan> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }

        return list;
    }

    private SalesMan mapRow(ResultSet rs) throws SQLException {
        SalesMan salesMan = new SalesMan();
        salesMan.setSalesManId(rs.getInt("SALESMAN_ID"));
        salesMan.setName       (rs.getString("NAME"));
        salesMan.setCity       (rs.getString("CITY"));
        salesMan.setCommission (rs.getDouble("COMMISSION"));
        return salesMan;
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}