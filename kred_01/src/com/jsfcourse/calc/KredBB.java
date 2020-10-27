package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredBB {
	private Double kwota, lat, procent; //pola pobrane od u¿ytkownika
	private Double odsetki, doSplaty, rata; //pola wyliczane przez funkcjê doTheMath
	
	@Inject
	FacesContext ctx;

	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getProcent() {
		return procent;
	}

	public void setProcent(Double procent) {
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

	// Put result in messages on AJAX call
		public String calc_AJAX() {
			if (doTheMath()) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rata: " + rata, null));
			}
			return null;
		}
	public String info() {
		return "info";
	}
}
