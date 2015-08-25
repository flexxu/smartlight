package org.smartlight.usermanager.model;

public class SysRole {
    private Integer id;

    private String name;

    private Integer status;

    private String remark;

    private Integer pid;
    
    private String nodeIds;
    
    private Integer granted; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

	public String getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(String nodeIds) {
		this.nodeIds = nodeIds;
	}

	public Integer getGranted() {
		return granted;
	}

	public void setGranted(Integer granted) {
		this.granted = granted;
	}
    
}