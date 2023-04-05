package com.jmp.security.service.security;

import com.jmp.security.entity.Person;
import com.jmp.security.service.PersonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonService personService;

    public CustomUserDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.getUserByEmail(username);

        String[] authorities = person.getAuthorities().stream()
                .map(authorityEntity -> authorityEntity.getName().name())
                .toArray(String[]::new);

        return withUsername(person.getEmail())
                .password(person.getPassword())
                .authorities(authorities).build();
    }
}
