package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
//index를 해주는 이유는 search할때 편리하라고!
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )  //mysql의 autoincrement는 Identitiy 방식으로 만들어진다!
    private Long id; //id는 사용자가 부여하는게 아니라 jpa가 영속성을 연속화 할때 자동으로 부여되는 번호

    @Setter @Column(nullable = false) private String title;  //제목 - Setter를 따로 설정하는 이유는 특정필드는 사용자들이 setting하지 못하게 하고 싶어서
    @Setter @Column(nullable = false, length = 10000) private String content; //본문
    @Setter private String hashtag; //해시태그

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; //생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; //생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정자


    //기본생성자
    protected Article() {}  //protected로 해놓으면 코드 블럭 밖에서 new로 생성하지 못하게 막음

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title,content,hashtag);
    }  // 이 방식으로 만들어주면 생성자 없이도 사용할 수 있게 된다.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // 비교대상인 나 자신인지 확인 this는 현재 객체 , o는 비교하려는 대상
        if (!(o instanceof Article article)) return false;
        // o 가 Article 클래스인지 확인 -> Article이 아니면 비교가 필요없으므로 false 반환 ->
        // 만약 Article 이라면, article이라는 변수에 타입을 변환해서 저장
        return id !=null && id.equals( article.id); //id 값 비교
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
