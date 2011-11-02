/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.vml;
import java.util.*;

/**
 * POSITION INFORMATION
 */
public class Concern
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Concern Attributes
  private String name;

  //Concern Associations
  private List<VariationPoint> variationPoints;
  private VmlSystem vmlSystem;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Concern(String aName)
  {
    name = aName;
    variationPoints = new ArrayList<VariationPoint>();
    cachedHashCode = -1;
    canSetName = true;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    if (!canSetName) { return false; }
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public VariationPoint getVariationPoint(int index)
  {
    VariationPoint aVariationPoint = variationPoints.get(index);
    return aVariationPoint;
  }

  public List<VariationPoint> getVariationPoints()
  {
    List<VariationPoint> newVariationPoints = Collections.unmodifiableList(variationPoints);
    return newVariationPoints;
  }

  public int numberOfVariationPoints()
  {
    int number = variationPoints.size();
    return number;
  }

  public boolean hasVariationPoints()
  {
    boolean has = variationPoints.size() > 0;
    return has;
  }

  public int indexOfVariationPoint(VariationPoint aVariationPoint)
  {
    int index = variationPoints.indexOf(aVariationPoint);
    return index;
  }

  public VmlSystem getVmlSystem()
  {
    return vmlSystem;
  }

  public static int minimumNumberOfVariationPoints()
  {
    return 0;
  }

  public boolean addVariationPoint(VariationPoint aVariationPoint)
  {
    boolean wasAdded = false;
    if (variationPoints.contains(aVariationPoint)) { return false; }
    Concern existingConcern = aVariationPoint.getConcern();
    if (existingConcern == null)
    {
      aVariationPoint.setConcern(this);
    }
    else if (!this.equals(existingConcern))
    {
      existingConcern.removeVariationPoint(aVariationPoint);
      addVariationPoint(aVariationPoint);
    }
    else
    {
      variationPoints.add(aVariationPoint);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeVariationPoint(VariationPoint aVariationPoint)
  {
    boolean wasRemoved = false;
    if (variationPoints.contains(aVariationPoint))
    {
      variationPoints.remove(aVariationPoint);
      aVariationPoint.setConcern(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean setVmlSystem(VmlSystem aVmlSystem)
  {
    boolean wasSet = false;
    VmlSystem existingVmlSystem = vmlSystem;
    vmlSystem = aVmlSystem;
    if (existingVmlSystem != null && !existingVmlSystem.equals(aVmlSystem))
    {
      existingVmlSystem.removeConcern(this);
    }
    if (aVmlSystem != null)
    {
      aVmlSystem.addConcern(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Concern compareTo = (Concern)obj;
  
    if (name == null && compareTo.name != null)
    {
      return false;
    }
    else if (name != null && !name.equals(compareTo.name))
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
    if (name != null)
    {
      cachedHashCode = cachedHashCode * 23 + name.hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetName = false;
    return cachedHashCode;
  }

  public void delete()
  {
    for(VariationPoint aVariationPoint : variationPoints)
    {
      aVariationPoint.setConcern(null);
    }
    if (vmlSystem != null)
    {
      VmlSystem placeholderVmlSystem = vmlSystem;
      this.vmlSystem = null;
      placeholderVmlSystem.removeConcern(this);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public String toString()
  {
    return name + ":" + numberOfVariationPoints() + " variation points";
  }
  
  public VariationPoint getVariationPoint(String name)
  {
    if (name == null)
    {
      return null;
    }
  
    for (VariationPoint vp : getVariationPoints())
    {
      if (name.equals(vp.getName()))
      {
        return vp;
      }
    }
    return null;
  }
}