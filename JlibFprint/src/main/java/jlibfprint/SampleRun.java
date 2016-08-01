/*
 * Jlibfprint
 * Copyright (C) 2012 Fabio Scippacercola <nonplay.programmer@gmail.com>
 *                    Agostino Savignano <savag88@gmail.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package jlibfprint;

import java.util.Arrays;

/**
 *  A simple execution can be used to test the library.
 * @author agostino
 */
public class SampleRun {
	/**
	 * @param args the command line arguments
	 */

	public static String discoverDevices(){
		DiscoveredDeviceList discoveredDeviceList = JlibFprint.discoverDevices();
		listDiscoveredDevices(discoveredDeviceList);
		DiscoveredDevice firstDiscoveredDevice = getFirstDiscoveredDevice(discoveredDeviceList);

		if (firstDiscoveredDevice == null){
			return("No device found to enroll fingers");
		}
		else
		{
			Device device = firstDiscoveredDevice.open();
			String deviceInfo = getDeviceInfo(device);
			EnrollResult enrollResult = enroll(device);
			if (EnrollResultCode.COMPLETE.equals(enrollResult.getCode())) {
				System.out.println("Print data: " + Arrays.toString(enrollResult.getPrintData().getData())); //print data get's set now only.
				identify(device, enrollResult.getPrintData());
			}
			device.close();
			return deviceInfo;
		}
	}
	
	public static String getDeviceInfo(Device device) {
		StringBuilder sb = new StringBuilder("=====================================");
		sb.append("\n");
		sb.append("Device: " + device);
		sb.append("\n");
		sb.append("\tNumber of enroll stages: " + device.getNumberEnrollStages());
		sb.append("\n");
		sb.append("\tImage width: " + device.getImageWidth());
		sb.append("\n");
		sb.append("\tImage height: " + device.getImageHeight());
		sb.append("\n");
		sb.append("=====================================");
		sb.append("\n");
		return sb.toString();
	}

	public static String listDiscoveredDevices(DiscoveredDeviceList discoveredDeviceList) {
		StringBuilder sb = new StringBuilder("Devices discovered: " + discoveredDeviceList.getDiscoveredDevices().length);
		sb.append("\n");
		sb.append("\n");
		
		for(DiscoveredDevice discoveredDevice: discoveredDeviceList.getDiscoveredDevices()) {
			sb.append("=====================================");
			sb.append("\n");
			sb.append(discoveredDevice);
			sb.append("\n");
			sb.append("\ttype_id: " + discoveredDevice.getType());
			sb.append("\n");
			sb.append("\tdriver_id: " + discoveredDevice.getDriverId());
			sb.append("\n");
			sb.append("\tDriver.name: " + discoveredDevice.getDriver().getName());
			sb.append("\n");
			sb.append("\tDriver.full_name: " + discoveredDevice.getDriver().getFullName());
			sb.append("\n");
			sb.append("\tDriver.id: " + discoveredDevice.getDriver().getId());
			sb.append("\n");
		}
		sb.append("=====================================");
		return sb.toString();
	}

	public static DiscoveredDevice getFirstDiscoveredDevice(DiscoveredDeviceList discoveredDeviceList) {

		for(DiscoveredDevice d : discoveredDeviceList.getDiscoveredDevices()){
			Device device = d.open();
			if(device.getImageHeight() > 0 && device.getImageWidth() > 0)
			{
				device.close();
				return d;
			}
		}

		return null;
	}

	public static EnrollResult enroll(Device device) {
		EnrollResult enrollResult = null;
		for ( int i = 0 ; i < device.getNumberEnrollStages() ; i++ ) {
			System.out.println("Please, enroll the finger");
			enrollResult = device.enroll();
			System.out.println("Enroll result code: " + enrollResult.getCode());
		}
		return enrollResult;
	}

	public static VerifyResultCode identify(Device device, PrintData printData) {
		return device.verify(printData);        
	}

}
