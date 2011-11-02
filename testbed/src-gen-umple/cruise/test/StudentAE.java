/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.test;
import java.util.*;

public class StudentAE
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StudentAE Attributes
  private int number;

  //StudentAE Associations
  private ProgramAE program;
  private List<MentorAE> mentors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StudentAE(int aNumber)
  {
    number = aNumber;
    mentors = new ArrayList<MentorAE>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public ProgramAE getProgram()
  {
    return program;
  }

  public MentorAE getMentor(int index)
  {
    MentorAE aMentor = mentors.get(index);
    return aMentor;
  }

  public List<MentorAE> getMentors()
  {
    List<MentorAE> newMentors = Collections.unmodifiableList(mentors);
    return newMentors;
  }

  public int numberOfMentors()
  {
    int number = mentors.size();
    return number;
  }

  public boolean hasMentors()
  {
    boolean has = mentors.size() > 0;
    return has;
  }

  public int indexOfMentor(MentorAE aMentor)
  {
    int index = mentors.indexOf(aMentor);
    return index;
  }

  public boolean setProgram(ProgramAE newProgram)
  {
    boolean wasSet = false;
    if (newProgram == null)
    {
      ProgramAE existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getStudent() != null)
      {
        existingProgram.setStudent(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramAE currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(newProgram))
    {
      currentProgram.setStudent(null);
    }

    program = newProgram;
    StudentAE existingStudent = newProgram.getStudent();

    if (!equals(existingStudent))
    {
      newProgram.setStudent(this);
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfMentors()
  {
    return 0;
  }

  public static int maximumNumberOfMentors()
  {
    return 3;
  }

  public boolean addMentor(MentorAE aMentor)
  {
    boolean wasAdded = false;
    if (mentors.contains(aMentor)) { return false; }
    if (numberOfMentors() >= maximumNumberOfMentors())
    {
      return wasAdded;
    }

    mentors.add(aMentor);
    if (aMentor.indexOfStudent(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMentor.addStudent(this);
      if (!wasAdded)
      {
        mentors.remove(aMentor);
      }
    }
    return wasAdded;
  }

  public boolean removeMentor(MentorAE aMentor)
  {
    boolean wasRemoved = false;
    if (!mentors.contains(aMentor))
    {
      return wasRemoved;
    }

    int oldIndex = mentors.indexOf(aMentor);
    mentors.remove(oldIndex);
    if (aMentor.indexOfStudent(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMentor.removeStudent(this);
      if (!wasRemoved)
      {
        mentors.add(oldIndex,aMentor);
      }
    }
    return wasRemoved;
  }

  public void delete()
  {
    if (program != null)
    {
      program.setStudent(null);
    }
    ArrayList<MentorAE> copyOfMentors = new ArrayList<MentorAE>(mentors);
    mentors.clear();
    for(MentorAE aMentor : copyOfMentors)
    {
      aMentor.removeStudent(this);
    }
  }

}