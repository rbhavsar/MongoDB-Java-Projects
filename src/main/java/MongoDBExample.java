import java.time.LocalDateTime;
import java.util.List;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * @author rbhavsar
 * Created on 7/25/20.
 */
public class MongoDBExample {


  /*
    Connection to the database
    Creating documents
    Retrieving documents
    Updating documents
    Deleting Documents
   */

  public static void main(String[] args) {
    MongoClient mongoClient = null;
    try {
      mongoClient = MongoDB.getInstace();
      User user = new User(100000,"Ravi","CEO",true);
      UserImpl userimpl = new UserImpl(mongoClient);
      int id = userimpl.save(user);

      user = userimpl.getById(id);
      System.out.println("User = "+user);

      user.setName("Ravi Bhavsar_"+LocalDateTime.now());
      user = userimpl.update(user);
      System.out.println("Updated User="+user);

      userimpl.delete(user.get_id());

      List<User> users = userimpl.getAll();
      System.out.println("All Users="+users);


    }catch (Exception ex){
      System.out.println("Exception while performing CRUD operation"+ex);
    }finally {
      mongoClient.close();
    }

  }

  private static DBObject createDBObject(User user) {
    BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

    docBuilder.append("_id", user.get_id());
    docBuilder.append("name", user.getName());
    docBuilder.append("role", user.getRole());
    docBuilder.append("isEmployee", user.isEmployee());
    return docBuilder.get();
  }
}
