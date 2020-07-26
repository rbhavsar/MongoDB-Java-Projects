import com.mongodb.MongoClient;

/**
 * @author rbhavsar
 * Created on 7/25/20.
 */
public class MongoDB {
  static MongoClient mongoClient;

  public static MongoClient getInstace(){
      if(mongoClient == null){
        mongoClient = new MongoClient( "10.211.55.3" , 27017 );
      }
      return mongoClient;
  }
}
