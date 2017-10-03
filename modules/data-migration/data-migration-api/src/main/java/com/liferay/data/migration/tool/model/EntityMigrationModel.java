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

package com.liferay.data.migration.tool.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the EntityMigration service. Represents a row in the &quot;MIG_EntityMigration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.data.migration.tool.model.impl.EntityMigrationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.data.migration.tool.model.impl.EntityMigrationImpl}.
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityMigration
 * @see com.liferay.data.migration.tool.model.impl.EntityMigrationImpl
 * @see com.liferay.data.migration.tool.model.impl.EntityMigrationModelImpl
 * @generated
 */
@ProviderType
public interface EntityMigrationModel extends BaseModel<EntityMigration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a entity migration model instance should use the {@link EntityMigration} interface instead.
	 */

	/**
	 * Returns the primary key of this entity migration.
	 *
	 * @return the primary key of this entity migration
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this entity migration.
	 *
	 * @param primaryKey the primary key of this entity migration
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the entity migration ID of this entity migration.
	 *
	 * @return the entity migration ID of this entity migration
	 */
	public long getEntityMigrationId();

	/**
	 * Sets the entity migration ID of this entity migration.
	 *
	 * @param entityMigrationId the entity migration ID of this entity migration
	 */
	public void setEntityMigrationId(long entityMigrationId);

	/**
	 * Returns the migration ID of this entity migration.
	 *
	 * @return the migration ID of this entity migration
	 */
	public long getMigrationId();

	/**
	 * Sets the migration ID of this entity migration.
	 *
	 * @param migrationId the migration ID of this entity migration
	 */
	public void setMigrationId(long migrationId);

	/**
	 * Returns the entity name of this entity migration.
	 *
	 * @return the entity name of this entity migration
	 */
	@AutoEscape
	public String getEntityName();

	/**
	 * Sets the entity name of this entity migration.
	 *
	 * @param entityName the entity name of this entity migration
	 */
	public void setEntityName(String entityName);

	/**
	 * Returns the start of this entity migration.
	 *
	 * @return the start of this entity migration
	 */
	public Date getStart();

	/**
	 * Sets the start of this entity migration.
	 *
	 * @param start the start of this entity migration
	 */
	public void setStart(Date start);

	/**
	 * Returns the end of this entity migration.
	 *
	 * @return the end of this entity migration
	 */
	public Date getEnd();

	/**
	 * Sets the end of this entity migration.
	 *
	 * @param end the end of this entity migration
	 */
	public void setEnd(Date end);

	/**
	 * Returns the count of this entity migration.
	 *
	 * @return the count of this entity migration
	 */
	public long getCount();

	/**
	 * Sets the count of this entity migration.
	 *
	 * @param count the count of this entity migration
	 */
	public void setCount(long count);

	/**
	 * Returns the last completion of this entity migration.
	 *
	 * @return the last completion of this entity migration
	 */
	public Date getLastCompletion();

	/**
	 * Sets the last completion of this entity migration.
	 *
	 * @param lastCompletion the last completion of this entity migration
	 */
	public void setLastCompletion(Date lastCompletion);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(EntityMigration entityMigration);

	@Override
	public int hashCode();

	@Override
	public CacheModel<EntityMigration> toCacheModel();

	@Override
	public EntityMigration toEscapedModel();

	@Override
	public EntityMigration toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}