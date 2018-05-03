package app.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, Serializable>
		implements BaseRepository<T, ID> {

	private JpaEntityInformation<T, ?> entityInformation;
	private EntityManager entityManager;
	
	public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	@Override
	public List<T> findByIds(ID... ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
