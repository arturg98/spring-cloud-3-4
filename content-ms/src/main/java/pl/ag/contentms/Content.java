package pl.ag.contentms;

import jakarta.persistence.*;

@Entity
@Table(name = "contents")//dąży się do tego aby tabele w bazie danych były w liczbie mnogiej
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String tag;

    public Content() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    //Moduł 5 lekcja 2
    //Mamy klasyfikację na Klientów HTTP Deklaratywnych i Klasycznych
    //Klasyczni są nazywani imperatywnymi, programisa musi napisać linijka po linijce kodu, w sposób aby obsłużyć w pełni całe żądanie
    //W podejściu klasycznym mamy RestClient'a, WebClienta, RestTemplata(ponoć deprecated, odchodzi do lamusa)
    //RestClient - programowanie funkcyjne
    //Przy komunikacji blokującej nasza aplikacja cały czas nasłuchuje na odpowiedź. Cały wątek zablokowany.
    //WebCLient - oparty o programowanie reaktywne, wymaga reaktywnego stacku technologicznego, zmienić kontener, dostosować aplikację, żeby była reaktywna, streowniki na bazie danych, tak żeby stack nie był blokujący
    //WebClinet jest asynchroniczny? Jak jest już minimalna porcja obiektu odpowiedzi to jest zwracana i można ją już przetważać. Nie ma takich opóźnień jak przy sys kolejkowych.
    //W przypadku programowania reaktywnego nie ma komunikacji blokującej. My posługujemy się strumieniami w komunikacji reaktywnej, możemy wykonać akcję .block(), wtedy ze strumienia dostaniemy synchroniczną odpowiedź
    //Taki mikroserwis dużo elastyczniej nam odpowiada, ma większą pule wątków na nasłuchiwanie, komunikację itp.
    //W imperatywnym jest łatwiejsze debugowanie, stostuje się przy bardziej rozbudowanych requestach z headerami itp, np. na konkretne kody odpowiedzi chcemy zareagować
    //Deklaratywny - jak nazwa wskazuje dekaruję co chce osiągnąć, a nie piszę w jaki sposób chcę to osiągnąć
    //Dekaratywny @HttpExchange (spring 3.2), wcześniej było OpenFeign, to jest zewnętrzne narzędzie ma dużo rozwiązań
    //HttpExchange nie zapewnia jeszcze aż tylu możliwości co OpenFeign
    //Deklaratywny jest bardziej wygodny, szybszy development
}
