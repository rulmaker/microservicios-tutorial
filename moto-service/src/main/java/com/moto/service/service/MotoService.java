package com.moto.service.service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }

    public Moto save(Moto moto){
        return motoRepository.save(moto);
    }

    public List<Moto> byUserId(int userId){
        return motoRepository.findByUsuarioId(userId);
    }
}
