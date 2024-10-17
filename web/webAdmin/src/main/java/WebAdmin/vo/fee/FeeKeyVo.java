package WebAdmin.vo.fee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.entity.FeeKey;
import model.entity.FeeValue;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class FeeKeyVo extends FeeKey {

    @Schema(description = "杂费value列表")
    private List<FeeValue> feeValueList;
}
