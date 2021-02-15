package com.embl.test.restfulmicroservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "persons_v1")
@Table(name = "persons_v1")
public class PersonV1Entity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6410131774165152570L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Size(min = 2, message = "FirstName should have atleast 2 characters")
    @ApiModelProperty(notes = "FirstName should have atleast 2 characters", example = "BBB")
    @Column(name = "firstname")
    private String firstName;
    
    @Column(name = "lastname")
    private String lastName;
    
    @Column(name = "age")
    private int age;
    
    @Column(name="favouritecolour")
    private String favouriteColour;
    
    
}
