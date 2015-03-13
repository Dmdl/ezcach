package com.rcapl.ezcash.service;

import java.io.FileNotFoundException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rcapl.ezcash.model.Request;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentServiceImplTest {

	@Autowired
	private PaymentService paymentService;

	@Test
	@Ignore
	public void testInvoiceGeneration() throws FileNotFoundException {
		Request req = new Request();
		req.setAmount(200);
		req.setMerchantCode("TESTMERCHANT");
		req.setReturnUrl("http://localhost/");
		String reqParam = paymentService.processPayment(req);
		System.out.println("reqParam -> " + reqParam);
	}

	@Test
	public void testGetResponse() {
		String query = "<input type=\"hidden\" value=\"<%=gZUpMvtrk8nQVrCbZHtkzpIQFJNVncQTyIM63Yav+AQclW3xVpFArJCrrly5RbCWxtX5mXDYKIpc0+aI2u3ZCAX/p7X3VnXA4lXJ1eqF2Qks1bV/m40YH+nAxfk/7gaASWrdjk7dejICUPetnHtZwXjifOYkGCVROeJ8JBAdDCg=%>\" name=\"merchantInvoice\">";
		paymentService.getResponse(query);
	}
}
