package br.com.ricardotulio.mikrotikadmin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "radcheck")
public class RadCheck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3706626413737304561L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username")
	@NotNull
	@Size(max = 64)
	private String userName;

	@Column
	@NotNull
	@Size(max = 64)
	private String attribute = "user-password";

	@Column
	@Size(max = 2)
	private String op = "==";

	@Column
	private String value;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "radusergroup", 
		joinColumns = @JoinColumn(name = "username", referencedColumnName="username"), 
		inverseJoinColumns = @JoinColumn(name = "groupname", referencedColumnName="groupname"))
	private List<RadGroupReply> radGroups = new ArrayList<RadGroupReply>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void addRadGroupReply(RadGroupReply radGroupReply) {
		this.radGroups.add(radGroupReply);
	}

	public List<RadGroupReply> getRadGroups() {
		return radGroups;
	}
	
}
