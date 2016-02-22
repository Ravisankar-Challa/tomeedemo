package com.example.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity

public class Member implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_NAME")
	@SequenceGenerator(name="GEN_NAME", allocationSize=1, initialValue=1, sequenceName="MEM_SEQ_GEN")
	private Long id;
	private String name;
	private static final long serialVersionUID = 1L;

	public Member() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
