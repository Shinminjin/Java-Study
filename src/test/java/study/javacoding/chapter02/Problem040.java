package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem040 {

    /**
     * NullPointerException 예외 발생을 줄이기 위해
     * null 참조 검사 기법이 널리 쓰임
     * <p>
     * 1. 리스트 참조 자체가 null 인지 확인
     * 2. 리스트가 null 객체를 포함하는지 확인
     */
    @Test
    void nullPointerException_발생_확인() {

        // 1.
        // given : 파라미터 자체가 null 일 경우
        // when
        NullPointerException e1 = Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    sumIntegersForException(null);
                }
        );

        // then
        assertThat(e1.getMessage())
                .isEqualTo("Cannot invoke \"java.util.List.stream()\" because \"integers\" is null");

        //------------------------------------------------------------------

        // 2.
        // given : 파라미터 요소에 null 이 포함될 경우
        List<Integer> numbers = Arrays.asList(1, 2, null, 4, null, 16, 7, null);

        // when
        NullPointerException e2 = Assertions.assertThrows(
                NullPointerException.class,
                () -> {sumIntegersForException(numbers);}
        );

        // then
        assertThat(e2.getMessage()).isEqualTo(null);
    }

    // Exception 발생을 위한 메소드
    private int sumIntegersForException(List<Integer> integers) {
        return integers.stream()
                .mapToInt(Integer::intValue).sum();
    }

    @Test
    void null_참조검사_메소드_수행() {

        // 1.
        // given : 파라미터 자체가 null 일 경우
        // when
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {sumIntegers(null);}
        );

        // then : NullPointerException 발생 전, IllegalArgumentException 발생시키도록 설계
        // 설계 의도에 맞는 결과확인 가능
        assertThat(e.getMessage()).isEqualTo("List cannot be null");

        //------------------------------------------------------------------

        // 2.
        // given : 파라미터 요소에 null 이 포함될 경우
        List<Integer> numbers = Arrays.asList(1, 2, null, 4, null, 16, 7, null);
        int sum = sumIntegers(numbers);

        // then : null 값을 필터링해서 덧셈수행
        assertThat(sum).isEqualTo(30);
    }

    // null 참조검사 수행을 위한 메소드
    private int sumIntegers(List<Integer> integers) {

        // 1. 리스트 파라미터 자체가 null 인지 check
        if (Objects.isNull(integers)) {
            throw new IllegalArgumentException("List cannot be null");
        }

        return integers.stream()
                // 2. 리스트 요소에 null 포함되어 있는지 check
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue).sum();
    }

    @Test
    void 파라미터_null_여부_확인용_메서드_수행() {

        // 1.
        // given : 파라미터 자체가 null 일 경우
        // when
        boolean result1 = checkNullsIntegers(null);

        // then
        assertThat(result1).isEqualTo(true);

        //------------------------------------------------------------------

        // 2.
        // given : 파라미터 요소에 null 이 포함될 경우
        List<Integer> numbers = Arrays.asList(1, 2, null, 4, null, 16, 7, null);
        boolean result2 = checkNullsIntegers(numbers);

        // then : null 값을 필터링해서 덧셈수행
        assertThat(result2).isEqualTo(true);

        // 비즈니스 로직 수행 시, 메소드 수행결과값이 true 일 경우 예외처리 진행
    }

    // 파라미터(integers) null 여부 확인용 메서드
    private boolean checkNullsIntegers(List<Integer> integers) {

        // 1. 파라미터 자체가 null 인지 check
        if (Objects.isNull(integers)) {
            return true;
        }

        return integers.stream()
                // 2. anyMatch: Predicate 가 주어진 스트림에서 적어도 한 요소와 일치하는지 확인
                .anyMatch(Objects::isNull);
    }

    @Test
    void 짝수요소_리스트추출_메서드_수행() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, null, 4, null, 16, 7, null);

        List<Integer> evens = evenIntegers(numbers);

        // then
        List<Integer> expectedNumbers = Arrays.asList(2, 4, 16);
        assertThat(evens).isEqualTo(expectedNumbers);
    }

    // 짝수요소 리스트추출 메서드
    private List<Integer> evenIntegers(List<Integer> integers) {

        if (integers == null) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> evens = new ArrayList<>();
        for (Integer number : integers) {
            if (number != null && number % 2 == 0) {
                evens.add(number);
            }
        }

        return evens;
    }
}
