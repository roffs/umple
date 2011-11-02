/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.umple.compiler;
import java.util.*;
import java.io.*;
import cruise.umple.util.*;
import cruise.umple.compiler.exceptions.*;
import cruise.umple.compiler.java.*;
import cruise.umple.util.StringFormatter;

public class JavaGenerator implements CodeGenerator,CodeTranslator
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JavaGenerator Attributes
  private UmpleModel model;
  private String output;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public JavaGenerator()
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
  
  private static Map<String,String> UpperCaseSingularLookupMap;
  private static Map<String,String> UpperCasePluralLookupMap;
  private static Map<String,String> AsIsSingularLookupMap;
  private static Map<String,String> AsIsPluralLookupMap;
  private static Map<String,String> UmpleToJavaPrimitiveMap;
  private static List<String> OneOrManyLookup;
  
  
  static
  {
    UpperCaseSingularLookupMap = new HashMap<String, String>();
    UpperCaseSingularLookupMap.put("parameterOne", "a{0}");
    UpperCaseSingularLookupMap.put("removeParameterOne", "placeholder{0}");    
    UpperCaseSingularLookupMap.put("parameterNew", "new{0}");
    UpperCaseSingularLookupMap.put("parameterNext", "next{0}");
    UpperCaseSingularLookupMap.put("addMethod", "add{0}");
    UpperCaseSingularLookupMap.put("removeMethod", "remove{0}");
    UpperCaseSingularLookupMap.put("indexOfMethod", "indexOf{0}");
    UpperCaseSingularLookupMap.put("parameterOld", "old{0}");
    UpperCaseSingularLookupMap.put("parameterExisting", "existing{0}");
    UpperCaseSingularLookupMap.put("parameterIsNew", "isNew{0}");
    UpperCaseSingularLookupMap.put("associationNew", "new{0}");
    UpperCaseSingularLookupMap.put("canSetMethod", "canSet{0}");
    UpperCaseSingularLookupMap.put("parameterCurrent", "current{0}");
    UpperCaseSingularLookupMap.put("deleteMethod", "delete{0}");
    UpperCaseSingularLookupMap.put("setMethod", "set{0}");
    UpperCaseSingularLookupMap.put("enterMethod", "enter{0}");
    UpperCaseSingularLookupMap.put("exitMethod", "exit{0}");
    UpperCaseSingularLookupMap.put("resetMethod", "reset{0}");
    UpperCaseSingularLookupMap.put("getMethod", "get{0}");
    UpperCaseSingularLookupMap.put("isMethod", "is{0}");
    UpperCaseSingularLookupMap.put("getFullMethod", "get{0}FullName");    
    UpperCaseSingularLookupMap.put("getDefaultMethod", "getDefault{0}");
    UpperCaseSingularLookupMap.put("didAdd", "didAdd{0}");
    UpperCaseSingularLookupMap.put("hasMethod", "has{0}");
    UpperCaseSingularLookupMap.put("associationCanSetOne","canSet{0}");
    UpperCaseSingularLookupMap.put("attributeCanSetOne","canSet{0}");
    UpperCaseSingularLookupMap.put("eventStartMethod", "start{0}Handler");
    UpperCaseSingularLookupMap.put("eventStopMethod", "stop{0}Handler");    
    UpperCaseSingularLookupMap.put("stateNull", "Null");

    UpperCasePluralLookupMap = new HashMap<String, String>();
    UpperCasePluralLookupMap.put("parameterMany", "new{0}");
    UpperCasePluralLookupMap.put("parameterAll", "all{0}");
    UpperCasePluralLookupMap.put("numberOfMethod", "numberOf{0}");
    UpperCasePluralLookupMap.put("minimumNumberOfMethod", "minimumNumberOf{0}");
    UpperCasePluralLookupMap.put("maximumNumberOfMethod", "maximumNumberOf{0}");
    UpperCasePluralLookupMap.put("isNumberOfValidMethod", "isNumberOf{0}Valid");
    UpperCasePluralLookupMap.put("parameterVerifiedMany", "verified{0}");
    UpperCasePluralLookupMap.put("parameterOldMany", "old{0}");
    UpperCasePluralLookupMap.put("parameterCheckNewMany", "checkNew{0}");
    UpperCasePluralLookupMap.put("parameterCopyOfMany", "copyOf{0}");
    UpperCasePluralLookupMap.put("getManyMethod", "get{0}");
    UpperCasePluralLookupMap.put("parameterMany", "new{0}");
    UpperCasePluralLookupMap.put("setManyMethod", "set{0}");
    UpperCasePluralLookupMap.put("didAddMany", "didAdd{0}");
    UpperCasePluralLookupMap.put("hasManyMethod", "has{0}");
    UpperCasePluralLookupMap.put("associationCanSetMany","canSet{0}");
    UpperCasePluralLookupMap.put("attributeCanSetMany","canSet{0}");
    UpperCasePluralLookupMap.put("requiredNumberOfMethod", "requiredNumberOf{0}");

    AsIsSingularLookupMap = new HashMap<String, String>();
    AsIsSingularLookupMap.put("associationOne","{0}");
    AsIsSingularLookupMap.put("attributeOne","{0}");
    AsIsSingularLookupMap.put("stateMachineOne","{0}");
    AsIsSingularLookupMap.put("stateOne","{0}");
    AsIsSingularLookupMap.put("stateString","\"{0}\"");
    AsIsSingularLookupMap.put("eventMethod","{0}");
    AsIsSingularLookupMap.put("eventHandler", "{0}Handler");
        
    AsIsPluralLookupMap = new HashMap<String, String>();
    AsIsPluralLookupMap.put("associationMany","{0}");
    AsIsPluralLookupMap.put("attributeMany","{0}");
    
    OneOrManyLookup = new ArrayList<String>();
    OneOrManyLookup.add("attribute");
    OneOrManyLookup.add("parameter");
    
    UmpleToJavaPrimitiveMap = new HashMap<String, String>();
    UmpleToJavaPrimitiveMap.put("Integer","int");
    UmpleToJavaPrimitiveMap.put("Boolean","boolean");
    UmpleToJavaPrimitiveMap.put("Double","double");
    UmpleToJavaPrimitiveMap.put("Float","float");
    

  }
  
  public void generate()
  {
    prepare();
    UmpleElement lastElement = null;
    try
    {
      for (UmpleElement currentElement : model.getUmpleElements())
      {
        if ("external".equals(currentElement.getModifier()))
        {
          continue;
        }
        writeFile(currentElement);
        lastElement = currentElement;
      }
    }
    catch (Exception e)
    {
      throw new UmpleCompilerException("There was a problem with generating classes. " + e, e);
    }

    if (lastElement == null)
    {
      String message = "There was a problem with generating classes.\nNo clases were compiled.\n";
      message += "Check the first line statement for probable cause.";
      throw new UmpleCompilerException(message,null);
    }
    GeneratorHelper.postpare(model);
  }
  
  
  public ILang getLanguageFor(UmpleElement aElement)
  {
    if (aElement instanceof UmpleInterface)
    {
      return new JavaInterfaceGenerator();
    }
    else if (aElement instanceof UmpleClass)
    {
      return new JavaClassGenerator();
    } 
    else{
        return null;        
    }
  }
  
  public String getType(UmpleVariable av)
  {
    String myType = av.getType();
    if (myType == null || myType.length() == 0)
    {
      return "String";
    }
    else if (UmpleToJavaPrimitiveMap.containsKey(myType))
    {
      return UmpleToJavaPrimitiveMap.get(myType);
    }
    else
    {
      return myType;
    }
  }
  
  public boolean isNullable(UmpleVariable av)
  {
    return !UmpleToJavaPrimitiveMap.containsKey(av.getType());
  }
  
  public String relatedTranslate(String name, AssociationVariable av)
  {
    return translate(name,av.getRelatedAssociation());
  }
  
  public String translate(String keyName, State state)
  {
    String singularName = state.getName();
    String pluralName = model.getGlossary().getPlural(singularName);
  
    if (UpperCasePluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCasePluralLookupMap.get(keyName),getUpperCaseName(pluralName));
    }
    else if (UpperCaseSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCaseSingularLookupMap.get(keyName),getUpperCaseName(singularName));
    }
    else if (AsIsPluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsPluralLookupMap.get(keyName),pluralName);
    }
    else if (AsIsSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsSingularLookupMap.get(keyName),singularName);
    }
    else if ("doActivityMethod".equals(keyName))
    {
      return StringFormatter.format("doActivity{0}{1}",getUpperCaseName(state.getStateMachine().getName()),getUpperCaseName(state.getName())); 
    }
    else if ("type".equals(keyName))
    {
      return getUpperCaseName(state.getStateMachine().getName());
    }
    
    return "UNKNOWN ID: " + keyName;
  }
  
  public String translate(String keyName, StateMachine sm)
  {
    String singularName = sm.getFullName();
    String pluralName = model.getGlossary().getPlural(singularName);
  
    if (UpperCasePluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCasePluralLookupMap.get(keyName),getUpperCaseName(pluralName));
    }
    else if (UpperCaseSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCaseSingularLookupMap.get(keyName),getUpperCaseName(singularName));
    }
    else if (AsIsPluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsPluralLookupMap.get(keyName),pluralName);
    }
    else if (AsIsSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsSingularLookupMap.get(keyName),singularName);
    }
    else if ("typeGet".equals(keyName) || "typeFull".equals(keyName))
    {
      return "String";
    }
    else if ("type".equals(keyName))
    {
      return getUpperCaseName(sm.getFullName());
    }
    else if ("listStates".equals(keyName))
    {
      String allEnums = "";
      for(State state : sm.getStates())
      {
        if (allEnums.length() > 0)
        {
          allEnums += ", ";
        }
        allEnums += translate("stateOne",state);
      }
      return allEnums;
    }
    
    return "UNKNOWN ID: " + keyName;
  }
  
  public String translate(String keyName, Event event)
  {
    String singularName = event.getName();
    String pluralName = model.getGlossary().getPlural(singularName);

    if (UpperCasePluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCasePluralLookupMap.get(keyName),getUpperCaseName(pluralName));
    }
    else if (UpperCaseSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCaseSingularLookupMap.get(keyName),getUpperCaseName(singularName));
    }
    else if (AsIsPluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsPluralLookupMap.get(keyName),pluralName);
    }
    else if (AsIsSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsSingularLookupMap.get(keyName),singularName);
    }
    
    return "UNKNOWN ID: " + keyName;
  }  
  
  public String translate(String name, UmpleInterface aInterface)
  {
    if ("packageDefinition".equals(name))
    {
      return aInterface.getPackageName().length() == 0 ? "" : "package " + aInterface.getPackageName() + ";"; 
    }
    if ("isA".equals(name))
    {
      return getImplementsForInterfaces(aInterface);
    }
    return "";
  }
  
  public String translate(String methodType)
  {
    if ("String".equals(methodType))
    {
      return "\"\"";
    }
    if ("int".equals(methodType))
    {
      return "0";
    }
    if ("boolean".equals(methodType))
    {
      return "false";
    }       
    return "null";
  }
  
  public String translate(String name, UmpleClass aClass)
  {
    if ("constructorMandatory".equals(name))
    {
      return aClass.getGeneratedClass().getLookup("constructorSignature_mandatory");
    }
    else if ("packageDefinition".equals(name))
    {
      return aClass.getPackageName().length() == 0 ? "" : "package " + aClass.getPackageName() + ";"; 
    }
    else if ("type".equals(name))
    {
      return aClass.getName();
    }
    else if ("isA".equals(name))
    {
      return getExtendAndImplements(aClass);
    }
    return "UNKNOWN ID: " + name;
  }
  
  private String getImplementsForInterfaces(UmpleInterface uInterface)
  {
      String implementedInterfaces = "";

      if (uInterface.hasExtendsInterface() == false){
          return "";
      }
      else{
          for (UmpleInterface aInterface : uInterface.getExtendsInterface())
          {
              implementedInterfaces += aInterface.getName() + ", " ; 
          }
          implementedInterfaces = implementedInterfaces.substring(0, implementedInterfaces.length()-2); 
          return " extends " + implementedInterfaces;
      }
  }

  private String getExtendAndImplements(UmpleClass uClass)
  {
      String extendsString = "";
      String implementsString = "";

      extendsString = getExtendClassesNames(uClass);
      implementsString = getImplementsInterfacesNames(uClass);

      return extendsString + implementsString; 
  }

  private String getExtendClassesNames(UmpleClass uClass)
  {
      UmpleClass parent = uClass.getExtendsClass();
      if (parent == null)
      {
          return "";
      }
      else{
          return   " extends " + parent.getName();  
      }
  }

  private String getImplementsInterfacesNames(UmpleClass uClass)
  {
      String implementedInterfaces = "";

      if (uClass.hasParentInterface() == false){
          return "";
      }
      else{
          for (UmpleInterface uInterface : uClass.getParentInterface())
          {
              implementedInterfaces += uInterface.getName() + "," ; 
          }
          implementedInterfaces = implementedInterfaces.substring(0, implementedInterfaces.length()-1); 
          return " implements " + implementedInterfaces;
      }
  }

  public String translate(String keyName, Attribute av)
  {
    return translate(keyName,av,av.getIsList());
  }
  
  public String translate(String keyName, AssociationVariable av)
  {
    return translate(keyName,av,av.isMany());
  }
  
  private String translate(String keyName, UmpleVariable av, boolean isMany)
  {
    if (OneOrManyLookup.contains(keyName))
    {
      String realKeyName = isMany ? keyName + "Many" : keyName + "One";
      return translate(realKeyName,av,isMany);
    }
    
    String singularName = isMany ? model.getGlossary().getSingular(av.getName()) : av.getName();
    String pluralName = isMany ? av.getName() : model.getGlossary().getPlural(av.getName());

    if (UpperCasePluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCasePluralLookupMap.get(keyName),getUpperCaseName(pluralName));
    }
    else if (UpperCaseSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(UpperCaseSingularLookupMap.get(keyName),getUpperCaseName(singularName));
    }
    else if (AsIsPluralLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsPluralLookupMap.get(keyName),pluralName);
    }
    else if (AsIsSingularLookupMap.containsKey(keyName))
    {
      return StringFormatter.format(AsIsSingularLookupMap.get(keyName),singularName);
    }
    else if ("parameterValue".equals(keyName))
    {
      if (av.getValue() == null)
      {
        return "null";
      }
      
      boolean isString = av.getValue().startsWith("\"") && av.getValue().endsWith("\"");
      if (isString && "Date".equals(av.getType()))
      {
        return "Date.valueOf("+ av.getValue() +")";      
      }
      else if (isString && "Time".equals(av.getType()))
      {
        return "Time.valueOf("+ av.getValue() +")";
      }
      else
      {
        return av.getValue();
      }
    }
    else if ("type".equals(keyName))
    {
      return getType(av);
    }
    else if ("typeMany".equals(keyName))
    {
      return isNullable(av) ? getType(av) : av.getType();
    }
    
    if (av instanceof AssociationVariable)
    {
      AssociationVariable assVar = (AssociationVariable)av;
      if ("callerArgumentsExcept".equals(keyName))
      {
        UmpleClass classToRemove = model.getUmpleClass(getType(assVar.getRelatedAssociation()));
        GeneratedClass generatedClassToRemove = classToRemove.getGeneratedClass();
        String   callerNameToRemove = translate("parameterOne",assVar);
        return StringFormatter.replaceParameter(generatedClassToRemove.getLookup("constructorSignature_caller"), callerNameToRemove, "this");
      }
      else if ("methodArgumentsExcept".equals(keyName))
      {
        UmpleClass classToRemove = model.getUmpleClass(getType(assVar.getRelatedAssociation()));
        GeneratedClass generatedClassToRemove = classToRemove.getGeneratedClass();
        String parameterNameToRemove = StringFormatter.format("{0} {1}", translate("type",assVar), translate("parameterOne",assVar));
        return StringFormatter.replaceParameter(generatedClassToRemove.getLookup("constructorSignature"), parameterNameToRemove, ""); 
      }
      else if ("callerArgumentsForMandatory".equals(keyName))
      {
        UmpleClass classToLookup = model.getUmpleClass(getType(av));
        String lookup = "constructorSignature_mandatory_" + assVar.getRelatedAssociation().getName();
        String parameters = classToLookup.getGeneratedClass().getLookup(lookup);
        return parameters;
      }
      else if ("associationCanSet".equals(keyName))
      {
        String actualLookup = assVar.isMany() ? "associationCanSetMany" : "associationCanSetOne";
        return translate(actualLookup,av,isMany);
      }
    }
    else if (av instanceof Attribute)
    {
      Attribute attVar = (Attribute)av;
      if ("attributeCanSet".equals(keyName))
      {
        String actualLookup = attVar.getIsList() ? "attributeCanSetMany" : "attributeCanSetOne";
        return translate(actualLookup,av,isMany);
      }
    }
    
    return "UNKNOWN ID: " + keyName;
  }
  
  public void prepare()
  {
    List<UmpleClass> allClasses = new ArrayList<UmpleClass>(model.getUmpleClasses());
    for (UmpleClass aClass : allClasses)
    {
      prepare(aClass);
    }
    
    for (UmpleClass aClass : model.getUmpleClasses())
    {
      GeneratedClass genClass = aClass.getGeneratedClass();
      generateSecondaryConstructorSignatures(genClass);
      addImports(aClass,genClass);
    }
    
    addRelatedImports();
  }

  public static String typeOf(String aType)
  {
    if (aType == null || aType.length() == 0)
    {
      return "String";
    }
    else if (aType.equals("Integer"))
    {
      return "int";
    }
    else if (aType.equals("Double"))
    {
      return "double";
    }
    else if (aType.equals("Boolean"))
    {
      return "boolean";
    }
    else
    {
      return aType;
    }
  }

  public String nameOf(String name, boolean hasMultiple)
  {
    if (name == null)
    {
      return null;
    }
    else if (hasMultiple)
    {
      //String pluralName = model.getGlossary().getPlural(name);
      return "all" + StringFormatter.toPascalCase(name);
    }
    else
    {
      //String singularName = model.getGlossary().getSingular(name);
      return "a" + StringFormatter.toPascalCase(name);
    }
  }

  
  //------------------------
  // PRIVATE METHODS
  //------------------------
  
  private String getUpperCaseName(String name)
  {
    return StringFormatter.toPascalCase(name);
  }  
  
  private void prepare(UmpleClass aClass)
  {
    if (aClass.getGeneratedClass() != null)
    {
      return;
    }
    else if (aClass.isRoot())
    {
      GeneratedClass genClass = aClass.createGeneratedClass(model);
      generateConstructorSignature(genClass);
    }
    else
    {
      UmpleClass parent = model.getUmpleClass(aClass.getExtendsClass().getName());
      prepare(parent);
      GeneratedClass genClass = aClass.createGeneratedClass(model);
      genClass.setParentClass(parent.getGeneratedClass());
      generateConstructorSignature(genClass);
    }

    for(Attribute av : aClass.getAttributes())
    {
      if (av.getModifier().equals("immutable") || aClass.getKey().isMember(av))
      {
        String code = StringFormatter.format("if (!{0}) { return false; }",translate("attributeCanSet",av));
        CodeInjection set = new CodeInjection("before",translate("setMethod",av) , code);
        set.setIsInternal(true);
        aClass.addCodeInjection(set);
      }
    
      if (aClass.getKey().isMember(av))
      {
        String code = StringFormatter.format("if (!{0}) { return false; }",translate("attributeCanSet",av));
        String methods = StringFormatter.format("{0},{1},{2},{3}",translate("addMethod",av),translate("removeMethod",av),translate("setManyMethod",av),translate("resetMethod",av));
        CodeInjection inject = new CodeInjection("before", methods, code);
        inject.setIsInternal(true);
        aClass.addCodeInjection(inject);
      }
    }
    
    for(AssociationVariable av : aClass.getAssociationVariables())
    {
      if (aClass.getKey().isMember(av))
      {
        String code = StringFormatter.format("if (!{0}) { return false; }",translate("associationCanSet",av));
        String methods = StringFormatter.format("{0},{1},{2},{3},{4}",translate("addMethod",av),translate("removeMethod",av),translate("setManyMethod",av),translate("setMethod",av),translate("resetMethod",av));
        CodeInjection inject = new CodeInjection("before",methods , code);
        inject.setIsInternal(true);
        aClass.addCodeInjection(inject);
      }
      
      if (av.isMany())
      {
        String code = StringFormatter.format("if ({0}.contains({1})) { return false; }",translate("attributeMany",av),translate("parameterOne",av));
        CodeInjection set = new CodeInjection("before",translate("addMethod",av) , code);
        set.setIsInternal(true);
        aClass.addCodeInjection(set);
      }
    }
    
    Map<String,String> lookups = new HashMap<String,String>();
    String executeMethods = "public static void execute(String message) { getInstance().addTrace(message); }\n";
    executeMethods += "public void reset() { getInstance().traces.clear(); }";
    lookups.put("consoleTemplate","System.out.println(\"{0}=\" + {1});");
    lookups.put("stringTemplate","StringTracer.execute(\"{0}=\" + {1});");
    lookups.put("fileTemplate","fileTracer({0});");
    lookups.put("dependPackage","1");
    lookups.put("extraCode",executeMethods);
    //GeneratorHelper.prepareAllStringTracers(this,model,aClass,lookups);
    prepareAllTraces(this,model,aClass,lookups);
    
    //Add  entry / exit methods to start and stop the timed events in Java
    boolean hasTimedEvents = false;
    for (StateMachine sm : aClass.getStateMachines())
    {
      prepareNestedStatesFor(sm,0);
      hasTimedEvents = prepareTimedEvents(sm) || hasTimedEvents;
    }
        
    if (hasTimedEvents)
    {
      aClass.addDepend(new Depend("java.util.*"));
    }
  }
  
  //====================== Start of Tracing code
  // Checks which tracer is selected and then calls appropriate functions to process trace directives
  public static void prepareAllTraces(CodeTranslator t, UmpleModel model, UmpleClass aClass, Map<String,String> templateLookups)
  {    
    //-- if Console tracer is used
    if ("Console".equals(model.getTraceType()))
    {
    	prepareConsoleTraces(aClass,t,templateLookups);
    }
    //-- if File tracer is used
    else if ("File".equals(model.getTraceType()))
    {
    	prepareFileTraces(aClass,t,templateLookups);
    }
    //-- if String tracer is used
    else if ("String".equals(model.getTraceType()))
    {
    	prepareStringTraces(model,aClass,t,templateLookups);
    }
  }

  // "Console Tracer" Look through all traces and inject the necessary code, it requires the following lookup
  //  + consoleTemplate
  private static void prepareConsoleTraces( UmpleClass aClass, CodeTranslator t, Map<String,String> templateLookups) 
  {
	  String consoleTemplate = templateLookups.get("consoleTemplate");
	  
	  // Go over each trace directive
	  for (TraceDirective traceDirective : aClass.getTraceDirectives())
	  {  
	    	// if the traceItem is an attribute
	        if (traceDirective.hasAttributes())
	        {
	        	processTraceDirectiveAttributes(traceDirective,t,consoleTemplate);	
	        }
	  }
  }

  // "File Tracer" Look through all traces and inject the necessary code, it requires the following lookup
  //  + fileTemplate
  private static void prepareFileTraces(UmpleClass aClass, CodeTranslator t, Map<String,String> templateLookups) 
  {
	  String fileTemplate = templateLookups.get("fileTemplate");
	  
	  // Go over each trace directive
	  for (TraceDirective traceDirective : aClass.getTraceDirectives())
	  {
		  // if the traceItem is an attribute
          if (traceDirective.hasAttributes())
          {
        	  processTraceDirectiveAttributes(traceDirective,t,fileTemplate);
          }
	  }
  }

  // "String Tracer" Look through all traces and inject the necessary code it requires the following lookups
  //  + stringTemplate
  //  + dependPackage 
  //  + executeMethod
  private static void prepareStringTraces(UmpleModel model, UmpleClass aClass, CodeTranslator t, Map<String,String> templateLookups) 
  {
	  String stringTemplate = templateLookups.get("stringTemplate");
	  String dependPackage = templateLookups.get("dependPackage");
	  String extraCode = templateLookups.get("extraCode");
	  
	  // Go over each trace directive
	  for (TraceDirective traceDirective : aClass.getTraceDirectives())
	  {
		  Map<String,String> lookups = new HashMap<String,String>();
		  String packageName = model.getDefaultPackage() == null ? "cruise.util" : model.getDefaultPackage();
          lookups.put("packageName",packageName);
          lookups.put("extraCode",extraCode);
          GeneratorHelper.prepareStringTracer(model, lookups);

          if (dependPackage != null && !packageName.equals(aClass.getPackageName()))
          {
            Depend d = new Depend(packageName + ".*");
            d.setIsInternal(true);
            aClass.addDepend(d);
          }
          // if the traceItem is an attribute
          if (traceDirective.hasAttributes())
          {
        	  processTraceDirectiveAttributes(traceDirective,t,stringTemplate);	 
          }
	  }
  }

  // Process every attribute in a trace directive
  private static void processTraceDirectiveAttributes( TraceDirective traceDirective, CodeTranslator t, String template ) 
  {	  
	  // Go over all attributes in trace directive
	  for( int i = 0 ; i < traceDirective.numberOfAttributes() ; ++i )
	  {
		  Attribute attr = traceDirective.getAttribute(i);
		  String attrCode = null, conditionType = null;
  		
		  // Process trace directive conditions if it has any 
		  if( traceDirective.hasCondition() )
		  {
			  processTraceCondition(traceDirective,t,template,attr);		
		  }
  		  else
  		  {
  			  // simple trace directive that traces attributes without any extra fragments
      		  attrCode = StringFormatter.format(template,t.translate("attribute",traceDirective.getAttribute(i)),t.translate("parameter",traceDirective.getAttribute(i)));
      		  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);  
  		  }
  		
		  if( traceDirective.getPeriodClause() != null )
  		  {
  			  attrCode = "Thread thr1 = new Thread(tracePeriod(" + preparePeriod(traceDirective.getPeriodClause()) + "));\n";
  			  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);
  			  attrCode = "thr1.start();\n";
  			  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);
    		  attrCode = StringFormatter.format(template,t.translate("attribute",traceDirective.getAttribute(i)),t.translate("parameter",traceDirective.getAttribute(i)));
    		  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);
  		  }
  	  }
  }

  // Process condition in a trace directive
  private static void processTraceCondition( TraceDirective traceDirective, CodeTranslator t, String template, Attribute attr ) 
  {
	  String attrCode = null, conditionType = null;
	  TraceCondition tc = traceDirective.getCondition(0);
	  
	  if( tc.getConditionType().equals("where") )
	  {
		  attrCode = "if( " + tc.getLhs() + " " + tc.getRhs().getComparisonOperator() + " " + tc.getRhs().getRhs() + " )";
		  conditionType = "where";	  
	  }
	  else if( tc.getConditionType().equals("until") )  
	  {  
		  attrCode = "if( " + tc.getLhs() + " " + getComparisonOperatorInverse(tc.getRhs().getComparisonOperator()) + " " + tc.getRhs().getRhs() + " )";  
		  conditionType = "until";		 
	  }
	  else if( tc.getConditionType().equals("after") )
	  {
		  attrCode = "if( " + tc.getLhs() + " " + tc.getRhs().getComparisonOperator() + " " + tc.getRhs().getRhs() + " )";
		  conditionType = "after";  
	  }
	   
	  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);

	  attrCode = "{";
	  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);
	  	  
	  attrCode = "  " + StringFormatter.format(template,t.translate("attribute",attr),t.translate("parameter",attr));
	  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);

	  attrCode = "}";
	  GeneratorHelper.prepareTraceDirectiveInject(traceDirective,t,attr,attrCode,conditionType);
  }
  
  private static String getComparisonOperatorInverse(String co) {
	  if( co.equals("==") ) return "!=";
	  else if( co.equals("!=") ) return "==";
	  else if( co.equals(">") ) return "<=";
	  else if( co.equals("<") ) return ">=";
	  else if( co.equals(">=") ) return "<";
	  else if( co.equals("<=") ) return ">";
	  
	  return null;
  }
  
  private static String preparePeriod(String periodClause) {
	  
	  // split periodClause into two strings (1) contains numeric time (2) contains time unit
	  String[] period = periodClause.split("s|m");
	  
	  // ToDo return time depending on kind of unit used (second or millisecond)
	  return period[0];
  }
  //====================== End of Tracing code
   
  private boolean prepareTimedEvents(StateMachine sm)
  {
    boolean hasTimedEvents = false;
    for (State s : sm.getStates())
    {
      for (Transition t : s.getTransitions())
      {
        Event e = t.getEvent();
        if (!e.getIsTimer())
        {
          continue;
        }
        hasTimedEvents = true;
        Action entryAction = new Action(StringFormatter.format("{0}();",translate("eventStartMethod",e)));
        entryAction.setActionType("entry");
        
        Action exitAction = new Action(StringFormatter.format("{0}();",translate("eventStopMethod",e)));
        exitAction.setActionType("exit");

        t.getFromState().addAction(entryAction);
        t.getFromState().addAction(exitAction);
      }
    }
    return hasTimedEvents;
  }
  
  private void prepareNestedStatesFor(StateMachine sm,int concurrentIndex)
  {
    if (sm.getParentState() != null)
    {
      State parentState = sm.getParentState();
      Map<String,String> lookups = new HashMap<String,String>();
      lookups.put("entryEventName",translate("enterMethod",parentState));
      lookups.put("exitEventName",translate("exitMethod",parentState));
      lookups.put("parentEntryActionCode",StringFormatter.format("if ({0} == {1}.{2}) { {3}({1}.{4}); }"
          ,translate("stateMachineOne",sm)
          ,translate("type",sm)
          ,translate("stateNull",sm)
          ,translate("setMethod",sm)
          ,translate("stateOne",sm.getStartState()))
      );
      lookups.put("parentExitActionCode",StringFormatter.format("{0}();",translate("exitMethod",parentState)));
      GeneratorHelper.prepareNestedStateMachine(sm,concurrentIndex,lookups);  
    }

    for (State s : sm.getStates())
    {
      int nestedSmIndex = 0;
      for (StateMachine nestedSm : s.getNestedStateMachines())
      {
        prepareNestedStatesFor(nestedSm,nestedSmIndex);
        nestedSmIndex += 1;
      }
    }
  }
  
  private void generateConstructorSignature(GeneratedClass genClass)
  {
    StringBuffer signature = new StringBuffer();
    StringBuffer signatureCaller = new StringBuffer();
    
    UmpleClass uClass = genClass.getUClass();
    
    if (uClass.getExtendsClass() != null) 
    {
      GeneratedClass parent = genClass.getParentClass();
      signature.append(parent.getLookup("constructorSignature"));
      signatureCaller.append(parent.getLookup("constructorSignature_caller"));
    }    
    
    for (Attribute av : uClass.getAttributes()) 
    {
      if (av.getIsAutounique() || av.getIsList() || "defaulted".equals(av.getModifier()) || av.getIsLazy() || av.getValue() != null)
      {
        continue;
      }

      if (signature.length() > 0) 
      {
        signature.append(", ");
        signatureCaller.append(", ");
      }
        
      signature.append(StringFormatter.format("{0} {1}",typeOf(av),nameOf(av)));
      signatureCaller.append(nameOf(av));      
    }

    for (AssociationVariable av : uClass.getAssociationVariables()) 
    {
      AssociationVariable relatedAv = av.getRelatedAssociation();

      if (av.getMultiplicity().getLowerBound() == 0 || !av.getIsNavigable())
      {
        continue;
      }
      
      if (relatedAv.getIsNavigable())
      {
        if (av.isMandatoryMany() && relatedAv.isMandatory())
        {
          continue;
        }
        
        if ((av.isMN() || av.isN()) && relatedAv.isOptionalN())
        {
          continue;
        }
      }
      
      if (signature.length() > 0) 
      {
        signature.append(", ");
        signatureCaller.append(", ");
      }
      
      String typeModifier = av.isMany() ? "..." : "";
      signature.append(StringFormatter.format("{0}{1} {2}",typeOf(av),typeModifier,nameOf(av)));
      signatureCaller.append(nameOf(av));      
    }

    genClass.setLookup("constructorSignature", signature.toString());
    genClass.setLookup("constructorSignature_caller", signatureCaller.toString());
  }
  
  private void generateSecondaryConstructorSignatures(GeneratedClass genClass)
  {
    UmpleClass uClass = genClass.getUClass();
    
    String mandatorySignature = genClass.getLookup("constructorSignature");
    
    for (AssociationVariable av : uClass.getAssociationVariables()) 
    {
      AssociationVariable relatedAv = av.getRelatedAssociation();
      if (av.isOnlyOne() && relatedAv.isOnlyOne() && av.getIsNavigable() && relatedAv.getIsNavigable())
      {
        UmpleClass relatedClass = model.getUmpleClass(av.getType());
        GeneratedClass relatedGen = relatedClass.getGeneratedClass();
        
        String selfParameter = StringFormatter.format("{0} {1}",typeOf(relatedAv),nameOf(relatedAv));
        String selfFor = StringFormatter.format("For{0}",av.getUpperCaseName());
        String newParameters = relatedGen.getLookup("constructorSignature");
        newParameters = StringFormatter.replaceParameter(newParameters, selfParameter, null);
        newParameters = StringFormatter.appendParameter(newParameters, selfFor);

        String relatedParameter = StringFormatter.format("{0} {1}",typeOf(av),nameOf(av));
        
        mandatorySignature = StringFormatter.replaceParameter(mandatorySignature, relatedParameter, newParameters);
        genClass.setLookup("constructorSignature_mandatory", mandatorySignature);
        
        String relatedFor = StringFormatter.format("For{0}",relatedAv.getUpperCaseName());
        String relatedCaller = genClass.getLookup("constructorSignature_caller");
        String relatedCallerParameter = nameOf(av);
        String mandatorySignatureCaller = StringFormatter.replaceParameter(relatedCaller, relatedCallerParameter, "_THIS_");
        mandatorySignatureCaller = StringFormatter.appendParameter(mandatorySignatureCaller, relatedFor);
        mandatorySignatureCaller = StringFormatter.replaceParameter(mandatorySignatureCaller, "_THIS_" + relatedFor, "this");
        
        String mandatoryCallerId = "constructorSignature_mandatory_" + relatedAv.getName();
        relatedGen.setLookup(mandatoryCallerId, mandatorySignatureCaller);
      }
    }
  }
  
  private void addRelatedImports()
  {
    for (UmpleClass aClass : model.getUmpleClasses())
    {
      GeneratedClass genClass = aClass.getGeneratedClass();
      
      if (aClass.getExtendsClass() != null)
      {
        if (!aClass.getPackageName().equals(aClass.getExtendsClass().getPackageName()))
        {
          genClass.addMultiLookup("import", aClass.getExtendsClass().getPackageName() + ".*");  
        }
        addImports(aClass.getExtendsClass(),genClass);
      }
      
      for (AssociationVariable av : aClass.getAssociationVariables()) 
      {
        if (!av.getIsNavigable())
        {
          continue;
        }
        
        AssociationVariable relatedAssociation = av.getRelatedAssociation();
        if (relatedAssociation.isOnlyOne())
        { 
          UmpleClass relatedClass = model.getUmpleClass(av.getType());
          while (relatedClass != null)
          {
            addAttributeImports(relatedClass,genClass);
            relatedClass = relatedClass.getExtendsClass();
          }
        }
      }
    }
  }
  
  private void addAttributeImports(UmpleClass aClass, GeneratedClass genClass)
  {
    String timeImport = "java.sql.Time";
    String dateImport = "java.sql.Date";
    String utilImport = "java.util.*";
    
    for (Attribute av : aClass.getAttributes()) 
    {
      
      if ("Time".equals(av.getType()))
      {
        genClass.addMultiLookup("import", timeImport);
      }
      else if ("Date".equals(av.getType()))
      {
        genClass.addMultiLookup("import", dateImport);
      }
      
      if (av.getIsList())
      {
        genClass.addMultiLookup("import", utilImport);
      }
    }
  }
  
  private void addImports(UmpleClass aClass, GeneratedClass genClass)
  {
    addAttributeImports(aClass,genClass);
      
    for (AssociationVariable av : aClass.getAssociationVariables()) 
    {
      if (!av.getIsNavigable())
      {
        continue;
      }
      
      if (av.isMany())
      {
        genClass.addMultiLookup("import", "java.util.*");
      }
    }
    
    for (String namespace : aClass.getNamespaces())
    {
      if (!namespace.equals(aClass.getPackageName()))
      {
        genClass.addMultiLookup("import", namespace + ".*");
      }
    }
  }
  
  private String nameOf(Attribute av)
  {
    return nameOf(av.getName(),av.getIsList());
  }
  
  private String nameOf(AssociationVariable av)
  {
    boolean hasMultiple = av.isMany();
    return nameOf(av.getName(),hasMultiple);
  }
  
  private static String typeOf(UmpleVariable var)
  {
    String aType = var.getType();
    return typeOf(aType);
  }
  
  private void writeFile(UmpleElement aClass) throws IOException
  {
    ILang language = getLanguageFor(aClass);
    
    String path = StringFormatter.addPathOrAbsolute( 
    						  model.getUmpleFile().getPath(), 
        	                  getOutput()) + 
        	                  aClass.getPackageName().replace(".", File.separator);
    
    String filename = path + File.separator + aClass.getName() + ".java";
    File file = new File(path);
    file.mkdirs();

    BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
    String contents = language.getCode(model, aClass);
    model.getGeneratedCode().put(aClass.getName(),contents);
    try
    {
      bw.write(contents);
      bw.flush();
    }
    finally
    {
      bw.close();
    }
  }
}