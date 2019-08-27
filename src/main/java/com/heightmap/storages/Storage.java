package com.heightmap.storages;

import ch.qos.logback.classic.Logger;
import com.heightmap.Height;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

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
	
}



