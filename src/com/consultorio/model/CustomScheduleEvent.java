package com.consultorio.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleRenderingMode;

public class CustomScheduleEvent implements ScheduleEvent {

    private String titulo;
    private String title;
    private Date atendimento;
    private Date endDate;

    public CustomScheduleEvent() {
    }

    public CustomScheduleEvent(String titulo, Date atendimento) {
        this.titulo = titulo;
        this.atendimento = atendimento;
    }

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGroupId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStartDate(LocalDateTime start) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocalDateTime getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEndDate(LocalDateTime end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAllDay() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getStyleClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOverlapAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleRenderingMode getRenderingMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getDynamicProperties() {
		// TODO Auto-generated method stub
		return null;
	}
}