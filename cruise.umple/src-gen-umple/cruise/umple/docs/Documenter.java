/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.umple.docs;
import java.io.*;
import java.net.*;
import cruise.umple.util.*;
import cruise.umple.compiler.*;
import java.util.*;

public class Documenter
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Documenter Attributes
  private String inputPath;
  private String outputPath;
  private List<String> messages;

  //Documenter Associations
  private ContentParser parser;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Documenter(String aInputPath, String aOutputPath)
  {
    inputPath = aInputPath;
    outputPath = aOutputPath;
    messages = new ArrayList<String>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInputPath(String aInputPath)
  {
    boolean wasSet = false;
    inputPath = aInputPath;
    wasSet = true;
    return wasSet;
  }

  public boolean setOutputPath(String aOutputPath)
  {
    boolean wasSet = false;
    outputPath = aOutputPath;
    wasSet = true;
    return wasSet;
  }

  public boolean addMessage(String aMessage)
  {
    boolean wasAdded = false;
    wasAdded = messages.add(aMessage);
    return wasAdded;
  }

  public boolean removeMessage(String aMessage)
  {
    boolean wasRemoved = false;
    wasRemoved = messages.remove(aMessage);
    return wasRemoved;
  }

  public String getInputPath()
  {
    return inputPath;
  }

  public String getOutputPath()
  {
    return outputPath;
  }

  public String getMessage(int index)
  {
    String aMessage = messages.get(index);
    return aMessage;
  }

  public String[] getMessages()
  {
    String[] newMessages = messages.toArray(new String[messages.size()]);
    return newMessages;
  }

  public int numberOfMessages()
  {
    int number = messages.size();
    return number;
  }

  public boolean hasMessages()
  {
    boolean has = messages.size() > 0;
    return has;
  }

  public int indexOfMessage(String aMessage)
  {
    int index = messages.indexOf(aMessage);
    return index;
  }

  public ContentParser getParser()
  {
    return parser;
  }

  public boolean setParser(ContentParser newParser)
  {
    boolean wasSet = false;
    parser = newParser;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    parser = null;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public boolean generate()
  {
    File inputDirectory = new File(getInputPath());
    if (!inputDirectory.exists())
    {
      addMessage("Unknown directory: " + getInputPath());
      return false;
    }

    setParser(new ContentParser("content"));

    parseGroupOrder(inputDirectory);
    parseContent(inputDirectory);
    
    if (getParser().analyze().getWasSuccess())
    {
      String message = "Created Goups:";
      for (Group g : getParser().getGroups())
      {
        message += " [" + g.getName() + "]";
      }
      addMessage(message);
      publish(getOutputPath());
      return true;
    }
    else
    {
      addMessage("Unable to analyze files in " + getInputPath());
      return false;
    }
  }

  public boolean publish(String path)
  {
    File file = new File(path);
    file.mkdirs();
    
    String navigationOutput = toNavigationHtml();
    Hashtable<String,String> referenceLookup = createReferenceLookup();
    for (Group group : getParser().getGroups())
    {
      for (Content content : group.getContents())
      {
        if (content.getShouldIncludeReferences())
        {
          updateReferences(content,referenceLookup);
        }
        String htmlOutput = toHtml(content, navigationOutput);
        
        if (htmlOutput.length() == 0)
        {
          addMessage("Failed on: " + content.getTitle());
          return false;
        }
        
        String filename = path + File.separator + content.getTitleFilename();
        SampleFileWriter.createFile(filename,htmlOutput);
        addMessage("Created: " + filename);
      }
    }
    return true;
  }
  
  public String toHtml(String title)
  {
    Content selectedContent = null;
    
    for (Group group : getParser().getGroups())
    {
      for (Content content : group.getContents())
      {
        if (content.getTitle().equals(title))
        {
          selectedContent = content;
          break;
        }
      }
    }
    
    return toHtml(selectedContent, toNavigationHtml());
  }

  private Hashtable<String, String> createReferenceLookup()
  {
    Hashtable<String, String> referenceLookup = new Hashtable<String, String>();
    for (Group group : getParser().getGroups())
    {
      for (Content content : group.getContents())
      {
        referenceLookup.put(content.getTitle(), content.getTitleFilename());
      }
    }
    return referenceLookup;
  }

  private void updateReferences(Content content, Hashtable<String,String> referenceLookup)
  {
    String newSyntax = content.getSyntax();
    String newDescription = content.getDescription();
    for(Iterator<String> iTitle = referenceLookup.keySet().iterator(); iTitle.hasNext();)
    {
      String title = iTitle.next();
      String titleFilename = referenceLookup.get(title);
      
      if (title.equals(content.getTitle()))
      {
        continue;
      }
      
      if (newSyntax != null)
      {
        newSyntax = newSyntax.replaceAll(title, StringFormatter.format("<a href=\"{1}\">{0}</a>",title,titleFilename));
      }
      if (newDescription != null)
      {
        newDescription = newDescription.replaceAll(title, StringFormatter.format("<a href=\"{1}\">{0}</a>",title,titleFilename));
      }
    }
    content.setSyntax(newSyntax);
    content.setDescription(newDescription);
  }

  private void parseContent(File inputDirectory)
  {
    File[] allFiles = SampleFileWriter.getAllFiles(inputDirectory);
    for (File aFile : allFiles)
    {
      if (aFile.getName().endsWith(".txt"))
      {
        if (!getParser().parse("content", SampleFileWriter.readContent(aFile)).getWasSuccess())
        {
          addMessage("Unable to parse "+ getParser().getParseResult().getPosition() +": " + aFile.getName() );
        }
      }
    }
  }
  
  private void parseGroupOrder(File inputDirectory)
  {
    File[] allFiles = SampleFileWriter.getAllFiles(inputDirectory);
    for (File aFile : allFiles)
    {
      if ("order.group".equals(aFile.getName()))
      {
        getParser().parse("groupOrder", SampleFileWriter.readContent(aFile));
      }
    }
  }
  
  private String toHtml(Content selectedContent, String navigationOutput)
  {
    int endOfExampleBeforePosition=0;
    
    if (selectedContent == null)
    {
      return "";
    }
    
    String htmlOutput = Template.HtmlTemplate;
    htmlOutput = htmlOutput.replace("@@TITLE@@", selectedContent.getTitle());
    htmlOutput = htmlOutput.replace("@@NAVIGATION@@", navigationOutput);
    htmlOutput = htmlOutput.replace("@@DESCRIPTION@@", selectedContent.getDescription());
    
    if (selectedContent.getSyntax() == null)
    {
      htmlOutput = htmlOutput.replace("@@SYNTAX@@", "");
    }
    else
    {
      String syntaxHtml = Template.SyntaxTemplate.replace("@@SYNTAX_CODE@@",selectedContent.getSyntax());
      htmlOutput = htmlOutput.replace("@@SYNTAX@@", syntaxHtml);
    }
    
    String exampleOutput = "";
    for (String example : selectedContent.getExamples())
    {
      String nextExample = Template.ExampleTemplate;
      String exampleHeader = exampleOutput.length() == 0 ? "Example" : "Another Example";
      nextExample = nextExample.replace("@@EXAMPLE_NUMBER@@",exampleHeader);
      
      endOfExampleBeforePosition = example.indexOf("//$?[End_of_model]$?");
      if(endOfExampleBeforePosition == -1) {endOfExampleBeforePosition = example.length();}
      nextExample = nextExample.replace("@@EXAMPLE_CODE@@", example.substring(0,endOfExampleBeforePosition));
      try {
        nextExample = nextExample.replace("@@EXAMPLE_CODE_URL@@",
            URLEncoder.encode(example,"UTF-8").replaceAll("\\+","%20").replaceAll("%2B","%252B"));
      }
      catch (java.io.UnsupportedEncodingException e) {
      }
      exampleOutput += nextExample;
    }
    htmlOutput = htmlOutput.replace("@@EXAMPLE@@", exampleOutput);
    htmlOutput = htmlOutput.replace("@@UMPLE_GRAMMAR@@", (new UmpleInternalParser()).toGrammar());
    return htmlOutput;
  }
  
  private String toNavigationHtml()
  {
    String navigationOutput = "";
    for (Group group : getParser().getGroups())
    {
      String nextGroupHeader = Template.NavigationHeaderTemplate;
      nextGroupHeader = nextGroupHeader.replace("@@NAVIGATION_HEADER_NAME@@",group.getName());
      navigationOutput += nextGroupHeader;

      for (Content content : group.getContents())
      {
        String nextGroupItem = Template.NavigationItemTemplate;
        nextGroupItem = nextGroupItem.replace("@@NAVIGATION_ITEM_NAME@@",content.getTitle()); 
        nextGroupItem = nextGroupItem.replace("@@NAVIGATION_ITEM_FILENAME@@",content.getTitleFilename());
        navigationOutput += nextGroupItem;
      }
    }
    return navigationOutput;
  }
}