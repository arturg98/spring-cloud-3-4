package pl.ag.ms1;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Component
public class ConfigurationRefresher {
    private final RestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();

    public ConfigurationRefresher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 100000)//wykonaj co 100 sek zaciągnięcie nowego pliku configuracyjnego
    @PostMapping("/actuator/refresh")
    public void refreshConfig() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("", headers);
        restTemplate.postForEntity("http://localhost:8080/actuator/refresh", request, String.class);
    }
}
