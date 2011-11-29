/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.umple.builder;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;

public class DynamicClassPathLoader
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DynamicClassPathLoader()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  private static final Class<?>[] parameters = new Class[]{URL.class};

  public static void addJar(String baseDirectory, String jarname) throws IOException
  {
    URL url = new URL("jar:file:///" + new File(baseDirectory).getAbsolutePath() + "/"+ jarname +"!/");
    DynamicClassPathLoader.addURL(url);
  }
  
  public static void addURL(URL url) throws IOException
  {
    URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
    Class<?> sysclass = URLClassLoader.class;

    try
    {
      Method method = sysclass.getDeclaredMethod("addURL",parameters);
      method.setAccessible(true);
      method.invoke(sysloader,new Object[]{ url });
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error, could not add URL to system classloader",e);
    }
  }
}