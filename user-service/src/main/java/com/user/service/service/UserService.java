package com.user.service.service;

import com.user.service.entity.User;
import com.user.service.feignclients.CarroFeignClient;
import com.user.service.feignclients.MotofeignClient;
import com.user.service.models.Car;
import com.user.service.models.Moto;
import com.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotofeignClient motofeignClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;
//resttemplate
    public List<Car> getCarros(int usuarioId){
        List<Car> carros = restTemplate.getForObject("http://localhost:8002/api/car/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/api/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

    //feignclient
    public Car saveCarro(int usuarioId, Car car){
        car.setUsuarioId(usuarioId);
        Car nuevoCarro = carroFeignClient.save(car);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motofeignClient.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        User usuario = userRepository.findById(usuarioId).orElse(null);

        if(usuario == null){
            resultado.put("Mensaje", "El usuario no existe");
        }

        resultado.put("usuario", usuario);

        List<Car> carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()){
            resultado.put("Carros", "El usuario no tien carros");
        }else{
            resultado.put("Carros", carros);
        }

        List<Moto> motos = motofeignClient.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("Motos", "El usuario no tien motos");
        }else{
            resultado.put("Motos", motos);
        }

        return resultado;
    }


    //normal methods
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
