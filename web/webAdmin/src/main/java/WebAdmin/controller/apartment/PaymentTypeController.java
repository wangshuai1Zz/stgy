package WebAdmin.controller.apartment;

import WebAdmin.aop.AuthCheck;
import WebAdmin.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.PaymentType;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService){
        this.paymentTypeService = paymentTypeService;
    }

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
        List<PaymentType> list = paymentTypeService.list();
        return Result.ok(list);
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result<Object> saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        boolean save = paymentTypeService.saveOrUpdate(paymentType);
        if (save){
            return Result.ok();
        }
        return Result.fail();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result<Object> deletePaymentById(@RequestParam Long id) {
        boolean b = paymentTypeService.removeById(id);
        if (b){
            return Result.ok();
        }
        return Result.fail();
    }

}















