package WebAdmin.mapper;

import model.entity.LeaseTerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_term(租期)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.LeaseTerm
*/
public interface LeaseTermMapper extends BaseMapper<LeaseTerm> {

}




