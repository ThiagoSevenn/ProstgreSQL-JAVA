package quest2;

import java.util.Scanner;

import quest2.exceptions.InvalidInfo;
import quest2.conexao.Conexao;
import quest2.entities.*;
import quest2.cruds.*;
import quest2.cruds.more.Op;


public class App 
{
    public static void main( String[] args )
    {
        Conexao conexao = new Conexao();

        CrudAluno crudAluno = new CrudAluno(conexao);
        CrudDisciplina crudDisciplina = new CrudDisciplina(conexao);
        CrudNota crudNota = new CrudNota(conexao);
        Op operacao = new Op(conexao);

        Scanner input = new Scanner(System.in);

        boolean loop = true;

        try {
            while (loop) {   

                System.out.println();            
                System.out.println("Qual tabela quer fazer sua consulta? ");
                System.out.println("---> aluno , ---> disciplina , ---> nota");
                System.out.println();
                System.out.println("OBS: caso queira ver a média de um aluno em suas matérias digite --> media");
                System.out.println("OBS: caso queira ver a média final de um aluno digite --> final");
                System.out.println("OBS: caso queira ver o resultado final de um aluno digite --> resultado");
                System.out.print("Resposta: ");
                String tabela = input.nextLine();
                

                if (tabela.equals("aluno")) {
                    int escolha= operacao(tabela, input);
                    input.nextLine();
                    System.out.println();
                    if (escolha == 1) {
                        System.out.print("Digite o nome do aluno que deseja inserir: ");
                        String nome = input.nextLine();

                        System.out.print("Digite a data de nascimento do aluno? ");
                        String data = input.nextLine();

                        Aluno aluno = new Aluno(nome, data);
                        
                        crudAluno.create(aluno);
                    }else if(escolha == 2){
                        crudAluno.read();

                    }else if(escolha == 3){
                        System.out.print("Digite o novo nome do aluno: ");
                        String nome = input.nextLine();

                        System.out.print("\nDigite a data de nascimento do aluno: ");
                        String data = input.nextLine();

                        Aluno aluno = new Aluno(nome, data);
                        
                        crudAluno.update(aluno, input);
                    }else if(escolha == 4){
                        crudAluno.delete(input);

                    }else{
                        throw new InvalidInfo("Essa opção não existe .");
                    }
                }
                else if(tabela.equals("disciplina")){
                    int escolha= operacao(tabela, input);
                    input.nextLine();
                    System.out.println();
                    if (escolha == 1) {
                        System.out.print("Digite o nome da disciplina que deseja inserir: ");
                        String nome = input.nextLine();

                        Disciplina disciplina = new Disciplina(nome);
                        
                        crudDisciplina.create(disciplina);
                    }else if(escolha == 2){
                        crudDisciplina.read();

                    }else if(escolha == 3){
                        System.out.print("Digite o novo nome da disciplina: ");
                        String nome = input.nextLine();

                        Disciplina disciplina = new Disciplina(nome);
                        
                        crudDisciplina.update(disciplina, input);
                    }else if(escolha == 4){
                        crudDisciplina.delete(input);

                    }else{
                        throw new InvalidInfo("Essa opção não existe .");
                    }

                }
                else if(tabela.equals("nota")){
                    int escolha= operacao(tabela, input);
                    input.nextLine();
                    System.out.println();
                    if (escolha == 1) {                        
                        System.out.print("Digite o valor da nota: ");
                        double notaA = input.nextDouble();
                        input.nextLine();

                        System.out.print("Digite a data da prova: ");
                        String data = input.nextLine();

                        System.out.print("Digite o id do aluno que tirou essa nota: ");
                        int id_aluno = input.nextInt();

                        System.out.print("Digite o id da disciplina da prova: ");
                        int id_disciplina = input.nextInt();
                        input.nextLine();

                        Nota nota = new Nota(notaA, data, id_aluno, id_disciplina);
                        
                        int quantidade = operacao.quantidadeDeProva(nota);
                        if (quantidade < 4) {
                            crudNota.create(nota);
                        }else{
                            throw new InvalidInfo("Não é possível criar mais de 3 notas por disciplina por aluno.");
                        }
                        
                    }else if(escolha == 2){
                        crudNota.read();

                    }else if(escolha == 3){
                        System.out.print("Digite a nova nota: ");
                        double notaA = input.nextDouble();
                        input.nextLine();

                        System.out.println("\nDigite a data da prova: ");
                        String data = input.nextLine();

                        System.out.print("Digite o id do aluno que tirou essa nota: ");
                        int id_aluno = input.nextInt();

                        System.out.print("Digite o id da disciplina da prova: ");
                        int id_disciplina = input.nextInt();
                        input.nextLine();

                        Nota nota = new Nota(notaA, data, id_aluno, id_disciplina);

                        int quantidade = operacao.quantidadeDeProva(nota);
                        if (quantidade < 4) {
                            crudNota.update(nota, input);
                        }else{
                            throw new InvalidInfo("Não é possível criar mais de 3 notas por disciplina por aluno.");
                        }
                    }else if(escolha == 4){
                        crudNota.delete(input);

                    }else{
                        throw new InvalidInfo("Essa opção não existe .");
                    }
                }
                else if(tabela.equals("media")){
                    System.out.println();
                    System.out.print("Média de todos os alunos ? (s/n) ");
                    if (input.nextLine().equals("s")) {
                        operacao.calcularMediaTodasNotas();
                    }else{
                        operacao.calcularMediaTodasNotasEspcific(input);
                    }
                }
                else if(tabela.equals("final")){
                    System.out.println();
                    System.out.print("Média final de todos os alunos ? (s/n) ");
                    if (input.nextLine().equals("s")) {
                        operacao.calcularFinal();
                    }else{
                        operacao.calcularFinalEspcific(input);
                    }
                }
                else if(tabela.equals("resultado")){
                    System.out.println();
                    System.out.print("Resultado de todos os alunos ? (s/n) ");
                    if (input.nextLine().equals("s")) {
                        operacao.situation();
                    }else{
                        operacao.situationEspcific(input);
                    }
                }
                else{
                    throw new InvalidInfo("Essa tabela não existe no banco de dados.");
                }
            
            }
        } 
        catch (InvalidInfo e) {
            System.out.println(e);
        }finally{
            input.close();
        }
        
    }

    public static int operacao(String tabela,Scanner input){
        System.out.println();
        System.out.println("Você vai ter acesso a tabela: "+tabela);

        System.out.println("---->Escolha a operação que desejar<----");

        System.out.println("1 -> Fazer a inserção de um(a) "+tabela+" no database.");
        System.out.println("2 -> Fazer a leitura da tabela '"+tabela+"'.");
        System.out.println("3 -> Fazer a atualização de um(a) "+tabela+" do database.");
        System.out.println("4 -> Fazer a deleção de um(a) "+tabela+" do database.");

        System.out.print("Resposta: ");
        int escolha = input.nextInt();

        return escolha;
    }
}
