package me.melyukhov.SpringProject.DataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Recipe")
public class Data {
	@Column(name = "fullName")
	private String fullName;

	@Column(name = "nameOfTheDish")
	private String nameOfTheDish;

	@Column(name = "recipe")
	private String recipe;

	@Id
	@Column(name = "recipe_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Data(int id) {
		this.id = id;
	}
	
	public Data(String fullName, String nameOfTheDish, String recipe) {
		this.nameOfTheDish = nameOfTheDish;
		this.fullName = fullName;
		this.recipe = recipe;
	}
	
	public Data(String fullName, String nameOfTheDish, String recipe, int id) {
		this.nameOfTheDish = nameOfTheDish;
		this.fullName = fullName;
		this.recipe = recipe;
		this.id=id;
	}

	public Data() {
		this.fullName = null;
		this.nameOfTheDish = null;
		this.recipe = null;
	}

	public String getNameOfTheDish() {
		return nameOfTheDish;
	}

	public String getFullName() {
		return fullName;
	}

	public String getRecipe() {
		return recipe;
	}

	public Integer getId() {
		return id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setNameOfTheDish(String nameOfTheDish) {
		this.nameOfTheDish = nameOfTheDish;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public void setId(int id) {
		this.id = id;
	}
}
