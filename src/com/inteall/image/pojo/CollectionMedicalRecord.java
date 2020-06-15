package com.inteall.image.pojo;

import java.util.Date;
import java.util.List;

public class CollectionMedicalRecord extends MedicalRecord{
	private String collection_medical_record_key;
	private String collection_key;
	private String medical_record_key;
	private String report_key;
	private String create_person;
	private Date create_time;
	private Date modify_time;
	private String modify_person;
	private String is_del;
	private Date del_time;
	private String del_person;
	private String collection_medical_record_remark1;
	private String collection_medical_record_remark2;
	private String collection_medical_record_remark3;
	private List<MedicalRecord> medicalRecord;
	
	public CollectionMedicalRecord(String collection_medical_record_key, String collection_key,
			String medical_record_key, String create_person, Date create_time, Date modify_time, String modify_person,
			String is_del, Date del_time, String del_person, String collection_medical_record_remark1,
			String collection_medical_record_remark2, String collection_medical_record_remark3,
			List<MedicalRecord> medicalRecord) {
		super();
		this.collection_medical_record_key = collection_medical_record_key;
		this.collection_key = collection_key;
		this.medical_record_key = medical_record_key;
		this.create_person = create_person;
		this.create_time = create_time;
		this.modify_time = modify_time;
		this.modify_person = modify_person;
		this.is_del = is_del;
		this.del_time = del_time;
		this.del_person = del_person;
		this.collection_medical_record_remark1 = collection_medical_record_remark1;
		this.collection_medical_record_remark2 = collection_medical_record_remark2;
		this.collection_medical_record_remark3 = collection_medical_record_remark3;
		this.medicalRecord = medicalRecord;
	}
	public List<MedicalRecord> getMedicalRecord() {
		return medicalRecord;
	}
	public void setMedicalRecord(List<MedicalRecord> medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	public CollectionMedicalRecord() {
		super();
	}
	public CollectionMedicalRecord(String collection_medical_record_key, String collection_key,
			String medical_record_key, String create_person, Date create_time, Date modify_time, String modify_person,
			String is_del, Date del_time, String del_person, String collection_medical_record_remark1,
			String collection_medical_record_remark2, String collection_medical_record_remark3) {
		super();
		this.collection_medical_record_key = collection_medical_record_key;
		this.collection_key = collection_key;
		this.medical_record_key = medical_record_key;
		this.create_person = create_person;
		this.create_time = create_time;
		this.modify_time = modify_time;
		this.modify_person = modify_person;
		this.is_del = is_del;
		this.del_time = del_time;
		this.del_person = del_person;
		this.collection_medical_record_remark1 = collection_medical_record_remark1;
		this.collection_medical_record_remark2 = collection_medical_record_remark2;
		this.collection_medical_record_remark3 = collection_medical_record_remark3;
	}
	public String getCollection_medical_record_key() {
		return collection_medical_record_key;
	}
	public void setCollection_medical_record_key(String collection_medical_record_key) {
		this.collection_medical_record_key = collection_medical_record_key;
	}
	public String getCollection_key() {
		return collection_key;
	}
	public void setCollection_key(String collection_key) {
		this.collection_key = collection_key;
	}
	public String getMedical_record_key() {
		return medical_record_key;
	}
	public void setMedical_record_key(String medical_record_key) {
		this.medical_record_key = medical_record_key;
	}
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getModify_person() {
		return modify_person;
	}
	public void setModify_person(String modify_person) {
		this.modify_person = modify_person;
	}
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public Date getDel_time() {
		return del_time;
	}
	public void setDel_time(Date del_time) {
		this.del_time = del_time;
	}
	public String getDel_person() {
		return del_person;
	}
	public void setDel_person(String del_person) {
		this.del_person = del_person;
	}
	public String getCollection_medical_record_remark1() {
		return collection_medical_record_remark1;
	}
	public void setCollection_medical_record_remark1(String collection_medical_record_remark1) {
		this.collection_medical_record_remark1 = collection_medical_record_remark1;
	}
	public String getCollection_medical_record_remark2() {
		return collection_medical_record_remark2;
	}
	public void setCollection_medical_record_remark2(String collection_medical_record_remark2) {
		this.collection_medical_record_remark2 = collection_medical_record_remark2;
	}
	public String getCollection_medical_record_remark3() {
		return collection_medical_record_remark3;
	}
	public void setCollection_medical_record_remark3(String collection_medical_record_remark3) {
		this.collection_medical_record_remark3 = collection_medical_record_remark3;
	}
	public String getReport_key() {
		return report_key;
	}
	public void setReport_key(String report_key) {
		this.report_key = report_key;
	}
	
}
