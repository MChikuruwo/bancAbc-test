package zw.co.bancabc.commonutils.domain.converter;

import org.apache.commons.lang.StringUtils;
import zw.co.bancabc.commonutils.domain.value.login.IdNumber;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class IdNumberConverter implements AttributeConverter<IdNumber, String> {
    @Override
    public String convertToDatabaseColumn(IdNumber idNumber) {
        if (idNumber == null) return null;
        return idNumber.value();
    }

    @Override
    public IdNumber convertToEntityAttribute(String s) {
        if (StringUtils.isBlank(s)) return null;
        return new IdNumber(s);
    }
}