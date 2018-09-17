package edu.psu.swe.commons.jaxrs.patch;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

import edu.psu.swe.commons.jaxrs.patch.exception.FailedOperationException;
import edu.psu.swe.commons.jaxrs.patch.exception.PatchOperationFailedException;
import edu.psu.swe.commons.jaxrs.patch.exception.PatchTestFailedException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PatchRequest {

  ArrayList<PatchOperation> patchOperationList;

  public PatchRequest(List<PatchOperation> patchOperationList) {
    this.patchOperationList = new ArrayList<>(patchOperationList);
  }

  public void apply(Object object) throws PatchOperationFailedException, PatchTestFailedException {
    int operationNumber = 0;

    try {
      for (PatchOperation patchOperation : this.patchOperationList) {
        JsonReference path = patchOperation.getPath().pointer;

        if (path == null) {
          throw new PatchOperationFailedException(operationNumber, "path is null");
        }
        JsonNode value = patchOperation.getValue();

        // non-existent properties are null, not NullNode
        if (value == null) {
          value = NullNode.getInstance();
        }
        switch (patchOperation.getOperation()) {
        case ADD: {
          path.add(object, null, value);
        } break;

        case COPY: {
          throw new PatchOperationFailedException(operationNumber, "copy: not implemented");
        }

        case MOVE: {
          throw new PatchOperationFailedException(operationNumber, "move: not implemented");
        }

        case REMOVE: {
          path.remove(object, null, value);
        } break;

        case REPLACE: {
          path.set(object, null, value);
        } break;

        case TEST: {
          if (!path.test(object, null, value)) {
            throw new PatchTestFailedException(operationNumber, "A test did not pass");
          }
        } break;
        }
        ++operationNumber;
      }
    } catch (PropertyTraversalException | FailedOperationException operationException) {
      throw new PatchOperationFailedException(operationNumber, operationException);
    }
  }
}
