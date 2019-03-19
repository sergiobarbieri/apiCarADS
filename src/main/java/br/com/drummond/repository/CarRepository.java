package br.com.drummond.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.drummond.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
