package edu.psu.converter;

import java.time.Instant;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class InstantPersistenceConverter implements AttributeConverter<Instant, Date> {

	@Override
	public Date convertToDatabaseColumn(Instant attribute) {
	    return Date.from(attribute);
	}

	@Override
	public Instant convertToEntityAttribute(Date dbData) {
	    return dbData.toInstant();
	}

}
