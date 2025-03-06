package quest2.cruds;

import quest2.conexao.Conexao;
import quest2.entities.Aluno;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudAluno {

    private Conexao conexao;
    private PreparedStatement pstm = null;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public CrudAluno(Conexao conexao) {
        this.conexao = conexao;
    }

    public void create(Aluno aluno){
        String query = "INSERT INTO aluno(nome_aluno, data_nascimento) VALUES (?, ?)";

        try {
            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            java.util.Date data;
            try {
                data = format.parse(aluno.getData_nascimento());
                
                pstm.setString(1, aluno.getNome_aluno());
                pstm.setDate(2, new Date(data.getTime()));
            
                pstm.execute();
                
                System.out.println();
                System.out.println("Um novo aluno foi adicionado ao banco de dados.");
                System.out.println();

            } catch (ParseException e) {
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível adicionar o aluno, verifique os dados e tente novamente.");
            System.out.println();
        }
    }

    public List<Aluno> read(){
        String query = "SELECT *FROM aluno";

        List<Aluno> list = new ArrayList<>();

        try {
            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Aluno aluno = new Aluno();

                aluno.setId_aluno(result.getInt("id_aluno"));
                aluno.setNome_aluno(result.getString("nome_aluno"));
                aluno.setData_nascimento(result.getDate("data_nascimento").toString());

                list.add(aluno);
            }

            System.out.println();
            System.out.println("---------------------------------------------");
            for (Aluno aluno : list) {
                System.out.println(aluno);
            }
            System.out.println("---------------------------------------------");


        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            pstm = null;
        }
        return list;
    }

    public void update(Aluno aluno, Scanner input){
        String query = "UPDATE aluno SET nome_aluno = ?, data_nascimento = ? WHERE id_aluno = ?";

        try {
            read();

            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            java.util.Date data;
            try {
                data = format.parse(aluno.getData_nascimento());
                
                pstm.setString(1, aluno.getNome_aluno());
                pstm.setDate(2, new Date(data.getTime()));

                System.out.print("Qual é o id do aluno que deseja atualizar? ");
                pstm.setInt(3, input.nextInt());
                input.nextLine();

                pstm.execute();
                
                System.out.println();
                System.out.println("O aluno foi atualizado com sucesso.");
                System.out.println();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível atualizar o aluno, verifique os dados e tente novamente.");
            System.out.println();
        }
    }

    public void delete(Scanner input){
        String query = "DELETE FROM aluno WHERE id_aluno = ?";

        try {
            read();

            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);
            
            System.out.print("Qual valor de id deseja deletar? ");
            pstm.setInt(1, input.nextInt());
            input.nextLine();

            pstm.execute();
            System.out.println();
            System.out.println("O aluno foi deletado do banco de dados.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível deletar o aluno, verifique os dados e tente novamente.");
            System.out.println();
        }
    }

}
