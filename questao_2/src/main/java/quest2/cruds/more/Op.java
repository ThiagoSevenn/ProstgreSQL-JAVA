package quest2.cruds.more;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import quest2.conexao.Conexao;
import quest2.cruds.CrudAluno;
import quest2.entities.Nota;

public class Op {
    private Conexao conexao;
    private PreparedStatement pstm = null;

    public Op(Conexao conexao) {
        this.conexao = conexao;
    }

    public void calcularMediaTodasNotasEspcific(Scanner input){
        String query = "SELECT A.nome_aluno ,\n" + //
                "D.nome_disciplina, \n" + //
                "ROUND(sum(N.nota) / COUNT(N.nota),2) AS \"Média Final\"\n" + //
                "FROM nota N\n" + //
                "INNER JOIN aluno A\n" + //
                "ON A.id_aluno = N.id_aluno\n" + //
                "INNER JOIN disciplina D\n" + //
                "ON D.id_disciplina = N.id_disciplina\n" + //
                "WHERE A.nome_aluno = ?\n" + //
                "GROUP BY A.nome_aluno, D.nome_disciplina";

        try {
            CrudAluno crud = new CrudAluno(conexao);

            crud.read();
            
            pstm = conexao.conected.prepareStatement(query);
            System.out.println();
            System.out.print("Digite o nome do aluno que deseja saber a média final das notas: ");
            pstm.setString(1, input.nextLine());

            ResultSet result = pstm.executeQuery();

            
            System.out.println();
            System.out.println("---------------------------------------------");
            while (result.next()) {
                System.out.print("Nome do aluno: "+result.getString("nome_aluno"));
                System.out.print(" ,  Nome da disciplina: "+result.getString("nome_disciplina"));
                System.out.println(" ,  Média da matéria: "+result.getString("Média Final"));
            }
            System.out.println("---------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calcularMediaTodasNotas(){
        String query = "SELECT A.nome_aluno ,\n" + //
                "D.nome_disciplina, \n" + //
                "ROUND(sum(N.nota) / COUNT(N.nota),2) AS \"Média Final\"\n" + //
                "FROM nota N\n" + //
                "INNER JOIN aluno A\n" + //
                "ON A.id_aluno = N.id_aluno\n" + //
                "INNER JOIN disciplina D\n" + //
                "ON D.id_disciplina = N.id_disciplina\n" + //
                "GROUP BY A.nome_aluno, D.nome_disciplina";

        try {
            
            pstm = conexao.conected.prepareStatement(query);
            System.out.println();

            ResultSet result = pstm.executeQuery();

            System.out.println();
            System.out.println("---------------------------------------------");
            while (result.next()) {
                System.out.print("Nome do aluno: "+result.getString("nome_aluno"));
                System.out.print(" ,  Nome da disciplina: "+result.getString("nome_disciplina"));
                System.out.println(" ,  Média da matéria: "+result.getString("Média Final"));
            }
            System.out.println("---------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void calcularFinalEspcific(Scanner input){
        String query = "SELECT A.nome_aluno , \n" + //
                        "ROUND(sum(N.nota) / COUNT(N.nota),2) AS \"Média Final Total\"\n" + //
                        "FROM nota N\n" + //
                        "INNER JOIN aluno A\n" + //
                        "ON A.id_aluno = N.id_aluno\n" + //
                        "WHERE A.nome_aluno = ?\n" + //
                        "GROUP BY A.nome_aluno";

        try {

           CrudAluno crud = new CrudAluno(conexao);

            crud.read();
            
            pstm = conexao.conected.prepareStatement(query);
            System.out.println();
            System.out.print("Digite o nome do aluno que deseja saber a média final: ");
            pstm.setString(1, input.nextLine());

            ResultSet result = pstm.executeQuery();
            
            System.out.println();
            System.out.println("---------------------------------------------");
            while (result.next()) {
                System.out.print("Nome do aluno: "+result.getString("nome_aluno"));
                System.out.println(" ,  Média Final: "+result.getString("Média Final Total"));
            }
            System.out.println("---------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void calcularFinal(){
        String query = "SELECT A.nome_aluno , \n" + //
                        "ROUND(sum(N.nota) / COUNT(N.nota),2) AS \"Média Final Total\"\n" + //
                        "FROM nota N\n" + //
                        "INNER JOIN aluno A\n" + //
                        "ON A.id_aluno = N.id_aluno\n" + //
                        "GROUP BY A.nome_aluno";

        try {
            
            pstm = conexao.conected.prepareStatement(query);
            System.out.println();

            ResultSet result = pstm.executeQuery();
            
            System.out.println();
            System.out.println("---------------------------------------------");
            while (result.next()) {
                System.out.print("Nome do aluno: "+result.getString("nome_aluno"));
                System.out.println(" ,  Média Final: "+result.getString("Média Final Total"));
            }
            System.out.println("---------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void situationEspcific(Scanner input){
        String query = "SELECT A.nome_aluno , \n" + //
                        "ROUND(sum(N.nota) / COUNT(N.nota),2) AS \"Média Final Total\",\n" + //
                        "CASE WHEN (sum(N.nota) / COUNT(N.nota)) >= 7 THEN 'Aprovado'\n" + //
                        "ELSE 'Reprovado' END AS \"Resultado\"\n" + //
                        "FROM nota N\n" + //
                        "INNER JOIN aluno A\n" + //
                        "ON A.id_aluno = N.id_aluno\n" + //
                        "WHERE A.nome_aluno = ? \n" + //
                        "GROUP BY A.nome_aluno";
        try {
            CrudAluno crud = new CrudAluno(conexao);

            crud.read();
            
            pstm = conexao.conected.prepareStatement(query);
            System.out.println();
            System.out.print("Digite o nome do aluno que deseja saber o resultado final: ");
            pstm.setString(1, input.nextLine());

            ResultSet result = pstm.executeQuery();
            
            System.out.println();
            System.out.println("---------------------------------------------");
            while (result.next()) {
                System.out.print("Nome do aluno: "+result.getString("nome_aluno"));
                System.out.print(" ,  Média Final: "+result.getString("Média Final Total"));
                System.out.println(" ,  Resultado: "+result.getString("Resultado"));
            }
            System.out.println("---------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void situation(){
        String query = "SELECT A.nome_aluno , \n" + //
                        "ROUND(sum(N.nota) / COUNT(N.nota),2) AS \"Média Final Total\",\n" + //
                        "CASE WHEN (sum(N.nota) / COUNT(N.nota)) >= 7 THEN 'Aprovado'\n" + //
                        "ELSE 'Reprovado' END AS \"Resultado\"\n" + //
                        "FROM nota N\n" + //
                        "INNER JOIN aluno A\n" + //
                        "ON A.id_aluno = N.id_aluno\n" + //
                        "GROUP BY A.nome_aluno";
        try {
            
            pstm = conexao.conected.prepareStatement(query);
            System.out.println();
            

            ResultSet result = pstm.executeQuery();
            
            System.out.println();
            System.out.println("---------------------------------------------");
            while (result.next()) {
                System.out.print("Nome do aluno: "+result.getString("nome_aluno"));
                System.out.print(" ,  Média Final: "+result.getString("Média Final Total"));
                System.out.println(" ,  Resultado: "+result.getString("Resultado"));
            }
            System.out.println("---------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int quantidadeDeProva(Nota nota){
        String query = "SELECT a.nome_aluno,d.nome_disciplina,\n" + //
                        "COUNT(n.nota) AS \"quantidade\"\n" + //
                        "FROM nota n\n" + //
                        "INNER JOIN aluno a\n" + //
                        "ON a.id_aluno = n.id_aluno\n" + //
                        "INNER JOIN disciplina d\n" + //
                        "ON d.id_disciplina = n.id_disciplina\n" + //
                        "WHERE a.id_aluno = ? \n" + //
                        "AND d.id_disciplina = ?\n" + //
                        "GROUP BY a.nome_aluno, d.nome_disciplina";

        int quantidade = 0;
        try {
            pstm = (PreparedStatement) conexao.conected.prepareStatement(query);

            pstm.setInt(1,nota.getId_aluno());
            pstm.setInt(2,nota.getId_disciplina());

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                quantidade = result.getInt("quantidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        

        return quantidade;
    }
}
