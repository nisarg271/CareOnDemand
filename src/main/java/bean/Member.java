package bean;

public class Member implements Cloneable{
	
	private String name;
	private Integer slotsPerCycle;
	private Integer repeatFactor;
	private Integer rotationCycle = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSlotsPerCycle() {
		return slotsPerCycle;
	}
	public void setSlotsPerCycle(Integer slotsPerCycle) {
		this.slotsPerCycle = slotsPerCycle;
	}
	public Integer getRepeatFactor() {
		return repeatFactor;
	}
	public void setRepeatFactor(Integer repeatFactor) {
		this.repeatFactor = repeatFactor;
	}
	public Integer getRotationCycle() {
		return rotationCycle;
	}
	public void setRotationCycle(Integer rotationCycle) {
		this.rotationCycle = rotationCycle;
	}
	@Override
	public String toString() {
		//return this.getName() + ":" + this.getSlotsPerCycle() + ":" + this.getRepeatFactor(); 
		return "Name:" + this.getName() + " Slots:" + this.getSlotsPerCycle() + " RF: " + this.getRepeatFactor() + " Cycle: " + this.getRotationCycle();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}