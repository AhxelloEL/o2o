package com.al.o2o.service.impl;

import com.al.o2o.dao.ProductSellDailyDao;
import com.al.o2o.entity.ProductSellDaily;
import com.al.o2o.service.ProductSellDailyService;
import com.al.o2o.util.PageCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.service.impl
 * @ClassName:ProductSellDailyServiceImpl
 * @Description
 * @date2021/9/7 12:51
 */
@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService {
    private static final Logger log = LoggerFactory.getLogger(ProductSellDailyServiceImpl.class);
    @Resource
    private ProductSellDailyDao productSellDailyDao;

    @Override
    public void dailyCalculate() {
        log.info("Quarz Runing !!!" + new Date());
        productSellDailyDao.insertProductSellDaily();
    }

    @Override
    public List<ProductSellDaily> getProductSellDailyList(ProductSellDaily productSellDailyCondition, Date beginTime, Date endTime) {
        return getProductSellDailyList(productSellDailyCondition,beginTime,endTime);
    }


}
