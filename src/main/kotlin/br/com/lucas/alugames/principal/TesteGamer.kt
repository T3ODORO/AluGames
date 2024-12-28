package br.com.lucas.alugames.modelo

fun main(){
    val gamer1 = Gamer("jaque", "jaque")
    println(gamer1)

    val gamer2 = Gamer(
        "Jeni",
        "jjj",
        "sssss",
        "assss"
    )

    println(gamer2)

    gamer1.let {
        it.dataNascimento = "ddddddd"
        it.usuario = "sssssss"
        //it.idInterno = "ddd"
    }

    println(gamer1)
}