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
	
	class Person{
		String Name;
		int Age;
		String City;
		String Country;
		
		public Person(String Name, int Age, String City,
				String Country) {
			this.Name=Name;
			this.Age= Age;
			this.City=City;
			this.Country=Country;
				
			
			
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public int getAge() {
			return Age;
		}

		public void setAge(int age) {
			Age = age;
		}

		public String getCity() {
			return City;
		}

		public void setCity(String city) {
			City = city;
		}

		public String getCountry() {
			return Country;
		}

		public void setCountry(String country) {
			Country = country;
		}

	}
	
}
