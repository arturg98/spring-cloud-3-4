package pl.ag.contentclientms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    //Musimy stworzyć instancje RestClienta, więc tworzymy Beana - zgodnie z dokumentacją
    //Implementacja jak na razie nie jest prosta i intuicyjna.

    @Bean
    public PostsClient get(){
        RestClient restClient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com/").build();//Żeby zwracał PostsClient to jeszcze trzeba dokonfigurować poza tą linijką kodu
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PostsClient.class);
    }
}
