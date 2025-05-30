object AlunoUtils {

    val alunos = mutableListOf<Aluno>()

    fun cadastrarAluno(): Aluno {
        println("Digite o nome do aluno (ou 'sair' para encerrar):")
        val nome = readLine() ?: return alunoSentinela()
        if (nome.lowercase() == "sair") return alunoSentinela()

        println("Digite a idade do aluno:")
        val idade = readLine()?.toIntOrNull() ?: return alunoSentinela()

        println("Digite o sexo do aluno:")
        val sexo = readLine() ?: return alunoSentinela()

        println("Digite as notas do aluno (separadas por vírgula):")
        val notasInput = readLine() ?: return alunoSentinela()
        val notas = notasInput.split(",").mapNotNull { it.toDoubleOrNull() }

        println("Digite a assiduidade do aluno (em porcentagem):")
        val assiduidade = readLine()?.toDoubleOrNull() ?: return alunoSentinela()

        val aluno = Aluno(
            nome = nome,
            idade = idade,
            sexo = sexo,
            nota = notas,
            assiduidade = assiduidade
        )

        println("Aluno adicionado com sucesso!\n")
        return aluno
    }

    private fun alunoSentinela(): Aluno {
        return Aluno(
            nome = "0",
            idade = 0,
            sexo = "",
            matricula = null,
            nota = emptyList(),
            assiduidade = 0.0,
            media = null,
            situacao = null
        )
    }

    fun mostrarMenu() {
        while (true) {
            println("\n=== MENU PRINCIPAL ===")
            println("1. Cadastrar aluno")
            println("2. Editar aluno")
            println("3. Remover aluno")
            println("0. Sair")
            print("Escolha uma opção: ")

            when (readLine()?.toIntOrNull() ?: -1) {
                1 -> cadastrarAlunoNoMenu()
                2 -> editarAluno()
                3 -> excluirAluno()
                0 -> {
                    println("Saindo... Até logo!")
                    break
                }
                else -> println("Opção inválida. Tente novamente.")
            }
        }
    }

    private fun cadastrarAlunoNoMenu() {
        val aluno = cadastrarAluno()
        if (aluno.nome != "0") {
            alunos.add(aluno)
            println("Aluno cadastrado com sucesso!")
        }
    }

    private fun editarAluno() {
        println("Digite o nome do aluno para editar:")
        val nomeBusca = readLine() ?: ""
        val aluno = alunos.find { it.nome.equals(nomeBusca, ignoreCase = true) }
        if (aluno != null) {
            println("Digite o novo nome do aluno (Enter se quiser passar pro próximo):")
            val novoNome = readLine()
            if (!novoNome.isNullOrBlank()) aluno.nome = novoNome

            println("Digite a nova idade do aluno (Enter se quiser passar pro próximo):")
            val novaIdade = readLine()
            if (!novaIdade.isNullOrBlank()) aluno.idade = novaIdade.toIntOrNull() ?: aluno.idade

            println("Digite o novo sexo do aluno (Enter se quiser passar pro próximo):")
            val novoSexo = readLine()
            if (!novoSexo.isNullOrBlank()) aluno.sexo = novoSexo

            println("Digite as novas notas do aluno (separadas por vírgula, Enter se quiser passar pro próximo):")
            val novasNotasInput = readLine()
            if (!novasNotasInput.isNullOrBlank()) {
                aluno.nota = novasNotasInput.split(",").mapNotNull { it.toDoubleOrNull() }
            }

            println("Digite a nova assiduidade do aluno (em porcentagem, Enter se quiser passar pro próximo):")
            val novaAssiduidadeInput = readLine()
            if (!novaAssiduidadeInput.isNullOrBlank()) {
                aluno.assiduidade = novaAssiduidadeInput.toDoubleOrNull() ?: aluno.assiduidade
            }

            aluno.calcularMedia()
            aluno.calcularSituacao()
            println("Aluno editado com sucesso!")
        } else {
            println("Aluno não encontrado.")
        }
    }

    private fun excluirAluno() {
        println("Digite o nome do aluno para excluir:")
        val nomeBusca = readLine() ?: ""
        val alunoRemover = alunos.find { it.nome.equals(nomeBusca, ignoreCase = true) }
        if (alunoRemover != null) {
            alunos.remove(alunoRemover)
            println("Aluno $nomeBusca removido com sucesso!")
        } else {
            println("Aluno não encontrado.")
        }
    }
}