package com.moto.service.controller;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moto")
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> listMotos(){
        List<Moto> bikes = motoService.getAll();

        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMoto(@PathVariable("id") int id){
        Moto moto = motoService.getMotoById(id);
        if(moto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto){
        Moto newMoto = motoService.save(moto);
        return ResponseEntity.ok(newMoto);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Moto>> listBikesByUserId(@PathVariable("userId") int id){
        List<Moto> bikes = motoService.byUserId(id);
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }
}
