package pl.ag.contentms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentRepository contentRepository;
    @Value("${message1}")
    private String message;

//    private List<Map<String, String>> contentList
//            = List.of(
//            Map.of("id", "1", "title", "AkademiaSpring.pl", "tag", "spring"),
//            Map.of("id", "2", "title", "ArchitektIT.pl", "tag", "cloud"),
//            Map.of("id", "3", "title", "Docker i Spring Cloud", "tag", "cloud")
//    );


    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

//    @GetMapping
//    public List<Map<String, String>> getContent() {
//        return contentList;
//    }

    @GetMapping
    public List<Content> getContent() {
        return contentRepository.findAll();
    }

    @GetMapping("/{tag}")
    public List<Content> getContentByTag(@PathVariable String tag) {
        return contentRepository.findByTagIgnoreCase(tag);
    }

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }

//    @GetMapping("/{tag}")
//    public List<Map<String, String>> getContentByTag(@PathVariable String tag) {
//        return contentList.stream()
//                .filter(content -> content.get("tag").equalsIgnoreCase(tag))
//                .toList();
//    }

}
