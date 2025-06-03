object AlunoUtils {

    // Lista global que armazena todos os alunos cadastrados no sistema
    val alunos = mutableListOf<Aluno>()

    /**
     * Lê uma string do usuário exibindo uma mensagem personalizada.
     * Retorna a string digitada ou uma string vazia se o usuário apenas pressionar Enter.
     */
    private fun lerString(msg: String): String {
        print(msg)
        return readLine() ?: ""
    }

    /**
     * Lê um número inteiro do usuário exibindo uma mensagem personalizada.
     * Retorna o valor digitado ou 0 caso o usuário digite algo inválido.
     */
    private fun lerInt(msg: String): Int {
        print(msg)
        return readLine()?.toIntOrNull() ?: 0
    }

    /**
     * Lê um número decimal (Double) do usuário exibindo uma mensagem personalizada.
     * Retorna o valor digitado ou 0.0 caso o usuário digite algo inválido.
     */
    private fun lerDouble(msg: String): Double {
        print(msg)
        return readLine()?.toDoubleOrNull() ?: 0.0
    }

    /**
     * Realiza o cadastro de um novo aluno, solicitando todos os dados necessários ao usuário.
     * Caso o usuário digite "0" no nome, retorna um aluno sentinela para indicar cancelamento.
     * @return Um objeto Aluno preenchido com os dados informados.
     */
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

    /**
     * Cria um aluno "sentinela" para indicar que o cadastro ou edição foi cancelado pelo usuário.
     * Esse aluno possui nome "0" e dados vazios.
     */
    private fun alunoSentinela() = Aluno(
        nome = "0", idade = 0, sexo = "",
        matricula = Aluno.gerarMatricula(),
        nota = emptyList(), assiduidade = 0.0,
        media = null, situacao = null
    )

    /**
     * Exibe o menu principal do sistema e gerencia as opções escolhidas pelo usuário.
     * Permite cadastrar, editar, remover, listar alunos ou sair do programa.
     */
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

    /**
     * Cadastra um novo aluno pelo menu, calcula sua média e situação,
     * adiciona à lista global e exibe a lista atualizada.
     */
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

    /**
     * Permite editar os dados de um aluno já cadastrado.
     * O usuário pode alterar nome, idade, sexo, notas e assiduidade.
     * Se o campo for deixado em branco, o valor anterior é mantido.
     */
    private fun editarAluno() {
        if (alunos.isEmpty()) { 
            println("Nenhum aluno cadastrado."); 
            return 
        }
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

    /**
     * Permite excluir um aluno da lista pelo nome.
     * O usuário pode cancelar a operação digitando "0".
     */
    private fun excluirAluno() {
        if (alunos.isEmpty()) { 
            println("Nenhum aluno cadastrado."); 
            return 
        }
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

    /**
     * Lista todos os alunos cadastrados, exibindo seus detalhes formatados.
     * Se não houver alunos, exibe uma mensagem informando.
     */
    fun listarAluno() {
        if (alunos.isEmpty()) {
            println("Nenhum aluno cadastrado")
        } else {
            println("\n=== LISTA DE ALUNOS ===")
            alunos.forEach { println(it.obterDetalhes()) }
        }
    }
}