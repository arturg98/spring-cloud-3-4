package pl.ag.recommendationms;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class RecommandationService {

    //@Autowired przy testach jednostkowych usunięcie i dodanie konstuktora, bo autowired tworzy przez context springowy
    private RestClient.Builder builder;

    public RecommandationService(RestClient.Builder builder) {
        this.builder = builder;
    }

    public List<Map<String, String>> getRecommandation(String tag) {
        return builder.build()
                .get()
                //.uri("http://CONTENT-MS/api/content/{tag}", tag) to działało dla Client Side Balancingu z @LoadBalanced
                .uri("http://localhost:8080/api/content/{tag}", tag)//podany adres do API-GATEWAY
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        //Skrót klawiszowy do pisania testu ctrl + shift +t
    }
}
