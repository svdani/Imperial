package com.imperial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.imperial.dao.ImperialDAO;
import com.imperial.entity.Rebel;

@RestController
@RequestMapping("/api")
public class ImperialController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImperialController.class);
	
    @Autowired
    private ImperialDAO imperialDAO;

    @GetMapping
    public  ResponseEntity<List<Rebel>> getRebel() {
    	try {
    		LOGGER.info("Start ImperialController getRebel()");
            List<Rebel> rebels = imperialDAO.findAll();
            LOGGER.info("End ImperialController getRebel()");
            return ResponseEntity.ok(rebels);
            
		} catch (Exception e) {
			LOGGER.error("Error ImperialController getRebel()");
			return ResponseEntity.noContent().build();
		
		}       
    }
    
    @RequestMapping(value="{id}")
    public ResponseEntity<Rebel> getRebelById(@PathVariable("id") int id) {
    	try {
    		LOGGER.info("Start ImperialController getRebelById()");
    		Optional<Rebel> optionalRebels = imperialDAO.findById(id);
    		
            if(optionalRebels.isPresent()){
            	LOGGER.info("End ImperialController getRebelById()");
            	return ResponseEntity.ok(optionalRebels.get());
            	
            } else {
            	LOGGER.warn("Id not found, End ImperialController getRebelById()");
            	return ResponseEntity.noContent().build();
            	
            }            
		} catch (Exception e) {
			LOGGER.error("Error ImperialController getRebelById()");
			return ResponseEntity.noContent().build();
			
		}
    }
    
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public void handleMethodArgumentTypeMismatch(IllegalArgumentException e, HttpServletResponse response) throws IOException {
    	try {
    		LOGGER.error("Error The value entered is not an Integer!");
        	response.resetBuffer();
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	response.setHeader("Content-Type", "application/json");
        	response.getOutputStream().print("{\"errorMessage\":\"The value entered is not an Integer!\"}");
		} catch (Exception e2) {
			LOGGER.error("Error handleMethodArgumentTypeMismatch!");
		}
    	
     }
    
    @PostMapping
    public ResponseEntity<Rebel> createRebel(@RequestBody Rebel rebel){
    	try {
    		LOGGER.info("Start ImperialController createRebel()");
        	Rebel newRebel = imperialDAO.save(rebel);
        	LOGGER.info("End ImperialController createRebel()");
        	return ResponseEntity.ok(newRebel);
        	
		} catch (Exception e) {
			LOGGER.error("Error ImperialController createRebel()");
			return ResponseEntity.noContent().build();
		
		} 	
    }

}
