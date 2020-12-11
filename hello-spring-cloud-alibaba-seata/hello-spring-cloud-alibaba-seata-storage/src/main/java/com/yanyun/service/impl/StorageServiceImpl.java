package com.yanyun.service.impl;

import com.yanyun.dao.StorageDao;
import com.yanyun.domain.Storage;
import com.yanyun.service.IStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:33
 * @description
 */
@Service
public class StorageServiceImpl implements IStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        Storage storage = storageDao.findByProductId(productId);
        storage.setTotal(storage.getTotal() - count);
        storageDao.save(storage);
        LOGGER.info("------->storage-service中扣减库存结束");
    }
}
