package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInit {

    public static void initialize() {
        // 1º Passo: Conecta no servidor (sem banco) e cria o Schema se não existir
        try (Connection conn = ConnectionFactory.getServerConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS salesapp;");
            System.out.println("[DatabaseInit] Banco 'salesapp' verificado/criado com sucesso.");
            
        } catch (SQLException e) {
            System.err.println("[DatabaseInit] Erro ao criar o banco: " + e.getMessage());
            return;
        }

        // 2º Passo: Conecta no banco agora criado e gera as tabelas se não existirem
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            // Tabela de Vendedores
            String sqlSalesman = "CREATE TABLE IF NOT EXISTS salesman ("
                    + "SALESMAN_ID  NUMERIC(5)    PRIMARY KEY,"
                    + "NAME         VARCHAR(30)   NOT NULL,"
                    + "CITY         VARCHAR(15),"
                    + "COMMISSION   DECIMAL(5,2)"
                    + ");";
            stmt.executeUpdate(sqlSalesman);

            // Tabela de Clientes
            String sqlCustomer = "CREATE TABLE IF NOT EXISTS customer ("
                    + "CUSTOMER_ID  NUMERIC(5)    PRIMARY KEY,"
                    + "CUST_NAME    VARCHAR(30)   NOT NULL,"
                    + "CITY         VARCHAR(15),"
                    + "GRADE        NUMERIC(3),"
                    + "SALESMAN_ID  NUMERIC(5),"
                    + "FOREIGN KEY (SALESMAN_ID) REFERENCES salesman(SALESMAN_ID)"
                    + ");";
            stmt.executeUpdate(sqlCustomer);

            // Tabela de Ordens
            String sqlOrders = "CREATE TABLE IF NOT EXISTS orders ("
                    + "ORD_NO       NUMERIC(5)    PRIMARY KEY,"
                    + "PURCH_AMT    DECIMAL(8,2),"
                    + "ORD_DATE     DATE,"
                    + "CUSTOMER_ID  NUMERIC(5),"
                    + "SALESMAN_ID  NUMERIC(5),"
                    + "FOREIGN KEY (CUSTOMER_ID) REFERENCES customer(CUSTOMER_ID),"
                    + "FOREIGN KEY (SALESMAN_ID) REFERENCES salesman(SALESMAN_ID)"
                    + ");";
            stmt.executeUpdate(sqlOrders);

            System.out.println("[DatabaseInit] Estrutura de tabelas verificada/criada com sucesso.");

        } catch (SQLException e) {
            System.err.println("[DatabaseInit] Erro ao criar as tabelas: " + e.getMessage());
        }
    }
}