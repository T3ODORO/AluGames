import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*

fun main() {
    val scan = Scanner(System.`in`)
    println("Digite um código de jogo para buscar")
    val busca = scan.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()
    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()
    println(json)

    val gson = Gson()
    val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(
            meuInfoJogo.info.title,
            meuInfoJogo.info.thumb
        )
        println(meuJogo)
    }

    resultado.onFailure {
        println("Jogo inexistente")
    }

    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada? S/N")
        val opcao = scan.nextLine()
        if (opcao.equals("s", true)) {
            print("Insira a descrição: ")
            val descricaoPersonalizada = scan.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        } else {
            meuJogo?.descricao = meuJogo?.titulo
        }
    }
}
