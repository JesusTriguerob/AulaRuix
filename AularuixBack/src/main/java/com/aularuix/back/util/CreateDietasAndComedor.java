package com.aularuix.back.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aularuix.back.entity.Comedor;
import com.aularuix.back.entity.Dieta;
import com.aularuix.back.service.ComedorService;
import com.aularuix.back.service.DietaService;

@Component
public class CreateDietasAndComedor implements CommandLineRunner {

	@Autowired
	ComedorService comedorService;
	
	@Autowired
	DietaService dietaService;
	
	@Override
	public void run(String... args) throws Exception {
//		Comedor comedor = new Comedor();
//		
//		comedorService.save(comedor);
		
	}

}
