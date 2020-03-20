package com.filmland.streaming.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageResponse implements Serializable {

	private static final long serialVersionUID = -6106949232305904543L;
	
	private String status;
    private String message;
    
}
