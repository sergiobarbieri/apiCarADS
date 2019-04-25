package br.com.drummond.resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.drummond.domain.Car;
import br.com.drummond.repository.CarRepository;

@RestController
public class CarAPI {
	
	@Autowired
	CarRepository carRepository;
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String test() {
		
		return "Sistema Car ativo - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		// classes: SimpleDateFormat e Date
	}
	
	/**
	 * Retorna todos o carros cadastrados
	 * @return
	 */
	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	public ResponseEntity<List<Car>> obterCar() {
		
		List<Car> cars; // Classe List -> define um array de objetos da classe Car
		
		cars = carRepository.findAll();
		if (cars.isEmpty())
			return new ResponseEntity<List<Car>>(cars, HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}
	
	/**
	 * Obter Car pelo id (chave primaria - PK)
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obterCarId(@PathVariable Integer id) {
		
		Optional<Car> car; // Optional: container objeto.
		
		car = carRepository.findById(id);
		
		if (car.isPresent())
			return new ResponseEntity<Car>(car.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Car>(car.get(), HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Inserir um novo carro
	 * @param car
	 * @return
	 */
	@RequestMapping(value = "/cars", method = RequestMethod.POST)
	public ResponseEntity<Car> inserirCarId(@RequestBody Car car) {
		
		Optional<Car> carRepo; 
		
		carRepo = carRepository.findById(car.getCarId());
		
		if (carRepo.isPresent())
			return new ResponseEntity<Car>(carRepo.get(), HttpStatus.CONFLICT);
						
		carRepository.save(car);
		
		return new ResponseEntity<Car>(car, HttpStatus.CREATED);
	}
	
	/**
	 * Alterar um carro existente
	 * @param car
	 * @return
	 */
	@RequestMapping(value = "/cars/{i}", method = RequestMethod.PUT)
	public ResponseEntity<Car> updateCarId(@RequestBody Car carPost, @PathVariable Integer id) {
		
		Optional<Car> carRepo; 
		
		carRepo = carRepository.findById(id);
		
		if (!carRepo.isPresent())
			return new ResponseEntity<Car>(carPost, HttpStatus.NO_CONTENT);
		
		if (carPost.getModel() != null)
			carRepo.get().setModel(carPost.getModel());
		if (carPost.getYear() != null)
			carRepo.get().setYear(carPost.getYear());
						
		carRepository.save(carRepo.get());
		
		return new ResponseEntity<Car>(carRepo.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cars/{i}", method = RequestMethod.DELETE)
	public ResponseEntity<Car> deleteCarId(@RequestBody Car carPost, @PathVariable Integer id) {
		
		Optional<Car> carRepo; 
		
		carRepo = carRepository.findById(id);
		
		if (!carRepo.isPresent())
			return new ResponseEntity<Car>(carPost, HttpStatus.NO_CONTENT);
	
		carRepository.deleteById(id);
		
		return new ResponseEntity<Car>(carRepo.get(), HttpStatus.OK);
	}

}
