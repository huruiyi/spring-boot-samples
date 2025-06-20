package vip.fairy;


import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.ui.idm.constant.GroupTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.fairy.flowable.FlowableApplication;

@SpringBootTest(classes = {FlowableApplication.class})
public class UserTest {

  @Autowired
  IdentityService identityService;


  @Test
  void addUser1() {
    User user = identityService.newUser("user1");
    user.setFirstName("user");
    user.setLastName("one");
    user.setEmail("user1@example.com");
    identityService.saveUser(user);

    Group adminGroup = identityService.newGroup("security-role");
    adminGroup.setName("security-role");
    adminGroup.setType(GroupTypes.TYPE_SECURITY_ROLE);
    identityService.saveGroup(adminGroup);

    identityService.createMembership("user1", "admin");
  }

    @Test
  void addUser2() {
    User user = identityService.newUser("user2");
    user.setFirstName("user");
    user.setLastName("two");
    user.setEmail("user2@example.com");
    identityService.saveUser(user);

    Group adminGroup = identityService.newGroup("assignment");
    adminGroup.setName("assignment");
    adminGroup.setType(GroupTypes.TYPE_ASSIGNMENT);
    identityService.saveGroup(adminGroup);

    identityService.createMembership("user2", "assignment");
  }

  @Test
  void deleteUser() {
    identityService.deleteUser("john.doe");
  }

}
