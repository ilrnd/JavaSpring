package ru.expogroup.HT5.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.expogroup.HT5.entity.Persons.Person;
import ru.expogroup.HT5.repository.person_repository.PersonRepository;


import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final PersonRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Search " + username);
        Person person = repository.findByLogin(username).orElseThrow(()->
                new UsernameNotFoundException("User " + username + " not found"));
        System.out.println("Found " + person);
        return new User(person.getLogin(), person.getPassword(), List.of(
                new SimpleGrantedAuthority(person.getRole())
        ));
    }
}
