package com.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.util.Optional;

/**
 @see <a href="https://www.baeldung.com/java-convert-localdate-sql-date">
 www.baeldung.com/java-convert-localdate-sql-date</a>
 */

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate localDate) {
        return Optional.ofNullable(localDate)
                .map(d -> java.sql.Date.valueOf(d))
                .orElse(null);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date date) {
        return Optional.ofNullable(date)
                .map(d -> d.toLocalDate())
                .orElse(null);
    }
}
