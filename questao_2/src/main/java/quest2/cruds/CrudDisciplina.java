package quest2.cruds;

import quest2.conexao.Conexao;
import quest2.entities.Disciplina;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudDisciplina {
    private Conexao conexao;
    private PreparedStatement pstm;

    public CrudDisciplina(Conexao conexao) {
        this.conexao = conexao;
    }

    public void create(Disciplina disciplina){
        String query = "INSERT INTO disciplina(nome_disciplina) VALUES (?)";

        try {
            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);
            
            pstm.setString(1, disciplina.getNome_disciplina());

            pstm.execute();
            
            System.out.println();
            System.out.println("Uma nova disciplina foi adicionada ao banco de dados.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível adicionar a disciplina, verifique os dados e tente novamente.");
            System.out.println();
        }
    }
    
    public List<Disciplina> read(){
        String query = "SELECT *FROM disciplina";
        List<Disciplina> list = new ArrayList<>();

        try {
            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Disciplina disciplina = new Disciplina();

                disciplina.setId_disciplina(result.getInt("id_disciplina"));
                disciplina.setNome_disciplina(result.getString("nome_disciplina"));
                list.add(disciplina);
            }

            System.out.println();
            System.out.println("---------------------------------------------");
            for (Disciplina disciplina : list) {
                System.out.println(disciplina);
            }
            System.out.println("---------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void update(Disciplina disciplina, Scanner input){
        String query = "UPDATE disciplina SET nome_disciplina = ? WHERE id_disciplina = ?";

        try {
            read();

            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);
                        
            System.out.print("Qual id da disciplina que deseja atualizar? ");
            pstm.setString(1, disciplina.getNome_disciplina());
            pstm.setInt(2, input.nextInt());
            input.nextLine();
            
            pstm.execute();

            System.out.println();
            System.out.println("A disciplina foi atualizada no banco de dados.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível atualizar a disciplina, verifique os dados e tente novamente.");
            System.out.println();
        }
    }

    public void delete(Scanner input){
        String query = "DELETE FROM disciplina WHERE id_disciplina = ?";

        try {
            read();

            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);


            System.out.print("Digite o id da disciplina que deseja excluir: ");
            pstm.setInt(1,input.nextInt());
            input.nextLine();

            pstm.execute();

            System.out.println();
            System.out.println("A disciplina foi removida do banco de dados.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível remover a disciplina, verifique os dados e tente novamente.");
            System.out.println();
        }
    }
}
