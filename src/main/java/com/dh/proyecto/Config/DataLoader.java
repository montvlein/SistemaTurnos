package com.dh.proyecto.Config;

import com.dh.proyecto.Models.entities.AppUser;
import com.dh.proyecto.Models.entities.AppUserioRoles;
import com.dh.proyecto.Repository.ORM.iUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private iUserRepository repository;

    @Autowired
    public void setRepository(iUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("admin");
        String password_user = passwordEncoder.encode("test");

        repository.save(new AppUser("admin", password, AppUserioRoles.ADMIN));
        repository.save(new AppUser("test", password_user, AppUserioRoles.USER));
    }
}
