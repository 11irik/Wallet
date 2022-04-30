package ru.griddodicks.wallet.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.griddodicks.wallet.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
    User findByEmail(String email);

}
