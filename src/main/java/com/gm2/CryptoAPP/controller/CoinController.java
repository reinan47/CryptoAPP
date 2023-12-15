package com.gm2.CryptoAPP.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gm2.CryptoAPP.dto.CoinTransationDTO;
import com.gm2.CryptoAPP.entity.Coin;
import com.gm2.CryptoAPP.repository.CoinRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RequestMapping("/coin")
@RestController
public class CoinController {
	
	@Autowired
	private CoinRepository coinRepository;
	
    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Coin coin){
        try{
            coin.setDatetime(new Timestamp(System.currentTimeMillis()));
            return  new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping()
    public ResponseEntity<?> get() {
    	try {
    		return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
    	}
    	catch (Exception error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    }
    
    @GetMapping("/{name}")
    public ResponseEntity<?> get(@PathVariable String name) {
    	try {
    		return new ResponseEntity<>(coinRepository.getByName(name),HttpStatus.OK);
    	}
    	catch (Exception error) {
			return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @Bean
    Coin init() {
    	Coin c1 = new Coin();
    	c1.setName("BitCoin");
    	c1.setPrice(new BigDecimal(0.1111));
    	c1.setQuantity(new BigDecimal(200));
    	c1.setDatetime(new Timestamp(System.currentTimeMillis()));
    	
    	Coin c2 = new Coin();
    	c2.setName("BitCoin");
    	c2.setPrice(new BigDecimal(0.1111));
    	c2.setQuantity(new BigDecimal(200));
    	c2.setDatetime(new Timestamp(System.currentTimeMillis()));
    	
    	Coin c3 = new Coin();
    	c3.setName("ethereum");
    	c3.setPrice(new BigDecimal(0.1111));
    	c3.setQuantity(new BigDecimal(200));
    	c3.setDatetime(new Timestamp(System.currentTimeMillis()));
    	
    	coinRepository.insert(c1);
    	coinRepository.insert(c2);
    	coinRepository.insert(c3);
    	
    	return c1;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
    	try {
			return new ResponseEntity<>(coinRepository.remove(id), HttpStatus.OK);
		} catch (Exception error) {
			return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PutMapping()
    public ResponseEntity put(@RequestBody Coin coin) {
    	try {
    		coin.setDatetime(new Timestamp(System.currentTimeMillis()));
			return new ResponseEntity<>(coinRepository.update(coin),HttpStatus.OK);
		} catch (Exception error) {
			return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
		}
    }
    
    

	
}
