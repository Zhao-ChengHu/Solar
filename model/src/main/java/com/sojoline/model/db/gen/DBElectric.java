package com.sojoline.model.db.gen;

import com.sojoline.model.db.DateElectric;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/02/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Entity
public class DBElectric {
	@Id(autoincrement = true)
	private Long id;
	
	@NotNull
	private String stationId;

	@NotNull
	private String date;

	@NotNull
	private String type;

	@ToMany(referencedJoinProperty = "electricId")
	private List<DateElectric> list;

	/** Used to resolve relations */
	@Generated(hash = 2040040024)
	private transient DaoSession daoSession;

	/** Used for active entity operations. */
	@Generated(hash = 154862813)
	private transient DBElectricDao myDao;

	@Generated(hash = 1981125883)
	public DBElectric(Long id, @NotNull String stationId, @NotNull String date,
			@NotNull String type) {
		this.id = id;
		this.stationId = stationId;
		this.date = date;
		this.type = type;
	}

	@Generated(hash = 1443944151)
	public DBElectric() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStationId() {
		return this.stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * To-many relationship, resolved on first access (and after reset).
	 * Changes to to-many relations are not persisted, make changes to the target entity.
	 */
	@Generated(hash = 1053980366)
	public List<DateElectric> getList() {
		if (list == null) {
			final DaoSession daoSession = this.daoSession;
			if (daoSession == null) {
				throw new DaoException("Entity is detached from DAO context");
			}
			DateElectricDao targetDao = daoSession.getDateElectricDao();
			List<DateElectric> listNew = targetDao._queryDBElectric_List(id);
			synchronized (this) {
				if (list == null) {
					list = listNew;
				}
			}
		}
		return list;
	}

	/** Resets a to-many relationship, making the next get call to query for a fresh result. */
	@Generated(hash = 589833612)
	public synchronized void resetList() {
		list = null;
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 128553479)
	public void delete() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.delete(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 1942392019)
	public void refresh() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.refresh(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 713229351)
	public void update() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.update(this);
	}

	/** called by internal mechanisms, do not call yourself. */
	@Generated(hash = 149602397)
	public void __setDaoSession(DaoSession daoSession) {
		this.daoSession = daoSession;
		myDao = daoSession != null ? daoSession.getDBElectricDao() : null;
	}
}
