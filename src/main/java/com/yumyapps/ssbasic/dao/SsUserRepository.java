package com.yumyapps.ssbasic.dao;

import com.yumyapps.ssbasic.entity.SsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SsUserRepository extends JpaRepository<SsUser, Long> {

    Optional<SsUser> findByUserName(String name);

}