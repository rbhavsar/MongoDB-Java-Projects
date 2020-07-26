import com.google.gson.Gson;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @author rbhavsar
 * Created on 7/25/20.
 */
public class Util {

  static Gson gson = new Gson();

  public static DBObject createDBObject(User user) {
    BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

    docBuilder.append("_id", user.get_id());
    docBuilder.append("name", user.getName());
    docBuilder.append("role", user.getRole());
    docBuilder.append("isEmployee", user.isEmployee());
    return docBuilder.get();
  }

  public static User jsonToJavaObject(String json){
    User user = null;
    if(json !=null && !json.isEmpty()) {
      user = gson.fromJson(json, User.class);
    }
    return user;
  }

}
