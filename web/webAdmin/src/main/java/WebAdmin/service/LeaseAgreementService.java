package WebAdmin.service;

import WebAdmin.vo.agreement.AgreementQueryVo;
import WebAdmin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import model.entity.LeaseAgreement;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LeaseAgreementService extends IService<LeaseAgreement> {

    AgreementVo getAgreement(Long id);

    IPage<AgreementVo> getAgreementPage(long current, long size, AgreementQueryVo queryVo);
}
