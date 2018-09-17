package edu.psu.swe.commons.jaxrs.patch;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatchUtil {

  public static List<PatchOperation> buildPatchOperationsForFields(Boolean currentValue, Boolean newValue, String patchPointerName) throws JsonPointerParseException {
    List<PatchOperation> patchOperations = new ArrayList<>();

    if ((currentValue == null && newValue != null) || (!currentValue.equals(newValue))) {
      JsonPointer jsonPointer = new JsonPointer(patchPointerName);
      patchOperations.add(PatchOperation.replace(jsonPointer, newValue));
      log.info("Updating {} to {}", patchPointerName, newValue);
    } else if (currentValue != null && newValue == null) {
      JsonPointer jsonPointer = new JsonPointer(patchPointerName);
      patchOperations.add(PatchOperation.remove(jsonPointer));
      log.info("Removing value {}", patchPointerName);
    }

    return patchOperations;
  }

  public static List<PatchOperation> buildPatchOperationsForFields(String currentValue, String newValue, String patchPointerName) throws JsonPointerParseException {
    List<PatchOperation> patchOperations = new ArrayList<>();

    if ((currentValue == null && newValue != null) || (!currentValue.equals(newValue))) {
      JsonPointer jsonPointer = new JsonPointer(patchPointerName);
      patchOperations.add(PatchOperation.replace(jsonPointer, newValue));
      log.info("Updating {} to {}", patchPointerName, newValue);
    } else if (currentValue != null && newValue == null) {
      JsonPointer jsonPointer = new JsonPointer(patchPointerName);
      patchOperations.add(PatchOperation.remove(jsonPointer));
      log.info("Removing value {}", patchPointerName);
    }

    return patchOperations;
  }

}
