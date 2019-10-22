package webservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestfulWebService {

	@ResponseBody
	@RequestMapping(value = "/v1/orgs/{orgId}/facs/{facId}/patients/{patientId}/assessments/templates/{templateAssessmentId}/schedules/{assessScheduleId}",  method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void testMethod(@RequestBody String body, @PathVariable(value = "orgId") int orgId,
			@PathVariable(value = "facId") int facId,
			@PathVariable(value = "patientId") String patientId,
			@PathVariable(value = "templateAssessmentId") String templateAssessmentId, 
			@PathVariable(value = "assessScheduleId") String assessScheduleId, HttpServletRequest request) {
		
		/*System.out.println("orgId: " + orgId);
		System.out.println("FacId: " + facId);
		System.out.println("patientId: " + patientId);
		System.out.println("templateAssessmentId: " + templateAssessmentId);
		System.out.println("assessScheduleId: " + assessScheduleId);*/
		
		System.out.println("param1:"+request.getParameter("param1"));
		System.out.println("param2:"+request.getParameter("param2"));
		
		System.out.println("Body:"+body);
		//return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
