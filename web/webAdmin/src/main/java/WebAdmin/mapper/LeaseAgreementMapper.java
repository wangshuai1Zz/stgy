package WebAdmin.mapper;

import WebAdmin.vo.agreement.AgreementQueryVo;
import WebAdmin.vo.agreement.AgreementVo;
import model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.LeaseAgreement
*/
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    AgreementVo getAgreementById(Long id);

    List<AgreementVo> getAgreementPage(IPage<AgreementVo> page, AgreementQueryVo query);
}




