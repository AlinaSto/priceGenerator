package com.spring.priceGeneratorApp.repository;

import com.spring.priceGeneratorApp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findCountryByCountryName(String countryName);
}
