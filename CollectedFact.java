package com.neilson.Csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CollectedFact {

	private static final String inputFileName = "D:\\Nielsen\\Data\\CollectedFact.csv";
	private static final String outputFileName = "D:\\Nielsen\\Data\\Output\\CollectedFact.csv";
	private static final String outputFileName1 = "D:\\Nielsen\\Data\\Output\\CollectedFact1.csv";

	private static List<CollectedFact> collectedFactList = null;

	private static Map<Integer, CollectedFact> collectedFactMap = new HashMap<Integer, CollectedFact>();

	private static Map<Integer, Map<Object, Object>> collectedFactGroup = new HashMap<Integer, Map<Object, Object>>();
	private static Map<Integer, Integer> totalByEntity = new HashMap<Integer, Integer>();

	private static Map<Integer, List<CollectedFact>> groupByEntity = new HashMap<Integer, List<CollectedFact>>();
	private static Map<Integer, List<CollectedFact>> sumValueCollectedMap = new HashMap<Integer, List<CollectedFact>>();
	private static Map<Integer, List<CollectedFact>> groupByEntityReducedMap = new HashMap<Integer, List<CollectedFact>>(); 
	private static Map<Integer, List<CollectedFact>> finalMerged = new HashMap<Integer, List<CollectedFact>>();
	
	private Integer audit_Id;
	private Integer newShop_Id;
	private Integer exhibition_Id;
	private Integer instance_Id;
	private Integer position_Id;
	private Integer location_Id;
	private Integer fact_Id;
	private Integer collectionLevel;
	private Integer entity_Id;
	private String save_Id;
	private String value;
	private String comment;
	private Integer auditor_Id;
	private String timestamp;

	// add elements to stream
	@SuppressWarnings("unchecked")
	public static <CollectedFact> Stream<CollectedFact> addToStream(
			Stream<CollectedFact> stream, Stream<CollectedFact> stream2) {
		List<CollectedFact> result = stream.collect(Collectors.toList()); // error
		result.addAll(stream2.collect(Collectors.toList()));
//		result.forEach(System.out::println);
		
		
		//code to save to an Output File
		  List<String> strList = result.stream().distinct().map(Object::toString)
				.collect(Collectors.toList());

		try {
			Files.write(Paths.get(outputFileName),
					(Iterable<String>) strList.stream()::iterator);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.stream();
	}

	public static void createCollectedFactMap() {

		System.out.println("THIS IS ====================================> CollectedFactMap Running!");
		
		
		Function<String, CollectedFact> mapToCollectedFact = (line) -> {
			String[] p = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			// System.out.println(p[0]+"********************************"+p[1]+p[2]);

			return new CollectedFact(Integer.parseInt(p[0]),
					Integer.parseInt(p[1]), Integer.parseInt(p[2]),
					Integer.parseInt(p[3]), Integer.parseInt(p[4]),
					Integer.parseInt(p[5]), Integer.parseInt(p[6]),
					Integer.parseInt(p[7]), Integer.parseInt(p[8]), p[9],
					p[10], p[11], Integer.parseInt(p[12]), p[13]);
		};
		
		Function<CollectedFact, CollectedFact> mapToNewFactSum = (collectedFactStream) -> {
			
//			collectedFactStream.setValue(totalByEntity.get(collectedFactStream.entity_Id)+"");
			System.out.println(collectedFactStream.entity_Id);
			return new CollectedFact(collectedFactStream.getAudit_Id(),collectedFactStream.getNewShop_Id(),collectedFactStream.getExhibition_Id()
					,collectedFactStream.getInstance_Id(),collectedFactStream.getPosition_Id()
					,collectedFactStream.getLocation_Id(),9211,collectedFactStream.getCollectionLevel(),collectedFactStream.getEntity_Id()
					,collectedFactStream.getSave_Id(),/*collectedFactStream.getValue()*/totalByEntity.get(collectedFactStream.entity_Id)+""
					,collectedFactStream.getComment(),collectedFactStream.getAuditor_Id(),collectedFactStream.getTimestamp());
		
		};

		InputStream is = null;
		BufferedReader br = null;

		try {
			is = new FileInputStream(new File(inputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(new InputStreamReader(is));

		// main List:----->
		collectedFactList = br.lines()
							.skip(1)
							.map(mapToCollectedFact)
							.collect(Collectors.toList());

		
		//Sum of Integer Values group by EntityID
		totalByEntity = collectedFactList
								.stream()
								.filter(collectedFact -> 
											collectedFact.getFact_Id() == 1 || collectedFact.getFact_Id() == 5
										)
								.collect(
										Collectors.groupingBy(
												CollectedFact::getEntity_Id,
												Collectors.summingInt(
																CollectedFact::getIntValue
																)
															)
										);
		
		//print totalByEntity
		for (Map.Entry<Integer, Integer> entrytotalByEntity : totalByEntity.entrySet()) {
			System.out.println(" Entity Id " + entrytotalByEntity.getKey()
					+ "        |        Value : " + entrytotalByEntity.getValue());
		}

		
		//Map with groupByEntity as Key and Complete row as Value
		groupByEntity = collectedFactList
				.stream()
				.collect(
						Collectors.groupingBy(
								CollectedFact::getEntity_Id));


		groupByEntityReducedMap = collectedFactList
				.stream()
				.filter(collectedFact -> 
				(collectedFact.getFact_Id() == 1 || collectedFact.getFact_Id() == 5)
						)
				.map(mapToNewFactSum)
				.collect(Collectors.groupingBy(CollectedFact::getEntity_Id));
		
		
		
		//Map with EntityReduceMap
		for (Map.Entry<Integer, List<CollectedFact>> entry6 : groupByEntityReducedMap.entrySet()) {
//				System.out.println("Inside");
//				System.out.println(" Entity Id " + entry6.getKey()
//				+ "        |        Value : " + entry6.getValue());
			}
		

		
		sumValueCollectedMap = collectedFactList
				.stream().filter(collectedFact -> 
				(collectedFact.getFact_Id() == 1 || collectedFact.getFact_Id() == 5)
			).map(CollectedFact -> {
							CollectedFact.setFact_Id(9211); 
							CollectedFact.setValue(totalByEntity.get(CollectedFact.entity_Id)+""); 
							return CollectedFact;
							})
				.collect(
						Collectors.groupingBy(
									CollectedFact::getEntity_Id
									)
						);
		
		
		
		addToStream(groupByEntity.entrySet().stream(), sumValueCollectedMap.entrySet().stream());



			

		System.out.println("=====================================  ProcessedMap Ends!=====================================================");
		
			System.out
				.println("THIS IS ====================================> CollectedFactMap Finished!");
	
	
	
	}


	public static Map<Integer, Map<Object, Object>> getCollectedFactGroup() {
		return collectedFactGroup;
	}

	public static void setCollectedFactGroup(
			Map<Integer, Map<Object, Object>> collectedFactGroup) {
		CollectedFact.collectedFactGroup = collectedFactGroup;
	}

	public static List<CollectedFact> getCollectedFactList() {
		return collectedFactList;
	}

	public static void setCollectedFactList(
			List<CollectedFact> collectedFactList) {
		CollectedFact.collectedFactList = collectedFactList;
	}

	public static Map<Integer, CollectedFact> getCollectedFactMap() {
		return collectedFactMap;
	}

	public static void setCollectedFactMap(
			Map<Integer, CollectedFact> collectedFactMap) {
		CollectedFact.collectedFactMap = collectedFactMap;
	}

	@Override
	public String toString() {

		return (new StringBuffer().append(audit_Id).append(",")
				.append(newShop_Id).append(",").append(exhibition_Id)
				.append(",").append(instance_Id).append(",")
				.append(position_Id).append(",").append(location_Id)
				.append(",").append(fact_Id).append(",")
				.append(collectionLevel).append(",").append(entity_Id)
				.append(",").append(save_Id).append(",").append(value)
				.append(",").append(comment).append(",").append(auditor_Id)
				.append(",").append(timestamp)).toString();

	}

	public CollectedFact(Integer audit_Id, Integer newShop_Id,
			Integer exhibition_Id, Integer instance_Id, Integer position_Id,
			Integer location_Id, Integer fact_Id, Integer collectionLevel,
			Integer entity_Id, String save_Id, String value, String comment,
			Integer auditor_Id, String timestamp) {
		super();
		this.audit_Id = audit_Id;
		this.newShop_Id = newShop_Id;
		this.exhibition_Id = exhibition_Id;
		this.instance_Id = instance_Id;
		this.position_Id = position_Id;
		this.location_Id = location_Id;
		this.fact_Id = fact_Id;
		this.collectionLevel = collectionLevel;
		this.entity_Id = entity_Id;
		this.save_Id = save_Id;
		this.value = value;
		this.comment = comment;
		this.auditor_Id = auditor_Id;
		this.timestamp = timestamp;
	}

	public CollectedFact() {
		// TODO Auto-generated constructor stub
	}

	public Integer getAudit_Id() {
		return audit_Id;
	}

	public void setAudit_Id(Integer audit_Id) {
		this.audit_Id = audit_Id;
	}

	public Integer getNewShop_Id() {
		return newShop_Id;
	}

	public Integer getIntValue() {

		return Integer.parseInt(value);

	}

	public void setNewShop_Id(Integer newShop_Id) {
		this.newShop_Id = newShop_Id;
	}

	public Integer getExhibition_Id() {
		return exhibition_Id;
	}

	public void setExhibition_Id(Integer exhibition_Id) {
		this.exhibition_Id = exhibition_Id;
	}

	public Integer getInstance_Id() {
		return instance_Id;
	}

	public void setInstance_Id(Integer instance_Id) {
		this.instance_Id = instance_Id;
	}

	public Integer getPosition_Id() {
		return position_Id;
	}

	public void setPosition_Id(Integer position_Id) {
		this.position_Id = position_Id;
	}

	public Integer getLocation_Id() {
		return location_Id;
	}

	public void setLocation_Id(Integer location_Id) {
		this.location_Id = location_Id;
	}

	public Integer getFact_Id() {
		return fact_Id;
	}

	public void setFact_Id(Integer fact_Id) {
		this.fact_Id = fact_Id;
	}

	public Integer getCollectionLevel() {
		return collectionLevel;
	}

	public void setCollectionLevel(Integer collectionLevel) {
		this.collectionLevel = collectionLevel;
	}

	public Integer getEntity_Id() {
		return entity_Id;
	}

	public void setEntity_Id(Integer entity_Id) {
		this.entity_Id = entity_Id;
	}

	public String getSave_Id() {
		return save_Id;
	}

	public void setSave_Id(String save_Id) {
		this.save_Id = save_Id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getAuditor_Id() {
		return auditor_Id;
	}

	public void setAuditor_Id(Integer auditor_Id) {
		this.auditor_Id = auditor_Id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public static String getInputfilename() {
		return inputFileName;
	}

	public static void generateCollectedFactFile() {
		List<String> strList = groupByEntity.entrySet().stream().map(Object::toString)
				.collect(Collectors.toList());

		try {
			Files.write(Paths.get(outputFileName),
					(Iterable<String>) strList.stream()::iterator);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
