package com.al.o2o.service;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service
 * @ClassName:CacheService
 * @Description jedis删除KEY
 * @date2021/8/10 11:10
 */
public interface CacheService {
    /**
     * 依据key前缀删除匹配该模式下的所有key-value 如传入:shopcategory,则shopcategory_allfirstlevel等
     * 以shopcategory打头的key_value都会被清空
     *
     * @param keyPrefix
     * @return
     */
    void removeFromCache(String keyPrefix);
}
