# PLEASE DO NOT EDIT THIS CODE
# This code was generated using the UMPLE 1.15.0.963 modeling language!
# NOTE: Ruby generator is experimental and is missing some features available in
# in other Umple generated languages like Java or PHP

module CruiseTest

class MentorAA


  #------------------------
  # MEMBER VARIABLES
  #------------------------

  #MentorAA Attributes - for documentation purposes
  #attr_reader :name

  #MentorAA Associations - for documentation purposes
  #attr_reader :students, :program

  #------------------------
  # CONSTRUCTOR
  #------------------------

  def initialize(a_name)
    @initialized = false
    @deleted = false
    @name = a_name
    @students = []
    @program = nil
    @initialized = true
  end

  #------------------------
  # INTERFACE
  #------------------------

  def set_name(a_name)
    was_set = false
    @name = a_name
    was_set = true
    was_set
  end

  def get_name
    @name
  end

  def get_student(index)
    a_student = @students[index]
    a_student
  end

  def get_students
    new_students = @students.dup
    new_students
  end

  def number_of_students
    number = @students.size
    number
  end

  def has_students
    has = @students.size > 0
    has
  end

  def index_of_student(a_student)
    index = @students.index(a_student)
    index = -1 if index.nil?
    index
  end

  def get_program
    @program
  end

  def self.minimum_number_of_students
    0
  end

  def self.maximum_number_of_students
    2
  end

  def add_student(a_student)
    was_added = false
    return false if index_of_student(a_student) != -1
    if number_of_students >= MentorAA.maximum_number_of_students
      return was_added
    end

    @students << a_student
    if a_student.index_of_mentor(self) != -1
      was_added = true
    else
      was_added = a_student.add_mentor(self)
      unless was_added
        @students.delete(a_student)
      end
    end
    was_added
  end

  def remove_student(a_student)
    was_removed = false
    unless @students.include?(a_student)
      return was_removed
    end

    oldIndex = @students.index(a_student)
    @students.delete_at(oldIndex)
    if a_student.index_of_mentor(self) == -1
      was_removed = true
    else
      was_removed = a_student.remove_mentor(self)
      @students.insert(oldIndex,a_student) unless was_removed
    end
    was_removed
  end

  def set_students(new_students)
    was_set = false
    verified_students = []
    new_students.each do |a_student|
      next if (verified_students.include?(a_student))
      verified_students << a_student
    end

    if (verified_students.size != new_students.size or verified_students.size() > MentorAA.maximum_number_of_students)
      return was_set
    end

    old_students = @students.dup
    @students.clear
    verified_students.each do |new_student|
      @students << new_student
      if (old_students.include?(new_student))
        old_students.delete(new_student)
      else
        new_student.add_mentor(self)
      end
    end

    old_students.each do |old_student|
      old_student.remove_mentor(self)
    end
    was_set = true
    was_set
  end

  def set_program(new_program)
    was_set = false
    if new_program.nil?
      existing_program = @program
      @program = nil
      
      if !existing_program.nil? and !existing_program.get_mentor.nil?
        existing_program.set_mentor(nil)
      end
      was_set = true
      return was_set
    end

    current_program = self.get_program
    if !current_program.nil? and !current_program.eql?(new_program)
      current_program.set_mentor(nil)
    end

    @program = new_program
    existing_mentor = new_program.get_mentor

    unless self.eql?(existing_mentor)
      new_program.set_mentor(self)
    end
    was_set = true
    was_set
  end

  def delete
    @deleted = true
    copy_of_students = @students.dup
    @students.clear
    copy_of_students.each do |a_student|
      a_student.remove_mentor(self)
    end
    unless @program.nil?
      @program.set_mentor(nil)
    end
  end

end
end