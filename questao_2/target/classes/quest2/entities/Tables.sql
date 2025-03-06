CREATE TABLE aluno(
    id_aluno SERIAL PRIMARY KEY,
    nome_aluno VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL
);

CREATE TABLE disciplina(
    id_disciplina SERIAL PRIMARY KEY,
    nome_disciplina VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE nota(
    id_nota SERIAL PRIMARY KEY,
    nota DECIMAL(4,2) NOT NULL,
    data_prova DATE NOT NULL,
    id_aluno INTEGER NOT NULL,
    id_disciplina INTEGER NOT NULL,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno) ON DELETE CASCADE,
    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina) ON DELETE CASCADE
);

DROP TABLE aluno CASCADE;
DROP TABLE disciplina CASCADE;
DROP TABLE nota;