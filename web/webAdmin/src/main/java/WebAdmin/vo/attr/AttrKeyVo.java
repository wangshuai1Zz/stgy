package WebAdmin.vo.attr;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.entity.AttrKey;
import model.entity.AttrValue;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class AttrKeyVo extends AttrKey {

    @Schema(description = "属性value列表")
    private List<AttrValue> attrValueList;
}
