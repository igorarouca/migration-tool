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

package com.liferay.data.migration.tool.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.migration.tool.model.Migration;
import com.liferay.data.migration.tool.service.MigrationLocalService;
import com.liferay.data.migration.tool.service.persistence.EntityMigrationPersistence;
import com.liferay.data.migration.tool.service.persistence.MigrationPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the migration local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.data.migration.tool.service.impl.MigrationLocalServiceImpl}.
 * </p>
 *
 * @author Dylan Rebelak
 * @see com.liferay.data.migration.tool.service.impl.MigrationLocalServiceImpl
 * @see com.liferay.data.migration.tool.service.MigrationLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class MigrationLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements MigrationLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.data.migration.tool.service.MigrationLocalServiceUtil} to access the migration local service.
	 */

	/**
	 * Adds the migration to the database. Also notifies the appropriate model listeners.
	 *
	 * @param migration the migration
	 * @return the migration that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Migration addMigration(Migration migration) {
		migration.setNew(true);

		return migrationPersistence.update(migration);
	}

	/**
	 * Creates a new migration with the primary key. Does not add the migration to the database.
	 *
	 * @param migrationId the primary key for the new migration
	 * @return the new migration
	 */
	@Override
	public Migration createMigration(long migrationId) {
		return migrationPersistence.create(migrationId);
	}

	/**
	 * Deletes the migration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param migrationId the primary key of the migration
	 * @return the migration that was removed
	 * @throws PortalException if a migration with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Migration deleteMigration(long migrationId)
		throws PortalException {
		return migrationPersistence.remove(migrationId);
	}

	/**
	 * Deletes the migration from the database. Also notifies the appropriate model listeners.
	 *
	 * @param migration the migration
	 * @return the migration that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Migration deleteMigration(Migration migration) {
		return migrationPersistence.remove(migration);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Migration.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return migrationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return migrationPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return migrationPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return migrationPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return migrationPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public Migration fetchMigration(long migrationId) {
		return migrationPersistence.fetchByPrimaryKey(migrationId);
	}

	/**
	 * Returns the migration with the primary key.
	 *
	 * @param migrationId the primary key of the migration
	 * @return the migration
	 * @throws PortalException if a migration with the primary key could not be found
	 */
	@Override
	public Migration getMigration(long migrationId) throws PortalException {
		return migrationPersistence.findByPrimaryKey(migrationId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(migrationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Migration.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("migrationId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(migrationLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Migration.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("migrationId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(migrationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Migration.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("migrationId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return migrationLocalService.deleteMigration((Migration)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return migrationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the migrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of migrations
	 * @param end the upper bound of the range of migrations (not inclusive)
	 * @return the range of migrations
	 */
	@Override
	public List<Migration> getMigrations(int start, int end) {
		return migrationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of migrations.
	 *
	 * @return the number of migrations
	 */
	@Override
	public int getMigrationsCount() {
		return migrationPersistence.countAll();
	}

	/**
	 * Updates the migration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param migration the migration
	 * @return the migration that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Migration updateMigration(Migration migration) {
		return migrationPersistence.update(migration);
	}

	/**
	 * Returns the entity migration local service.
	 *
	 * @return the entity migration local service
	 */
	public com.liferay.data.migration.tool.service.EntityMigrationLocalService getEntityMigrationLocalService() {
		return entityMigrationLocalService;
	}

	/**
	 * Sets the entity migration local service.
	 *
	 * @param entityMigrationLocalService the entity migration local service
	 */
	public void setEntityMigrationLocalService(
		com.liferay.data.migration.tool.service.EntityMigrationLocalService entityMigrationLocalService) {
		this.entityMigrationLocalService = entityMigrationLocalService;
	}

	/**
	 * Returns the entity migration persistence.
	 *
	 * @return the entity migration persistence
	 */
	public EntityMigrationPersistence getEntityMigrationPersistence() {
		return entityMigrationPersistence;
	}

	/**
	 * Sets the entity migration persistence.
	 *
	 * @param entityMigrationPersistence the entity migration persistence
	 */
	public void setEntityMigrationPersistence(
		EntityMigrationPersistence entityMigrationPersistence) {
		this.entityMigrationPersistence = entityMigrationPersistence;
	}

	/**
	 * Returns the migration local service.
	 *
	 * @return the migration local service
	 */
	public MigrationLocalService getMigrationLocalService() {
		return migrationLocalService;
	}

	/**
	 * Sets the migration local service.
	 *
	 * @param migrationLocalService the migration local service
	 */
	public void setMigrationLocalService(
		MigrationLocalService migrationLocalService) {
		this.migrationLocalService = migrationLocalService;
	}

	/**
	 * Returns the migration persistence.
	 *
	 * @return the migration persistence
	 */
	public MigrationPersistence getMigrationPersistence() {
		return migrationPersistence;
	}

	/**
	 * Sets the migration persistence.
	 *
	 * @param migrationPersistence the migration persistence
	 */
	public void setMigrationPersistence(
		MigrationPersistence migrationPersistence) {
		this.migrationPersistence = migrationPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.data.migration.tool.model.Migration",
			migrationLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.data.migration.tool.model.Migration");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MigrationLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Migration.class;
	}

	protected String getModelClassName() {
		return Migration.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = migrationPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.data.migration.tool.service.EntityMigrationLocalService.class)
	protected com.liferay.data.migration.tool.service.EntityMigrationLocalService entityMigrationLocalService;
	@BeanReference(type = EntityMigrationPersistence.class)
	protected EntityMigrationPersistence entityMigrationPersistence;
	@BeanReference(type = MigrationLocalService.class)
	protected MigrationLocalService migrationLocalService;
	@BeanReference(type = MigrationPersistence.class)
	protected MigrationPersistence migrationPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}