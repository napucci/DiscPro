package com.capstone.discgolfstore.controllers;

import com.capstone.discgolfstore.entities.Disc;
import com.capstone.discgolfstore.services.DiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discs")
public class DiscController {

    @Autowired
    private DiscService discService;

    @GetMapping
    public List<Disc> getDiscCatalog(){
       return discService.getAllDiscs();
    }


}
