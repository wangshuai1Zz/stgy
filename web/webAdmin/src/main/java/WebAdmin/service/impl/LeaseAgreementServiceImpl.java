package WebAdmin.service.impl;

import Common.exception.MyApiError;
import WebAdmin.mapper.LeaseAgreementMapper;
import WebAdmin.service.LeaseAgreementService;
import WebAdmin.vo.agreement.AgreementQueryVo;
import WebAdmin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Override
    public AgreementVo getAgreement(Long id) {
        return baseMapper.getAgreementById(id);
    }

    @Override
    public IPage<AgreementVo> getAgreementPage(long current, long size, AgreementQueryVo queryVo) {
        IPage<AgreementVo> page = new Page<>(current,size);
        List<AgreementVo> agreementList = baseMapper.getAgreementPage(page,queryVo);
        if (agreementList.isEmpty()) throw new MyApiError("err",200);
        page.setRecords(agreementList);
        return page;
    }
}




