package ru.griddodicks.wallet.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.griddodicks.wallet.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
