package freedomcar.njupractice.data.dao.user;


import org.springframework.data.jpa.repository.JpaRepository;
import trapx00.tagx00.entity.account.Role;
import trapx00.tagx00.entity.account.User;

import java.util.ArrayList;

public interface UserDao extends JpaRepository<User, String> {
    User findUserByUsername(String username);

    ArrayList<User> findUsersByRole(Role role);
}
