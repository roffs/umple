<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

class MentorAB
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorAB Attributes
  private $name;

  //MentorAB Associations
  private $students;
  private $program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName)
  {
    $this->name = $aName;
    $this->students = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getStudent($index)
  {
    $aStudent = $this->students[$index];
    return $aStudent;
  }

  public function getStudents()
  {
    $newStudents = $this->students;
    return $newStudents;
  }

  public function numberOfStudents()
  {
    $number = count($this->students);
    return $number;
  }

  public function hasStudents()
  {
    $has = $this->numberOfStudents() > 0;
    return $has;
  }

  public function indexOfStudent($aStudent)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->students as $student)
    {
      if ($student->equals($aStudent))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getProgram()
  {
    return $this->program;
  }

  public static function minimumNumberOfStudents()
  {
    return 0;
  }

  public static function maximumNumberOfStudents()
  {
    return 2;
  }

  public function addStudent($aStudent)
  {
    $wasAdded = false;
    if ($this->indexOfStudent($aStudent) !== -1) { return false; }
    if ($this->numberOfStudents() >= self::maximumNumberOfStudents())
    {
      return $wasAdded;
    }

    $this->students[] = $aStudent;
    if ($aStudent->indexOfMentor($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aStudent->addMentor($this);
      if (!$wasAdded)
      {
        array_pop($this->students);
      }
    }
    return $wasAdded;
  }

  public function removeStudent($aStudent)
  {
    $wasRemoved = false;
    if ($this->indexOfStudent($aStudent) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfStudent($aStudent);
    unset($this->students[$oldIndex]);
    if ($aStudent->indexOfMentor($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aStudent->removeMentor($this);
      if (!$wasRemoved)
      {
        $this->students[$oldIndex] = $aStudent;
        ksort($this->students);
      }
    }
    $this->students = array_values($this->students);
    return $wasRemoved;
  }

  public function setProgram($newProgram)
  {
    $wasSet = false;
    if ($newProgram == null)
    {
      $existingProgram = $this->program;
      $this->program = null;
      
      if ($existingProgram != null && $existingProgram->getMentor() != null)
      {
        $existingProgram->setMentor(null);
      }
      $wasSet = true;
      return $wasSet;
    }
    
    $currentProgram = $this->getProgram();
    if ($currentProgram != null && $currentProgram != $newProgram)
    {
      $currentProgram->setMentor(null);
    }
    
    $this->program = $newProgram;
    $existingMentor = $newProgram->getMentor();
    
    if ($this != $existingMentor)
    {
      $newProgram->setMentor($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfStudents = $this->students;
    $this->students = array();
    foreach ($copyOfStudents as $aStudent)
    {
      if ($aStudent->numberOfMentors() <= StudentAB::minimumNumberOfMentors())
      {
        $aStudent->delete();
      }
      else
      {
        $aStudent->removeMentor($this);
      }
    }
    if ($this->program != null)
    {
      $this->program->setMentor(null);
    }
  }

}
?>