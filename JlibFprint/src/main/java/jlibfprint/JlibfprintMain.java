package jlibfprint;

import java.util.Arrays;

public class JlibfprintMain {

	public static void main(String[] args) {
		//discover process
//		DiscoveredDeviceList discoveredDeviceList = JlibFprint.discoverDevices();
//		String discoveredDevices = SampleRun.listDiscoveredDevices(discoveredDeviceList);
//		System.out.println(discoveredDevices);
//		
//		//enrollment process
//		DiscoveredDevice firstDiscoveredDevice = SampleRun.getFirstDiscoveredDevice(discoveredDeviceList);
//
//		if (firstDiscoveredDevice == null){
//			System.out.println("No device found to enroll fingers");
//		}
//		else
//		{
//			Device device = firstDiscoveredDevice.open();
//			String deviceInfo = SampleRun.getDeviceInfo(device);
//			System.out.println(deviceInfo);
//			EnrollResult enrollResult = SampleRun.enroll(device);
//			if (EnrollResultCode.COMPLETE.equals(enrollResult.getCode())) {
//				System.out.println("Print data: " + Arrays.toString(enrollResult.getPrintData().getData())); //print data get's set now only.
//				SampleRun.identify(device, enrollResult.getPrintData());
//			}
//			device.close();
//		}
	}

}
