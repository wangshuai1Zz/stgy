package WebAdmin.service.impl;

import WebAdmin.mapper.DistrictInfoMapper;
import WebAdmin.service.DistrictInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.DistrictInfo;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【district_info】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfo>
    implements DistrictInfoService {
}




