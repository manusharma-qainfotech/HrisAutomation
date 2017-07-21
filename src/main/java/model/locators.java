package model;

public class locators {
	String element;
	String locatorType;
	String locatorValue;

	public locators(String element, String locatorType, String locatorValue) {
		this.element = element;
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;

	}

	public String getElement() {
		return element;
	}

	public String getLocatorType() {
		
		return locatorType;
	}
public String getLocatorValue(String date) {
	return locatorValue.replace("$", date);	

	}
public String getLocatorValue(String date,String leaveType) {
	
	return locatorValue.replace("$", date).replace("#", leaveType);
}

	public String getLocatorValue() {
		return locatorValue;
	}
}