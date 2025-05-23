package pl.ag.contentclientms;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Start {

    private final PostsClient postsClient;

    public Start(PostsClient postsClient) {
        this.postsClient = postsClient;
    }
//Niech to będzie metoda, która będzie uruchamiana na starcie.
    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        System.out.println(postsClient.getPosts(1l));//Niech wyświetli nam 1 obiekt przy starcie apki

        System.out.println(postsClient.getPosts().stream().filter(element -> element.userId() == 3).collect(Collectors.toList()));//bierzemy całą liste postClientów i szukamy takiego co jest równy 3

        //Gdyby obsługiwał posta, moglibyśmy użyć:
        //postsClient.createPost(new Post(1l, 1l, "siema", "byku"));
    }
}
