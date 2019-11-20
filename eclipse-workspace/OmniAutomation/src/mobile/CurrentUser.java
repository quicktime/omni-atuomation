package mobile;

/**
 * Handles all actions under the Current User screen
 * @author Brendan Dolan
 * @date Created on: Mar 20, 2018
 */
public class CurrentUser extends BaseState {
	
	/**
	 * Logs out the current user
	 */
	public static void logout() {
		Navigation.toCurrentUser();
		mobileObject("Device.global.ImageView2").click();
		mobileObject("Device.global.button1").click();
	}
	
	/**
	 * Changes the current user's password
	 * @param oldPassword - password to change
	 * @param newPassword - password to change to
	 */
	public static void changePassword(String oldPassword, String newPassword) {
		Navigation.toCurrentUser();
		mobileTextField("Device.currentUser.passwordEdit").setText(oldPassword);
		mobileTextField("Device.currentUser.newPasswordEdit").setText(newPassword);
		mobileTextField("Device.currentUser.newPasswordConfirmEdit").setText(newPassword);
	}
}
