/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.mirror.cores.models;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import io.github.kubesys.mirror.cores.annotations.Description;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author  wuheng@iscas.ac.cn
 * @version 0.1.0
 * @since   2023/06/21
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Description(type = "数据表模型", value = "#NAME#")
public class CommonModel   {

	@Id
	@Column(name = "name", length = 256)
	@Description(value = "Kubernetes的metadata.name，只能是小写字母、数字、连字符（-）或点号（.）组成，且以字母或数字开头")
    private Long name;
	
	@Id
	@Column(name = "namespace", length = 64)
	@Description(value = "Kubernetes的metadata.namespace,只能是小写字母、数字、连字符（-）组成，且以字母或数字开头")
	private String namespace;
	
	@Id
	@Column(name = "apiGroup", length = 128)
	@Description(value = "Kubernetes的apiVersion除去'/'前面一段，只能是小写字母、数字、点号（.）组成，且以字母开头")
	private String apiGroup;
	
	@Id
	@Column(name = "region", length = 128)
	@Description(value = "自定义字段，任意字符，也可以是中文")
	private String region;
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "data", columnDefinition = "json")
	@Description(value = "对应kubectl get [kind] [name] -n [namespace] -o json的具体数据")
    private JsonNode data;
	
	@CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
	@Description(value = "数据创建时间，如2023-06-20 08:49:50.090407")
    private LocalDateTime createdAt;

	@UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
	@Description(value = "数据更新时间，如2023-06-20 08:49:50.090407")
    private LocalDateTime updatedAt;

	/**
	 * @return  
	 */
	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}


	public String getApiGroup() {
		return apiGroup;
	}

	public void setApiGroup(String apiGroup) {
		this.apiGroup = apiGroup;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}