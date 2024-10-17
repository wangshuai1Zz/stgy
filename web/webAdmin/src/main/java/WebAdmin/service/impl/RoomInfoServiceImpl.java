package WebAdmin.service.impl;

import Common.exception.MyApiError;
import WebAdmin.mapper.RoomInfoMapper;
import WebAdmin.service.*;
import WebAdmin.vo.attr.AttrValueVo;
import WebAdmin.vo.graph.GraphVo;
import WebAdmin.vo.room.RoomDetailVo;
import WebAdmin.vo.room.RoomItemVo;
import WebAdmin.vo.room.RoomQueryVo;
import WebAdmin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.*;
import model.enums.ItemType;
import model.enums.ReleaseStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

    private final GraphInfoService graphInfoService;

    private final RoomAttrValueService roomAttrValueService;

    private final RoomFacilityService roomFacilityService;

    private final RoomLabelService roomLabelService;

    private final RoomPaymentTypeService roomPaymentTypeService;

    private final RoomLeaseTermService roomLeaseTermService;

    private final ApartmentInfoService apartmentInfoService;
    public RoomInfoServiceImpl(GraphInfoService graphInfoService, RoomAttrValueService roomAttrValueService, RoomFacilityService roomFacilityService, RoomLabelService roomLabelService, RoomPaymentTypeService roomPaymentTypeService, RoomLeaseTermService roomLeaseTermService,@Lazy ApartmentInfoService apartmentInfoService) {
        this.graphInfoService = graphInfoService;
        this.roomAttrValueService = roomAttrValueService;
        this.roomFacilityService = roomFacilityService;
        this.roomLabelService = roomLabelService;
        this.roomPaymentTypeService = roomPaymentTypeService;
        this.roomLeaseTermService = roomLeaseTermService;
        this.apartmentInfoService = apartmentInfoService;
    }

    @Override
    @Transactional
    public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {
        boolean save = this.saveOrUpdate(roomSubmitVo);
        if (!save) throw new MyApiError("err",200);
        if (roomSubmitVo.getId() != null){
            this.delOtherTable(roomSubmitVo.getId());
        }

        Long id = roomSubmitVo.getId();
        List<Long> attrValueIds = roomSubmitVo.getAttrValueIds();
        List<Long> facilityInfoIds = roomSubmitVo.getFacilityInfoIds();
        List<Long> labelInfoIds = roomSubmitVo.getLabelInfoIds();
        List<Long> paymentTypeIds = roomSubmitVo.getPaymentTypeIds();
        List<Long> leaseTermIds = roomSubmitVo.getLeaseTermIds();

        List<RoomAttrValue> roomAttrValues = new ArrayList<>();
        for (Long attrValueId : attrValueIds) {
            RoomAttrValue roomAttrValue = RoomAttrValue
                    .builder()
                    .roomId(id)
                    .attrValueId(attrValueId)
                    .build();
            roomAttrValues.add(roomAttrValue);
        }
        boolean b1 = roomAttrValueService.saveBatch(roomAttrValues);
        if (!b1) throw new MyApiError("err",200);

        List<RoomFacility> roomFacilities = new ArrayList<>();
        for (Long facilityInfoId : facilityInfoIds) {
            RoomFacility roomFacility = RoomFacility.builder()
                    .roomId(id)
                    .facilityId(facilityInfoId)
                    .build();
            roomFacilities.add(roomFacility);
        }
        boolean facilitiesSaved = roomFacilityService.saveBatch(roomFacilities);
        if (!facilitiesSaved) {
            throw new MyApiError("err", 200);
        }

        List<RoomLabel> roomLabels = new ArrayList<>();
        for (Long labelInfoId : labelInfoIds) {
            RoomLabel roomLabel = RoomLabel.builder()
                    .roomId(id)
                    .labelId(labelInfoId)
                    .build();
            roomLabels.add(roomLabel);
        }
        boolean labelsSaved = roomLabelService.saveBatch(roomLabels);
        if (!labelsSaved) {
            throw new MyApiError("err", 200);
        }

        List<RoomPaymentType> roomPaymentTypes = new ArrayList<>();
        for (Long paymentTypeId : paymentTypeIds) {
            RoomPaymentType roomPaymentType = RoomPaymentType.builder()
                    .roomId(id)
                    .paymentTypeId(paymentTypeId)
                    .build();
            roomPaymentTypes.add(roomPaymentType);
        }
        boolean paymentTypesSaved = roomPaymentTypeService.saveBatch(roomPaymentTypes);
        if (!paymentTypesSaved) {
            throw new MyApiError("err", 200);
        }

        List<RoomLeaseTerm> roomLeaseTerms = new ArrayList<>();
        for (Long leaseTermId : leaseTermIds) {
            RoomLeaseTerm roomLeaseTerm = RoomLeaseTerm.builder()
                    .roomId(id)
                    .leaseTermId(leaseTermId)
                    .build();
            roomLeaseTerms.add(roomLeaseTerm);
        }
        boolean leaseTermsSaved = roomLeaseTermService.saveBatch(roomLeaseTerms);
        if (!leaseTermsSaved) {
            throw new MyApiError("err", 200);
        }

        List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
        List<GraphInfo> graphList = new ArrayList<>();
        for (GraphVo graphVo : graphVoList) {
            GraphInfo graph = GraphInfo.builder()
                    .itemId(id)
                    .itemType(ItemType.ROOM)
                    .name(graphVo.getName())
                    .url(graphVo.getUrl())
                    .build();
            graphList.add(graph);
        }
        boolean graphSave = graphInfoService.saveBatch(graphList);
        if (!graphSave) throw new MyApiError("err",200);
    }

    @Override
    public IPage<RoomItemVo> getPage(long current, long size, RoomQueryVo queryVo) {
        IPage<RoomItemVo> iPage = new Page<>(current,size);
        iPage.setRecords(baseMapper.getPage(iPage,queryVo));
        return iPage;
    }

    @Override
    @Transactional
    public void removeRoomById(Long id) {
        RoomInfo roomInfo = this.getById(id);
        if (roomInfo == null) throw new MyApiError("err",200);
        boolean b = this.delOtherTable(id);
        if (!b) throw new MyApiError("err",200);
    }

    @Override
    public RoomDetailVo getRoomById(Long id) {
        RoomDetailVo roomDetailVo = new RoomDetailVo();
        RoomInfo roomInfo = this.getById(id);
        BeanUtils.copyProperties(roomInfo,roomDetailVo);

        Long apartmentId = roomInfo.getApartmentId();
        ApartmentInfo apartment = apartmentInfoService.getById(apartmentId);
        roomDetailVo.setApartmentInfo(apartment);

        List<AttrValueVo> attrById = roomAttrValueService.getAttrById(id);
        roomDetailVo.setAttrValueVoList(attrById);

        List<FacilityInfo> facilityList = roomFacilityService.getFacilityInfoList(id);
        roomDetailVo.setFacilityInfoList(facilityList);

        List<LabelInfo> labelInfos = roomLabelService.getLabelList(id);
        roomDetailVo.setLabelInfoList(labelInfos);

        List<PaymentType> paymentList = roomPaymentTypeService.getPayList(id);
        roomDetailVo.setPaymentTypeList(paymentList);

        List<LeaseTerm> leaseTermList = roomLeaseTermService.getLeaseList(id);
        roomDetailVo.setLeaseTermList(leaseTermList);

        List<GraphVo> graphList = graphInfoService.getRoomGraphList(id);
        roomDetailVo.setGraphVoList(graphList);
        return roomDetailVo;
    }

    @Override
    public void updateStatus(Long id, ReleaseStatus status) {
        LambdaUpdateWrapper<RoomInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(RoomInfo::getIsRelease,status)
                .eq(RoomInfo::getId,id);
        boolean update = this.update(new RoomInfo(), lambdaUpdateWrapper);
        if (!update) throw new MyApiError("err",200);
    }

    @Override
    public List<RoomInfo> getByApartmentId(Long id) {
        LambdaQueryWrapper<RoomInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoomInfo::getApartmentId,id);
        return this.list(lambdaQueryWrapper);
    }

    @Transactional
    protected boolean delOtherTable(Long id){
        boolean RoomRemove = this.removeById(id);
        if (!RoomRemove) return false;

        LambdaQueryWrapper<GraphInfo> GraphLambdaQueryWrapper = new LambdaQueryWrapper<>();
        GraphLambdaQueryWrapper.eq(GraphInfo::getItemId,id);
        GraphLambdaQueryWrapper.eq(GraphInfo::getItemType,2);
        boolean GraphRemove = graphInfoService.remove(GraphLambdaQueryWrapper);
        if (!GraphRemove) return false;

        LambdaQueryWrapper<RoomAttrValue> attrValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attrValueLambdaQueryWrapper.eq(RoomAttrValue::getRoomId,id);
        boolean attrValueRemove = roomAttrValueService.remove(attrValueLambdaQueryWrapper);
        if (!attrValueRemove) return false;

        LambdaQueryWrapper<RoomFacility> roomFacilityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomFacilityLambdaQueryWrapper.eq(RoomFacility::getRoomId,id);
        boolean roomFacilityRemove = roomFacilityService.remove(roomFacilityLambdaQueryWrapper);
        if (!roomFacilityRemove) return false;

        LambdaQueryWrapper<RoomLabel> roomLabelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomLabelLambdaQueryWrapper.eq(RoomLabel::getRoomId,id);
        boolean roomLabelRemove = roomLabelService.remove(roomLabelLambdaQueryWrapper);
        if (!roomLabelRemove) return false;

        LambdaQueryWrapper<RoomPaymentType> roomPaymentTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomPaymentTypeLambdaQueryWrapper.eq(RoomPaymentType::getRoomId,id);
        boolean PaymentRemove = roomPaymentTypeService.remove(roomPaymentTypeLambdaQueryWrapper);
        if (!PaymentRemove) return false;

        LambdaQueryWrapper<RoomLeaseTerm> leaseTermLambdaQueryWrapper = new LambdaQueryWrapper<>();
        leaseTermLambdaQueryWrapper.eq(RoomLeaseTerm::getRoomId,id);
        return roomLeaseTermService.remove(leaseTermLambdaQueryWrapper);
    }
}




