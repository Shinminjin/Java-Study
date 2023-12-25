package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem056 {

    public class UnknownPlayerException extends RuntimeException {
        public UnknownPlayerException() {
            super();
        }

        public UnknownPlayerException(String message) {
            super(message);
        }

        public UnknownPlayerException(Throwable cause) {
            super(cause);
        }

        public UnknownPlayerException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public class SportType {
    }

    public class Individual extends SportType {
    }

    public class Team extends SportType {
    }

    public enum PlayerTypes {
        TENNIS,
        FOOTBALL,
        SNOOKER,
        GOLF,
        VOLLEY
    }

    private SportType fetchSportTypeByPlayerType(PlayerTypes playerType) {
        return switch (playerType) {
            case TENNIS, GOLF, SNOOKER -> new Individual();
            case FOOTBALL, VOLLEY -> new Team();
        };
    }

    @Test
    void 다수의_case_레이블_확인() {
        // TENNIS, GOLF, SNOOKER 전달 시,
        // Individual 클래스 인스턴스 반환
        assertThat(fetchSportTypeByPlayerType(PlayerTypes.TENNIS)).isInstanceOf(Individual.class);

        // FOOTBALL, VOLLEY 전달 시,
        // Team 클래스 인스턴스 반환
        assertThat(fetchSportTypeByPlayerType(PlayerTypes.FOOTBALL)).isInstanceOf(Team.class);
    }
}
