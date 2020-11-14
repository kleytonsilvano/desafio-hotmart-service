package com.hotmart.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_product")
public class Product implements Serializable{

	private static final long serialVersionUID = -7738448718724658529L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
	private String name;

    @Column
	private String description;
    
    @OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tb_product_categories",
	            joinColumns =        {@JoinColumn(name = "product")}, 
	            inverseJoinColumns = {@JoinColumn(name="category")})
	private List<ProductCategory> categories;
    
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

}