package WebAdmin.mapper;

import WebAdmin.vo.apartment.ApartmentItemVo;
import WebAdmin.vo.apartment.ApartmentQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import model.entity.ApartmentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.ApartmentInfo
*/
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    List<ApartmentItemVo> getPages(IPage<ApartmentItemVo> page, ApartmentQueryVo query);
}




