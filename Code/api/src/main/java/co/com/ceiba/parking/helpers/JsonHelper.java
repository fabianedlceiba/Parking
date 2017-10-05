package co.com.ceiba.parking.helpers;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHelper {

  private JsonHelper() {
    throw new IllegalStateException("Utility class");
  }

  public static byte[] toJson(Object object) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsBytes(object);
  }

}
