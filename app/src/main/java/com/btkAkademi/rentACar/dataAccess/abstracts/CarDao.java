package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarDao extends JpaRepository<Car, Integer> {

	List<Car> getByBrand_Id(int brandId);

	List<Car> getByColor_Id(int colorId);

	List<Car> getByCity_Id(int cityId);

	Car getByCarClass(String carClass);

	List<Car> getByCarClassId(int id);

	List<Car> getByCity_IdAndBrand_Id(int cityId,int brandId);


	@Query(value = "select \r\n" + "	c.id \r\n" + "from cars c\r\n"
			+ "left join car_maintenance m on c.id = m.car_id and m.end_date is null\r\n"
			+ "left join rentals r on c.id = r.car_id and (r.returned_date is null or r.returned_date>NOW())\r\n"
			+ "where r.id is null and m.id is null and c.car_car_class_id = ?1\r\n" + "order by RANDOM()\r\n"
			+ "limit 1", nativeQuery = true)
	List<Integer> getAvailableCarByCarClassId(int carClassId);

}
