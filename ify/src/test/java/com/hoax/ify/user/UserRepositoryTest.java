package com.hoax.ify.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsername_whenUserExists_returnsUser() {
        User user = new User("test-user", "test-display", "P4ssw0rd");
        testEntityManager.persist(user);

        User inDB = userRepository.findByUsername("test-user");

        assertThat(inDB).isNotNull();
    }

    @Test
    public void findByUsername_whenUserDoesNotExist_returnsNull() {
        User inDB = userRepository.findByUsername("test-user");

        assertThat(inDB).isNull();
    }
}
