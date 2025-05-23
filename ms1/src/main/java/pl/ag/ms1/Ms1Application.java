package pl.ag.ms1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableDiscoveryClient można też dodać tą adnotacje, ale nie jest potrzebna zwiększa czytelność, wtedy nie ma znaczenia czy pod spodem jest eureka czy consul
public class Ms1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ms1Application.class, args);
    }

}
