package ru.yandex.practicum.filmorate.adapter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@ConfigurationPropertiesBinding
public class InstantConverter implements Converter<String, Instant> {
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Instant convert(String source) {
        return LocalDate.parse(source, timeFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}
