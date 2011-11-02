/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.patterns.test;

public class WidgetC
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WidgetC Attributes
  private String id;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WidgetC(String aId)
  {
    id = aId;
    cachedHashCode = -1;
    canSetId = true;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    if (!canSetId) { return false; }
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    WidgetC compareTo = (WidgetC)obj;
  
    if (id == null && compareTo.id != null)
    {
      return false;
    }
    else if (id != null && !id.equals(compareTo.id))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (id != null)
    {
      cachedHashCode = cachedHashCode * 23 + id.hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetId = false;
    return cachedHashCode;
  }

  public void delete()
  {}

}