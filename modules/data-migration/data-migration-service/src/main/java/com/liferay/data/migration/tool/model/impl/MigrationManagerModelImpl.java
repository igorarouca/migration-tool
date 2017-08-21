/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.data.migration.tool.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.migration.tool.model.MigrationManager;
import com.liferay.data.migration.tool.model.MigrationManagerModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the MigrationManager service. Represents a row in the &quot;SYNC_MigrationManager&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link MigrationManagerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MigrationManagerImpl}.
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationManagerImpl
 * @see MigrationManager
 * @see MigrationManagerModel
 * @generated
 */
@ProviderType
public class MigrationManagerModelImpl extends BaseModelImpl<MigrationManager>
	implements MigrationManagerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a migration manager model instance should use the {@link MigrationManager} interface instead.
	 */
	public static final String TABLE_NAME = "SYNC_MigrationManager";
	public static final Object[][] TABLE_COLUMNS = {
			{ "managerId", Types.BIGINT },
			{ "timeStarted", Types.TIMESTAMP },
			{ "timeCompleted", Types.TIMESTAMP },
			{ "fromDate", Types.TIMESTAMP },
			{ "recordsSynced", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("managerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("timeStarted", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("timeCompleted", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fromDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("recordsSynced", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table SYNC_MigrationManager (managerId LONG not null primary key,timeStarted DATE null,timeCompleted DATE null,fromDate DATE null,recordsSynced LONG)";
	public static final String TABLE_SQL_DROP = "drop table SYNC_MigrationManager";
	public static final String ORDER_BY_JPQL = " ORDER BY migrationManager.timeStarted DESC";
	public static final String ORDER_BY_SQL = " ORDER BY SYNC_MigrationManager.timeStarted DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.data.migration.tool.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.data.migration.tool.model.MigrationManager"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.data.migration.tool.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.data.migration.tool.model.MigrationManager"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.data.migration.tool.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.data.migration.tool.model.MigrationManager"));

	public MigrationManagerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _managerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setManagerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _managerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MigrationManager.class;
	}

	@Override
	public String getModelClassName() {
		return MigrationManager.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("managerId", getManagerId());
		attributes.put("timeStarted", getTimeStarted());
		attributes.put("timeCompleted", getTimeCompleted());
		attributes.put("fromDate", getFromDate());
		attributes.put("recordsSynced", getRecordsSynced());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long managerId = (Long)attributes.get("managerId");

		if (managerId != null) {
			setManagerId(managerId);
		}

		Date timeStarted = (Date)attributes.get("timeStarted");

		if (timeStarted != null) {
			setTimeStarted(timeStarted);
		}

		Date timeCompleted = (Date)attributes.get("timeCompleted");

		if (timeCompleted != null) {
			setTimeCompleted(timeCompleted);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Long recordsSynced = (Long)attributes.get("recordsSynced");

		if (recordsSynced != null) {
			setRecordsSynced(recordsSynced);
		}
	}

	@Override
	public long getManagerId() {
		return _managerId;
	}

	@Override
	public void setManagerId(long managerId) {
		_managerId = managerId;
	}

	@Override
	public Date getTimeStarted() {
		return _timeStarted;
	}

	@Override
	public void setTimeStarted(Date timeStarted) {
		_timeStarted = timeStarted;
	}

	@Override
	public Date getTimeCompleted() {
		return _timeCompleted;
	}

	@Override
	public void setTimeCompleted(Date timeCompleted) {
		_timeCompleted = timeCompleted;
	}

	@Override
	public Date getFromDate() {
		return _fromDate;
	}

	@Override
	public void setFromDate(Date fromDate) {
		_fromDate = fromDate;
	}

	@Override
	public long getRecordsSynced() {
		return _recordsSynced;
	}

	@Override
	public void setRecordsSynced(long recordsSynced) {
		_recordsSynced = recordsSynced;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			MigrationManager.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MigrationManager toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (MigrationManager)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MigrationManagerImpl migrationManagerImpl = new MigrationManagerImpl();

		migrationManagerImpl.setManagerId(getManagerId());
		migrationManagerImpl.setTimeStarted(getTimeStarted());
		migrationManagerImpl.setTimeCompleted(getTimeCompleted());
		migrationManagerImpl.setFromDate(getFromDate());
		migrationManagerImpl.setRecordsSynced(getRecordsSynced());

		migrationManagerImpl.resetOriginalValues();

		return migrationManagerImpl;
	}

	@Override
	public int compareTo(MigrationManager migrationManager) {
		int value = 0;

		value = DateUtil.compareTo(getTimeStarted(),
				migrationManager.getTimeStarted());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MigrationManager)) {
			return false;
		}

		MigrationManager migrationManager = (MigrationManager)obj;

		long primaryKey = migrationManager.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<MigrationManager> toCacheModel() {
		MigrationManagerCacheModel migrationManagerCacheModel = new MigrationManagerCacheModel();

		migrationManagerCacheModel.managerId = getManagerId();

		Date timeStarted = getTimeStarted();

		if (timeStarted != null) {
			migrationManagerCacheModel.timeStarted = timeStarted.getTime();
		}
		else {
			migrationManagerCacheModel.timeStarted = Long.MIN_VALUE;
		}

		Date timeCompleted = getTimeCompleted();

		if (timeCompleted != null) {
			migrationManagerCacheModel.timeCompleted = timeCompleted.getTime();
		}
		else {
			migrationManagerCacheModel.timeCompleted = Long.MIN_VALUE;
		}

		Date fromDate = getFromDate();

		if (fromDate != null) {
			migrationManagerCacheModel.fromDate = fromDate.getTime();
		}
		else {
			migrationManagerCacheModel.fromDate = Long.MIN_VALUE;
		}

		migrationManagerCacheModel.recordsSynced = getRecordsSynced();

		return migrationManagerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{managerId=");
		sb.append(getManagerId());
		sb.append(", timeStarted=");
		sb.append(getTimeStarted());
		sb.append(", timeCompleted=");
		sb.append(getTimeCompleted());
		sb.append(", fromDate=");
		sb.append(getFromDate());
		sb.append(", recordsSynced=");
		sb.append(getRecordsSynced());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.data.migration.tool.model.MigrationManager");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>managerId</column-name><column-value><![CDATA[");
		sb.append(getManagerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>timeStarted</column-name><column-value><![CDATA[");
		sb.append(getTimeStarted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>timeCompleted</column-name><column-value><![CDATA[");
		sb.append(getTimeCompleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fromDate</column-name><column-value><![CDATA[");
		sb.append(getFromDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordsSynced</column-name><column-value><![CDATA[");
		sb.append(getRecordsSynced());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = MigrationManager.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			MigrationManager.class
		};
	private long _managerId;
	private Date _timeStarted;
	private Date _timeCompleted;
	private Date _fromDate;
	private long _recordsSynced;
	private MigrationManager _escapedModel;
}