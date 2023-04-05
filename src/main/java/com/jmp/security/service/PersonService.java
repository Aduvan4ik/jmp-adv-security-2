package com.jmp.security.service;

import com.jmp.security.entity.Person;

public interface PersonService {
    Person getUserByEmail(String email);

}