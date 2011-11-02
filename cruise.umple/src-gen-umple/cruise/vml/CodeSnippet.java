/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.vml;

public class CodeSnippet
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CodeSnippet Attributes
  private String code;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetCode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CodeSnippet(String aCode)
  {
    code = aCode;
    cachedHashCode = -1;
    canSetCode = true;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    if (!canSetCode) { return false; }
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public String getCode()
  {
    return code;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    CodeSnippet compareTo = (CodeSnippet)obj;
  
    if (code == null && compareTo.code != null)
    {
      return false;
    }
    else if (code != null && !code.equals(compareTo.code))
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
    if (code != null)
    {
      cachedHashCode = cachedHashCode * 23 + code.hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetCode = false;
    return cachedHashCode;
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public String toString()
  {
    if (code == null)
    {
      return "";
    }
    else
    {
      return code;
    }
  }
}