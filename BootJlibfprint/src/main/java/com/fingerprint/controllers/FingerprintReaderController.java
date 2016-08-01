package com.fingerprint.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fingerprint.model.DisplayableDevice;
import com.fingerprint.service.EnrollmentService;

import jlibfprint.DiscoveredDevice;
import jlibfprint.DiscoveredDeviceList;
import jlibfprint.EnrollResult;
import jlibfprint.JlibFprint;
import jlibfprint.SampleRun;


@Controller
public class FingerprintReaderController {
	
	
	private EnrollmentService enrollmentService;
	
	@Autowired
	public FingerprintReaderController(EnrollmentService enrollmentService){
		this.enrollmentService = enrollmentService;
	}

	@RequestMapping("/")
	public String index(Model model){
		DiscoveredDeviceList discoveredDeviceList = JlibFprint.discoverDevices();
		List <DisplayableDevice> displayableDevices = convertToDisplay(discoveredDeviceList);
		model.addAttribute("devices",displayableDevices);
		return "index";
	}
	
	@RequestMapping("/enroll")
	public String enroll(Model model){
		DiscoveredDeviceList discoveredDeviceList = JlibFprint.discoverDevices();
		DiscoveredDevice firstDiscoveredDevice = SampleRun.getFirstDiscoveredDevice(discoveredDeviceList);
		Future<EnrollResult> result = null;

		if (firstDiscoveredDevice == null){
			model.addAttribute("message","No device found to enroll fingers");
		}
		else
		{
			result = enrollmentService.startEnrollment(firstDiscoveredDevice);
		}
		
		if(result != null)
			model.addAttribute("done",result.isDone());
		
		return "views/enroll";
	}

	@RequestMapping("/steps")
	@ResponseBody
	public String steps(){
		return this.enrollmentService.getAllEnrollSteps();
	}

	private List<DisplayableDevice> convertToDisplay(DiscoveredDeviceList discoveredDeviceList){

		List<DisplayableDevice> displayableDevices = new ArrayList<DisplayableDevice>();

		for(DiscoveredDevice d : discoveredDeviceList.getDiscoveredDevices()){
			DisplayableDevice toDisplay = new DisplayableDevice();
			toDisplay.setType(d.getType());
			toDisplay.setDriverId(d.getDriverId());
			toDisplay.setDriverName(d.getDriver().getFullName());

			displayableDevices.add(toDisplay);
		}

		return displayableDevices;
	}
	


}
