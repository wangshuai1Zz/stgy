package WebAdmin.vo.apartment;

import WebAdmin.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.entity.ApartmentInfo;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Schema(description = "公寓信息")
@Data
public class ApartmentSubmitVo extends ApartmentInfo {

    @Schema(description="公寓配套id")
    private List<Long> facilityInfoIds;

    @Schema(description="公寓标签id")
    private List<Long> labelIds;

    @Schema(description="公寓杂费值id")
    private List<Long> feeValueIds;

    @Schema(description="公寓图片id")
    private List<GraphVo> graphVoList;

}
