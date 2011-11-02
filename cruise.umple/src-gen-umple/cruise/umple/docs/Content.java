/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.umple.docs;
import java.util.*;

public class Content
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Content Attributes
  private String title;
  private boolean shouldIncludeReferences;
  private String description;
  private String syntax;
  private List<String> examples;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Content(String aTitle, String aDescription, String aSyntax)
  {
    title = aTitle;
    shouldIncludeReferences = true;
    description = aDescription;
    syntax = aSyntax;
    examples = new ArrayList<String>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setShouldIncludeReferences(boolean aShouldIncludeReferences)
  {
    boolean wasSet = false;
    shouldIncludeReferences = aShouldIncludeReferences;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setSyntax(String aSyntax)
  {
    boolean wasSet = false;
    syntax = aSyntax;
    wasSet = true;
    return wasSet;
  }

  public boolean addExample(String aExample)
  {
    boolean wasAdded = false;
    wasAdded = examples.add(aExample);
    return wasAdded;
  }

  public boolean removeExample(String aExample)
  {
    boolean wasRemoved = false;
    wasRemoved = examples.remove(aExample);
    return wasRemoved;
  }

  public String getTitle()
  {
    return title;
  }

  public boolean getShouldIncludeReferences()
  {
    return shouldIncludeReferences;
  }

  public String getDescription()
  {
    return description;
  }

  public String getSyntax()
  {
    return syntax;
  }

  public String getExample(int index)
  {
    String aExample = examples.get(index);
    return aExample;
  }

  public String[] getExamples()
  {
    String[] newExamples = examples.toArray(new String[examples.size()]);
    return newExamples;
  }

  public int numberOfExamples()
  {
    int number = examples.size();
    return number;
  }

  public boolean hasExamples()
  {
    boolean has = examples.size() > 0;
    return has;
  }

  public int indexOfExample(String aExample)
  {
    int index = examples.indexOf(aExample);
    return index;
  }

  public boolean isShouldIncludeReferences()
  {
    return shouldIncludeReferences;
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public String getTitleFilename()
  {
    return title.replace(" ","") + ".html";
  }
}