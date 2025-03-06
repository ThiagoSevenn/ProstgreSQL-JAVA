package quest2.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection conected;

    public Conexao() {
        try {
            conected = DriverManager.getConnection("jdbc:postgresql://localhost:5432/segundodb","postgres","12345");
            if (conected != null) {
                System.out.println("Conectado ao database");
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível realizar a conexão, verifique os dados na linha 13 - Classe: Conexao.java");
        }
    }  
}
