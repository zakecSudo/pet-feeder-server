package com.degree.petFeeder.configuration;

import com.degree.petFeeder.enums.KeyEnum;
import com.degree.petFeeder.error.ApiException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;


@Component
public class StringToEnumConverterFactory implements ConverterFactory<String, KeyEnum> {

    @Override
    public <T extends KeyEnum> Converter<String, T> getConverter(Class<T> type) {
        return new StringToEnumConverter(type);
    }

    private static class StringToEnumConverter<T extends KeyEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (null == source) {
                return null;
            }
            for (T e : this.enumType.getEnumConstants()) {
                if (e.getKey().equalsIgnoreCase(source)) {
                    return e;
                }
            }
            throw new ApiException(ApiException.UNKNOWN_ENUM_VALUE, "Vrednost " + source + " ni definirana.");
        }
    }

}
