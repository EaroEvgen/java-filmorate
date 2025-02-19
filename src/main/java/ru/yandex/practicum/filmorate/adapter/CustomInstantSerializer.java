package ru.yandex.practicum.filmorate.adapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

@JsonComponent
public class CustomInstantSerializer {

    public static class LocalInstantSerializer extends JsonSerializer<Instant> {

        private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (isNull(value)) {
                return;
            }
            OffsetDateTime timeUtc = value.atOffset(ZoneOffset.systemDefault().getRules().getOffset(value));
            gen.writeString(timeUtc.format(timeFormatter));
        }
    }
}

