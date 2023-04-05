package com.jmp.security.repository;


import com.jmp.security.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmailIgnoreCase(String email);

}
