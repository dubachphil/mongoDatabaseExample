package ch.dubach.mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import ch.dubach.mongo.model.Database;

public class App {
	
	public static List<DBObject> result = new ArrayList<DBObject>();
	
	public static void main(String[] args) {
		
		// Datenbank instanzieren
		// Einstellungen in DatabaseConstants
		Database db = new Database();
		
		// Datenbank öffnen
		db.openDB();
		
		// Datenbank lesen
		readDB(db);

		// Datenbank Objekt erstellen
		BasicDBObject phil = new BasicDBObject();
		phil.put("Vorname", "Phil");
		phil.put("Nachname", "Dubach");

		// Datenbank Objekt erstellen
		BasicDBObject yvi = new BasicDBObject();
		yvi.put("Vorname", "Yvette");
		yvi.put("Nachname", "Huber");
		
		// Datensätze in Liste speichern
		List<BasicDBObject> docs = new ArrayList<BasicDBObject>();
		docs.add(phil);
		docs.add(yvi);
		
		// Datensätze in DB schreiben
		db.insertDocuments(docs);

		// Datenbank in Console ausgeben
		readDB(db);
		
		// Einzelne Datensätze anschauen
		System.out.println(result.get(0));
		System.out.println(result.get(1));
		
		// Auf Feld eines Datensatz zugreifen
		System.out.println(result.get(0).get("Vorname"));
		
		// Suche einen DB Eintrag
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("Vorname", "Yvette");
		DBObject response = db.findOne(searchQuery);
		System.out.println(response);
		
		// Datenbank Collection löschen
		// Nur für Testzwecke damit nicht zuviele Daten in der DB sind
		db.dropCollection();

		// Datenbank schliessen
		db.closeDB();
	}
	
	public static void readDB(Database db) {
		result = db.readDocuments();
		for (DBObject dbObject : result) {
			System.out.println(dbObject.get("_id") + " : "
					+ dbObject.get("Vorname") + " : "
					+ dbObject.get("Nachname"));
		}
	}
}
