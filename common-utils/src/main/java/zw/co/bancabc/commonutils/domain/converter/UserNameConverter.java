package zw.co.bancabc.commonutils.domain.converter;

import org.apache.commons.lang.StringUtils;
import zw.co.bancabc.commonutils.domain.value.UserName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserNameConverter implements AttributeConverter<UserName, String> {
    @Override
    public String convertToDatabaseColumn(UserName userName) {
        if (userName == null) return null;
        return userName.value();
    }

    @Override
    public UserName convertToEntityAttribute(String s) {
        if (StringUtils.isBlank(s)) return null;
        return new UserName(s);
    }
}