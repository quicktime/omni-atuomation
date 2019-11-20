package postman;

import java.io.IOException;

import helper.StringManipulation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Gets test data through Postman http://10.11.14.2:8080/v1/module/tests/
 * @author Brendan Dolan
 * @date Created on: Feb 22, 2018
 */
public class PostmanGet extends BaseState {
	
	public static String getModule() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6c00ab36-e221-1d67-10d5-da40416c0066")
		  .build();

		Response response = null;
		String sResponse = null;
		try {
			response = client.newCall(request).execute();
			sResponse = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sResponse;
	}
	
	/**
	 * Gets module errors through Postman
	 * @return module errors as string
	 */
	public static String getModuleErrors() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6c00ab36-e221-1d67-10d5-da40416c0066")
		  .build();

		Response response = null;
		String sResponse = null;
		try {
			response = client.newCall(request).execute();
			sResponse = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.close();
		return sResponse;
	}

	/**
	 * Gets test data through Postman
	 * @return test data as string
	 */
	public static String getTests() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/tests/")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6c00ab36-e221-1d67-10d5-da40416c0066")
		  .build();

		Response response = null;
		String sResponse = null;
		try {
			response = client.newCall(request).execute();
			sResponse = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.close();
		return sResponse;
	}
	
	/**
	 * Gets test error data through Postman
	 * @param sampleExternalID - sample ID to get errors
	 * @return test error data as string
	 */
	public static String getTestErrors(String sampleExternalID) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/tests/")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6c00ab36-e221-1d67-10d5-da40416c0066")
		  .build();

		Response response = null;
		String sResponse = null;
		try {
			response = client.newCall(request).execute();
			sResponse = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String errorText = StringManipulation.postmanTestErrors(sampleExternalID, sResponse);
		response.close();
		return errorText;
	}

	/**
	 * Gets test error code through Postman
	 * @param sampleExternalID - sample ID to get errors
	 * @return test error code as string
	 */
	public static String getTestErrorCode(String sampleExternalID) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/tests/")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6c00ab36-e221-1d67-10d5-da40416c0066")
		  .build();

		Response response = null;
		String sResponse = null;
		try {
			response = client.newCall(request).execute();
			sResponse = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String errorText = StringManipulation.postmanFullTestErrorCode(sampleExternalID, sResponse);
		response.close();
		return errorText;
	}
	
	/**
	 * Gets module error code through Postman
	 * @param sampleExternalID - sample ID to get errors
	 * @return test error code as string
	 */
	public static String getModuleErrorCode() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6c00ab36-e221-1d67-10d5-da40416c0066")
		  .build();

		Response response = null;
		String sResponse = null;
		try {
			response = client.newCall(request).execute();
			sResponse = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String errorText = StringManipulation.postmanFullModuleErrorCode(sResponse);
		response.close();
		return errorText;
	}

	/**
	 * Gets self test data through Postman
	 * @return self test data as string
	 */
	public static String getSelfTest() {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "6c00ab36-e221-1d67-10d5-da40416c0066")
		  .build();

		Response response = null;
		String sResponse = null;
		try {
			response = client.newCall(request).execute();
			sResponse = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String selfTest = StringManipulation.postSelfTestData(sResponse);
		response.close();
		return selfTest;
	}

	/**
	 * Gets instrument state through Postman
	 * @return instrument state as string
	 */
	public static String getInstrumentState() {
		String sResponse = getModule();
		String instrumentState = StringManipulation.postmanInstrumentState(sResponse);
		return instrumentState;
	}

	/**
	 * Gets door status through Postman
	 * @return door status as string
	 */
	public static String getDoorStatus() {
		String sResponse = getModule();
		String doorStatus = StringManipulation.postmanDoorStatus(sResponse);
		return doorStatus;
	}

	/**
	 * Gets cart status through Postman
	 * @return cart status as string
	 */
	public static String getCartStatus() {
		String sResponse = getModule();
		String doorStatus = StringManipulation.postmanCartStatus(sResponse);
		return doorStatus;
	}
}
