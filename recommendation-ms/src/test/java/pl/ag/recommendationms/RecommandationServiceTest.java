package pl.ag.recommendationms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RecommandationServiceTest {
    //testy jednostkowe nie potrzebują kontekstu springa do uruchomienia
    //One powinny się uruchamiać przede wszystkim szybko

    @Mock
    private RestClient.Builder restClientBuilder;

    @Mock
    private RestClient restClient;

    //Muszę stworzyć obiekt żądania i obiekt odpowiedzie
    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    private RecommandationService recommandationService;

    @BeforeEach
//Musimy skonfigurować jak zachowywać będą się nasze mocki
        //Metoda ta będzie się uruchamiała przed każdym testem jednostkowym, który będzie zawierać się w tej klasie
    void setUp() {
        //Nie mamy kontekstu springa, więc musimy sami zadbać o inicjalizację
        MockitoAnnotations.openMocks(this);//Zainicjalizować mocków z użyciem mockito
        recommandationService = new RecommandationService(restClientBuilder);
        when(restClientBuilder.build())//W momencie kiedy restClientBuilder ma wołaną metodę build
                .thenReturn(restClient);//wówczas chcę zwrócić obekt mojego restCLienta, żebyśmy mogli posługiwać się nim w ramach poszczególnych metod

        //ok teraz mam obiekt restClienta
        //Teraz chcę na nim zasymulować wywołaną metodę get(), wówczas będziemy zwracać naszą odpowiedź
        when(restClient.get()).thenReturn(requestHeadersUriSpec);//odpowiedź którą będziemy sobie musieli skonfigurować

        when(requestHeadersUriSpec.uri(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.<Object[]>any()
        )).thenReturn(requestHeadersUriSpec);

        //dla metody retrieve spodziewamy się, że otrzymamy response, który będzie odzwierciedleniem prawdziwego responsu, który będzie przychodzić nam z innego mikroserwisu
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    public void shouldReturnListOfRecommendationWhenTagIsProvided() {
        //Musimy w tescie jednostkowym zadbac o to, aby jak najlepiej odzwierciedlić typy zwracane (rzeczywiste typy)
        // GIVEN - co mamy na starcie
        List<Map<String, String>> mockResponse = List.of(
                Map.of("id", "1", "title", "ArchitektIT.pl", "tag", "java")
        );
        when(responseSpec.body(List.class)).thenReturn(mockResponse);//Jeżeli ktoś wykona na responsie body i jako parametr przekaże liste, to wówczas ma być zwrócona ta odpowiedź

        //WHEN czyli co konkretnie ma się wykonać
        List<Map<String, String>> result = recommandationService.getRecommandation("java");
        //THEN - określa zbiór sprawdzeń
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).get("id"));
        assertEquals("ArchitektIT.pl", result.get(0).get("title"));//Gdy ktoś nas zapyta o title, to ma być ArchitekIT oczekiwany
        assertEquals("java", result.get(0).get("tag"));
    }

    //Testy jednostkowe nie powinny testować metod - one powinny testować funkcjonalności

}