# 📚 Projeto Kotlin: Cadastro de Alunos

Sistema simples em Kotlin para cadastrar, editar, remover e exibir relatórios de alunos, totalmente via console!

---

## ✨ Funcionalidades

- ✅ Cadastro de aluno (nome, idade, sexo, notas e assiduidade)
- ✅ Edição dos dados do aluno
- ✅ Remoção de alunos
- ✅ Exibição de relatório final com todos os alunos

---

## 🗂️ Estrutura do Projeto

```
📁 calcMediaFacul
 ├── Aluno.kt
 ├── AlunoUtils.kt
 ├── Main.kt
 └── README.md
```

---

## ⚙️ Como Compilar e Executar

### 1. Pré-requisitos

- [Kotlin Compiler](https://kotlinlang.org/docs/command-line.html) instalado e configurado
- Java (JDK) instalado e configurado

### 2. Compilando o Projeto

Abra o terminal na pasta do projeto e execute:

```sh
kotlinc Aluno.kt AlunoUtils.kt Main.kt -include-runtime -d Main.jar
```

Isso irá gerar o arquivo executável:

```
Main.jar
```

### 3. Executando o Projeto

No terminal, execute:

```sh
java -jar Main.jar
```

O sistema abrirá o menu interativo no console para você começar a usar!

---

## 📝 Observações

- No menu, basta digitar o número da opção desejada e seguir as instruções.
- Para parar o programa, escolha a opção **0**.
- O relatório final com todos os alunos cadastrados será exibido ao final.

---

<div align="center">
  <b>Feito com 💙 em Kotlin</b>
</div>