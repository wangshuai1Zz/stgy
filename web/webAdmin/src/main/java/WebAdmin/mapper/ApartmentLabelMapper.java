package WebAdmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.entity.ApartmentLabel;
import model.entity.LabelInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_label(公寓标签关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.ApartmentLabel
*/
public interface ApartmentLabelMapper extends BaseMapper<ApartmentLabel> {
    List<LabelInfo> getLabelByApartmentId(Long id);
}




