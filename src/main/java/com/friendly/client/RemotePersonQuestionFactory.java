package com.friendly.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.friendly.Friendly;
import com.friendly.people.PersonQuestion;
import com.thoughtworks.xstream.XStream;

/**
 * A factory that generates {@link PersonQuestion} objects from a provided REST
 * service, specified by the provider.address property from friendly.properties.
 * 
 * @author Ben Burton
 */
public class RemotePersonQuestionFactory {

	public static PersonQuestion getQuestion() {
		String xml = null;
		try {
			xml = getRemoteQuestionXML();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		XStream xstream = new XStream();
		return (PersonQuestion) xstream.fromXML(xml);
	}

	public static String getRemoteQuestionXML() throws Exception {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(Friendly.getProperty("provider.address")));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String page = sb.toString();
			return page;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
