package co.com.ceiba.parking.helpers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

  private static final long serialVersionUID = 5334509585679436656L;

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

  public CustomLocalDateTimeSerializer() {
    this(null);
  }

  public CustomLocalDateTimeSerializer(Class<LocalDateTime> t) {
    super(t);
  }

  @Override
  public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
    gen.writeString(FORMATTER.format(value));
  }
}