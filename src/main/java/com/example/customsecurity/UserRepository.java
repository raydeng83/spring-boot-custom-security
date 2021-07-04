package com.example.customsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class UserRepository {
    private static final Integer THREE_SECONDS = 3000;

    @Autowired
    private Environment env;

//    @Autowired
//    private LdapTemplate ldapTemplate;

    public User getUserByUid(String uid, String dataSource) {

        LdapContextSource ctxSrc = new LdapContextSource();
        ctxSrc.setUrl(env.getRequiredProperty("data-source."+ dataSource + ".urls"));
        ctxSrc.setBase(env.getRequiredProperty("data-source."+ dataSource + ".base"));
        ctxSrc.setUserDn(env.getRequiredProperty("data-source."+ dataSource + ".username"));
        ctxSrc.setPassword(env.getRequiredProperty("data-source."+ dataSource + ".password"));

        ctxSrc.afterPropertiesSet(); // this method should be called.

        LdapTemplate ldapTemplate = new LdapTemplate(ctxSrc);

        LdapQuery query = query()
                .searchScope(SearchScope.SUBTREE)
                .timeLimit(THREE_SECONDS)
                .countLimit(10)
                .attributes("*")
                .base(LdapUtils.emptyLdapName())
                .where("objectclass").is("person")
                .and("uid").is(uid);

        List<User> users = ldapTemplate.search(query, new UserAttributesMapper());

        return users.get(0);
    }

    private class UserAttributesMapper implements AttributesMapper<User> {
        public User mapFromAttributes(Attributes attrs) throws NamingException {
            User user = new User();
            user.setUsername((String)attrs.get("uid").get());
            System.out.println((byte[]) attrs.get("userpassword").get());
            user.setPassword(new String((byte[]) attrs.get("userpassword").get()));
            user.setUserPassword((byte[])attrs.get("userpassword").get());
            return user;
        }
    }

}
