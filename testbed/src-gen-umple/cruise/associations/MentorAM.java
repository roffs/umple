/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.associations;
import java.util.*;

public class MentorAM
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAM Attributes
  private String name;

  //MentorAM Associations
  private List<StudentAM> students;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorAM(String aName)
  {
    name = aName;
    students = new ArrayList<StudentAM>();
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

  public StudentAM getStudent(int index)
  {
    StudentAM aStudent = students.get(index);
    return aStudent;
  }

  public List<StudentAM> getStudents()
  {
    List<StudentAM> newStudents = Collections.unmodifiableList(students);
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

  public int indexOfStudent(StudentAM aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }

  public static int minimumNumberOfStudents()
  {
    return 0;
  }

  public boolean addStudent(StudentAM aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    MentorAM existingMentor = aStudent.getMentor();
    if (existingMentor == null)
    {
      aStudent.setMentor(this);
    }
    else if (!this.equals(existingMentor))
    {
      existingMentor.removeStudent(aStudent);
      addStudent(aStudent);
    }
    else
    {
      students.add(aStudent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudent(StudentAM aStudent)
  {
    boolean wasRemoved = false;
    if (students.contains(aStudent))
    {
      students.remove(aStudent);
      aStudent.setMentor(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public void delete()
  {
    for(StudentAM aStudent : students)
    {
      aStudent.setMentor(null);
    }
  }

}