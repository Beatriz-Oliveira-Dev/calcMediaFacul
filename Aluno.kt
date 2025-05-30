class Aluno(var nome: String, 
var idade: Int, 
var sexo: String, 
var nota: List<Double>, 
var assiduidade: Double,
var media: Double?= null,
var situacao: Boolean?= null) {
    fun calcularMedia(){
        var soma = 0.0
        for (i in nota){
            soma += i
        }
        media = soma / nota.size
        println("A média do aluno $nome é: $media")
    }
    fun calcularSituacao(){
        if ((media?: 0.0) >= 7.0 && assiduidade >= 75.0){
            situacao = true
            println("O aluno $nome está aprovado.")
        } else {
            situacao = false
            println("O aluno $nome está reprovado.")
        }
    }

}