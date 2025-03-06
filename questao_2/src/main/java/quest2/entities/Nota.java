package quest2.entities;

public class Nota {
    private int id_nota;
    private double nota;
    private String data_prova;
    private int id_aluno;
    private int id_disciplina;

    public Nota() {
    }

    public Nota(double nota, String data_prova, int id_aluno, int id_disciplina) {
        this.nota = nota;
        this.data_prova = data_prova;
        this.id_aluno = id_aluno;
        this.id_disciplina = id_disciplina;
    }

    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
    public String getData_prova() {
        return data_prova;
    }
    public void setData_prova(String data_prova) {
        this.data_prova = data_prova;
    }
    public int getId_aluno() {
        return id_aluno;
    }
    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
    public int getId_disciplina() {
        return id_disciplina;
    }
    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    @Override
    public String toString() {
        return "ID: " + id_nota + " ,  Nota: " + nota + " ,  Data da Prova: " + data_prova + " ,  ID-Aluno: " + id_aluno + " ,  ID-Disciplina: " + id_disciplina ;
    }
    
}
