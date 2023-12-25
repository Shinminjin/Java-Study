package study.javacoding.chapter02;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Date;

// 빌더 패턴으로 불변 클래스 작성
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem051 {

    @Getter
    public static final class User {
        private final String nickname;
        private final String password;
        private final String firstname;
        private final String lastname;
        private final String email;
        private final Date created;

        private User(UserBuilder builder) {
            this.nickname = builder.nickname;
            this.password = builder.password;
            this.created = builder.created;
            this.firstname = builder.firstname;
            this.lastname = builder.lastname;
            this.email = builder.email;
        }

        public static UserBuilder getBuilder(String nickname, String password) {
            return new User.UserBuilder(nickname, password);
        }

        public static final class UserBuilder {
            private final String nickname;
            private final String password;
            private final Date created;
            private String email;
            private String firstname;
            private String lastname;

            public UserBuilder(String nickname, String password) {
                this.nickname = nickname;
                this.password = password;
                this.created = new Date();
            }

            public UserBuilder firstName(String firsname) {
                this.firstname = firsname;
                return this;
            }

            public UserBuilder lastName(String lastname) {
                this.lastname = lastname;
                return this;
            }

            public UserBuilder email(String email) {
                this.email = email;
                return this;
            }

            public User build() {
                return new User(this);
            }
        }
    }

    @Test
    void 빌더_패턴으로_불변_클래스_작성() {
        // user with nickname and password
        User user1 = User.getBuilder("marin21", "hjju9887h").build();
        log.info("User 1 successfully created on: {}", user1.getCreated());

        // user with nickname, password and email
        User user2 = User.getBuilder("ionk", "44fef22")
                .email("ion@gmail.com")
                .build();
        log.info("User 2 successfully created on: {}", user2.getCreated());

        // user with nickname, password, email, firstname and lastname
        User user3 = User.getBuilder("monika", "klooi0988")
                .email("monika@gmail.com")
                .firstName("Monika")
                .lastName("Ghuenter")
                .build();
        log.info("User 3 successfully created on: {}", user3.getCreated());
    }
}
