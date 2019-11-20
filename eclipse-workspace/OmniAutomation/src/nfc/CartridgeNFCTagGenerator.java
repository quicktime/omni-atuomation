package nfc;

import com.borland.silktest.jtf.Utils;
import com.borland.silktest.jtf.common.types.Point;

import mobile.Navigation;

/**
 * Handles all actions taken within the CartridgeNFCTagGenerator app
 * @author Brendan Dolan
 * @date Created on: Mar 29, 2018
 */
public class CartridgeNFCTagGenerator extends mobile.BaseState {

	/**
	 * Changes a scanned NFC Tag's Product Code, and subsequent BCC
	 * @param productCode - New product code to set 
	 * @param BCC - New BCC to set
	 */
	public static void changeProductCode(String productCode, String BCC) {
		mobileDevice().pressHome();
		mobileObject("Device.cartridgeNFCTagGeneratorApp").click();
		mobileButton("Device.cartridgeNFCTagGenerator.scanNfcBtn").click();
		// TODO: Scanning the NFC Tag
		mobileButton("Device.cartridgeNFCTagGenerator.productCodeEditButton").click();
		mobileTextField("Device.cartridgeNFCTagGenerator.productCodeEditText").setText(productCode);
		mobileButton("Device.cartridgeNFCTagGenerator.productCodeEditButton").click();
		mobileButton("Device.cartridgeNFCTagGenerator.bccEditButton").click();
		mobileTextField("Device.cartridgeNFCTagGenerator.bccEditText").setText(BCC);
		for (int i = 0; i < 11; i++) {
			Navigation.dragUp();
		}
		mobileButton("Device.cartridgeNFCTagGenerator.writeNfcTag").click();
		// TODO: Scanning the NFC Tag
		mobileDevice().pressHome();
		mobileObject("Device.omniApp").click();
	}
}
