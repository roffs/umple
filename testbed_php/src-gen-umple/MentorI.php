<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

class MentorI
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorI Attributes
  private $name;

  //MentorI Associations
  private $students;
  private $program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $allStudents)
  {
    $this->name = $aName;
    $this->students = array();
    $didAddStudents = $this->setStudents($allStudents);
    if (!$didAddStudents)
    {
      throw new Exception("Unable to create MentorI, must have 2 to 4 students");
    }
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
    return 2;
  }

  public static function maximumNumberOfStudents()
  {
    return 4;
  }

  public function addStudent($aStudent)
  {
    $wasAdded = false;
    if ($this->indexOfStudent($aStudent) !== -1) { return false; }
    if ($this->numberOfStudents() < self::maximumNumberOfStudents())
    {
      $this->students[] = $aStudent;
      $wasAdded = true;
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

    if ($this->numberOfStudents() <= self::minimumNumberOfStudents())
    {
      return $wasRemoved;
    }

    unset($this->students[$this->indexOfStudent($aStudent)]);
    $this->students = array_values($this->students);
    $wasRemoved = true;
    return $wasRemoved;
  }

  public function setStudents($newStudents)
  {
    $wasSet = false;
    $verifiedStudents = array();
    foreach ($newStudents as $aStudent)
    {
      if (array_search($aStudent,$verifiedStudents) !== false)
      {
        continue;
      }
      $verifiedStudents[] = $aStudent;
    }

    if (count($verifiedStudents) != count($newStudents) || count($verifiedStudents) < self::minimumNumberOfStudents()  || count($verifiedStudents) > self::maximumNumberOfStudents())
    {
      return $wasSet;
    }

    $this->students = $verifiedStudents;
    $wasSet = true;
    return $wasSet;
  }

  public function setProgram($newProgram)
  {
    $wasSet = false;
    if ($newProgram == null)
    {
      $existingProgram = $this->program;
      $this->program = null;
      
      if ($existingProgram != null && $existingProgram->getMentorI() != null)
      {
        $existingProgram->setMentorI(null);
      }
      $wasSet = true;
      return $wasSet;
    }
    
    $currentProgram = $this->getProgram();
    if ($currentProgram != null && $currentProgram != $newProgram)
    {
      $currentProgram->setMentorI(null);
    }
    
    $this->program = $newProgram;
    $existingMentorI = $newProgram->getMentorI();
    
    if ($this != $existingMentorI)
    {
      $newProgram->setMentorI($this);
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
    $this->students = array();
    if ($this->program != null)
    {
      $this->program->setMentorI(null);
    }
  }

}
?>