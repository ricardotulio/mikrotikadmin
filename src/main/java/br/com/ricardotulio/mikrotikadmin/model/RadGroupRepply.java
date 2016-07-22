package br.com.ricardotulio.mikrotikadmin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "radgroupreply")
public class RadGroupRepply implements Serializable {

	private static final long serialVersionUID = 8035854998595534708L;

	@Id
	private Long id;

	@Column
	@NotNull
	@Size(max = 64)
	private String groupname;

	@Column
	@NotNull
	@Size(max = 64)
	private String attribute = "Mikrotik-Rate-Limit";

	@Column
	@Size(max = 2)
	private String op = "==";

	@Column
	private String value;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "radusergroup", joinColumns = @JoinColumn(name = "groupname", referencedColumnName = "groupname", unique = false), inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username", unique = false))
	private List<RadCheck> radChecks = new ArrayList<RadCheck>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupName) {
		this.groupname = groupName;
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

	public List<RadCheck> getRadChecks() {
		return radChecks;
	}

	public void setRadChecks(List<RadCheck> radChecks) {
		this.radChecks = radChecks;
	}

}
