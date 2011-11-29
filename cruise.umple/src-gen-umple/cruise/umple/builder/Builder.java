/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.umple.builder;
import java.io.*;
import java.net.*;
import org.apache.tools.ant.*;

public class Builder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Builder()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public URL compile(String directory, String jarname, String projectName, String javaTarget)
  {

    String buildfile = createAntFile(directory, "build-"+ projectName +".xml", jarname, javaTarget);
    if (buildfile == null)
    {
      return null;
    }
    
    try {
      runAnt(buildfile);
      File jarfile = new File(directory + "//" + jarname) ;
      return jarfile.exists() ? new URL("jar:file:///" + jarfile.getAbsolutePath() + "!/") : null;
    } 
    catch (MalformedURLException e) 
    {
      return null;
    }
    catch (BuildException e2)
    {
      return null;
    }
  }
  
  public void load(String directory, String jarname) 
  {
    try {
      //String jarfile = "jar:file:///" + new File(directory).getAbsolutePath() + "/"+ jarname +"!/";
      DynamicClassPathLoader.addJar(directory, jarname);
    } 
    catch (IOException e) 
    {
      throw new RuntimeException("Unable to load application jar",e);
    }
  }
  
  public boolean runAnt(String buildFilename)
  {
    try
    {
      Process p = Runtime.getRuntime().exec("ant -f " + (new File(buildFilename)).getAbsolutePath());
      p.waitFor();
      return true;  
    }
    catch (IOException e)
    {
      return false;
    }
    catch (InterruptedException e2)
    {
      return false;
    }
  }
  
  public void runAntOLD(String buildFilename)
  {
    File buildFile = new File(buildFilename);
    Project p = new Project();
    p.setUserProperty("ant.file", buildFile.getAbsolutePath());
    p.init();
    ProjectHelper helper = ProjectHelper.getProjectHelper();
    p.addReference("ant.projectHelper", helper);
    helper.parse(p, buildFile);
    p.executeTarget(p.getDefaultTarget());    
  }
  
  private String createAntFile(String baseDirectory, String buildname, String jarname, String javaTarget)
  {
    
    String antScript = "" +
      "<project name=\"runtime-compiler\" default=\"go\" basedir=\".\">\n" +
      "  <target name=\"clean\">\n" +
      "    <delete file=\""+ jarname +"\" failonerror=\"false\" />\n" +
      "  </target>\n" +
      "  <target name=\"compile\">\n" +
      "    <javac debug=\"true\" debuglevel=\"source,lines,vars\" includeAntRuntime=\"false\" destdir=\".\" srcdir=\".\" target=\""+ javaTarget +"\">\n" +
      "      <exclude name=\"**/.svn\"/>\n" +
      "    </javac>\n" +
      "  </target>\n" +
      "  <target name=\"jar\">\n" +
      "    <jar destfile=\""+ jarname +"\" basedir=\".\" excludes=\"**/.svn\" />\n" +
      "  </target>\n" +
      "  <target name=\"go\" depends=\"clean,compile,jar\" /> \n" +       
      "</project>";
    
    try {
      String buildfile = baseDirectory + "/" + buildname;
      BufferedWriter out = new BufferedWriter(new FileWriter(buildfile));
      out.write(antScript);
      out.close();
      return buildfile;
    } 
    catch (IOException e) 
    {
      return null;
    }
  }
}