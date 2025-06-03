class Aluno(
    var nome: String,
    var idade: Int,
    var sexo: String,
    var matricula: String = gerarMatricula(),
    var nota: List<Double>,
    var assiduidade: Double,
    var media: Double? = null,
    var situacao: Boolean? = null
) {

    fun calcularMedia() {
        media = if (nota.isNotEmpty()) nota.average() else 0.0
    }

    fun calcularSituacao() {
        situacao = (media ?: 0.0) >= 7.0 && assiduidade >= 75.0
    }

    fun obterDetalhes(): String {
        val situacaoString = when (situacao) {
            true -> "Aprovado"
            false -> "Reprovado"
            else -> "Não avaliado"
        }
        val mediaFormatada = media?.let { String.format("%.2f", it) } ?: "-"
        return "Nome: $nome, Idade: $idade, Sexo: $sexo, Matrícula: $matricula, Situação: $situacaoString, Média: $mediaFormatada"
    }

    companion object {
        private var contadorMatricula = 1
        fun gerarMatricula(): String {
            val matricula = contadorMatricula.toString().padStart(4, '0')
            contadorMatricula++
            return matricula
        }
    }
}