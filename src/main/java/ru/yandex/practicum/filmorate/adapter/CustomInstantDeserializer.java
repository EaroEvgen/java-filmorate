package ru.yandex.practicum.filmorate.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

@JsonComponent
public class CustomInstantDeserializer {
    public static class LocalInstantDeserializer extends JsonDeserializer<Instant> {

        private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        @Override
        public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String date = jsonParser.getText();
            if (date.isEmpty() || isNull(date)) {
                return null;
            }
            return LocalDate.parse(date, timeFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant();
        }
    }
}
