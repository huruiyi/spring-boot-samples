package com.example.demo.contoller;

import com.example.demo.entity.Country;
import com.example.demo.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Country> save(@RequestBody Country request) throws RuntimeException {
        return new ResponseEntity<>(countryService.save(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Country>> findAllCountries() throws RuntimeException {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<Country> findById(@RequestParam Integer id) throws RuntimeException {
        return new ResponseEntity<>(countryService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/deleteById")
    public ResponseEntity<List<Country>> deleteById(@RequestParam Integer id) throws RuntimeException {
        return new ResponseEntity<>(countryService.delete(id), HttpStatus.OK);
    }
}
