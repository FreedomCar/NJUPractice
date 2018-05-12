package freedomcar.njupractice.data.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import trapx00.tagx00.entity.account.TempUser;

public interface TempUserDao extends JpaRepository<TempUser, String> {
    TempUser findTempUserByUsername(String username);
}
