package redmineTaskPackage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.collections.ListUtils;

import com.google.gson.Gson;

import static java.lang.System.*;

public class App {
	String url;
	String key;

	public App(String url, String key) {
		this.url = url;
		this.key = key;
	}

	public static void main(String[] args) throws UnsupportedOperationException, IOException {

		/*
		 * CloseableHttpClient httpclient = HttpClients.createDefault(); HttpGet httpget
		 * = new HttpGet(
		 * "https://pivotal.truliacare.com/issues.json?key=b06446911d9e55d2fba1bdb50819c400d50b98b8"
		 * ); HttpResponse httpresponse = httpclient.execute(httpget); Scanner sc = new
		 * Scanner(httpresponse.getEntity().getContent());
		 */
		// System.out.println(httpresponse.getStatusLine());

		String url = "https://pivotal.truliacare.com/issues.json?";
		String key = "b06446911d9e55d2fba1bdb50819c400d50b98b8";
		App app = new App(url, key);

		app.url = app.buildUrlWithKey(app.url, app.key);

		// Task 1
		// app.getIssuesFromGivenProjectTask1("sandbox");

		// Task2
		String date = "2019-12-05";
		LocalDate localdate = LocalDate.parse(date);
		app.getNotWorkedIssuesOnGivenDateTask2(localdate);

	}

	private void getNotWorkedIssuesOnGivenDateTask2(LocalDate date) throws ClientProtocolException, IOException {
		
		List<Issue> requiredIssues = getAllRequiredIssues();
		List<Float> idsOfIncompleteIssues = getIdsOfIssues(requiredIssues);
		List<Float> idsOfIssuesWithTimeEntryOnGivenDate = getIdsOfIssuesWithTimeEntryOnGivenDay(date);
		
	}
	
	public List<Float> getIdsOfIssuesWithTimeEntryOnGivenDay(LocalDate localdate) throws ClientProtocolException, IOException {
		String urlT = "https://pivotal.truliacare.com/time_entries.json?";
		String key = "b06446911d9e55d2fba1bdb50819c400d50b98b8";
		String date = localdate.toString();
		urlT = addParam(urlT, "from", date);
		urlT = addParam(urlT, "to", date);
		out.println(urlT);
		
		
		
		List<Float> ids = new ArrayList<Float>();
		
		return ids;
	}

	public List<Float> getIdsOfIssues(List<Issue> requiredIssues){
		List<Float> ids = new ArrayList<Float>();
		
		for(Issue issue: requiredIssues) {	
			ids.add(issue.id);
		}
		return ids;
	}

	

	public List<Issue> getAllRequiredIssues() throws ClientProtocolException, IOException {
		String jsonOpenIssues = getJsonOpenIssues(url);
		String jsonInProgressIssues = getJsonInProgressIssues(url);
		String jsonOnHoldIssues = getJsonOnHoldIssues(url);

		IssueResponse issueResponseOpen = new Gson().fromJson(jsonOpenIssues, IssueResponse.class);
		IssueResponse issueResponseInProgress = new Gson().fromJson(jsonInProgressIssues, IssueResponse.class);
		IssueResponse issueResponseOnHold = new Gson().fromJson(jsonOnHoldIssues, IssueResponse.class);

		List<Issue> allIssues = ListUtils.union(issueResponseOpen.issues, issueResponseInProgress.issues);
		allIssues = ListUtils.union(allIssues, issueResponseOnHold.issues);
		return allIssues;
	}

	public String getJsonOnHoldIssues(String url) throws ClientProtocolException, IOException {
		String localurl = addParam(url, "status_id", "14");
		return getJsonFromUrl(localurl);
	}

	public String getJsonInProgressIssues(String url) throws ClientProtocolException, IOException {
		String localurl = addParam(url, "status_id", "13");
		return getJsonFromUrl(localurl);
	}

	public String getJsonOpenIssues(String url) throws ClientProtocolException, IOException {
		String localurl = addParam(url, "status_id", "7");
		return getJsonFromUrl(localurl);
	}

	public void getIssuesFromGivenProjectTask1(String projectName) throws ClientProtocolException, IOException {
		Dictionary<String, String> projects = getProjectsDictionary();
		url = addParam(url, "project_id", projects.get(projectName));
		String issueResponseJson = getJsonFromUrl(url);

		IssueResponse issueResponse = new Gson().fromJson(issueResponseJson, IssueResponse.class);
		out.println(issueResponse.total_count);
		for (Issue issue : issueResponse.issues) {
			out.println("*******************************************");
			out.println(issue.id);
			if (issue.assigned_to != null)
				out.println(issue.assigned_to.name);
			out.println(issue.status.name);
			out.println(issue.project.name);
			out.println(issue.subject);
			out.println(issue.start_date);
			out.println("*******************************************");
		}
		out.println("DONE: issues printed");
	}

	public String getJsonFromUrl(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		HttpResponse httpresponse = httpclient.execute(httpget);
		Scanner sc = new Scanner(httpresponse.getEntity().getContent());
		String json = sc.nextLine();
		sc.close();
		httpclient.close();
		return json;
	}

	public Dictionary<String, String> getProjectsDictionary() {
		Dictionary<String, String> projects = new Hashtable<String, String>();
		projects.put("hr-admin", "17");
		projects.put("it-infrastructure", "15");
		projects.put("training", "21");
		projects.put("weekly-symposium", "27");
		projects.put("sandbox", "1");

		return projects;
	}

	public String buildUrlWithKey(String url, String key) {
		return url + "key=" + key;
	}

	public String addParam(String url, String param, String value) {

		return url + "&" + param + "=" + value;
	}
}
