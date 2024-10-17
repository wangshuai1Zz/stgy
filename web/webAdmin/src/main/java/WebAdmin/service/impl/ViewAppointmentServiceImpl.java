package WebAdmin.service.impl;

import WebAdmin.vo.appointment.AppointmentQueryVo;
import WebAdmin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import model.entity.ViewAppointment;
import WebAdmin.mapper.ViewAppointmentMapper;
import WebAdmin.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Override
    public IPage<AppointmentVo> getViewPage(long current, long size, AppointmentQueryVo queryVo) {
        IPage<AppointmentVo> page = new Page<>(current,size);
        List<AppointmentVo> appointmentVoList = baseMapper.getViewPages(page,queryVo);
        page.setRecords(appointmentVoList);
        return page;
    }
}




