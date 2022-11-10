package dao;

import entity.SsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SsUserRepository extends JpaRepository<SsUser, Long> {

    Optional<SsUser> findByUserName(String name);

}