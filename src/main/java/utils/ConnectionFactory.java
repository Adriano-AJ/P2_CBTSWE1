package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionFactory {
 
    // URL normal apontando para o banco
    private static final String URL      = "jdbc:mysql://localhost:3306/salesapp"
                                         + "?useSSL=false"
                                         + "&allowPublicKeyRetrieval=true"
                                         + "&serverTimezone=America/Sao_Paulo";

    // URL alternativa para quando o banco ainda NÃO EXISTE (conecta apenas no servidor)
    private static final String URL_SERVER = "jdbc:mysql://localhost:3306/"
                                         + "?useSSL=false"
                                         + "&allowPublicKeyRetrieval=true"
                                         + "&serverTimezone=America/Sao_Paulo";

    private static final String USER     = "root";
    private static final String PASSWORD = ""; // Altere para a sua senha local
 
    private ConnectionFactory() {}
 
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado.", e);
        }
    }
 
    // Conexão padrão com o banco salesapp
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Conexão temporária apenas com o servidor para criar o banco
    public static Connection getServerConnection() throws SQLException {
        return DriverManager.getConnection(URL_SERVER, USER, PASSWORD);
    }
}