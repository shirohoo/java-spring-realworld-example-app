package sample.shirohoo.realworld.core.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "article_tag",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"article_id", "tag_id"})})
public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Column(nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    public ArticleTag(Article article, Tag tag) {
        if (article == null || article.getId() == null) {
            throw new IllegalArgumentException("article is null or unknown article.");
        }
        if (tag == null || tag.getId() == null) {
            throw new IllegalArgumentException("tag is null or unknown tag.");
        }

        this.article = article;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ArticleTag other
                && Objects.equals(this.getId(), other.getId())
                && Objects.equals(this.getArticle(), other.getArticle())
                && Objects.equals(this.getTag(), other.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getArticle(), this.getTag());
    }
}
