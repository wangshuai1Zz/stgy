package WebAdmin.service.impl;

import Common.exception.MyApiError;
import WebAdmin.mapper.ApartmentInfoMapper;
import WebAdmin.service.*;
import WebAdmin.vo.apartment.ApartmentDetailVo;
import WebAdmin.vo.apartment.ApartmentItemVo;
import WebAdmin.vo.apartment.ApartmentQueryVo;
import WebAdmin.vo.apartment.ApartmentSubmitVo;
import WebAdmin.vo.fee.FeeValueVo;
import WebAdmin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.*;
import model.enums.ItemType;
import model.enums.ReleaseStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Common.result.ResultCodeEnum.FAIL;
import static Common.result.ResultCodeEnum.PARAM_ERROR;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    private final ApartmentFeeValueService apartmentFeeValueService;
    private final ApartmentLabelService apartmentLabelService;
    private final ApartmentFacilityService apartmentFacilityService;
    private final GraphInfoService graphInfoService;
    private final RoomInfoService roomInfoService;

    public ApartmentInfoServiceImpl(ApartmentFeeValueService apartmentFeeValueService, ApartmentLabelService apartmentLabelService, ApartmentFacilityService apartmentFacilityService, GraphInfoService graphInfoService, RoomInfoService roomInfoService){
        this.apartmentFeeValueService = apartmentFeeValueService;
        this.apartmentLabelService = apartmentLabelService;
        this.apartmentFacilityService = apartmentFacilityService;
        this.graphInfoService = graphInfoService;
        this.roomInfoService = roomInfoService;
    }


    @Override
    public IPage<ApartmentItemVo> getPage(long current, long size, ApartmentQueryVo queryVo) {
        IPage<ApartmentItemVo> page = new Page<>(current, size);
        page.setRecords(baseMapper.getPages(page,queryVo));
        return page;
    }

    @Override
    @Transactional
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        this.saveOrUpdate(apartmentSubmitVo);
        if (apartmentSubmitVo.getId()!=null){
            this.removeApartmentById(apartmentSubmitVo.getId());
        }

        Long afterSaveId = apartmentSubmitVo.getId();
        int i = saveOtherTable(apartmentSubmitVo, afterSaveId);
        if (i == -1) {
            throw MyApiError.Fail(FAIL);
        }
    }

    @Override
    @Transactional
    public void removeApartment(Long id) {
        LambdaQueryWrapper<RoomInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoomInfo::getApartmentId,id);
        List<RoomInfo> list = roomInfoService.list(lambdaQueryWrapper);
        if (!(list.isEmpty())) throw MyApiError.Fail(FAIL);
        Boolean removeApartmentById = removeApartmentById(id);
        if (!removeApartmentById) throw MyApiError.Fail(FAIL);
    }

    @Override
    public ApartmentDetailVo getApartment(Long id) {
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        LambdaQueryWrapper<ApartmentInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ApartmentInfo::getId,id);
        ApartmentInfo apartmentInfo = this.getOne(lambdaQueryWrapper);
        if (apartmentInfo == null) throw MyApiError.Fail(PARAM_ERROR);
        BeanUtils.copyProperties(apartmentInfo,apartmentDetailVo);

        List<FeeValueVo> apartmentFeeValueList = apartmentFeeValueService.getApartMentFeeValuelist(id);

        List<FacilityInfo> facilityInfoList = apartmentFacilityService.getFacilityInfoList(id);

        List<LabelInfo> labelInfoListList = apartmentLabelService.getLabelList(id);

        List<GraphVo> graphVoList = graphInfoService.getGraphList(id);
        apartmentDetailVo.setFeeValueVoList(apartmentFeeValueList != null ? apartmentFeeValueList : Collections.emptyList());
        apartmentDetailVo.setFacilityInfoList(facilityInfoList != null ? facilityInfoList : Collections.emptyList());
        apartmentDetailVo.setLabelInfoList(labelInfoListList != null ? labelInfoListList : Collections.emptyList());
        apartmentDetailVo.setGraphVoList(graphVoList);
        return apartmentDetailVo;
    }

    @Override
    @Transactional
    public void setStatusById(Long id, ReleaseStatus status) {
        LambdaUpdateWrapper<ApartmentInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(ApartmentInfo::getIsRelease,status)
                .eq(ApartmentInfo::getId,id);
        boolean update = this.update(new ApartmentInfo(), lambdaUpdateWrapper);
        if (!update) throw new MyApiError("err",200);
    }

    @Override
    public List<ApartmentInfo> getListByDistrict(Long id) {
        LambdaQueryWrapper<ApartmentInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ApartmentInfo::getDistrictId,id);
        return this.list(lambdaQueryWrapper);
    }

    @Transactional
    protected Boolean removeApartmentById(Long id){

        LambdaQueryWrapper<ApartmentFeeValue> FeeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        FeeValueLambdaQueryWrapper.eq(ApartmentFeeValue::getApartmentId,id);
        boolean FeeValueRemove = apartmentFeeValueService.remove(FeeValueLambdaQueryWrapper);
        if (!FeeValueRemove){
            throw MyApiError.Fail(FAIL);
        }

        LambdaQueryWrapper<ApartmentLabel> labelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        labelLambdaQueryWrapper.eq(ApartmentLabel::getApartmentId,id);
        boolean LabelRemove = apartmentLabelService.remove(labelLambdaQueryWrapper);
        if (!LabelRemove){
            throw MyApiError.Fail(FAIL);
        }

        LambdaQueryWrapper<ApartmentFacility> FacilityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        FacilityLambdaQueryWrapper.eq(ApartmentFacility::getApartmentId,id);
        boolean FacilityRemove = apartmentFacilityService.remove(FacilityLambdaQueryWrapper);
        if (!FacilityRemove){
            throw MyApiError.Fail(FAIL);
        }

        LambdaQueryWrapper<GraphInfo> GraphLambdaQueryWrapper = new LambdaQueryWrapper<>();
        GraphLambdaQueryWrapper.eq(GraphInfo::getItemId,id);
        GraphLambdaQueryWrapper.eq(GraphInfo::getItemType,1);
        boolean GraphRemove = graphInfoService.remove(GraphLambdaQueryWrapper);
        if (!GraphRemove){
            throw MyApiError.Fail(FAIL);
        }
        return true;
    }

    @Transactional
    protected int saveOtherTable(ApartmentSubmitVo apartmentSubmitVo,Long afterSaveId){
        List<Long> FeeValues = apartmentSubmitVo.getFeeValueIds();
        if (FeeValues!= null){
            List<ApartmentFeeValue> FeeValuelist = new ArrayList<>();
            for (Long FeeValue : FeeValues) {
                ApartmentFeeValue apartmentFee = ApartmentFeeValue
                        .builder()
                        .apartmentId(afterSaveId)
                        .feeValueId(FeeValue)
                        .build();
                FeeValuelist.add(apartmentFee);
            }
            boolean FeeValueSaveBatch = apartmentFeeValueService.saveBatch(FeeValuelist);
            if (!FeeValueSaveBatch){
                return -1;
            }
        }

        List<Long> LabelIds = apartmentSubmitVo.getLabelIds();
        if (LabelIds!= null){
            List<ApartmentLabel> Labellist = new ArrayList<>();
            for (Long LabelId : LabelIds) {
                ApartmentLabel apartmentLabel = ApartmentLabel
                        .builder()
                        .apartmentId(afterSaveId)
                        .labelId(LabelId)
                        .build();
                Labellist.add(apartmentLabel);
            }
            boolean LabelSaveBatch = apartmentLabelService.saveBatch(Labellist);
            if (!LabelSaveBatch){
                return -1;
            }
        }

        List<Long> FacilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
        List<ApartmentFacility> FacilityInfoIdlist = new ArrayList<>();
        for (Long FacilityInfoId : FacilityInfoIds) {
            ApartmentFacility apartmentLabel = ApartmentFacility
                    .builder()
                    .apartmentId(afterSaveId)
                    .facilityId(FacilityInfoId)
                    .build();
            FacilityInfoIdlist.add(apartmentLabel);
        }
        boolean FacilityInfoIdSaveBatch = apartmentFacilityService.saveBatch(FacilityInfoIdlist);
        if (!FacilityInfoIdSaveBatch){
            return -1;
        }

        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        List<GraphInfo> graphInfoList = new ArrayList<>();
        for (GraphVo graphVo : graphVoList) {
            GraphInfo graphInfo = GraphInfo
                    .builder()
                    .name(graphVo.getName())
                    .itemType(ItemType.APARTMENT)
                    .itemId(afterSaveId)
                    .url(graphVo.getUrl())
                    .build();
            graphInfoList.add(graphInfo);
        }
        boolean graphInfoSaveBatch = graphInfoService.saveBatch(graphInfoList);
        if (!graphInfoSaveBatch){
            return -1;
        }
        return 1;
    }
}




