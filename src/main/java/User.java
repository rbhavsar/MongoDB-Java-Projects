import java.util.Random;

/**
 * @author rbhavsar
 * Created on 7/25/20.
 */
public class User {

  private int _id;
  private String name;
  private String role;
  private boolean isEmployee;

  public User(int _id, String name, String role, boolean isEmployee) {
    this._id = new Random().nextInt(_id);
    this.name = name;
    this.role = role;
    this.isEmployee = isEmployee;
  }


  public int get_id() {
    return _id;
  }
  public void set_id(int _id) {
    this._id = _id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  public boolean isEmployee() {
    return isEmployee;
  }
  public void setEmployee(boolean isEmployee) {
    this.isEmployee = isEmployee;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("_id=").append(_id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", role='").append(role).append('\'');
    sb.append(", isEmployee=").append(isEmployee);
    sb.append('}');
    return sb.toString();
  }
}
