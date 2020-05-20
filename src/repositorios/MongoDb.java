/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Invitado
 */
public class MongoDb {
    private final MongoClient cliente;
    private final MongoDatabase db;

    public MongoDb(String host, String database) {
        this.cliente=new MongoClient(host);
        this.db = cliente.getDatabase(database);
    }

    public MongoDatabase getDb() {
        return this.db;
    }
}
