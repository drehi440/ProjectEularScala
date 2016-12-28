package com.neilson.read;

import java.lang.reflect.GenericArrayType;

import com.neilsen.dto.BarCodeDto;
import com.neilson.read.GenricDao;


public class DataProcessor1 {
	public static void main(String[] args) {
		

	    final DataProcessor1 read = new DataProcessor1();
	    GenricDao genricdao = new GenricDao();
	    final EventTimeWatch logger = new EventTimeWatch();
	    logger.start("Start Processor:");
	    read.load(genricdao);
	    
	}
	
	
	
	public void load(final GenricDao genricDao){
		
		BarCodeDto.loadBarcodeData(genricDao);
		
	}
}
