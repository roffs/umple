/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.umple.compiler;
import cruise.umple.util.*;

public class JsonGenerator implements CodeGenerator
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JsonGenerator Attributes
  private UmpleModel model;
  private String output;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public JsonGenerator()
  {
    model = null;
    output = "";
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setModel(UmpleModel aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setOutput(String aOutput)
  {
    boolean wasSet = false;
    output = aOutput;
    wasSet = true;
    return wasSet;
  }

  public UmpleModel getModel()
  {
    return model;
  }

  public String getOutput()
  {
    return output;
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public void generate()
  {
    String jsonForClasses = StringFormatter.format("{0}umpleClasses{0}:[", "\"");
    String jsonForInterfaces = StringFormatter.format("{0}umpleInterfaces{0}:[", "\"");
    String jsonForAssociations = StringFormatter.format("{0}umpleAssociations{0}:[", "\"");

    boolean isFirst = true;
    for (UmpleClass aClass : model.getUmpleClasses())
    {
      String x = aClass.getPosition().getX() + "";
      String y = aClass.getPosition().getY() + "";
      String width = aClass.getPosition().getWidth() + "";
      String height = aClass.getPosition().getHeight() + "";
      String name = aClass.getName();

      String jsonForAttributes = "";
      for (Attribute attribute : aClass.getAttributes())
      {
        if (jsonForAttributes.length() > 0)
        {
          jsonForAttributes += ", "; 
        }
        String attributeType = attribute.getType() == null ? "String" : attribute.getType();
        jsonForAttributes += StringFormatter.format("{ {0}type{0} : {0}{1}{0}, {0}name{0} : {0}{2}{0} }","\"",attributeType,attribute.getName());
      }
      
      if (!isFirst)
      {
        jsonForClasses += ", ";
      }
      
      String extendsJson = "";
      if (aClass.getExtendsClass() != null)
      {
        String extendsClassName = aClass.getExtendsClass().getName();
        extendsJson = StringFormatter.format(", {0}extendsClass{0}: {0}{1}{0}","\"",extendsClassName);
      }
      
      jsonForClasses += StringFormatter.format("{{0}position{0}: {{0}x{0}: {1}, {0}y{0}: {2}, {0}width{0}: {3}, {0}height{0}: {4}}, {0}attributes{0}: [{7}], {0}id{0}: {0}{5}{0}, {0}name{0}: {0}{6}{0}{8}}", "\"", x, y, width, height, name, name, jsonForAttributes,extendsJson);
      isFirst = false;
    }
    
    boolean isFirstInterface = true;
    for (UmpleInterface aInterface : model.getUmpleInterfaces())
    {
      String x = aInterface.getPosition().getX() + "";
      String y = aInterface.getPosition().getY() + "";
      String width = aInterface.getPosition().getWidth() + "";
      String height = aInterface.getPosition().getHeight() + "";
      String name = aInterface.getName();
           
      if (!isFirstInterface)
      {
    	  jsonForInterfaces += ", ";
      }
      jsonForInterfaces += StringFormatter.format("{{0}position{0}: {{0}x{0}: {2}, {0}y{0}: {3}, {0}width{0}: {4}, {0}height{0}: {5}},{0}id{0}: {0}{1}{0}, {0}name{0}: {0}{1}{0}}","\"",name, x, y, width, height);
      isFirstInterface = false;
    }
    
    int index = 0;
    for (Association aAssoc : model.getAssociations())
    {
      UmpleClass c1 = model.getUmpleClass(aAssoc.getEnd(0).getClassName());
      UmpleClass c2 = model.getUmpleClass(aAssoc.getEnd(1).getClassName());
      
      if (index > 0)
      {
        jsonForAssociations += ", ";
      }
      
      int x1 = -1;
      int y1 = -1;
      int x2 = -1;
      int y2 = -1;
      String name = aAssoc.getName();
      String multOne = aAssoc.getEnd(0).toSimpleString();
      String multTwo = aAssoc.getEnd(1).toSimpleString();
      String roleOne = aAssoc.getEnd(0).getRoleName();
      String roleTwo = aAssoc.getEnd(1).getRoleName();
      
      if (roleOne.equals("") || aAssoc.getEnd(0).getIsDefaultRoleName()) roleOne = "undefined";
      if (roleTwo.equals("") || aAssoc.getEnd(1).getIsDefaultRoleName()) roleTwo = "undefined";
      
      if (aAssoc.numberOfPositions() > 0)
      {
        Coordinate position = aAssoc.getPosition(0);
        x1 = position.getX();
        y1 = position.getY();
      }
      
      if (aAssoc.numberOfPositions() > 1)
      {
        Coordinate position = aAssoc.getPosition(1);
        x2 = position.getX();
        y2 = position.getY();
      }
      
      String associationId = "umpleAssociation_" + index;
      
      jsonForAssociations += StringFormatter.format("{\"offsetOnePosition\": {\"x\": {2}, \"y\": {3}, \"width\": 0, \"height\": 0}, \"offsetTwoPosition\": {\"x\": {4}, \"y\": {5}, \"width\": 0, \"height\": 0}, \"id\": \"{6}\", \"classOneId\": \"{0}\", \"classTwoId\": \"{1}\", \"multiplicityOne\": \"{7}\", \"multiplicityTwo\": \"{8}\", \"name\": \"{9}\", \"roleOne\": \"{10}\", \"roleTwo\": \"{11}\", \"isLeftNavigable\":\"{12}\", \"isRightNavigable\":\"{13}\"}", c1.getName(), c2.getName(), x1, y1, x2, y2,associationId, multOne, multTwo, name, roleOne, roleTwo, aAssoc.getIsLeftNavigable(), aAssoc.getIsRightNavigable());
      index += 1;
    }

    jsonForClasses += "]";
    jsonForAssociations += "]";
    jsonForInterfaces += "]";
    //String json = "{" + jsonForClasses + ", " + jsonForInterfaces + ", " + jsonForAssociations + " }";
    String json = "{" + jsonForClasses  + ", " + jsonForAssociations + " }";
    model.setCode(json);
  }
}