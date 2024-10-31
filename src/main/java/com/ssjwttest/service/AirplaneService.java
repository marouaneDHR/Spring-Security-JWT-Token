package com.ssjwttest.service;

import com.ssjwttest.entities.Airplane;
import com.ssjwttest.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository ;

    public void addAirplane(Airplane airplane){
         airplaneRepository.save(airplane) ;
    }
    public List<Airplane> getAirplanes(){
        return airplaneRepository.findAll() ;
    }
}
