import java.util.ArrayList;
import java.util.List;

public class Job {
  private String mName;
  private boolean mAvaliable;
  private int mId;
  private static List<Job> instances = new ArrayList<Job>();

  public Job(String name) {
    mName = name;
    mAvaliable = true;
    instances.add(this);
    mId = instances.size();
  }

  public String getName() {
    return mName;
  }

  public static List<Job> all() {
    return instances;
  }
}
