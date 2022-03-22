package net.seehope.springsecurity.social.qq;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author JoinYang
 * @date 2022/3/15 9:47
 */
public class QQAdapter implements ApiAdapter<QQ> {

    //测试获取用户信息的接口能不能ping通
    @Override
    public boolean test(QQ qq) {
        return true;
    }


    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo qqUserInfo=qq.getUserInfo();
        connectionValues.setDisplayName(qqUserInfo.getNickname());
        connectionValues.setImageUrl(qqUserInfo.getFigureurl());
        connectionValues.setProviderUserId(qqUserInfo.getOpenId());
    }

    //获取主页（不用理会）
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
