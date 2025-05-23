package pl.ag.recommendationms;

import jakarta.persistence.*;

@Entity
@Table(name = "contents_from_recommendation")//dąży się do tego aby tabele w bazie danych były w liczbie mnogiej
public class ContentFromRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String tag;

    public ContentFromRecommendation() {
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
}
