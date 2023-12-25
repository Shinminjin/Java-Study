package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem057 {

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

    public class Player {
    }

    public class FootballPlayer extends Player {
    }

    public class SnookerPlayer extends Player {
    }

    public class TennisPlayer extends Player {
    }

    public enum PlayerTypes {
        TENNIS,
        FOOTBALL,
        SNOOKER
    }

    private Player createPlayer(PlayerTypes playerType) {
        return switch (playerType) {
            case TENNIS -> {
                log.info("Creating a TennisPlayer ...");
                yield new TennisPlayer();
            }
            case FOOTBALL -> {
                log.info("Creating a FootballPlayer ...");
                yield new FootballPlayer();
            }
            case SNOOKER -> {
                log.info("Creating a SnookerPlayer ...");
                yield new SnookerPlayer();
            }
            // default 는 필수가 아님
            default -> throw new IllegalArgumentException("Invalid player type: " + playerType);
        };
    }

    @Test
    void switch_명령문_블록_실행() {
        // switch 명령문 결과 확인
        assertThat(createPlayer(PlayerTypes.TENNIS)).isInstanceOf(TennisPlayer.class);
        assertThat(createPlayer(PlayerTypes.FOOTBALL)).isInstanceOf(FootballPlayer.class);
        assertThat(createPlayer(PlayerTypes.SNOOKER)).isInstanceOf(SnookerPlayer.class);
    }
}
