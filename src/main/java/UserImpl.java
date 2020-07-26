import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * @author rbhavsar
 * Created on 7/25/20.
 */
public class UserImpl implements UserDAO {
  DB db;
  DBCollection col;


  public UserImpl(MongoClient mongoClient){
    db = mongoClient.getDB("myDb");
    col = db.getCollection("users");
  }

  /*
    create BasicDBObject map
    put values
    collection.insert()
   */
  @Override
  public int save(User user) {
    DBObject userDoc = Util.createDBObject(user);
    WriteResult result = col.insert(userDoc);
    return (int)userDoc.get("_id");
  }

  /*
      create BasicDBObject query
      collection.find(query) to get DBCursor
      Iterate DBCursor
   */
  @Override
  public User getById(int id) {
    User user = null;
    DBObject query = BasicDBObjectBuilder.start().add("_id", id).get();
    DBCursor cursor = col.find(query);
    while(cursor.hasNext()){
      user = Util.jsonToJavaObject(cursor.next().toString());
    }
    return user;
  }

  @Override
  public List<User> getAll(){
    DBCursor cursor = col.find();
    List<User> users = new ArrayList<>();
    while (cursor.hasNext()) {
      DBObject obj = cursor.next();
      User user = Util.jsonToJavaObject(obj.toString());
      users.add(user);
    }
    return users;
  }

  /*
    create BasicDBObject with all key value
    create BasicDBObject searchQuery for Id
    collection.update(query,document)

   */

  @Override
  public User update(User user){
    BasicDBObject newDocument = new BasicDBObject();
    newDocument.put("role",user.getRole());
    newDocument.put("isEmployee",!user.isEmployee());
    newDocument.put("name", user.getName()+"_updated");

    BasicDBObject searchQuery = new BasicDBObject().append("_id", user.get_id());
    col.update(searchQuery, newDocument);
    return getById(user.get_id());
  }

  /*
    BasicDBObject searchQuery for id
    collection.remove(query)
  */

  @Override
  public void delete(int id){
    BasicDBObject searchQuery = new BasicDBObject().append("_id", id);
    WriteResult result = col.remove(searchQuery);
    User user = getById(id);
    if(user!=null){
      System.out.println("Records is not deleted.");
    }
  }




}
