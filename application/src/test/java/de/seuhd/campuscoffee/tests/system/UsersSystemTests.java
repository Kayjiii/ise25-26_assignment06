package de.seuhd.campuscoffee.tests.system;

import de.seuhd.campuscoffee.domain.model.Pos;
import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.posRequests;
import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.userRequests;

public class UsersSystemTests extends AbstractSysTest {

    @Test
    void createUser() {
        User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());

        assertEqualsIgnoringIdAndTimestamps(createdUser, userToCreate);
    }

    @Test
    void getUserById() {
        List<User> createdUserList = TestFixtures.createUsers(userService);
        User createdUser = createdUserList.getFirst();

        User retrievedUser = userDtoMapper.toDomain(
                userRequests.retrieveById(createdUser.id())
        );

        assertEqualsIgnoringTimestamps(retrievedUser, createdUser);
    }

    @Test
    void filterUserByName() {
        List<User> createdUserList = TestFixtures.createUsers(userService);
        User createdUser = createdUserList.getFirst();
        String userName = createdUser.loginName();
        User filteredUser = userDtoMapper.toDomain(userRequests.retrieveByFilter("name", userName));

        assertEqualsIgnoringTimestamps(filteredUser, createdUser);
    }

}