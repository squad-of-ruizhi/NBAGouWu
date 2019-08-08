package com.yc.gw.entity;

public class Addres {
	
	private int addrid;
	private String province;
    private  String city;
    private String area;
    private String  detailed;
    private  String usid;
    private String  aname;
    private String  atel;
    private String  amail;
    private String  adefault;
	public Addres(int addrid, String province, String city, String area, String detailed, String usid, String aname,
			String atel, String amail, String adefault) {
		super();
		this.addrid = addrid;
		this.province = province;
		this.city = city;
		this.area = area;
		this.detailed = detailed;
		this.usid = usid;
		this.aname = aname;
		this.atel = atel;
		this.amail = amail;
		this.adefault = adefault;
	}
	public Addres() {
		super();
	}
	@Override
	public String toString() {
		return "Addres [addrid=" + addrid + ", province=" + province + ", city=" + city + ", area=" + area
				+ ", detailed=" + detailed + ", usid=" + usid + ", aname=" + aname + ", atel=" + atel + ", amail="
				+ amail + ", adefault=" + adefault + "]";
	}
	public int getAddrid() {
		return addrid;
	}
	public void setAddrid(int addrid) {
		this.addrid = addrid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAtel() {
		return atel;
	}
	public void setAtel(String atel) {
		this.atel = atel;
	}
	public String getAmail() {
		return amail;
	}
	public void setAmail(String amail) {
		this.amail = amail;
	}
	public String getAdefault() {
		return adefault;
	}
	public void setAdefault(String adefault) {
		this.adefault = adefault;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addrid;
		result = prime * result + ((adefault == null) ? 0 : adefault.hashCode());
		result = prime * result + ((amail == null) ? 0 : amail.hashCode());
		result = prime * result + ((aname == null) ? 0 : aname.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((atel == null) ? 0 : atel.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((detailed == null) ? 0 : detailed.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((usid == null) ? 0 : usid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Addres other = (Addres) obj;
		if (addrid != other.addrid)
			return false;
		if (adefault == null) {
			if (other.adefault != null)
				return false;
		} else if (!adefault.equals(other.adefault))
			return false;
		if (amail == null) {
			if (other.amail != null)
				return false;
		} else if (!amail.equals(other.amail))
			return false;
		if (aname == null) {
			if (other.aname != null)
				return false;
		} else if (!aname.equals(other.aname))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (atel == null) {
			if (other.atel != null)
				return false;
		} else if (!atel.equals(other.atel))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (detailed == null) {
			if (other.detailed != null)
				return false;
		} else if (!detailed.equals(other.detailed))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (usid == null) {
			if (other.usid != null)
				return false;
		} else if (!usid.equals(other.usid))
			return false;
		return true;
	}
}
