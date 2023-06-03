package com.openbootcamp.ejercicio.controllers;

import com.openbootcamp.ejercicio.entities.Laptop;
import com.openbootcamp.ejercicio.repository.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Se encarga de las solicitudes HTTP y de sus formatos
public class LaptopController {
    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/findall")
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    @GetMapping("find{id}")
    //ResponseEntity representa toda la respuesta HTTP
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {//Pathvariable se usa para que coincida
        // el dato con el endpoint
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);//Optional nos ayuda a no generar un valor nulo
        if (laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get()); //Respuesta http
        else return ResponseEntity.notFound().build(); //Respuesta http
    }

    @PostMapping("/create")
    public Laptop create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        return laptopRepository.save(laptop);
    }

    @PutMapping("/update{id}")
    public ResponseEntity<Laptop> update(@PathVariable Long id, @RequestBody Laptop updateLaptop) {
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if (laptopOpt.isPresent()) {
            Laptop laptop = laptopOpt.get();
            Laptop updatedLaptop = laptopRepository.save(laptop);
            return ResponseEntity.ok(updatedLaptop);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Laptop> laptop = laptopRepository.findById(id);
        if (laptop.isPresent()) {
            laptopRepository.deleteById(id);
            return ResponseEntity.ok("Eliminado con exito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        laptopRepository.deleteAll();
        return ResponseEntity.ok("Todos los laptops se eliminaron");
    }
}


