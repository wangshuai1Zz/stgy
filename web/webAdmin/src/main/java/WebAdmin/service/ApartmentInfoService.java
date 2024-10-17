package WebAdmin.service;

import WebAdmin.vo.apartment.ApartmentDetailVo;
import WebAdmin.vo.apartment.ApartmentItemVo;
import WebAdmin.vo.apartment.ApartmentQueryVo;
import WebAdmin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import model.entity.ApartmentInfo;
import model.enums.ReleaseStatus;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface ApartmentInfoService extends IService<ApartmentInfo> {

    /**
     * 根据条件获取分页
     * @param current
     * @param size
     * @param queryVo
     * @return
     */
    IPage<ApartmentItemVo> getPage(long current, long size, ApartmentQueryVo queryVo);

    void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo);

    void removeApartment(Long id);

    ApartmentDetailVo getApartment(Long id);

    void setStatusById(Long id, ReleaseStatus status);

    List<ApartmentInfo> getListByDistrict(Long id);
}
