package webservice;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class RestClient {
	
	public static void main(String[] args) throws Throwable {
		
		String url = "http://localhost:8087/NVoter/v1/orgs/111/facs/1/patients/1/assessments/templates/123456/schedules/7890";
		//
		final RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		ClientHttpRequestFactory requestFactory = rt.getRequestFactory();
		if (requestFactory instanceof SimpleClientHttpRequestFactory) {
			((SimpleClientHttpRequestFactory) requestFactory).setReadTimeout(60000);
		} else if (requestFactory instanceof HttpComponentsClientHttpRequestFactory) {
			((HttpComponentsClientHttpRequestFactory) requestFactory).setReadTimeout(60000);
		}

		JsonObject outboundJSON = new JsonObject();
		
		outboundJSON.addProperty("param1", "value1");
		outboundJSON.addProperty("param2", "value2");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		final String newURL = builder.build().toUriString();
		final HttpMethod finalHttpMethod = HttpMethod.PUT;
		final HttpEntity<?> requestEntity = new HttpEntity<String>(new Gson().toJson(outboundJSON), headers);

		//Retrying the call to SkinWoundVendor for 3 times
		SimpleRetryPolicy policy = new SimpleRetryPolicy();
		policy.setMaxAttempts(3);
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
	    fixedBackOffPolicy.setBackOffPeriod(3000);
	    
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		retryTemplate.setRetryPolicy(policy);
		
		ResponseEntity<String> response = retryTemplate.execute(new RetryCallback<ResponseEntity<String>, Throwable>() {
			public ResponseEntity<String> doWithRetry(RetryContext context) {
				ResponseEntity<String> response = null;
				HttpStatus responseStatusCode = null;
				String loggerMessage = null;
				String responseBody = null;
				try{
					response = rt.exchange(newURL, finalHttpMethod, requestEntity,  String.class, "1", "11", "3", "44", "66");
					responseStatusCode = response.getStatusCode();
					responseBody =  response.getBody();

					if (!responseStatusCode.equals(HttpStatus.OK)) {
						throw new RuntimeException();
					}
					else{
						loggerMessage = "Rest call "+ (context.getRetryCount() + 1) + "/"+ 3 +" successful";
					}
				}
				catch (Exception e) {
					loggerMessage = "Rest call "+ (context.getRetryCount() + 1) + "/"+ 3 +" failed due to exception: " + e.getMessage();
					e.printStackTrace();
					throw e;
				}
				return response;
			}
		});
		//
		
		/*final RestTemplate rt = new RestTemplate();
	      
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      
	      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
	      
	      //builder.queryParam("param1", "value1").queryParam("param2", "value2").queryParam("param3", "value3");
	      
	      final String newURL = builder.build().toUriString();
	      
	      JsonObject jsonObject = new JsonObject();
	      jsonObject.addProperty("param1", "value1");
	      jsonObject.addProperty("param2", "value2");
	      
	      final HttpEntity<?> requestEntity = new HttpEntity<Object>(jsonObject, headers);
	      
	      //
	      SimpleRetryPolicy policy = new SimpleRetryPolicy();
	      policy.setMaxAttempts(3);
	      RetryTemplate template = new RetryTemplate();
	      template.setRetryPolicy(policy);
			 ResponseEntity<String> response = template.execute(new RetryCallback<ResponseEntity<String>, Throwable>() {
			    public ResponseEntity<String> doWithRetry(RetryContext context) {
			    	System.out.println("Inside main main: ");
			    	ResponseEntity<String> response = rt.exchange(newURL, HttpMethod.PUT, requestEntity,  String.class, "1", "11", "3", "44", "66");
			        System.out.println("Inside response code: ");
			    	return response;
			    }
			});*/
			//
	      
	      //ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, requestEntity,  String.class, "1", "11", "3", "44", "66");
	      
	      System.out.println(response.getStatusCode());
	}
	
	/*public static void main(String[] args) throws Exception {
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(ServiceClass.class);
		ServiceClass serviceClass = (ServiceClass) ctx.getBean("serviceClass");
		System.out.println(serviceClass.serviceObj());
	}*/
	
}
