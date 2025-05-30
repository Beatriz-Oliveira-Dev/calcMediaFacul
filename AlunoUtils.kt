object AlunoUtils{
    fun cadastrarAluno(): Aluno {
    println("Digite o nome do aluno (ou 'sair' para encerrar):")
    val nome = readLine() ?: break
    if (nome.lowercase() == "sair") break

    println("Digite a idade do aluno:")
    val idade = readLine()?.toIntOrNull() ?: continue
    
    println("Digite o sexo do aluno:")
    val sexo = readLine() ?: continue

    println("Digite as notas do aluno (separadas por vírgula):")
    val notasInput = readLine() ?: continue
    val notas = notasInput.split(",").mapNotNull{it.toDoubleOrNull()}

    println("Digite a assiduidade do aluno (em porcentagem):")
    val assiduidade = readLine()?.toDoubleOrNull() ?: continue

    val aluno = Aluno(nome = nome, idade = idade, sexo = sexo, nota = notas, assiduidade = assiduidade)

    aluno.calcularMedia()
    aluno.calcularSituacao()
    alunos.add(aluno)
    println("Aluno adicionado com sucesso!\n")
    return aluno

    }
}