package com.zs.demo.service;

import com.zs.demo.bean.Department;
import com.zs.demo.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;


@Service
public class DeptService {

    @Autowired
    DepartmentDao departmentDao;

    //@Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;


    /**
     *  缓存的数据能存入redis；
     *  第二次从缓存中查询就不能反序列化回来；
     *  存的是dept的json数据;CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     * @param id
     * @return
     */

    // 使用缓存管理器得到缓存，进行api调用
    @Cacheable(cacheNames = "dept",cacheManager = "deptCacheManager")
    public Department getDeptById(Integer id){
        Department department = departmentDao.deptById(id);

        //获取某个缓存
        Cache dept = deptCacheManager.getCache("dept");
        dept.put("dept:1",department);

        return department;
    }


}
