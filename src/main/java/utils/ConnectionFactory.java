package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionFactory {
 
    // ─── Configurações do banco ───────────────────────────────────────────────
    //
    //  MySQL:       jdbc:mysql://localhost:3306/salesapp
    //
    private static final String URL      = "jdbc:mysql://localhost:3306/salesapp"
                                         + "?useSSL=false"
                                         + "&allowPublicKeyRetrieval=true"
                                         + "&serverTimezone=America/Sao_Paulo";
    private static final String USER     = "root";
    private static final String PASSWORD = "sua_senha";
 
    // ─── Construtor privado ───────────────────────────────────────────────────
    //     Impede instanciação — todos os métodos são estáticos.
 
    private ConnectionFactory() {}
 
    // ─── Registra o driver JDBC uma única vez ao carregar a classe ───────────
 
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                "Driver MySQL não encontrado. Verifique a dependência no pom.xml.", e
            );
        }
    }
 
    // ─── Retorna uma nova conexão com o banco ────────────────────────────────
 
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}