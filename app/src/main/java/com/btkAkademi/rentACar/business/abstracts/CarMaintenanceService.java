package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;


import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarMaintenanceService {

	DataResult<List<CarMaintenanceListDto>> getAll();
	
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	
	boolean checkIfCarIsInMaintenance (int carId) ;
}
