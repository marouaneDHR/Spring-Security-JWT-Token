    package com.ssjwttest.Controller;

    import com.ssjwttest.entities.Airplane;
    import com.ssjwttest.entities.Airport;
    import com.ssjwttest.jwtEntities.AppUser;
    import com.ssjwttest.jwtEntities.AuthRequestDTO;
    import com.ssjwttest.jwtEntities.JwtResponseDTO;
    import com.ssjwttest.repository.AirportRepository;
    import com.ssjwttest.repository.AppUserRepository;
    import com.ssjwttest.service.AccountService;
    import com.ssjwttest.service.AirplaneService;
    import com.ssjwttest.service.AuthenticationService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.Collection;

    @RestController
    @RequestMapping(value = "", produces = "application/json")
    @RequiredArgsConstructor
    @CrossOrigin(origins = "*")
    public class AccountServiceController {

        @Autowired
        private AccountService accountService ;

        @Autowired
        private AppUserRepository appUserRepository ;

        @Autowired
        private AirportRepository airportRepository ;

        @Autowired
        private AuthenticationService authenticationService ;
        @Autowired
        private AirplaneService airplaneService ;

        @PostMapping("/login")
        public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
            return authenticationService.login(authRequestDTO) ;
        }

        @GetMapping("/")
        public  String Home(){
            return "Hello World" ;
        }

        @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
        @GetMapping("/user")
        public String User(){
                return "Hello User";
        }

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/admin")
        public  Collection<AppUser> Users(){
            return accountService.getListUsers() ;
        }

        @PostMapping("/test")
        public String testposting(@RequestBody AppUser user){
            appUserRepository.save(user) ;
            return "{\"message\":\"Done\"}" ;
        }

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/airports/coordinates")
        public  Collection<Airport> AirportsCoordinate(){
            return airportRepository.findAll() ;
        }

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/airplanes/infos")
        public  Collection<Airplane> AirplanesInfo(){
            return airplaneService.getAirplanes();
        }

    }
