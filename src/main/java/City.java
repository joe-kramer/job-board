import java.util.List;
import java.util.ArrayList;

public class City {
  private String mName;
  private static List<Job> jobs;
  private static List<City> instances = new ArrayList<City>();
  private int mId;

  public City(String name) {
    mName = name;
    instances.add(this);
    mId = instances.size();
  }

  public String getName() {
    return mName;
  }

  public static List<City> all() {
    return instances;
  }

  public int getId() {
    return mId;
  }

  public static City find(int id) {
    try {
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public static List<Job> getJobs() {
    return jobs;
  }
}
