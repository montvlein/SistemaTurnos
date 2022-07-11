package com.dh.proyecto.Services.implement;

import com.dh.proyecto.Repository.ORM.iUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServices implements UserDetailsService {

    private iUserRepository iUserRepository;

    @Autowired
    public void setUserRepository(iUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return iUserRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User email not found") );
    }
}
