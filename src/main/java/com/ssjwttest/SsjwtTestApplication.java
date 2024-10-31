
package com.ssjwttest;

import com.ssjwttest.entities.Airplane;
import com.ssjwttest.entities.Airport;
import com.ssjwttest.entities.Coordinate;
import com.ssjwttest.jwtEntities.AppRole;
import com.ssjwttest.jwtEntities.AppUser;
import com.ssjwttest.repository.AirportRepository;
import com.ssjwttest.service.AccountService;
import com.ssjwttest.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SsjwtTestApplication {

    @Autowired
    private  PasswordEncoder passwordEncoder ;

    public static void main(String[] args) {
        SpringApplication.run(SsjwtTestApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountService accountService, AirplaneService airplaneService,
                                        AirportRepository airportRepository){
        return (args->{
            accountService.createUser(new AppUser(null, "Marouane", "2001", new ArrayList<>()));
            accountService.createUser(new AppUser(null, "Mahdi", "2006", new ArrayList<>()));

            accountService.createRole(new AppRole(null,"ROLE_USER"));
            accountService.createRole(new AppRole(null,"ROLE_ADMIN"));
            accountService.createRole(new AppRole(null,"ROLE_MANAGER"));

            accountService.addRoleToUser("Marouane", "ROLE_USER");
            accountService.addRoleToUser("Mahdi", "ROLE_USER");
            accountService.addRoleToUser("Mahdi", "ROLE_ADMIN");

            airportRepository.save(new Airport(null, "USA", new Coordinate(39.368,-94.914)));
            airportRepository.save(new Airport(null, "MOROCCO", new Coordinate(31.949,-4.401)));
            airportRepository.save(new Airport(null, "SPAIN", new Coordinate(28.626,-17.756)));
            //airportRepository.save(new Airport(null, "NETHERLANDS", new Coordinate(52.309,4.764)));
            airportRepository.save(new Airport(null, "CHINA", new Coordinate(40.08,116.584)));

            airplaneService.addAirplane(new Airplane(null,"ShortAirplane", Airplane.TYPE.SHORT, 2000, 9331,2745 ));
            airplaneService.addAirplane(new Airplane(null,"MediumAirplane", Airplane.TYPE.MEDIUM, 3100, 30030, 6850));
            airplaneService.addAirplane(new Airplane(null,"LongAirplane", Airplane.TYPE.LONG, 12100, 242470, 14320));
        }) ;
    }
}
