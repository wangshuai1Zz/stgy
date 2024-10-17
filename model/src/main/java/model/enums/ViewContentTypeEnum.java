package model.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ViewContentTypeEnum {
    DEFAULT("default","application/octet-stream"),
    PNG("png", "image/png"),
    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpeg"),
    GIF("gif", "image/gif"),
    WBMP("wbmp", "image/vnd.wap.wbmp"),
    TIFF("tiff", "image/tiff"),
    JFIF("jfif", "image/jpeg"),
    TIF("tif", "image/tiff"),
    FAX("fax", "image/fax"),
    JPE("jpe", "image/jpeg"),
    NET("net", "image/pnetvue"),
    RP("rp", "image/vnd.rn-realpix"),
    ICO("ico", "image/x-icon");

    private final String prefix;

    private final String type;

    private static final Map<String, ViewContentTypeEnum> ENUM_MAP = new HashMap<>();

    static {
        ViewContentTypeEnum[] values = values();
        for (ViewContentTypeEnum value : values) {
            ENUM_MAP.put(value.getPrefix(), value);
        }
    }

    public static String getTypeByPrefix(String prefix) {
        ViewContentTypeEnum viewContentTypeEnum = ENUM_MAP.get(prefix);
        if (viewContentTypeEnum == null) {
            return prefix;
        }
        return viewContentTypeEnum.getType();
    }

    public static String getContentType(String prefix){
        if(StringUtils.isEmpty(prefix)){
            return DEFAULT.getType();
        }
        prefix = prefix.substring(prefix.lastIndexOf(".") + 1);
        String type = getTypeByPrefix(prefix);
        if (StringUtils.isNotEmpty(type)) {
            return type;
        }
        return DEFAULT.getType();
    }

    ViewContentTypeEnum(String prefix, String type) {
        this.prefix = prefix;
        this.type = type;
    }

}