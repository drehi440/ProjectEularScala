package com.neilson.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamJava {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		System.out.println("THIS IS WORKING!");
		
		Function<String, Person> mapToPerson = (line) -> {
			  String[] p = line.split(", ");
			  return new Person(p[0], Integer.parseInt(p[1]), p[2], p[3]);
			};
		
		InputStream is = new FileInputStream(new File("working.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		
		Stream<Person> persons = br.lines()
							   .skip(1)
							   .map(mapToPerson)
							   .filter(person -> person.getAge() > 17);
				
				//				br.lines().skip(1).map(mapToPerson).filter(person -> person.getAge() > 17)
//			    .limit(50);
			    
				
		
		
		
		
		
		
	} 
	
	
	
}


