package pl.ag.recommendationms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    RecommandationService recommandationService;

    public RecommendationController(RecommandationService recommandationService) {
        this.recommandationService = recommandationService;
    }

    @GetMapping("/{tag}")
    public List<Map<String, String>> getRecommandation(@PathVariable String tag) {
        return recommandationService.getRecommandation(tag);
    }
}
