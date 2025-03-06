package quest2.cruds;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import quest2.conexao.Conexao;
import quest2.entities.Nota;

public class CrudNota {
    private Conexao conexao;
    private PreparedStatement pstm;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    public CrudNota(Conexao conexao) {
        this.conexao = conexao;
    }

    public void create(Nota nota){
        String query = "INSERT INTO nota(nota, data_prova, id_aluno, id_disciplina) VALUES (?, ?, ?, ?)";

        try {
            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            java.util.Date data;
            try {
                data = format.parse(nota.getData_prova());

                pstm.setDouble(1, nota.getNota());
                pstm.setDate(2, new Date(data.getTime()));
                pstm.setInt(3, nota.getId_aluno());
                pstm.setInt(4, nota.getId_disciplina());



                pstm.execute();

                System.out.println();
                System.out.println("Uma nova nota foi adicionado ao banco de dados.");
                System.out.println();
            } catch (ParseException e) {
                e.fillInStackTrace();
            }
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível adicionar a nota, verifique os dados e tente novamente.");
            System.out.println();
        }
    }
    
    public List<Nota> read(){
        String query = "SELECT *FROM nota";

        List<Nota> list = new ArrayList<>();
        try {
            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Nota nota = new Nota();

                nota.setId_nota(result.getInt("id_nota"));
                nota.setNota(result.getDouble("nota"));
                nota.setData_prova(result.getDate("data_prova").toString());
                nota.setId_aluno(result.getInt("id_aluno"));
                nota.setId_disciplina(result.getInt("id_disciplina"));

                list.add(nota);
            }

            System.out.println();
            System.out.println("---------------------------------------------------------------------");
            for (Nota nota : list) {
                System.out.println(nota);
            }
            System.out.println("---------------------------------------------------------------------");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Nota nota, Scanner input){
        String query = "UPDATE nota SET nota = ?, data_prova = ?, id_aluno = ?, id_disciplina = ?"+
        " WHERE id_nota = ?";

        try {
            read();

            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            java.util.Date data;
            try {
                data = format.parse(nota.getData_prova());

                pstm.setDouble(1, nota.getNota());
                pstm.setDate(2, new Date(data.getTime()));
                pstm.setInt(3, nota.getId_aluno());
                pstm.setInt(4, nota.getId_disciplina());
                
                System.out.print("Qual é o id da nota que deseja atualizar? ");
                nota.setId_nota(input.nextInt());
                input.nextLine();

                pstm.setInt(5, nota.getId_nota());

                pstm.execute();

                System.out.println();
                System.out.println("A nota foi atualizado com sucesso.");
                System.out.println();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível atualizar a nota, verifique os dados e tente novamente.");
            System.out.println();
        }
    }

    public void delete(Scanner input){
        String query = "DELETE FROM nota WHERE id_nota = ?";

        try {
            read();

            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            System.out.print("Qual valor de id deseja deletar? ");
            pstm.setInt(1, input.nextInt());
            input.nextLine();

            pstm.execute();

            System.out.println();
            System.out.println("A nota foi deletada do banco de dados.");
            System.out.println();
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Não foi possível deletar a nota, verifique os dados e tente novamente.");
            System.out.println();        }

    }
}
