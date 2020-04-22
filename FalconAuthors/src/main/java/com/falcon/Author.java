package com.falcon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide a name")
    private String authname;

    @NotEmpty(message = "Please provide a location")
    private String location;

    @NotNull(message = "Please provide a age")
    @Min(value=10, message = "Age should not be less than 10")
    private int age;

    // avoid this "No default constructor for entity"
    public Author() {
    }

    public Author(Long id, String name, String location, int age) {
        this.id = id;
        this.authname = name;
        this.location = location;
        this.age = age;
    }

    public Author(String name, String location, int age) {
        this.authname = name;
        this.location = location;
        this.age = age;
    }


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthname() {
		return authname;
	}

	public void setAuthname(String authname) {
		this.authname = authname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + authname + '\'' +
                ", location='" + location + '\'' +
                ", age=" + age +
                '}';
    }
}
