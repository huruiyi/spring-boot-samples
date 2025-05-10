package com.example.service.impl;

import com.example.entity.Country;
import com.example.repository.CountryRepository;
import com.example.service.ICountryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements ICountryService {

  private static final String REDIS_CACHE_VALUE = "country";

  @Resource
  private CountryRepository countryRepository;

  @CachePut(value = REDIS_CACHE_VALUE, key = "#country.id")
  public Country save(Country country) {
    return countryRepository.save(country);
  }

  @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
  public List<Country> delete(Integer id) {
    countryRepository.deleteById(id);
    return countryRepository.findAll();
  }

  @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
  public Country findById(Integer id) {
    return countryRepository.findFirstById(id);
  }

  public List<Country> findAll() {
    return countryRepository.findAll();
  }

}
