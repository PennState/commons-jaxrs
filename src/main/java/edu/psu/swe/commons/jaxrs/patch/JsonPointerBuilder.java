package edu.psu.swe.commons.jaxrs.patch;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import edu.psu.swe.commons.jaxrs.patch.JsonPointerParser.ArrayIndexContext;
import edu.psu.swe.commons.jaxrs.patch.JsonPointerParser.EndOfArrayContext;
import edu.psu.swe.commons.jaxrs.patch.JsonPointerParser.JsonPointerContext;
import edu.psu.swe.commons.jaxrs.patch.JsonPointerParser.ReferenceTokenContext;
import lombok.Getter;

public class JsonPointerBuilder implements JsonPointerListener {

  List<Object> path = new ArrayList<>();

  @Override
  public void visitTerminal(TerminalNode node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitErrorNode(ErrorNode node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void enterJsonPointer(JsonPointerContext ctx) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void exitJsonPointer(JsonPointerContext ctx) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void enterReferenceToken(ReferenceTokenContext ctx) {
  }

  @Override
  public void exitReferenceToken(ReferenceTokenContext ctx) {
    String name = ctx.reference.getText().replace("~1", "/").replace("~0", "~");

    this.path.add(name);
  }

  @Override
  public void enterArrayIndex(ArrayIndexContext ctx) {
  }

  @Override
  public void exitArrayIndex(ArrayIndexContext ctx) {
    int arrayIndex = Integer.parseInt(ctx.index.getText());

    this.path.add(arrayIndex);
  }

  @Override
  public void enterEndOfArray(EndOfArrayContext ctx) {
  }

  @Override
  public void exitEndOfArray(EndOfArrayContext ctx) {
    int arrayIndex = -1;

    this.path.add(arrayIndex);
  }

  public JsonReference build() {
    return this.build(0);
  }

  private JsonReference build(int index) {
    JsonReference jsonReference;

    if (index < this.path.size()) {
      Object pathPart = this.path.get(index);
      JsonReference next = this.build(index + 1);
      jsonReference = pathPart instanceof String ? new PropertyName((String) pathPart, next) : new ArrayIndex((Integer) pathPart, next);
    } else {
      jsonReference = null;
    }
    return jsonReference;
  }
}
