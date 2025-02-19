package ru.yandex.practicum.filmorate.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Duration;

import static java.util.Objects.isNull;

@JsonComponent
public class CustomDurationDeserializer {
    public static class LocalDurationDeserializer extends JsonDeserializer<Duration> {

        @Override
        public Duration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String date = jsonParser.getText();
            if (date.isEmpty() || isNull(date)) {
                return null;
            }
            return Duration.ofMinutes(Integer.parseInt(date));
        }
    }
}
