package study.javacoding.chapter02;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;

// 불변 객체 내 잘못된 데이터 유입 방지
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem052 {

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
        public static final class UserBuilder {
            @NotNull(message = "cannot be null")
            @Size(min = 3, max = 20, message = "must be between 3 and 20 characters")
            private final String nickname;

            @NotNull(message = "cannot be null")
            @Size(min = 6, max = 50, message = "must be between 6 and 50 characters")
            private final String password;

            @Size(min = 3, max = 20, message = "must be between 3 and 20 characters")
            private String firstname;

            @Size(min = 3, max = 20, message = "must be between 3 and 20 characters")
            private String lastname;

            @Email(message = "must be valid")
            private String email;

            private final Date created;

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

    private static <T> void printConstraintViolations(
            String caption, Set<ConstraintViolation<T>> violations
    ) {
        log.info("{}", caption);

        violations.forEach((v) -> {
            log.info("\t{}, {}", v.getPropertyPath(), v.getMessage());
        });
    }

    @Test
    void 불변_객체_내_잘못된_데이터_유입_방지_확인() {
        User user;

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        User.UserBuilder userBuilder = new User.UserBuilder("monika", "klooi0988")
                .email("monika@gmail.com")
                .firstName("Monika")
                .lastName("Gunther");

        final Set<ConstraintViolation<User.UserBuilder>> violations
                = validator.validate(userBuilder);

        if (violations.isEmpty()) {
            user = userBuilder.build();
            log.info("User successfully created on: {}", user.getCreated());
        } else {
            printConstraintViolations("UserBuilder Violations: ", violations);
        }
    }
}
