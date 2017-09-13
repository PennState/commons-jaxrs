package edu.psu.swe.commons.jaxrs.patch;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import lombok.Data;

@Data
public class JsonPointer {

  String jsonPointer;
  JsonReference pointer;

  public JsonPointer() {
    
  }

  public JsonPointer(String jsonPointer) throws JsonPointerParseException {
    this.jsonPointer = jsonPointer;
    this.parse(jsonPointer);
  }

  protected void parse(String jsonPointer) throws JsonPointerParseException {
    JsonPointerLexer l = new JsonPointerLexer(new ANTLRInputStream(jsonPointer));
    JsonPointerParser p = new JsonPointerParser(new CommonTokenStream(l));

    p.setBuildParseTree(true);
    p.addErrorListener(new BaseErrorListener() {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
      }
    });

    try {
      ParseTree parseTree = p.jsonPointer();
      JsonPointerBuilder jsonPointerBuilder = new JsonPointerBuilder();
      ParseTreeWalker.DEFAULT.walk(jsonPointerBuilder, parseTree);

      this.pointer = jsonPointerBuilder.build();
    } catch (IllegalStateException e) {
      throw new JsonPointerParseException(e);
    }
  }

  @Override
  public String toString() {
    return this.jsonPointer;
  }

}
