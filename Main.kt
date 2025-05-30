
fun main(){
var aluno1 = Aluno("João", 20, "Masculino", listOf(8.0, 7.5, 9.0), 80.0)
var aluno2 = Aluno("Maria", 22, "Feminino", listOf(6.0, 5.5, 7.0), 70.0)
aluno1.calcularMedia()
aluno1.calcularSituacao()
aluno2.calcularMedia()
aluno2.calcularSituacao()
}