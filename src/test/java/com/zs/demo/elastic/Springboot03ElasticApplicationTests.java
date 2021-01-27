package com.zs.demo.elastic;


import com.zs.demo.bean.Article;
import com.zs.demo.bean.Desk;
import com.zs.demo.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticApplicationTests {

	@Autowired
	JestClient jestClient;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void test02(){
		Desk book = new Desk();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor("吴承恩");
		bookRepository.index(book);


		for (Desk bk : bookRepository.findByBookNameLike("游")) {
			System.out.println(bk);
		}

	}




	// 访问地址 http://localhost:9200/atguigu/news/1
	// No bean named 'elasticsearchTemplate' available报这个的话就在配置文件中加elasticsearch的配置
	@Test
	public void contextLoads() {
		//1、给Es中索引（保存）一个文档；
		Article article = new Article();
		article.setId(1);
		article.setTitle("好消息");
		article.setAuthor("zhangsan");
		article.setContent("Hello World");

		//构建一个索引功能
		Index index = new Index.Builder(article).index("atguigu").type("news").build();

		try {
			//执行
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//测试搜索
	@Test
	public void search(){

		//查询表达式
		String json ="{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"hello\"\n" +
				"        }\n" +
				"    }\n" +
				"}";

		//更多操作：https://github.com/searchbox-io/Jest/tree/master/jest
		//构建搜索功能
		Search search = new Search.Builder(json).addIndex("atguigu").addType("news").build();

		//执行
		try {
			SearchResult result = jestClient.execute(search);
			System.out.println(result.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
