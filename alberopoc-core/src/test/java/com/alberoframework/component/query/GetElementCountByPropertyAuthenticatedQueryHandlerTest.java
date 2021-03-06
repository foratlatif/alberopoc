package com.alberoframework.component.query;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub2;
import com.alberoframework.component.common.CommonTestStubs.InMemoryEntityForRequestTestStub2Repository;
import com.alberoframework.component.common.CommonTestStubs.InMemoryEntityForRequestTestStubRepository;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetElementCountByPropertyAuthenticatedQueryHandler;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetElementCountByPropertyQuery;
import com.alberoframework.component.query.testing.SimpleAuthenticatedQueryHandlerRepositoryTestSupport;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.google.common.collect.Sets;

public class GetElementCountByPropertyAuthenticatedQueryHandlerTest extends SimpleAuthenticatedQueryHandlerRepositoryTestSupport<GetElementCountByPropertyAuthenticatedQueryHandler, GetElementCountByPropertyQuery, Integer>{

	@Test
	public TestSpecification testSuccessWithoutStateCheck() {
		String userId = "userId";
		return 	given(
						 		entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2"),
						 				new EntityForRequestTestStub2(1L, "prop2"),
						 				new EntityForRequestTestStub2(2L, "prop")
				 				)
						 )
						.when(handle(query(new GetElementCountByPropertyQuery("prop2"), userId)))
						.then(2)
						.when(handle(query(new GetElementCountByPropertyQuery("prop"), userId)))
						.then(3)
						.when(handle(query(new GetElementCountByPropertyQuery("prop3"), userId)))
						.then(0);
 								
	}
	
	@Test
	public TestSpecification testSuccessWithStateCheck() {
		String userId = "userId";
		return 	given(
						 		entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2"),
						 				new EntityForRequestTestStub2(1L, "prop2"),
						 				new EntityForRequestTestStub2(2L, "prop")
				 				)
						 )
						.when(handle(query(new GetElementCountByPropertyQuery("prop2"), userId)))
						.then(2,
								entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2"),
						 				new EntityForRequestTestStub2(1L, "prop2"),
						 				new EntityForRequestTestStub2(2L, "prop")
				 				))
						.when(handle(query(new GetElementCountByPropertyQuery("prop"), userId)))
						.then(3)
						.when(handle(query(new GetElementCountByPropertyQuery("prop3"), userId)))
						.then(0,
								entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2"),
						 				new EntityForRequestTestStub2(1L, "prop2"),
						 				new EntityForRequestTestStub2(2L, "prop")
				 				));
 								
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Set<CrudRepository<? extends Entity<?>, ?>> repositories() {
			return new HashSet<CrudRepository<? extends Entity<?>, ?>>(Sets.newHashSet(
					new InMemoryEntityForRequestTestStubRepository(),
					new InMemoryEntityForRequestTestStub2Repository()));
	}
	
}
