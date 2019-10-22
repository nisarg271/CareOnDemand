package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bean.Member;

public class ScheduleGenerationUtil {
	
	public static Map<String, Member> generateSchedule(Map<String, Integer> memberMap){
		Date startDate = new Date("04/01/2016");
		Date endDate = new Date("06/30/2016");
		List<String> workingDaysList = getWorkingDaysBetweenTwoDates(startDate, endDate);
		//System.out.println("Working Days List:" + workingDaysList);
		//memberMap = populateData();
		return generateOutput(workingDaysList, memberMap);

	}
	
	public static Map<String, Member> generateOutput(List<String> workingDaysList, Map<String, Integer> memberMap){
		
		Map<String, Member> finalOutputMap = new LinkedHashMap<String, Member>();
		int totalSlots = 13;
		List<Member> memberBeanList = new ArrayList<Member>();
		System.out.println("Total Slots: " + totalSlots);
		
		//populating memberBeanList
		Member member = null;
		for(Entry<String, Integer> entry : memberMap.entrySet()){
			member = new Member();
			member.setName(entry.getKey());
			member.setSlotsPerCycle(entry.getValue());
			member.setRepeatFactor(totalSlots/entry.getValue());
			memberBeanList.add(member);
		}
		
		System.out.println(memberBeanList);
		
		for(int i=0; i<workingDaysList.size(); i++) {
			if(memberBeanList.size() > 0){
				Member mb = memberBeanList.get(0);
				finalOutputMap.put(workingDaysList.get(i), mb);
				//System.out.println(workingDaysList.get(i) + ":" + mb.getName() + ":factor:" + mb.getRepeatFactor() +" :slots: " +mb.getSlotsPerCycle());
				memberBeanList.remove(mb);
				memberBeanList.add((memberBeanList.size()), mb);
				
				List<Member> tempMemberBeanList = new ArrayList<Member>();
				
				int counter = 0;
				for(Member tempMemberBean: memberBeanList){
					tempMemberBean.setRepeatFactor(tempMemberBean.getRepeatFactor()-1);
					if(tempMemberBean.getRepeatFactor() == 0){
						tempMemberBean.setRepeatFactor(totalSlots/tempMemberBean.getSlotsPerCycle());
						tempMemberBeanList.add(counter, tempMemberBean);
						counter++;
					}
					else{
						tempMemberBean.setRotationCycle(tempMemberBean.getRotationCycle()+1);
						tempMemberBeanList.add(tempMemberBean);
					}
				}
				memberBeanList =  copyList(tempMemberBeanList);
			}
		}
		return finalOutputMap;
	}
	
	public static List<Member> copyList(List<Member> sourceList){
		List<Member> destinationList = new ArrayList<Member>();
		try{
			for(Member member : sourceList){
				destinationList.add((Member) member.clone());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destinationList;
	}
	
	public static Map<String, Integer> populateData(){
		
		Map<String, Integer> memberMap = new LinkedHashMap<String, Integer>();
		memberMap.put("Nisarg", 2);//value is the slots/cycle
		memberMap.put("Riddhi", 2);
		memberMap.put("Nikunj", 1);
		memberMap.put("Payal", 1);
		memberMap.put("Jay", 1);
		memberMap.put("Richa", 1);
		memberMap.put("Shu", 1);
		memberMap.put("Shi", 1);
		memberMap.put("Priyanka", 2);
		memberMap.put("Prashant", 1);
		
		return memberMap;
	}
	
	public static List<String> getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
		
		List<String> workingDaysList = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return new ArrayList<String>();
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            //++workDays;
	            workingDaysList.add(sdf.format(startCal.getTime()));
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
	    
	    //System.out.println("Working Days: " + workDays);
	    return workingDaysList;
	}
}