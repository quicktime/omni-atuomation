package postman;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import testcontrol.Main;

/**
 * Responsible for all Put commands in Putty
 * @author Brendan Dolan
 * @date Created on: Feb 22, 2018
 */
public class PostmanPut extends BaseState {

	/**
	 * Puts an error code into Omni Instrument http://10.11.14.2:8080/v1/module/testcommands/
	 * Generated from Postman
	 * @param code
	 */
	public static void sendError(String code) {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, code);
		Request request = new Request.Builder()
		  .url("http://10.11.14.2:8080/v1/module/testcommands/")
		  .put(body)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "0c361198-8f1b-4790-7942-e89e82f48da0")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			Main.debug.LOG("Sending error code: '" + code + "'...");
			Main.debug.LOG(response.toString());
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.debug.LOG("Error code accepted.");
	}
}
