//package utils;
//
//import model.enums.ItemType;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StringToEnum implements Converter<String, ItemType> {
//
//    @Override
//    public ItemType convert(@NotNull String source) {
//        for (ItemType value : ItemType.values()) {
//            if (value.getCode().toString().equals(source)){
//                return value;
//            }
//        }
//        return null;
//    }
//}
