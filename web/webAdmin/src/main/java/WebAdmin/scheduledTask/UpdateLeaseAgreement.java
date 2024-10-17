package WebAdmin.scheduledTask;

import WebAdmin.service.LeaseAgreementService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import model.entity.LeaseAgreement;
import model.enums.LeaseStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UpdateLeaseAgreement {

    private final LeaseAgreementService leaseAgreementService;

    public UpdateLeaseAgreement(LeaseAgreementService leaseAgreementService) {
        this.leaseAgreementService = leaseAgreementService;
    }

        @Scheduled(cron = "0 0 0 * * ?")
        public void reportCurrentTimeWithFixedRate() {
            log.info("执行租约更新");
            LocalDate now = LocalDate.now();
            LambdaQueryWrapper<LeaseAgreement> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.le(LeaseAgreement::getLeaseEndDate,now).in(LeaseAgreement::getStatus,2,5,7);
            List<LeaseAgreement> list = leaseAgreementService.list(lambdaQueryWrapper);
            list.forEach(leaseAgreement -> {
                leaseAgreement.setStatus(LeaseStatus.EXPIRED);
                leaseAgreement.setUpdateTime(new Date());
            });
            leaseAgreementService.updateBatchById(list);
        }
}
