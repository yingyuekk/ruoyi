package cn.xq.boot.framework.shiro.service;

import cn.xq.boot.common.constant.Constants;
import cn.xq.boot.common.constant.ShiroConstants;
import cn.xq.boot.common.exception.user.UserPasswordNotMatchException;
import cn.xq.boot.common.exception.user.UserPasswordRetryLimitExceedException;
import cn.xq.boot.common.utils.MessageUtils;
import cn.xq.boot.framework.manager.AsyncManager;
import cn.xq.boot.framework.manager.factory.AsyncFactory;
import cn.xq.boot.system.domain.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述
 * 登录密码方法
 * @author xieqiong
 * @date 2019/11/22
 */
@Component
public class SysPasswordService
{
    @Autowired
    private CacheManager cacheManager;
    /**用户缓存*/
    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    /**
     * 验证密码
     * @param user
     * @param password
     */
    public void validate(SysUser user, String password)
    {
        String loginName = user.getLoginName();
        AtomicInteger retryCount = loginRecordCache.get(loginName);
        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }
        if (!matches(user, password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }

    }

    /**
     * 验证密码
     * @param user
     * @param newPassword
     * @return
     */
    public boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    /**
     * 密码加密
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

    public void unlock(String loginName){
        loginRecordCache.remove(loginName);
    }
}
