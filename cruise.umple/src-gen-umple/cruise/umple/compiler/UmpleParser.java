/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/
package cruise.umple.compiler;

public interface UmpleParser
{
  public String toGrammar();
  public ParseResult parse(String ruleName, String codeToParse);
  public ParseResult analyze(boolean shouldGenerateCode);
  public Token getRootToken();
  public UmpleModel getModel();
  public boolean setModel(UmpleModel model);
  public ParseResult getParseResult();
}