package com.bst.mallh5.payment;

import com.bst.common.entity.pay.Payment;
import com.bst.common.modle.Result;
import com.bst.mallh5.service.pay.IPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 获取付款方式控制器
 */
@Controller
@RequestMapping("/mall/order_pay_way")
public class PaymentController {
    @Resource
    private IPaymentService paymentService;

    @ResponseBody
    @RequestMapping(value = "/queryUserPayment", method = RequestMethod.GET)
    public ResponseEntity<Result> queryUserPayment(HttpServletRequest request) {
        ResponseEntity<Result> result;
        List<Payment> paymentList = paymentService.queryPayments();
        return ResponseEntity.ok(Result.ok(paymentList));
    }
}
