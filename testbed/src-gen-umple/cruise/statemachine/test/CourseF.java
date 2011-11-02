/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.statemachine.test;

public class CourseF
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseF State Machines
  enum Status { Off, On }
  enum StatusMotorIdle { Null, MotorIdle, MotorRunning }
  enum StatusFanIdle { Null, FanIdle, FanRunning }
  private Status status;
  private StatusMotorIdle statusMotorIdle;
  private StatusFanIdle statusFanIdle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseF()
  {
    setStatus(Status.Off);
    if (statusMotorIdle == null) { setStatusMotorIdle(StatusMotorIdle.Null); }
    if (statusFanIdle == null) { setStatusFanIdle(StatusFanIdle.Null); }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    if (statusMotorIdle != StatusMotorIdle.Null) { answer += "." + statusMotorIdle.toString(); }
    if (statusFanIdle != StatusFanIdle.Null) { answer += "." + statusFanIdle.toString(); }
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public StatusMotorIdle getStatusMotorIdle()
  {
    return statusMotorIdle;
  }

  public StatusFanIdle getStatusFanIdle()
  {
    return statusFanIdle;
  }

  public boolean turnOn()
  {
    boolean wasEventProcessed = false;

    switch (status)
    {
      case Off:
        setStatus(Status.On);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean turnOff()
  {
    boolean wasEventProcessed = false;

    switch (status)
    {
      case On:
        exitStatus();
        setStatus(Status.Off);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean enterOn()
  {
    boolean wasEventProcessed = false;

    switch (statusMotorIdle)
    {
      case Null:
        setStatusMotorIdle(StatusMotorIdle.MotorIdle);
        wasEventProcessed = true;
        break;
    }

    switch (statusFanIdle)
    {
      case Null:
        setStatusFanIdle(StatusFanIdle.FanIdle);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean exitOn()
  {
    boolean wasEventProcessed = false;

    switch (statusMotorIdle)
    {
      case MotorIdle:
        setStatusMotorIdle(StatusMotorIdle.Null);
        wasEventProcessed = true;
        break;
      case MotorRunning:
        setStatusMotorIdle(StatusMotorIdle.Null);
        wasEventProcessed = true;
        break;
    }

    switch (statusFanIdle)
    {
      case FanIdle:
        setStatusFanIdle(StatusFanIdle.Null);
        wasEventProcessed = true;
        break;
      case FanRunning:
        setStatusFanIdle(StatusFanIdle.Null);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean flip()
  {
    boolean wasEventProcessed = false;

    switch (statusMotorIdle)
    {
      case MotorIdle:
        setStatusMotorIdle(StatusMotorIdle.MotorRunning);
        wasEventProcessed = true;
        break;
      case MotorRunning:
        setStatusMotorIdle(StatusMotorIdle.MotorIdle);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean flop()
  {
    boolean wasEventProcessed = false;

    switch (statusFanIdle)
    {
      case FanIdle:
        setStatusFanIdle(StatusFanIdle.FanRunning);
        wasEventProcessed = true;
        break;
      case FanRunning:
        setStatusFanIdle(StatusFanIdle.FanIdle);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case On:
        exitOn();
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case On:
        if (statusMotorIdle == StatusMotorIdle.Null) { setStatusMotorIdle(StatusMotorIdle.MotorIdle); }
        if (statusFanIdle == StatusFanIdle.Null) { setStatusFanIdle(StatusFanIdle.FanIdle); }
        break;
    }
  }

  private void setStatusMotorIdle(StatusMotorIdle aStatusMotorIdle)
  {
    statusMotorIdle = aStatusMotorIdle;
    if (status != Status.On && aStatusMotorIdle != StatusMotorIdle.Null) { setStatus(Status.On); }
  }

  private void setStatusFanIdle(StatusFanIdle aStatusFanIdle)
  {
    statusFanIdle = aStatusFanIdle;
    if (status != Status.On && aStatusFanIdle != StatusFanIdle.Null) { setStatus(Status.On); }
  }

  public void delete()
  {}

}