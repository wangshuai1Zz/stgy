package WebAdmin.service.impl;

import WebAdmin.mapper.CityInfoMapper;
import WebAdmin.service.CityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.CityInfo;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【city_info】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService {

}




