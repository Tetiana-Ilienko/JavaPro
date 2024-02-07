package de.aittr.g_31_2_security.controllers;


import de.aittr.g_31_2_security.domain.Car;
import de.aittr.g_31_2_security.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService service;

    public CarController(CarService service) {
        this.service = service;
    }
@GetMapping("/all")
    public List<Car> getAll(){
        return  service.getAll();

    }

    @GetMapping("/by_id/{id}")
    public Car getById(@PathVariable int id){
        return service.getBiId(id);
    }
    @PostMapping("/save")
    public Car save(@RequestBody Car car){
        return service.save(car);
    }
}
