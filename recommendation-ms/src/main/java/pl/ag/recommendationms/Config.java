package pl.ag.recommendationms;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Config {

    //@LoadBalanced//Uruchamia możliwość odpytania Eureki o dany adres mikroserwisu np. http://CONTENT-MS/api/content/
    //wtedy najpierw aplikacja zapyta eurki czy zna taką aplikację CONTENT-MS jeśli tak zostanie wstawiony adres serwisu z eureki
    //Czyli tutaj mamy Client Side Load Balancing, czyli od klienta wychodzi incjatywa, żeby skomunikować się z właściwym serwisem
    //Clientem jest RecommendationService, bo on prosi o dane od Content-ms, aplikacja Client-ska pobiera wpisy z serviceRegistry (w tym przypadku Eureka)

    //Round Robin - mechanizm przydzielania zasobów, jest to zmodyfikowana wersja FIFO - nie występuje zagłodzenie, wykorzystuje go LoadBalancing
    //Każdy proces dostaje swój przydział zasobów
    @Bean
    RestClient.Builder getRestClientBuilder() {
        return  RestClient.builder();
    }
}
