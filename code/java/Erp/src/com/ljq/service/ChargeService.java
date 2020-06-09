package com.ljq.service;

import java.sql.SQLException;
import java.util.List;

import com.ljq.model.Charge;

public interface ChargeService {
	public List<Charge> obtainChargeList(Charge charge) throws SQLException;	
	public Charge selectCharge(String id) throws SQLException;
	public boolean deleteCharge(String id) throws SQLException;
	public boolean updateCharge(Charge charge) throws SQLException;
	public void insertCharge(Charge charge) throws SQLException;
}
