package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class ArticleComment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @ManyToOne(optional = false) private Article article;  //게시글
    //@ManyToOne은 여러개의 엔티티가 하나의 관련 엔티티를 참조할 때 쓴는 글
    // (댓글을 기준으로 댓글 여러개가 하나의 article을 참조하므로 다대일 관계)
   @Setter @Column(nullable = false, length = 500)  private String content;  //본문

    /* Article 필드에서도 반복되는 부분이므로 AuditingFields 클래스로 묶는 방식 사용
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false,length = 100) private String createdBy;
    @LastModifiedDate  @Column(nullable = false) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false,length = 100) private String modifiedBy;
    */
    protected ArticleComment() {}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    //팩토리 메서드
    public static ArticleComment of (Article article, String content){
        return new ArticleComment(article,content);
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
