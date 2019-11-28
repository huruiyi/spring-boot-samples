package com.hry.search.web;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hry.search.mapping.ProductDocument;
import com.hry.search.mapping.ProductDocumentBuilder;
import com.hry.search.repository.ProductDocumentRepository;
import com.hry.search.service.EsSearchService;

@Controller
public class IndexController {

	@Autowired
	RestHighLevelClient highLevelClient;

	@Resource
	private EsSearchService esSearchService;

	@Resource
	private ElasticsearchRestTemplate elasticsearchTemplate;

	@Resource
	private ProductDocumentRepository productDocumentRepository;

	private Logger log = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@RequestMapping(value = "/")
	public String index() throws InterruptedException {
		elasticsearchTemplate.putMapping(ProductDocument.class);

		log.info("历史数据清理,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

		esSearchService.deleteAll();

		log.info("数据初始化开始,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

		for (int i = 1; i < 6; i++) {
			Integer id = (i - 1) * 3;

			Thread.sleep(1000);
			id = id + 1;
			ProductDocument productDocument = ProductDocumentBuilder.create().addId(String.format("%03d", id))
					.addProductName("无印良品 MUJI 基础润肤化妆水").addProductDesc("无印良品 MUJI 基础润肤化妆水 高保湿型 200ml")
					.addCreateTime(new Date()).addUpdateTime(new Date()).builder();

			Thread.sleep(1000);
			id = id + 1;
			ProductDocument productDocument1 = ProductDocumentBuilder.create().addId(String.format("%03d", id))
					.addProductName("荣耀 V10 尊享版").addProductDesc("荣耀 V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待")
					.addCreateTime(new Date()).addUpdateTime(new Date()).builder();

			Thread.sleep(1000);
			id = id + 1;
			ProductDocument productDocument2 = ProductDocumentBuilder.create().addId(String.format("%03d", id))
					.addProductName("资生堂(SHISEIDO) 尿素红罐护手霜")
					.addProductDesc("日本进口 资生堂(SHISEIDO) 尿素红罐护手霜 100g/罐 男女通用 深层滋养 改善粗糙").addCreateTime(new Date())
					.addUpdateTime(new Date()).builder();
			save(productDocument, productDocument1, productDocument2);
		}
		log.info("数据初始化结束,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

		return "Hello Elasticsearch.........";
	}

	public void save(ProductDocument... productDocuments) {
		productDocumentRepository.saveAll(Arrays.asList(productDocuments));

	}
}
