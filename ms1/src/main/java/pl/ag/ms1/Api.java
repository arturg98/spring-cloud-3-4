package pl.ag.ms1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope//Kiedy pojawi się jakiekolwiek wywołanie beana - to zaktualizuj pliki konfiguracyjne
//konfiguracja zostanie zaktualizowana po strzale pod: POST http://localhost:8080/actuator/refresh
@RestController
public class Api {
    //Wstrzyknięcie do pola wartości z properties
    //@Value("${message}")
    @Value("${message: we are without config server}")//definiowanie domyślnej wartości, gdy nie zostanie wczytana konfiguracja
    private String message;

    @GetMapping("/hello")
    public String hello() {
        return message;
    }
}
