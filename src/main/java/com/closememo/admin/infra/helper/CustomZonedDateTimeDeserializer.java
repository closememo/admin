package com.closememo.admin.infra.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

  private static final long serialVersionUID = -5194994314367299900L;

  private final static DateTimeFormatter ISO_LOCAL_DATE_TIME_WITH_ZONE =
      DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault());

  public CustomZonedDateTimeDeserializer() {
    this(null);
  }

  protected CustomZonedDateTimeDeserializer(Class<?> vc) {
    super(vc);
  }

  public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return ZonedDateTime.parse(p.getText(), ISO_LOCAL_DATE_TIME_WITH_ZONE);
  }
}
