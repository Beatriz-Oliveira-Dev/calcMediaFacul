/**
 * Classe que representa um aluno, armazenando dados pessoais, notas, assiduidade,
 * média e situação acadêmica.
 */
class Aluno(
    var nome: String,                  // Nome completo do aluno
    var idade: Int,                    // Idade do aluno
    var sexo: String,                  // Sexo do aluno
    var matricula: String = gerarMatricula(), // Matrícula única gerada automaticamente
    var nota: List<Double>,            // Lista de notas do aluno em avaliações
    var assiduidade: Double,           // Porcentagem de presença nas aulas
    var media: Double? = null,         // Média das notas (calculada posteriormente)
    var situacao: Boolean? = null      // Situação: true = aprovado, false = reprovado, null = não avaliado
) {

    /**
     * Calcula a média das notas do aluno e armazena no atributo 'media'.
     * Se não houver notas, a média será 0.0.
     */
    fun calcularMedia() {
        media = if (nota.isNotEmpty()) nota.average() else 0.0
    }

    /**
     * Define a situação do aluno com base na média e assiduidade.
     * O aluno é considerado aprovado se a média for maior ou igual a 7.0
     * e a assiduidade for maior ou igual a 75%. Caso contrário, é reprovado.
     * Se a média não foi calculada, considera 0.0.
     */
    fun calcularSituacao() {
        situacao = (media ?: 0.0) >= 7.0 && assiduidade >= 75.0
    }

    /**
     * Retorna uma string detalhada com todas as informações do aluno,
     * incluindo nome, idade, sexo, matrícula, situação (aprovado/reprovado/não avaliado)
     * e média formatada com duas casas decimais.
     */
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

        /**
         * Gera uma matrícula única para cada aluno.
         * O valor é incrementado automaticamente a cada novo aluno criado,
         * garantindo que não haja duplicidade.
         * O formato é um número com 4 dígitos, preenchido com zeros à esquerda.
         */
        fun gerarMatricula(): String {
            val matricula = contadorMatricula.toString().padStart(4, '0')
            contadorMatricula++
            return matricula
        }
    }
}