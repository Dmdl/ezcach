package com.rcapl.ezcash.service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rcapl.ezcash.model.Request;
import com.rcapl.ezcash.model.Response;
import com.rcapl.ezcash.utill.AppUtil;
import com.rcapl.ezcash.utill.SecurityUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${service.url}")
	private String serviceUrl;

	@Value("${public.key.path}")
	private String publicKeyPath;

	@Value("${private.key.path}")
	private String privateKeyPath;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String processPayment(Request request) throws FileNotFoundException {
		try {
			String transId = AppUtil.generateRandomString();
			request.setTransactionId(transId);
			String invoice = SecurityUtil.encriptQueryString(request,
					publicKeyPath);
			return SecurityUtil.createParamString(invoice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException();
		}
	}

	public void processResponse(String encodedResponse) {
		Response response = SecurityUtil.decriptResponse(encodedResponse,
				privateKeyPath);
		System.out.println("refId -> " + response.getRefId());
	}

	public String getResponse(String encodedResponse) {
		System.out.println("serviceUrl -> " + serviceUrl);
		ResponseEntity<String> response = restTemplate.postForEntity(
				serviceUrl, encodedResponse, String.class);
		HttpStatus statusCode = response.getStatusCode();
		String body = response.getBody();
		System.out.println("statusCode " + statusCode);
		return body;
	}

	public String getEncriptedInvoice(Request request)
			throws FileNotFoundException {
		try {
			String transId = AppUtil.generateRandomString();
			request.setTransactionId(transId);
			String invoice = SecurityUtil.encriptQueryString(request,
					publicKeyPath);
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException();
		}
	}

}
