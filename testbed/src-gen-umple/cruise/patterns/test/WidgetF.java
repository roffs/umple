/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.patterns.test;
import java.util.*;

public class WidgetF
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WidgetF Attributes
  private List<String> ids;
  private List<String> logs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WidgetF()
  {
    ids = new ArrayList<String>();
    logs = new ArrayList<String>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean addId(String aId)
  {
    boolean wasAdded = false;
    wasAdded = ids.add(aId);
    return wasAdded;
  }

  public boolean removeId(String aId)
  {
    boolean wasRemoved = false;
    wasRemoved = ids.remove(aId);
    return wasRemoved;
  }

  public boolean addLog(String aLog)
  {
    boolean wasAdded = false;
    wasAdded = logs.add(aLog);
    return wasAdded;
  }

  public boolean removeLog(String aLog)
  {
    boolean wasRemoved = false;
    wasRemoved = logs.remove(aLog);
    return wasRemoved;
  }

  public String getId(int index)
  {
    String aId = ids.get(index);
    return aId;
  }

  public String[] getIds()
  {
    String[] newIds = ids.toArray(new String[ids.size()]);
    return newIds;
  }

  public int numberOfIds()
  {
    addLog("before numberOfIds");
    int number = ids.size();
    addLog("after numberOfIds");
    return number;
  }

  public boolean hasIds()
  {
    boolean has = ids.size() > 0;
    return has;
  }

  public int indexOfId(String aId)
  {
    int index = ids.indexOf(aId);
    return index;
  }

  public String getLog(int index)
  {
    String aLog = logs.get(index);
    return aLog;
  }

  public String[] getLogs()
  {
    String[] newLogs = logs.toArray(new String[logs.size()]);
    return newLogs;
  }

  public int numberOfLogs()
  {
    int number = logs.size();
    return number;
  }

  public boolean hasLogs()
  {
    boolean has = logs.size() > 0;
    return has;
  }

  public int indexOfLog(String aLog)
  {
    int index = logs.indexOf(aLog);
    return index;
  }

  public void delete()
  {}

}