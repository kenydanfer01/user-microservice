package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByDni(String dni);

    Optional<UserEntity> findByMail(String dni);

    Optional<UserEntity> findByIdAndRoleEntityId(Long id, Long idRole);

    List<UserEntity> findAllByRoleEntityId(Long idRole, Pageable pageable);

    Boolean existsByDni (String dni);

    Boolean existsByMail (String dni);

    void deleteById(Long id);

}
