package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredBB {
	private String kwota;
	private String lat;
	private String procent;
	private Double odsetki;
	private Double doSplaty;
	private Double rata;
	
	@Inject
	FacesContext ctx;

	public String getKwota() {
		return kwota;
	}

	public void setKwota(String kwota) {
		this.kwota = kwota;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getProcent() {
		return procent;
	}

	public void setProcent(String procent) {
		this.procent = procent;
	}
	
	public Double getRata() {
		return rata;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}

	public boolean doTheMath() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double lat = Double.parseDouble(this.lat);
			double procent = Double.parseDouble(this.procent);

			odsetki = procent * (lat*12);
			doSplaty = kwota + (kwota*(odsetki/100));
			rata = doSplaty / (lat*12);
			rata *= 100;
			rata = (double) Math.round(rata);
			rata /= 100;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
