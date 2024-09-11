package tech.sanroque.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sanroque.magalums.entity.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
