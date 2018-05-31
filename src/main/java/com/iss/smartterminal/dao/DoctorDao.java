package com.iss.smartterminal.dao;

import com.iss.smartterminal.model.Doctor;

public interface DoctorDao {

	public int add(Doctor doctor);

	public int update(Doctor doctor);

	public int isDoctorExist(Doctor doctor);

	public Doctor getDoctByNircPwd(Doctor doctor);

	public Doctor getDoctorById(String id);

}
