
package com.example.demo.repository;

import com.example.demo.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
    Country findFirstById(Integer id);

    @Override
    List<Country> findAll();

    @Override
    void deleteById(Integer integer);
}
