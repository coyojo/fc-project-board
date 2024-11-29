package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//각종 설정을 적을때 쓰는것
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("demo");
        // demo는 임의 데이터 jpa가 auditing을 할때마다 사람이름을 demo로 넣게 된다 나중에 바꿔야한다
        //TODO : 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자
    }

}
