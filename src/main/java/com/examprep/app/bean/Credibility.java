package com.examprep.app.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@Entity
@DatabaseTable(tableName = "Credibility", daoClass = CredibilityDaoImpl.class)
public class Credibility {

	@DatabaseField(generatedId = true, columnName = "c_id")
	private int c_id;
	
	@DatabaseField
	private int credibility;
}
