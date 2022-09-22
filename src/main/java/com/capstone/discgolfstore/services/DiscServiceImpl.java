package com.capstone.discgolfstore.services;

import com.capstone.discgolfstore.dtos.DiscDto;
import com.capstone.discgolfstore.entities.Disc;
import com.capstone.discgolfstore.repositories.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DiscServiceImpl implements DiscService {

    @Autowired
    private DiscRepository discRepository;


    @Override
    public List<Disc> getAllDiscs(){
        return discRepository.findAll();
    }


}
