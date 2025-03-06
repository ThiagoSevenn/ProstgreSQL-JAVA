package quest2.entities;

public class Disciplina {
    private int id_disciplina;
    private String nome_disciplina;

    public Disciplina() {
    }

    public Disciplina( String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }
    
    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }
    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    @Override
    public String toString() {
        return "ID: "+id_disciplina+" ,  Nome da Disciplina: " + nome_disciplina ;
    }

    
    
}
