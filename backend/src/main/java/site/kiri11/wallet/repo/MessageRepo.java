package site.kiri11.wallet.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import site.kiri11.wallet.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
