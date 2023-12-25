package study.javacoding.chapter02;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Date;

// toString() 오버라이딩
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem054 {

    @Getter
    public class NoToStringUser {
        private final String nickname;
        private final String password;
        private final String firstname;
        private final String lastname;
        private final String email;
        private final Date created;

        public NoToStringUser(String nickname, String password, String firstname, String lastname, String email) {
            this.nickname = nickname;
            this.password = password;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.created = new Date();
        }
    }

    @Test
    void toString_오버라이드_없이_클래스_인스턴스_출력하기() {
        NoToStringUser user = new NoToStringUser("sparg21", "kkd454ffc", "Leopold", "Mark", "markl@yahoo.com");

        // study.javacoding.chapter02.Problem054$User@53251a66
        // 클래스명 @ 부호 없는 16진수 해시 코드
        log.info("user: {}", user);
    }

    @Getter
    public class User {
        private final String nickname;
        private final String password;
        private final String firstname;
        private final String lastname;
        private final String email;
        private final Date created;

        public User(String nickname, String password, String firstname, String lastname, String email) {
            this.nickname = nickname;
            this.password = password;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.created = new Date();
        }

        @Override
        public String toString() {
            return "User{" + "nickname=" + nickname
                    + ", firstname=" + firstname + ", lastname=" + lastname
                    + ", email=" + email + ", created=" + created + '}';
        }
    }

    @Test
    void toString_오버라이드_후_클래스_인스턴스_출력하기() {
        User user = new User("sparg21", "kkd454ffc", "Leopold", "Mark", "markl@yahoo.com");

        // User{nickname=sparg21, firstname=Leopold, lastname=Mark, email=markl@yahoo.com, created=Sun Dec 24 20:29:43 KST 2023}
        log.info("user: {}", user);
    }
}
