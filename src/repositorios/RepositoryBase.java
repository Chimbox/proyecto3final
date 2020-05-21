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
public abstract class RepositoryBase<T> implements Repository<T>{
    
    private final MongoClient mongo;

    public RepositoryBase(MongoClient mongo) {
        this.mongo=mongo;
    }
    
    public MongoDatabase getDatabase(){
        return this.mongo.getDatabase("faceboot");
    }
}
