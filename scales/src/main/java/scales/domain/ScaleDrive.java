package scales.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SCALE_DRIVES database table.
 * 
 */
@Entity
@Table(name="SCALE_DRIVES")
@NamedQuery(name="ScaleDrive.findAll", query="SELECT s FROM ScaleDrive s")
public class ScaleDrive implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descr;

	@Temporal(TemporalType.DATE)
	@Column(name="DRIVE_DATE")
	private Date driveDate;

	@Column(name="START_TIME")
	private Timestamp startTime;

	@Column(name="END_TIME")
	private Timestamp endTime;
	
	
	public ScaleDrive() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getDriveDate() {
		return this.driveDate;
	}

	public void setDriveDate(Date driveDate) {
		this.driveDate = driveDate;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

}