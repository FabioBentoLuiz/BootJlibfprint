package com.fingerprint.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.fingerprint.domain.EnrollmentStep;
import com.fingerprint.repository.EnrollStatusRepository;

import jlibfprint.Device;
import jlibfprint.DiscoveredDevice;
import jlibfprint.EnrollResult;
import jlibfprint.EnrollResultCode;
import jlibfprint.SampleRun;
import jlibfprint.VerifyResultCode;


@Service
public class EnrollmentService {
	
	private EnrollStatusRepository enrollRepository;
	
	@Autowired
	public EnrollmentService(EnrollStatusRepository enrollRepository){
		this.enrollRepository = enrollRepository;
	}

	@Async
	public Future<EnrollResult> startEnrollment(DiscoveredDevice firstDiscoveredDevice) {
		Device device = firstDiscoveredDevice.open();
		String deviceInfo = SampleRun.getDeviceInfo(device);
		System.out.println(deviceInfo);
		

		EnrollResult enrollResult = null;
		EnrollmentStep es = new EnrollmentStep();
		int i = 0;
				
		for (i = 0 ; i < device.getNumberEnrollStages() ; i++) {
			
			es.setStep(i+1);
			es.setMessage("Please, enroll the finger");
			es.setTimestamp(LocalDateTime.now());
			enrollRepository.save(es);

			enrollResult = device.enroll();
			
			es = new EnrollmentStep();
			es.setStep(i+1);
			es.setMessage((i+1) + ". enroll done.");
			es.setResultCode(enrollResult.getCode().toString());
			es.setTimestamp(LocalDateTime.now());
			enrollRepository.save(es);
		}
		
		if (EnrollResultCode.COMPLETE.equals(enrollResult.getCode())) {
			es = new EnrollmentStep();
			es.setStep(i+1);
			es.setMessage("Please, enroll the finger again to verify... Print data: " + Arrays.toString(enrollResult.getPrintData().getData()));
			es.setResultCode(enrollResult.getCode().toString());
			es.setTimestamp(LocalDateTime.now());
			enrollRepository.save(es);
			
			VerifyResultCode code = SampleRun.identify(device, enrollResult.getPrintData());
			
			es = new EnrollmentStep();
			es.setStep(i+1);
			es.setMessage("Verification result: " + code);
			es.setResultCode(enrollResult.getCode().toString());
			es.setTimestamp(LocalDateTime.now());
			enrollRepository.save(es);
		}
		device.close();
		return new AsyncResult<EnrollResult>(enrollResult);
	}
	
	public String getAllEnrollSteps(){
		StringBuilder sb = new StringBuilder();
		
		for(EnrollmentStep es : this.enrollRepository.findAll()){
			sb.append(es.toString());
			sb.append("<br>");
		}
		
		return sb.toString();
	}
	
}
