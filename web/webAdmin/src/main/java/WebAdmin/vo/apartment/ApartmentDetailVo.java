package WebAdmin.vo.apartment;

import WebAdmin.vo.fee.FeeValueVo;
import WebAdmin.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.entity.ApartmentInfo;
import model.entity.FacilityInfo;
import model.entity.LabelInfo;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "公寓信息")
@Data
public class ApartmentDetailVo extends ApartmentInfo {

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "标签列表")
    private List<LabelInfo> labelInfoList;

    @Schema(description = "配套列表")
    private List<FacilityInfo> facilityInfoList;

    @Schema(description = "杂费列表")
    private List<FeeValueVo> feeValueVoList;

}
