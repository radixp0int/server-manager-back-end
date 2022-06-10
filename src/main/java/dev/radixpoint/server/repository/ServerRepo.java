package dev.radixpoint.server.repository;

import dev.radixpoint.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> { //Give the domain and type of primary key
    Server findByIpAddress(String ipAddress);
}
