package com.sojoline.model.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBELECTRIC".
*/
public class DBElectricDao extends AbstractDao<DBElectric, Long> {

    public static final String TABLENAME = "DBELECTRIC";

    /**
     * Properties of entity DBElectric.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StationId = new Property(1, String.class, "stationId", false, "STATION_ID");
        public final static Property Date = new Property(2, String.class, "date", false, "DATE");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
    }

    private DaoSession daoSession;


    public DBElectricDao(DaoConfig config) {
        super(config);
    }
    
    public DBElectricDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBELECTRIC\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"STATION_ID\" TEXT NOT NULL ," + // 1: stationId
                "\"DATE\" TEXT NOT NULL ," + // 2: date
                "\"TYPE\" TEXT NOT NULL );"); // 3: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBELECTRIC\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DBElectric entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getStationId());
        stmt.bindString(3, entity.getDate());
        stmt.bindString(4, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DBElectric entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getStationId());
        stmt.bindString(3, entity.getDate());
        stmt.bindString(4, entity.getType());
    }

    @Override
    protected final void attachEntity(DBElectric entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DBElectric readEntity(Cursor cursor, int offset) {
        DBElectric entity = new DBElectric( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // stationId
            cursor.getString(offset + 2), // date
            cursor.getString(offset + 3) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DBElectric entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStationId(cursor.getString(offset + 1));
        entity.setDate(cursor.getString(offset + 2));
        entity.setType(cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DBElectric entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DBElectric entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DBElectric entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}