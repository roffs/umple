/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.test;
import java.util.*;

public class MentorT
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorT Attributes
  private String name;

  //MentorT Associations
  private List<StudentT> students;
  private ProgramT program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorT(String aName)
  {
    name = aName;
    students = new ArrayList<StudentT>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public StudentT getStudent(int index)
  {
    StudentT aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentT> getStudents()
  {
    List<StudentT> newStudents = Collections.unmodifiableList(students);
    return newStudents;
  }

  public int numberOfStudents()
  {
    int number = students.size();
    return number;
  }

  public boolean hasStudents()
  {
    boolean has = students.size() > 0;
    return has;
  }

  public int indexOfStudent(StudentT aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }

  public ProgramT getProgram()
  {
    return program;
  }

  public boolean isNumberOfStudentsValid()
  {
    boolean isValid = numberOfStudents() >= minimumNumberOfStudents() && numberOfStudents() <= maximumNumberOfStudents();
    return isValid;
  }

  public static int requiredNumberOfStudents()
  {
    return 3;
  }

  public static int minimumNumberOfStudents()
  {
    return 3;
  }

  public static int maximumNumberOfStudents()
  {
    return 3;
  }

  public boolean addStudent(StudentT aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    if (numberOfStudents() >= maximumNumberOfStudents())
    {
      return wasAdded;
    }

    students.add(aStudent);
    if (aStudent.indexOfMentor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aStudent.addMentor(this);
      if (!wasAdded)
      {
        students.remove(aStudent);
      }
    }
    return wasAdded;
  }

  public boolean removeStudent(StudentT aStudent)
  {
    boolean wasRemoved = false;
    if (!students.contains(aStudent))
    {
      return wasRemoved;
    }

    if (numberOfStudents() <= minimumNumberOfStudents())
    {
      return wasRemoved;
    }

    int oldIndex = students.indexOf(aStudent);
    students.remove(oldIndex);
    if (aStudent.indexOfMentor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aStudent.removeMentor(this);
      if (!wasRemoved)
      {
        students.add(oldIndex,aStudent);
      }
    }
    return wasRemoved;
  }

  public boolean setStudents(StudentT... newStudents)
  {
    boolean wasSet = false;
    ArrayList<StudentT> verifiedStudents = new ArrayList<StudentT>();
    for (StudentT aStudent : newStudents)
    {
      if (verifiedStudents.contains(aStudent))
      {
        continue;
      }
      verifiedStudents.add(aStudent);
    }

    if (verifiedStudents.size() != newStudents.length || verifiedStudents.size() < minimumNumberOfStudents() || verifiedStudents.size() > maximumNumberOfStudents())
    {
      return wasSet;
    }

    ArrayList<StudentT> oldStudents = new ArrayList<StudentT>(students);
    students.clear();
    for (StudentT newStudent : verifiedStudents)
    {
      students.add(newStudent);
      if (oldStudents.contains(newStudent))
      {
        oldStudents.remove(newStudent);
      }
      else
      {
        newStudent.addMentor(this);
      }
    }

    for (StudentT oldStudent : oldStudents)
    {
      oldStudent.removeMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setProgram(ProgramT newProgram)
  {
    boolean wasSet = false;
    if (newProgram == null)
    {
      ProgramT existingProgram = program;
      program = null;
      
      if (existingProgram != null && existingProgram.getMentor() != null)
      {
        existingProgram.setMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    ProgramT currentProgram = getProgram();
    if (currentProgram != null && !currentProgram.equals(newProgram))
    {
      currentProgram.setMentor(null);
    }

    program = newProgram;
    MentorT existingMentor = newProgram.getMentor();

    if (!equals(existingMentor))
    {
      newProgram.setMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<StudentT> copyOfStudents = new ArrayList<StudentT>(students);
    students.clear();
    for(StudentT aStudent : copyOfStudents)
    {
      if (aStudent.numberOfMentors() <= StudentT.minimumNumberOfMentors())
      {
        aStudent.delete();
      }
      else
      {
        aStudent.removeMentor(this);
      }
    }
    if (program != null)
    {
      program.setMentor(null);
    }
  }

}