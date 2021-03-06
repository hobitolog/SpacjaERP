package pl.poznan.put.student.spacjalive.erp.service;

import org.json.JSONArray;
import pl.poznan.put.student.spacjalive.erp.entity.Equipment;
import pl.poznan.put.student.spacjalive.erp.entity.EquipmentCategory;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

public interface EquipmentService {
	
	List<Equipment> getEquipmentList();
	
	EquipmentCategory getCategory(int id) throws NotFoundException;
	
	List<Equipment> getEquipmentByCategory(int id);
	
	List<EquipmentCategory> getCategories();
	
	void saveEquipment(Equipment equipment);
	
	Equipment getEquipment(int id) throws NotFoundException;
	
	void deleteEquipment(int id);
	
	List<Equipment> getFreeEquipment(String dateSince, String timeSince, String dateTo, String timeTo);
	
	JSONArray getFreeEquipmentJson(String dateSince, String timeSince, String dateTo, String timeTo);
	
}
