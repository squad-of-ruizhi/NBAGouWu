package com.yc.gw.entity;

public class Sping {
	private int spId;
    private String spname;
    private  int spprice;
    private String sppic;
    private String  spsize;
    private  String spcolor;
    private String spcate;
    
	
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public int getspprice() {
		return spprice;
	}
	public void setspprice(int spprice) {
		this.spprice = spprice;
	}
	public String getSppic() {
		return sppic;
	}
	public void setSppic(String sppic) {
		this.sppic = sppic;
	}
	public String getSpsize() {
		return spsize;
	}
	public void setSpsize(String spsize) {
		this.spsize = spsize;
	}
	public String getSpcolor() {
		return spcolor;
	}
	public void setSpcolor(String spcolor) {
		this.spcolor = spcolor;
	}
	public String getSpcate() {
		return spcate;
	}
	public void setSpcate(String spcate) {
		this.spcate = spcate;
	}
    
	@Override
	public String toString() {
		return "Sping [spId=" + spId + ", spname=" + spname + ", spprice=" + spprice + ", sppic=" + sppic + ", spsize="
				+ spsize + ", spcolor=" + spcolor + ", spcate=" + spcate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + spId;
		result = prime * result + ((spcate == null) ? 0 : spcate.hashCode());
		result = prime * result + ((spcolor == null) ? 0 : spcolor.hashCode());
		result = prime * result + ((spname == null) ? 0 : spname.hashCode());
		result = prime * result + ((sppic == null) ? 0 : sppic.hashCode());
		result = prime * result + ((spsize == null) ? 0 : spsize.hashCode());
		result = prime * result + spprice;
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
		Sping other = (Sping) obj;
		if (spId != other.spId)
			return false;
		if (spcate == null) {
			if (other.spcate != null)
				return false;
		} else if (!spcate.equals(other.spcate))
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
		if (spsize == null) {
			if (other.spsize != null)
				return false;
		} else if (!spsize.equals(other.spsize))
			return false;
		if (spprice != other.spprice)
			return false;
		return true;
	}
	
	
	public Sping() {
		super();
		// TODO Auto-generated constructor stub
	}
}
