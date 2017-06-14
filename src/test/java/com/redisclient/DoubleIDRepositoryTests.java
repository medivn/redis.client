/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redisclient;

import com.redisclient.idrepository.DoubleIDRepository;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Rizwan Idrees
 * @author Mohsin Husen
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/double-id-repository-test.xml")
public class DoubleIDRepositoryTests {

//	@Autowired
//	private DoubleIDRepository repository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;


	@Before
	public void before() {
		elasticsearchTemplate.deleteIndex(DoubleIDEntity.class);
		elasticsearchTemplate.createIndex(DoubleIDEntity.class);
		elasticsearchTemplate.refresh(DoubleIDEntity.class,true);
	}

	@Test
	public void shouldDoBulkIndexDocument() {
		// given
		Double documentId1 = RandomUtils.nextDouble();
		DoubleIDEntity sampleEntity1 = new DoubleIDEntity();
		sampleEntity1.setId(documentId1);
		sampleEntity1.setMessage("some message");
		sampleEntity1.setVersion(System.currentTimeMillis());

		Double documentId2 = RandomUtils.nextDouble();
		DoubleIDEntity sampleEntity2 = new DoubleIDEntity();
		sampleEntity2.setId(documentId2);
		sampleEntity2.setMessage("some message");
		sampleEntity2.setVersion(System.currentTimeMillis());
		Map map = elasticsearchTemplate.getSetting(DoubleIDEntity.class);
		System.out.println("======================="+map);
		/*// when
		repository.save(Arrays.asList(sampleEntity1, sampleEntity2));
		// then
		DoubleIDEntity entity1FromElasticSearch = repository.findOne(documentId1);
		assertThat(entity1FromElasticSearch, is(notNullValue()));

		DoubleIDEntity entity2FromElasticSearch = repository.findOne(documentId2);
		assertThat(entity2FromElasticSearch, is(notNullValue()));*/
	}

	/*@Test
	public void shouldSaveDocument() {
		// given
		Double documentId = RandomUtils.nextDouble();
		DoubleIDEntity sampleEntity = new DoubleIDEntity();
		sampleEntity.setId(documentId);
		sampleEntity.setMessage("some message");
		sampleEntity.setVersion(System.currentTimeMillis());
		// when
		repository.save(sampleEntity);
		// then
		DoubleIDEntity entityFromElasticSearch = repository.findOne(documentId);
		assertThat(entityFromElasticSearch, is(notNullValue()));
	}*/
}
