package com.rcapl.ezcash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rcapl.ezcash.model.Request;
import com.rcapl.ezcash.service.PaymentService;

@Controller
public class IndexController {

	@Autowired
	private PaymentService paymentService;

	@Value("${service.url}")
	private String serviceUrl;

	@Value("${rest.service}")
	private String restService;

	@Value("${return.url}")
	private String returnUrl;

	@ModelAttribute("request")
	public Request generateRequestModel() {
		return new Request();
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("url", serviceUrl);
		model.addAttribute("rest", restService);
		model.addAttribute("returnUrl", returnUrl);
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String pay(@ModelAttribute("request") Request request,
			BindingResult result, Model model) {
		try {
			String encriptInvoice = paymentService.processPayment(request);
			System.out.println("encriptInvoice -> " + encriptInvoice);
			String body = paymentService.getResponse(encriptInvoice);
			model.addAttribute("content", body);
			return "pay";
		} catch (Exception e) {
			return "error";
		}
	}

	@RequestMapping(value = "/invoice", consumes = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public String getInvoice(
			@RequestParam(value = "merchantCode", required = true) String mCode,
			@RequestParam(value = "amount", required = true) String amount,
			@RequestParam(value = "returnUrl", required = true) String returnUrl) {

		try {
			System.out.println("mCode " + mCode + " amount " + amount
					+ " returnUrl " + returnUrl);
			Request request = new Request();
			request.setAmount(Double.parseDouble(amount));
			request.setMerchantCode(mCode);
			request.setReturnUrl(returnUrl);
			String queryString = paymentService.getEncriptedInvoice(request);
			return queryString;
		} catch (Exception e) {
			return null;
		}

	}
}
