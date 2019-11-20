package nfc;

import com.borland.silktest.jtf.Utils;
import com.borland.silktest.jtf.common.types.Point;

/**
 * Handles all actions in the NFC Tools app
 * @author Brendan Dolan
 * @date Created on: Mar 22, 2018
 */
public class NFCTools extends mobile.BaseState {
	
	/**
	 * Writes a provided nfc tag to the nfc sticker positioned under the phone
	 * @param nfc - String to write to nfc sticker
	 */
	public static void writeTag(String nfc) {
		mobileDevice().pressHome();
		mobileObject("Device.NFCToolsApp").click();
		mobileObject("Device.NFCTools.WriteObject").click();
		mobileObject("Device.NFCTools.MoreOptionsButton").click();
		mobileObject("Device.NFCTools.MoreOptions.ClearRecordList").click();
		mobileButton("Device.global.button1").click();
		mobileButton("Device.NFCTools.Write.AddARecordButton").click();
		mobileObject("Device.NFCTools.Write.AddARecord.TextObject").click();
		mobileTextField("Device.NFCTools.Write.AddARecord.Text.enterText").setText(nfc);
		mobileButton("Device.NFCTools.Write.AddARecord.Text.OKButton").click();
		mobileButton("Device.NFCTools.Write.WriteButton").click();
		mobileDevice().sleep();
		mobileDevice().wakeUp();
		Utils.sleep(1000); // TODO enhance sleep (waitForObject)
		mobileDevice().swipe(new Point(200, 900), new Point(700, 900));
		mobileDevice().pressHome();
		mobileObject("Device.omniApp").click();
	}

}
