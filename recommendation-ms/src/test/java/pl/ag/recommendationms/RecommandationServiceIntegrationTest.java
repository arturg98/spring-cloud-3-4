package pl.ag.recommendationms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(classes = {RecommandationService.class, Config.class})
public class RecommandationServiceIntegrationTest {

    //Tutaj już będziemy podnosić kontekst springa i żebyśmy nie podnieśli za dużo Bean'ów
    //Gdy wykorzystujemy @SpringBootTest - to podnosimy Bean'y z całego projektu, więc dla przyspieszenia testu wybierzemy te klasy, które sa nam potrzebne

    @Autowired//Teraz mogę tutaj z powodzeniem wstrzyknąć nasz RecommendationService
    private RecommandationService recommandationService;

    @Autowired
    private RestClient.Builder restClientBuilder;

    //My używamy aplikacji, która podnosi naszą warstwę servera. Mokowanie naszego serwera, po to abyśmy mogli te wszystkie żądania servera przechwytywać
    //sprawdzać czy one są prawidłowe, czy zawierają wszystko to co potrzebują
    private MockRestServiceServer mockServer;

    @BeforeEach//Będziemy tutaj inicjalizować nasz mock server
    void setUp() {
        mockServer = MockRestServiceServer.bindTo(restClientBuilder).build();//bind tu pozwala nam na zbindowanie. Połączenie naszego buildera z mockServerem
        //To oznacza, że my bezpośrednio nie będziemy wysyłać prawdziwego żądania do servera tylko do naszego mocka, więc będziemy mogli sprawdzić co ten builder robi
        //Nasz mockowany server będzie mógł tym wszystkim zarządzać
    }

    @Test
    public void shouldCallApiAndReturnListOfRecommendationsWhenTagIsProvided(){
        //GIVEN
        //tego Json on sam napisał z palca, w jawny sposób widzimy co jest przesyłane, nie jesteśmy zależni od naszego typu
        String mockResponse = "[{\"id\":\"1\",\"title\":\"ArchitektIT.pl\",\"tag\":\"java\"}]";

        this.mockServer
                .expect(requestToUriTemplate("http://localhost:8080/api/content/{tag}", "java"))
                .andExpect(method(GET))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
        //WHEN
        List<Map<String, String>> result = recommandationService.getRecommandation("java");
        //THEN
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).get("id"));
        assertEquals("ArchitektIT.pl", result.get(0).get("title"));
        assertEquals("java", result.get(0).get("tag"));

        mockServer.verify();

    }
}
