package ch.dubach.mongo.model;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class DatabaseConnection {

	protected DB db;
	protected DBCollection collection;
	protected Mongo mongo;

	public void openDB() {
		try {
			mongo = new Mongo(DatabaseConstants.DB_HOST, 27017);
			db = mongo.getDB(DatabaseConstants.DB_NAME);
			connectDBCollection(DatabaseConstants.COLLECTION_NAME);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private boolean connectDBCollection(String collectionName) {
		if (null == collectionName || collectionName.isEmpty()) {
			return false;
		}
		collection = db.getCollection(collectionName);
		return true;
	}

	public void closeDB() {
		mongo.close();
	}

	public long getCollectionCount() {
		return collection.count();
	}

	public DBCollection getCollection() {
		return collection;
	}

}
