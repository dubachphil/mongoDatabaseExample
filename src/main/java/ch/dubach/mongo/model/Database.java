package ch.dubach.mongo.model;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Database extends DatabaseConnection {

	public void insertDocuments(List<BasicDBObject> documents) {
		for (DBObject dbObject : documents) {
			collection.insert(dbObject);
		}
	}

	public List<DBObject> readDocuments() {
		DBCursor cursor = collection.find();
		List<DBObject> docs = new ArrayList<DBObject>();
		for (DBObject dbObject : cursor) {
			docs.add(dbObject);
		}
		return docs;
	}
	
	public DBCursor searchDocuments(BasicDBObject searchQuery) {
		return collection.find(searchQuery);
	}
	
	public DBObject findOne(BasicDBObject searchQuery) {
		return collection.findOne(searchQuery);
	}
	
	public void removeDocuments(BasicDBObject searchQuery) {
		collection.remove(searchQuery);
	}
	
	public void dropCollection() {
		collection.drop();
		
	}

}
