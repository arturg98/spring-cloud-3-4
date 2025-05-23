package pl.ag.contentclientms;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@RestController
public class ContentCLient {
    private final RestClient restClient;

    public ContentCLient() {
        this.restClient = RestClient.builder().build();
    }

    @GetMapping("/call-client")
    @CircuitBreaker(name = "contentClientCall", fallbackMethod = "fallBackMethodGetContent")//W momencie kiedy coś pójdzie nie tak, ma się wykonać ta metoda
    public List<Content> get(@RequestParam String tag) {
        return restClient.get()
                .uri("http://localhost:8080/api/content/{tag}", tag)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
    //Chcemy odwołać się do usługi, która nie działa.

    //Musimy zdefiniować fallback method, która będzie się wykonywała jeśli coś pójdzie nie tak
    public List<Content> fallBackMethodGetContent(String tag, Throwable throwable) {
        //dodatkowo możemy zawżeć Throwable, który będzie nas informował o błędzie zwróconym przez API, tzn możemy zobaczyć czy to jest 404, 400, 500 itp
        Content c1 = new Content();
        c1.setId(1l);
        c1.setTag("java");
        c1.setTitle("ArchitektIT");
        return Arrays.asList(c1);
        //więc mogę sobie w sytuacji awaryjnej zwrócić jakiś predefiniowany Content
    }
}
