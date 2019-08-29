package com.heightmap.storages;

import ch.qos.logback.classic.Logger;
import com.heightmap.Coordinate;
import com.heightmap.Height;
import com.heightmap.Map;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Storage {
	private static MongoDatabase database;
	private static MongoCollection heightsCollection;
	
	static {
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27018"));
		database = mongoClient.getDatabase("maps");
		heightsCollection = database.getCollection("heights");
		
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
		rootLogger.setLevel(Level.ERROR);
	}
	
	
	public void putHeight(double latitude, double longitude, int height) {
		Document point = new Document()
				.append("latitude", latitude)
				.append("longitude", longitude)
				.append("height", height);
		
		heightsCollection.insertOne(point);
	}
	
	public int getHeight(double latitude, double longitude) {
		
		FindIterable<Document> findIterableDocs = heightsCollection.find(and(
				eq("latitude", latitude),
				eq("longitude", longitude)
		));
		
		if (findIterableDocs.cursor().hasNext()) {
			return findIterableDocs.first().getInteger("height");
		} else {
			return Height.ERROR_VALUE;
		}
	}
	
	/**
	Поиск осуществляется по координатам
	 - по широте  по убыванию  (сверху вниз)
	 - по долготе возрастанию ( слево на право)
	 */
	public HashMap<String, Coordinate> find(double latitudeStart, double latitudeEnd, double longitudeStart, double longitudeEnd, int precision){
		FindIterable<Document> findIterableDocs = heightsCollection.find(and(
				lte("latitude", latitudeStart),
				gte("latitude", latitudeEnd),
				gte("longitude", longitudeStart),
				lte("longitude", longitudeEnd)
		));
		
		HashMap<String, Coordinate> hashMap = new HashMap<>();
		MongoCursor cursor = findIterableDocs.cursor();
		while (cursor.hasNext()){
			Document document = (Document) cursor.next();
			
			double latPoint = Map.customRound(document.getDouble("latitude"), precision);
			double lonPoint = Map.customRound(document.getDouble("longitude"), precision);
			
			String key = latPoint + "_" + lonPoint;
			if(!hashMap.containsKey(key)){
				hashMap.put(key,new Coordinate(latPoint, lonPoint, document.getInteger("height")));
			}
		}

		return hashMap;
	}
}



