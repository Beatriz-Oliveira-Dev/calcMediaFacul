object AlunoUtils {

    val alunos = mutableListOf<Aluno>()

    private fun lerString(msg: String): String {
        print(msg)
        return readLine() ?: ""
    }

    private fun lerInt(msg: String): Int {
        print(msg)
        return readLine()?.toIntOrNull() ?: 0
    }

    private fun lerDouble(msg: String): Double {
        print(msg)
        return readLine()?.toDoubleOrNull() ?: 0.0
    }

    fun cadastrarAluno(): Aluno {
        val nome = lerString("Nome (ou 0 para voltar): ")
        if (nome == "0") return alunoSentinela()

        val idade = lerInt("Idade: ")
        val sexo = lerString("Sexo: ")
        val notas = lerString("Notas (separadas por vírgula): ")
            .split(",").mapNotNull { it.trim().toDoubleOrNull() }
        val assiduidade = lerDouble("Assiduidade (%): ")

        return Aluno(nome, idade, sexo, nota = notas, assiduidade = assiduidade)
    }

    private fun alunoSentinela() = Aluno(
        nome = "0", idade = 0, sexo = "",
        matricula = Aluno.gerarMatricula(),
        nota = emptyList(), assiduidade = 0.0,
        media = null, situacao = null
    )

    fun mostrarMenu() {
        while (true) {
            println("\n=== MENU PRINCIPAL ===")
            println("1. Cadastrar aluno")
            println("2. Editar aluno")
            println("3. Remover aluno")
            println("4. Listar alunos")
            println("0. Sair")
            when (lerInt("Escolha uma opção: ")) {
                1 -> cadastrarAlunoNoMenu()
                2 -> editarAluno()
                3 -> excluirAluno()
                4 -> listarAluno()
                0 -> { println("Saindo... Até logo!"); break }
                else -> println("Opção inválida. Tente novamente.")
            }
        }
    }

    private fun cadastrarAlunoNoMenu() {
        val aluno = cadastrarAluno()
        if (aluno.nome != "0") {
            alunos.add(aluno)
            aluno.calcularMedia()
            aluno.calcularSituacao()
            println("Aluno cadastrado com sucesso!")
            listarAluno()
        }
    }

    private fun editarAluno() {
        if (alunos.isEmpty()) { println("Nenhum aluno cadastrado."); return }
        listarAluno()
        val nomeBusca = lerString("Nome do aluno para editar (ou 0 para voltar): ")
        if (nomeBusca == "0") return
        val aluno = alunos.find { it.nome.equals(nomeBusca, true) }
        if (aluno != null) {
            val novoNome = lerString("Novo nome (Enter para manter): ")
            if (novoNome.isNotBlank()) aluno.nome = novoNome

            val novaIdade = lerString("Nova idade (Enter para manter): ")
            if (novaIdade.isNotBlank()) aluno.idade = novaIdade.toIntOrNull() ?: aluno.idade

            val novoSexo = lerString("Novo sexo (Enter para manter): ")
            if (novoSexo.isNotBlank()) aluno.sexo = novoSexo

            val novasNotas = lerString("Novas notas (separadas por vírgula, Enter para manter): ")
            if (novasNotas.isNotBlank()) aluno.nota = novasNotas.split(",").mapNotNull { it.trim().toDoubleOrNull() }

            val novaAssiduidade = lerString("Nova assiduidade (%), Enter para manter: ")
            if (novaAssiduidade.isNotBlank()) aluno.assiduidade = novaAssiduidade.toDoubleOrNull() ?: aluno.assiduidade

            aluno.calcularMedia()
            aluno.calcularSituacao()
            println("Aluno editado com sucesso!")
        } else {
            println("Aluno não encontrado.")
        }
    }

    private fun excluirAluno() {
        if (alunos.isEmpty()) { println("Nenhum aluno cadastrado."); return }
        listarAluno()
        val nomeBusca = lerString("Nome do aluno para excluir (ou 0 para voltar): ")
        if (nomeBusca == "0") return
        val alunoRemover = alunos.find { it.nome.equals(nomeBusca, true) }
        if (alunoRemover != null) {
            alunos.remove(alunoRemover)
            println("Aluno $nomeBusca removido com sucesso!")
        } else {
            println("Aluno não encontrado.")
        }
    }

    fun listarAluno() {
        if (alunos.isEmpty()) {
            println("Nenhum aluno cadastrado")
        } else {
            println("\n=== LISTA DE ALUNOS ===")
            alunos.forEach { println(it.obterDetalhes()) }
        }
    }
}