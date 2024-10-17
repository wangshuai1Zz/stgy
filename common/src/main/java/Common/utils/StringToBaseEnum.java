package Common.utils;

import model.enums.BaseEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component("StringToBaseEnum")
public class StringToBaseEnum implements ConverterFactory<String,BaseEnum> {

    @NotNull
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(@NotNull Class<T> targetType) {
        return source -> {
            T[] enumConstants = targetType.getEnumConstants();
            for (T enumConstant : enumConstants) {
                if (enumConstant.getCode().toString().equals(source)){
                    return enumConstant;
                }
            }
            return null;
        };
    }
}
