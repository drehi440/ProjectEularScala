package com.nielsen.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class FilterDeserializer extends JsonDeserializer<Filter> {
	@Override
	public Filter deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode node1 = parser.getCodec().readTree(parser);
		JsonNode node = node1.get(0);
		ObjectMapper mapper = new ObjectMapper();
		Filter filter = new Filter();
		
		//Code change start here!
		switchCondition(node, mapper);
		
		
		
		

		// int id = (Integer) ((IntNode) node.get("id")).numberValue();

		return filter;
	}

	public Filter switchCondition(JsonNode node,ObjectMapper mapper) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
		Filter filter = new Filter();
		
		if (node.has(Operations.AND.getValue().trim())) {
//			recursiveCheck(Operations.OR.getValue().trim(), node, filter, mapper);
			
			System.out.println("[ OPERATION FOUND : ]   " + Operations.AND.getValue().trim());
			List<And> and = mapper.readValue(mapper.writeValueAsString(node.get(Operations.AND.getValue().trim())),
					new com.fasterxml.jackson.core.type.TypeReference<List<And>>() {
					});
			switchCondition(node, mapper);
			
			filter.setAnd(and);//filter.setAnd(or(and,and,and))
		}
		if (node.has(Operations.OR.getValue().trim())) {

//			recursiveCheck(Operations.AND.getValue().trim(), node, filter, mapper);
			System.out.println("[ OPERATION FOUND : ]   " + Operations.OR.getValue().trim());
			List<Or> or = mapper.readValue(mapper.writeValueAsString(node.get(Operations.OR.getValue().trim())),
					new com.fasterxml.jackson.core.type.TypeReference<List<Or>>() {
					});
			switchCondition(node, mapper);
			filter.setOr(or);
		}
		if (node.has(Operations.EQUAL.getValue().trim())) {
			System.out.println("[ OPERATION FOUND : ]   " + Operations.EQUAL.getValue().trim());
			String[] eq = mapper.readValue(mapper.writeValueAsString(node.get(Operations.EQUAL.getValue().trim())),
					new com.fasterxml.jackson.core.type.TypeReference<String[]>() {
					});
			switchCondition(node, mapper);
			filter.setEq(eq);
		}
		if (node.has(Operations.GEQ.getValue().trim())) {
			System.out.println("[ OPERATION FOUND : ]   " + Operations.GEQ.getValue().trim());
			String[] geq = mapper.readValue(mapper.writeValueAsString(node.get(Operations.GEQ.getValue().trim())),
					new com.fasterxml.jackson.core.type.TypeReference<String[]>() {
					});
			switchCondition(node, mapper);
			filter.setGeq(geq);
		}
		if (node.has(Operations.DIV.getValue().trim())) {
			System.out.println("[ OPERATION FOUND : ]   " + Operations.DIV.getValue().trim());
			String[] div = mapper.readValue(mapper.writeValueAsString(node.get(Operations.DIV.getValue().trim())),
					new com.fasterxml.jackson.core.type.TypeReference<String[]>() {
					});
			switchCondition(node, mapper);
			filter.setDiv(div);
		}
		if(node.has(Operations.IN.getValue().trim())){
			 In in = new In();
		 		Map<String, Object> map = mapper.readValue(mapper.writeValueAsString(node.get(Operations.IN.getValue().trim())), Map.class);
		 		if(map !=null && !map.isEmpty())
		 		for (Map.Entry<String, Object> entry : map.entrySet()) {
		 			System.out.println(" :::   IN Key  ::::" + entry.getKey() );
		 			System.out.println(" :::   IN Value  ::::" + entry.getValue() );
		 			ArrayList<Object> value = (ArrayList<Object>) entry.getValue();
		 			if(value != null && !value.isEmpty()){
		 				String queryString = entry.getKey() + " in(";
		 			
		 				for (Iterator iterator = value.iterator(); iterator.hasNext();) {
		 					Object object = (Object) iterator.next();
		 					queryString += object + ",";
		 				}
		 				queryString  = queryString.substring(0,queryString.lastIndexOf(","));
		 				queryString  += ")";
		 				//queryString = queryString+")";
		 				in.setQueryString(queryString);
		 			}
		 		}
		 		switchCondition(node, mapper);
		 		filter.setIn(in);
		}
		if (node.has(Operations.SUM.getValue().trim())) {
			System.out.println("[ OPERATION FOUND : ]   " + Operations.SUM.getValue().trim());
			String[] sum = mapper.readValue(mapper.writeValueAsString(node.get(Operations.SUM.getValue().trim())),
					new com.fasterxml.jackson.core.type.TypeReference<String[]>() {});
			filter.setSum(sum);
		}
		if (node.has(Operations.MAX.getValue().trim())) {
			System.out.println("[ OPERATION FOUND : ]   " + Operations.MAX.getValue().trim());
			String[] max = mapper.readValue(mapper.writeValueAsString(node.get(Operations.MAX.getValue().trim())),
					new com.fasterxml.jackson.core.type.TypeReference<String[]>() {});
			switchCondition(node, mapper);
			filter.setMax(max);
		}
		
		return filter;
	}

}
	
