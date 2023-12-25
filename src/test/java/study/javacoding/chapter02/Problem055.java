package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem055 {

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

    public class TennisPlayer extends Player {
    }

    public class SnookerPlayer extends Player {
    }

    public class FootballPlayer extends Player {
    }

    public enum PlayerTypes {
        TENNIS,
        FOOTBALL,
        SNOOKER,
        UNKNOWN
    }

    private Player createPlayerSwitchStatementUgly(PlayerTypes playerType) {
        Player player = null;

        switch (playerType) {
            case TENNIS:
                player = new TennisPlayer();
                break;
            case FOOTBALL:
                player = new FootballPlayer();
                break;
            case SNOOKER:
                player = new SnookerPlayer();
                break;
            case UNKNOWN:
                throw new UnknownPlayerException("Player type is unknown");
            default:
                throw new IllegalArgumentException("Invalid player type: " + playerType);
        }

        return player;
    }

    private Player createPlayerSwitchStatementNice(PlayerTypes playerType) {
        switch (playerType) {
            case TENNIS:
                return new TennisPlayer();
            case FOOTBALL:
                return new FootballPlayer();
            case SNOOKER:
                return new SnookerPlayer();
            case UNKNOWN:
                throw new UnknownPlayerException("Player type is unknown");
            default:
                throw new IllegalArgumentException("Invalid player type: " + playerType);
        }
    }

    private Player createPlayerSwitchExpression(PlayerTypes playerType) {
        return switch (playerType) {
            case TENNIS -> new TennisPlayer();
            case FOOTBALL -> new FootballPlayer();
            case SNOOKER -> new SnookerPlayer();
            case UNKNOWN -> throw new UnknownPlayerException("Player type is unknown");
            // default 는 필수가 아님
            default -> throw new IllegalArgumentException("Invalid player type: " + playerType);
        };
    }

    private Player createPlayerSwitchExpressionYield(PlayerTypes playerType) {
        return switch (playerType) {
            case TENNIS:
                yield new TennisPlayer();
            case FOOTBALL:
                yield new FootballPlayer();
            case SNOOKER:
                yield new SnookerPlayer();
            case UNKNOWN:
                throw new UnknownPlayerException("Player type is unknown");
            default:
                throw new IllegalArgumentException("Invalid player type: " + playerType);
        };
    }

    @Test
    void switch_표현식_확인() {
        // switch statement
        Player playerSwitchStatementUgly = createPlayerSwitchStatementUgly(PlayerTypes.TENNIS);
        log.info("playerSwitchStatementUgly : {}", playerSwitchStatementUgly);
        assertThat(playerSwitchStatementUgly).isInstanceOf(TennisPlayer.class);

        Player playerSwitchStatementNice = createPlayerSwitchStatementNice(PlayerTypes.FOOTBALL);
        log.info("playerSwitchStatementNice : {}", playerSwitchStatementNice);
        assertThat(playerSwitchStatementNice).isInstanceOf(FootballPlayer.class);

        // switch expression
        Player playerSwitchExpression = createPlayerSwitchExpression(PlayerTypes.SNOOKER);
        log.info("playerSwitchExpression : {}", playerSwitchExpression);
        assertThat(playerSwitchExpression).isInstanceOf(SnookerPlayer.class);

        Player playerSwitchExpressionYield = createPlayerSwitchExpressionYield(PlayerTypes.FOOTBALL);
        log.info("playerSwitchExpressionYield : {}", playerSwitchExpressionYield);
        assertThat(playerSwitchExpressionYield).isInstanceOf(FootballPlayer.class);
    }
}
