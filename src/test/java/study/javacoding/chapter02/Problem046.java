package study.javacoding.chapter02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

// equals()와 hashCode()
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem046 {

    @AllArgsConstructor
    public class NoOverridePlayer {
        private int id;
        private String name;
    }

    @Test
    void 인스턴스_생성후_동등한지_비교() {

        // when
        NoOverridePlayer p1 = new NoOverridePlayer(1, "Rafael Nadal");
        NoOverridePlayer p2 = new NoOverridePlayer(1, "Rafael Nadal");

        /**
         * Object.equals()
         * : 두 객체의 메모리 주소가 똑같이 표현될 때,
         *   두 객체를 동등하게 봄
         *
         * Object.hashCode()
         * : 객체의 메모리 주소를 표현하는 정수를 반환
         */

        // then
        log.info("p1 hash Code : {}", p1.hashCode());
        log.info("p2 hash Code : {}", p2.hashCode());

        // p1, p2 서로 다른 메모리 주소에 저장되므로 false 반환
        // 기본 equals() 구현을 활용하면, 문제 해결 못함
        assertThat(p1.equals(p2)).isEqualTo(false);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class EqualsOverridePlayer {
        private int id;
        private String name;

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null) {
                return false;
            }

            if (getClass() != object.getClass()) {
                return false;
            }

            final EqualsOverridePlayer other = (EqualsOverridePlayer) object;
            if (this.id != other.id) {
                return false;
            }

            if (!Objects.equals(this.name, other.name)) {
                return false;
            }

            return true;
        }

    }

    @Test
    void equals_오버라이드_진행_후_인스턴스_동등비교() {

        // when
        EqualsOverridePlayer p1 = new EqualsOverridePlayer(1, "Rafael Nadal");
        EqualsOverridePlayer p2 = new EqualsOverridePlayer(1, "Rafael Nadal");

        // then
        log.info("p1 hash Code : {}", p1.hashCode());
        log.info("p2 hash Code : {}", p2.hashCode());
        assertThat(p1.equals(p2)).isEqualTo(true);
    }

    @Test
    void equals_오버라이드_진행_후_인스턴스를_컬렉션에_추가() {
        EqualsOverridePlayer p1 = new EqualsOverridePlayer(1, "Rafael Nadal");
        EqualsOverridePlayer p2 = new EqualsOverridePlayer(1, "Rafael Nadal");

        Set<EqualsOverridePlayer> players = new HashSet<>();
        players.add(p1);
        players.add(p2);

        log.info("p1.hashCode() : {}", p1.hashCode());
        log.info("p2.hashCode() : {}", p2.hashCode());
        // p1, p2 동일해야 할텐데..?
        assertThat(p1.hashCode()).isNotEqualTo(p2.hashCode());

        // HashSet 크기는 1이어야 할텐데..?
        log.info("players.size() : {}", players.size());
        assertThat(players.size()).isNotEqualTo(1);

        // Rafael Nadal 을 포함해야 할텐데..?
        assertThat(players.contains(new EqualsOverridePlayer(1, "Rafael Nadal"))).isNotEqualTo(true);
    }

    // equals(), hashCode() 오버라이딩 수행
    @Getter
    @Setter
    @AllArgsConstructor
    public class Player {
        private int id;
        private String name;

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (getClass() != obj.getClass()) {
                return false;
            }

            final Player other = (Player) obj;
            if (this.id != other.id) {
                return false;
            }

            if (!Objects.equals(this.name, other.name)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + this.id;
            hash = 79 * hash + Objects.hashCode(this.name);
            return hash;
        }
    }

    @Test
    void equals_hashCode_오버라이드_진행_후_인스턴스를_컬렉션에_추가() {
        Player p1 = new Player(1, "Rafael Nadal");
        Player p2 = new Player(1, "Rafael Nadal");

        Set<Player> players = new HashSet<>();
        players.add(p1);
        players.add(p2);

        log.info("p1.hashCode() : {}", p1.hashCode());
        log.info("p2.hashCode() : {}", p2.hashCode());
        // p1, p2 동일
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());

        // HashSet 크기는 1
        log.info("players.size() : {}", players.size());
        assertThat(players.size()).isEqualTo(1);

        // Rafael Nadal 을 포함
        assertThat(players.contains(new Player(1, "Rafael Nadal"))).isEqualTo(true);
    }
}
