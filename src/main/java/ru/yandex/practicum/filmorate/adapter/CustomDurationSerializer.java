package ru.yandex.practicum.filmorate.adapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

@JsonComponent
public class CustomDurationSerializer {

    public static class LocalDurationSerializer extends JsonSerializer<Duration> {

        private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public void serialize(Duration value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (isNull(value)) {
                return;
            }
            gen.writeNumber(value.getSeconds() / 60);
        }
    }
}
