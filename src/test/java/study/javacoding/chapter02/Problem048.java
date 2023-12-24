package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 불변 문자열
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem048 {

    /**
     * 자바는 큰 따옴표 속 내용을 문자열 리터럴로 간주하고
     * 문자열 리터럴을 문자열 상수 풀이라는 특수 메모리 영역에 저장함
     */
    @Test
    void 문자열_hashCode_확인() {
        // String 객체가 몇개 생성될까?
        String x = "book";
        String y = "book";
        String z = "book";

        // 값이 "book"인 객체 딱 1개만 생성됨
        assertThat(x.hashCode()).isEqualTo(y.hashCode()).isEqualTo(z.hashCode());
    }

    @Test
    void 문자열_hashCode_확인2() {
        String x = "book";
        String y = "book";
        String z = "book";

        log.info("x.hashCode() : {}", x.hashCode());
        log.info("y.hashCode() : {}", y.hashCode());
        log.info("z.hashCode() : {}", z.hashCode());
        assertThat(x.hashCode()).isEqualTo(y.hashCode()).isEqualTo(z.hashCode());

        x = x.replace("b", "c");
        // 자바는 새로운 String 객체 x를 생성한다.
        log.info("x.hashCode() : {}", x.hashCode());
        log.info("y.hashCode() : {}", y.hashCode());
        log.info("z.hashCode() : {}", z.hashCode());
        assertThat(y.hashCode()).isEqualTo(z.hashCode()).isNotEqualTo(x.hashCode());
    }

}
