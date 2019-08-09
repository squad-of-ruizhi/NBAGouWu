package com.yc.gw.entity;

import java.io.Serializable;

public class CartInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4413249411043832440L;
	
	private int shopid;
	private int spId;
	private int samount;
	private int usid;
	private String spname;
	private double spprice;
	private String sppic;
	private String spcolor;
	private String spsize;
	private String tolprice;
	
	public String getTolprice() {
		return tolprice;
	}
	public void setTolprice(String tolprice) {
		this.tolprice = tolprice;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public int getSamount() {
		return samount;
	}
	public void setSamount(int samount) {
		this.samount = samount;
	}
	public int getUsid() {
		return usid;
	}
	public void setUsid(int usid) {
		this.usid = usid;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public double getSpprice() {
		return spprice;
	}
	public void setSpprice(double spprice) {
		this.spprice = spprice;
	}
	public String getSppic() {
		return sppic;
	}
	public void setSppic(String sppic) {
		this.sppic = sppic;
	}
	public String getSpcolor() {
		return spcolor;
	}
	public void setSpcolor(String spcolor) {
		this.spcolor = spcolor;
	}
	public String getSpsize() {
		return spsize;
	}
	public void setSpsize(String spsize) {
		this.spsize = spsize;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + samount;
		result = prime * result + shopid;
		result = prime * result + spId;
		result = prime * result + ((spcolor == null) ? 0 : spcolor.hashCode());
		result = prime * result + ((spname == null) ? 0 : spname.hashCode());
		result = prime * result + ((sppic == null) ? 0 : sppic.hashCode());
		long temp;
		temp = Double.doubleToLongBits(spprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((spsize == null) ? 0 : spsize.hashCode());
		result = prime * result + ((tolprice == null) ? 0 : tolprice.hashCode());
		result = prime * result + usid;
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
		CartInfo other = (CartInfo) obj;
		if (samount != other.samount)
			return false;
		if (shopid != other.shopid)
			return false;
		if (spId != other.spId)
			return false;
		if (spcolor == null) {
			if (other.spcolor != null)
				return false;
		} else if (!spcolor.equals(other.spcolor))
			return false;
		if (spname == null) {
			if (other.spname != null)
				return false;
		} else if (!spname.equals(other.spname))
			return false;
		if (sppic == null) {
			if (other.sppic != null)
				return false;
		} else if (!sppic.equals(other.sppic))
			return false;
		if (Double.doubleToLongBits(spprice) != Double.doubleToLongBits(other.spprice))
			return false;
		if (spsize == null) {
			if (other.spsize != null)
				return false;
		} else if (!spsize.equals(other.spsize))
			return false;
		if (tolprice == null) {
			if (other.tolprice != null)
				return false;
		} else if (!tolprice.equals(other.tolprice))
			return false;
		if (usid != other.usid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CartInfo [shopid=" + shopid + ", spId=" + spId + ", samount=" + samount + ", usid=" + usid + ", spname="
				+ spname + ", spprice=" + spprice + ", sppic=" + sppic + ", spcolor=" + spcolor + ", spsize=" + spsize
				+ ", tolprice=" + tolprice + "]";
	}
	public CartInfo(int shopid, int spId, int samount, int usid, String spname, double spprice, String sppic,
			String spcolor, String spsize) {
		super();
		this.shopid = shopid;
		this.spId = spId;
		this.samount = samount;
		this.usid = usid;
		this.spname = spname;
		this.spprice = spprice;
		this.sppic = sppic;
		this.spcolor = spcolor;
		this.spsize = spsize;
	}
	public CartInfo() {
		super();
	}
}
