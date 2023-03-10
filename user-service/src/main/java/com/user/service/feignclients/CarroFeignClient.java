package com.user.service.feignclients;

import com.user.service.models.Car;
import com.user.service.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarroFeignClient {
    @PostMapping
    public Car save(@RequestBody Car car);

    @GetMapping("/usuario/{usuarioId}")
    public List<Car> getCarros(@PathVariable("usuarioId") int usuarioId);
}
