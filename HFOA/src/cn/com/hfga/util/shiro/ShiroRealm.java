package cn.com.hfga.util.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.com.hfga.dao.user.UserDAO;
import cn.com.hfga.entity.user.UserEntity;
import cn.com.hfga.util.common.SpringUtil;

/**
 * hfga's
 * @author hf
 * shiro鏉冮檺绫�
 */
public class ShiroRealm extends JdbcRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	
    	/*String userCode = (String)principals.getPrimaryPrincipal();
        UserDAO userDAO = (UserDAO)SpringUtil.getBeanByName("userDAO");
        UserEntity userEntityDB = userDAO.findByUserCode(userCode); 
        
        Set <String> roleCodes  = new HashSet<String>();
        Set <String> permissionCodes = new HashSet<String>();
        
        for(RoleEntity roleEntity : userEntityDB.getRoleEntities()) {
        	roleCodes.add(roleEntity.getRoleCode());
        	
        	for(PermissionEntity permissionEntity : roleEntity.getPermissionEntities()) {
        		permissionCodes.add(permissionEntity.getPermissionCode());
        	}
         }
        
        for(PermissionEntity permissionEntity : userEntityDB.getPermissionEntities()) {
        	permissionCodes.add(permissionEntity.getPermissionCode());
        }
            // info.setStringPermissions(permissionCodes);
        
        	
        SimpleAuthorizationInfo info =null;*/
    
        return null;
        
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {/*
    	
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String userCode = upToken.getUsername();

        // Null username is invalid
        if (userCode == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        
        UserDAO userDAO = (UserDAO)SpringUtil.getBeanByName("userDAO");
        UserEntity userEntityDB = userDAO.findByUserCode(userCode);
        

        if (userEntityDB == null) {
            throw new UnknownAccountException("not exsit userName.");
        }
        
        if(userEntityDB.getPassword() == null) {
        	throw new AccountException("Null password are not allowed by this realm.");
        }
        
        SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(userCode, userEntityDB.getUserPassword(), getName());
 	    
        
    */return null;}

}
