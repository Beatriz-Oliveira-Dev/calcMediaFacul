
fun main(){

val alunos = mutableListOf<Aluno>()

while (true){
        val aluno = cadastrarAluno()
        if (aluno.nome == "0") break
        alunos.add(aluno)
}

println("\n--- Relatório de Alunos ---")
for (aluno in alunos){
    println(aluno.obterDetalhes())
}
}