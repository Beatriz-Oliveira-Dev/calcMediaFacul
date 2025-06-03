object AlunoUtils {

    val alunos = mutableListOf<Aluno>()

    fun cadastrarAluno(): Aluno {
        println("Digite o nome do aluno (ou 0 para voltar ao menu):")
        val nome = readLine() ?: return alunoSentinela()
        if (voltarAoMenu(nome)) return alunoSentinela()


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
        return aluno
    }

    private fun alunoSentinela(): Aluno {
        return Aluno(
            nome = "0",
            idade = 0,
            sexo = "",
            matricula = Aluno.gerarMatricula(),
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
            aluno.calcularMedia()
            aluno.calcularSituacao()
            listarAluno()
        }
    }

    private fun editarAluno() {
        if (alunos.isEmpty()) { println("Nenhum aluno cadastrado ainda. Retornando ao menu..."); return }
        listarAluno()
        println("Digite o nome do aluno para editar:(Ou 0 para voltar ao menu)")
         val nomeBusca = readLine() ?: ""
        if (voltarAoMenu(nomeBusca)) return
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
        println("Digite o nome do aluno para excluir:(ou 0 para voltar ao menu)")
         listarAluno()
         println("Digite o nome do aluno:")
        val nomeBusca = readLine() ?: ""
        val alunoRemover = alunos.find { it.nome.equals(nomeBusca, ignoreCase = true) }
        if (alunoRemover != null) {
            if (voltarAoMenu(nomeBusca)) return
            alunos.remove(alunoRemover)
            println("Aluno $nomeBusca removido com sucesso!")
        } else {
            println("Aluno não encontrado.")
        }
    }

    fun listarAluno() {
        if (alunos.isEmpty()) {
            println("Nenhum aluno cadastrado")
        } else{
            println("\n=== LISTA DE ALUNOS ===")
            alunos.forEach { println(it.obterDetalhes()) }
        }
    }

    private fun voltarAoMenu(entrada: String): Boolean {
        return entrada == "0"
    }
}