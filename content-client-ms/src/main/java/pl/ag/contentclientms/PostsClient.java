package pl.ag.contentclientms;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
//Deklaratywny HTTP
@HttpExchange
public interface PostsClient {//Post w znaczeniu, że posty będziemy pobierać z api

    @GetExchange("/posts/{id}")
    Post getPosts(@PathVariable("id") Long id);

    @GetExchange("/posts/")
    List<Post> getPosts();

    @PostExchange("/posts")//przykład na metode post, nasze api zewnętrzne obsułguje tylko GET
    void createPost(@RequestBody Post post);
}
