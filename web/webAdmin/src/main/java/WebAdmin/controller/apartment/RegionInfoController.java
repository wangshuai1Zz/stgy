package WebAdmin.controller.apartment;

import WebAdmin.aop.AuthCheck;
import WebAdmin.service.CityInfoService;
import WebAdmin.service.DistrictInfoService;
import WebAdmin.service.ProvinceInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.CityInfo;
import model.entity.DistrictInfo;
import model.entity.ProvinceInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Common.result.Result;

import java.util.List;

@Tag(name = "地区信息管理")
@RestController
@RequestMapping("/admin/region")
@AuthCheck(mustBeAdmin = true)
public class RegionInfoController {

    private final CityInfoService cityInfoService;
    private final ProvinceInfoService provinceInfoService;
    private final DistrictInfoService districtInfoService;

    public RegionInfoController(CityInfoService cityInfoService, ProvinceInfoService provinceInfoService, DistrictInfoService districtInfoService) {
        this.cityInfoService = cityInfoService;
        this.provinceInfoService = provinceInfoService;
        this.districtInfoService = districtInfoService;
    }

    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        return Result.ok( provinceInfoService.list());
    }

    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        LambdaQueryWrapper<CityInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CityInfo::getProvinceId,id);
        return Result.ok(cityInfoService.list(lambdaQueryWrapper));
    }

    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id) {
        LambdaQueryWrapper<DistrictInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DistrictInfo::getCityId,id);
        return Result.ok(districtInfoService.list(lambdaQueryWrapper));
    }

}
