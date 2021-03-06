package es.optsicom.lib.web.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import es.optsicom.lib.expresults.DBExperimentRepositoryManagerFactory;
import es.optsicom.lib.expresults.ExperimentRepositoryFactory;
import es.optsicom.lib.expresults.db.DBManager;
import es.optsicom.lib.expresults.db.DBManagerProvider;
import es.optsicom.lib.expresults.manager.ExperimentManager;
import es.optsicom.lib.expresults.manager.ExperimentRepositoryManager;
import es.optsicom.lib.expresults.model.Experiment;

@Component
public class ExperimentService {

	private ExperimentRepositoryManager expRepoManager;
	private DBManager dbManager;

	@PostConstruct
	public void init() throws SQLException {

		dbManager = DBManagerProvider.getDBManager();

		ExperimentRepositoryFactory expRepoFactory = null;
		expRepoFactory = new DBExperimentRepositoryManagerFactory(dbManager);

		expRepoManager = expRepoFactory.createExperimentRepositoryManager();

	}

	public Experiment findExperimentById(long id) {
		return expRepoManager.findExperiment(id);
	}

	public ExperimentManager findExperimentManagerById(long id) {
		return expRepoManager.findExperimentManagerById(id);
	}

	public void removeExperiment(long experimentId) {

		expRepoManager.beginTx();
		expRepoManager.removeExperiment(experimentId);
		expRepoManager.commitTx();
	}

	public List<Experiment> findExperiments() {
		return expRepoManager.findExperiments();
	}

	public DBManager getDBManager() {
		return dbManager;
	}
}