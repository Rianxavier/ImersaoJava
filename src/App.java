import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Pegar só os dados que interresam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
    
        // Exibir e manipular os dados
        int n = 1;
        for (Map<String,String> filme : listaDeFilmes) {
            
            System.out.println(n + " -");
            System.out.println("Titulo: " + filme.get("title"));
            System.out.println("URL da imagem: " +filme.get("image"));
            System.out.println("Rating: " +filme.get("imDbRating"));
            System.out.println("__________________________________________________________________________________");
            n++;
        }
    }
}
