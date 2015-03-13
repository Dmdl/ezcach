package com.rcapl.ezcash.service;

import java.io.FileNotFoundException;

import com.rcapl.ezcash.model.Request;

public interface PaymentService {

	public String processPayment(Request request) throws FileNotFoundException;

	public void processResponse(String encodedResponse);

	public String getResponse(String encodedResponse);

	String getEncriptedInvoice(Request request) throws FileNotFoundException;
}
