package com.rcapl.ezcash.utill;

import java.text.DecimalFormat;

import com.rcapl.ezcash.model.Request;
import com.rcapl.ezcash.model.Response;

import lk.dialog.corporate.ewallet.web.client.EMoneyClientReponse;
import lk.dialog.corporate.ewallet.web.client.EMoneyClientRequest;

public class SecurityUtil {

	public static String encriptQueryString(Request request, String keyPath) {
		System.out.println("keyPath ->" + keyPath);
		EMoneyClientRequest client = new EMoneyClientRequest(keyPath);
		String merchantCode = request.getMerchantCode();
		double amount = request.getAmount();
		DecimalFormat decim = new DecimalFormat("0.00");
		String amountFormat = decim.format(amount);
		String transactionId = request.getTransactionId();
		String returnUrl = request.getReturnUrl();
		System.out.println("merchantCode " + merchantCode + " amountFormat "
				+ amountFormat + " transactionId " + transactionId
				+ " returnUrl " + returnUrl);
		client.setMerchantCode(merchantCode);
		client.setTransactionAmount(amountFormat);
		client.setTransactionId(transactionId);
		client.setReturnUrl(returnUrl);
		String invoice = client.getInvoice();
		return invoice;
	}

	public static String createParamString(String encodedInvoice) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<input type=\"hidden\" value=\"<%=" + encodedInvoice
				+ "%>\" name=\"merchantInvoice\"> ");
		return buffer.toString();
	}

	public static Response decriptResponse(String response, String privateKey) {
		EMoneyClientReponse clientResponse = new EMoneyClientReponse(privateKey);
		clientResponse.setReciept(response);
		Response res = new Response();
		res.setStatus(clientResponse.getStatusCode());
		res.setDescription(clientResponse.getStatusDescription());
		res.setRefId(clientResponse.getWalletReferenceId());
		return res;
	}
}
