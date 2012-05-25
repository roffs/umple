/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.1751 modeling language!*/

package cruise.statemachine.test;

public class CourseT
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseT Attributes
  private int count;

  //CourseT State Machines
  enum One { Off, On, Wait }
  private One one;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseT(int aCount)
  {
    count = aCount;
    setOne(One.Off);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCount(int aCount)
  {
    boolean wasSet = false;
    count = aCount;
    wasSet = true;
    return wasSet;
  }

  public int getCount()
  {
    return count;
  }

  public String getOneFullName()
  {
    String answer = one.toString();
    return answer;
  }

  public One getOne()
  {
    return one;
  }

  private boolean __autotransition3__()
  {
    boolean wasEventProcessed = false;
    
    One aOne = one;
    switch (aOne)
    {
      case Off:
        if (count > 10)
        {
          setOne(One.On);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private boolean __autotransition4__()
  {
    boolean wasEventProcessed = false;
    
    One aOne = one;
    switch (aOne)
    {
      case Off:
        if (count <= 10)
        {
          setOne(One.Wait);
          wasEventProcessed = true;
          break;
        }
        break;
    }

    return wasEventProcessed;
  }

  private void setOne(One aOne)
  {
    one = aOne;

    // entry actions and do activities
    switch(one)
    {
      case Off:
        __autotransition3__();
        __autotransition4__();
        break;
    }
  }

  public void delete()
  {}

}