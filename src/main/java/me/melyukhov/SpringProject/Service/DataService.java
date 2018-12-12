package me.melyukhov.SpringProject.Service;

import org.springframework.data.repository.CrudRepository;

import me.melyukhov.SpringProject.DataBase.Data;

public interface DataService extends CrudRepository<Data, Integer>{
	
}
