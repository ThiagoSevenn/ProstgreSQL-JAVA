package quest2.entities;

public class Aluno {
    private int id_aluno;
    private String nome_aluno;
    private String data_nascimento;

    public Aluno() {
    }

    public Aluno(String nome_aluno, String data_nascimento) {
        this.nome_aluno = nome_aluno;
        this.data_nascimento = data_nascimento;
    }

    public int getId_aluno() {
            return id_aluno;
        }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
    
    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String toString() {
        return "ID: "+id_aluno+" ,  Nome do aluno: " + nome_aluno + " ,  Data nascimento: " + data_nascimento ;
    }

    

    
   

}
