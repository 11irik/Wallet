package site.kiri11.wallet.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.kiri11.wallet.domain.User;

import java.util.Set;

public interface UserDetailsRepo extends JpaRepository<User, String> {
    User findByEmail(String email);

}
