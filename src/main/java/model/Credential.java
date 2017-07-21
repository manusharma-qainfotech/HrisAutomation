package model;

import java.util.Map;

public class Credential {
	String designation;
	String rank;
	String YOE;
	String PYE;
	String url;

	public Credential(Map<String, Object> map) {
	 designation = map.get("designation").toString();
		rank = map.get("rank").toString();
      YOE = map.get("YOE").toString();
      PYE = map.get("PYE").toString();
      url = map.get("url").toString();
	}

	public String getDesignation() {
		return designation;
	}
	
	public String getrank() {
		return rank;
	}
	public String getYOE()
	{
		return YOE;
	}
  public String getPYE()
  {
	  return PYE;
  }
  public String getUrl()
  {
	  return url;
  }
}
