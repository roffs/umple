<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

class PersonA
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PersonA Attributes
  private $name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->name = "x";
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    StringTracer::execute("name={$aName}");
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>