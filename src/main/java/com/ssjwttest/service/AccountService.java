package com.ssjwttest.service;

import com.ssjwttest.jwtEntities.AppRole;
import com.ssjwttest.jwtEntities.AppUser;
import com.ssjwttest.repository.AppRoleRepository;
import com.ssjwttest.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class AccountService {

    private  PasswordEncoder passwordEncoder ;
    private AppUserRepository appUserRepository ;
    private AppRoleRepository appRoleRepository ;

    public AccountService(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,
                            PasswordEncoder passwordEncoder){
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder ;
    }

    public AppUser createUser(AppUser appuser){
        String pswd = appuser.getPassword();
        appuser.setPassword(passwordEncoder.encode(pswd));
        return appUserRepository.save(appuser);
    }
    public AppRole createRole(AppRole appRole){
        return appRoleRepository.save(appRole);
    }

    public void addRoleToUser(String username, String Role){
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRolename(Role) ;
        appUser.getAppRoles().add(appRole) ;
    }

    public Collection<AppUser> getListUsers(){
        return appUserRepository.findAll() ;
    }
}
