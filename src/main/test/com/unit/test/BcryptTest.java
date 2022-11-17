package com.unit.test;

import com.imgBlog.user.service.UserService;
import com.imgBlog.user.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml","file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class BcryptTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Test
    public void bcryptTest() {
        try {
            String rawPassword = "vam123";                //인코딩 전 메서드
            String encodePassword1;                        // 인코딩된 메서드
            String encodePassword2;                        // 똑같은 비밀번호 데이터를 encdoe()메서드를 사용했을 때 동일한 인코딩된 값이 나오는지 확인하기 위해 추가

            encodePassword1 = passwordEncoder.encode(rawPassword);
            encodePassword2 = passwordEncoder.encode(rawPassword);

            // 인코딩된 패스워드 출력
            System.out.println("encdoePassword1 : " + encodePassword1);
            System.out.println("encdoePassword2 : " + encodePassword2);

            String truePassowrd = "vam123";
            String falsePassword = "asdfjlasf";

            System.out.println("truePassword verify : " + passwordEncoder.matches(truePassowrd, encodePassword1));
            System.out.println("falsePassword verify : " + passwordEncoder.matches(falsePassword, encodePassword2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void passwordChk() {

        boolean passwordMatRes = false;
        UserVo userVo = new UserVo();
        userVo.setUserId("lgs0503");
        userVo.setPassword("123123");

        passwordMatRes = userService.login(userVo);
        System.out.println("loginResult : " + passwordMatRes);
    }
}