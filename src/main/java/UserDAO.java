import java.util.List;

/**
 * @author rbhavsar
 * Created on 7/25/20.
 */
public interface UserDAO {

  int save(User user);

  User update(User user);

  void delete(int id);

  List<User> getAll();

  //void save(List<User> users);

  User getById(int id);

  //long getUserCount();

  //List<ItemCount> getTopTenItems();
}
