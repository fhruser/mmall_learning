package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String    username);

    int checkEmail(String    email);

    //登录成功的话，登录信息返回给前端（User信息）
    //mybatis传多个信息的时候@Param("sql中对应的名字")
    User    selectLogin(@Param("username") String  username, @Param("password") String password);

    String  selectQuestionByUsername(String username);

    int checkAnswer(@Param("username")String  username,@Param("question")String question,@Param("answer")String answer);

    int updatePasswordByUsername(@Param("username") String  username, @Param("passwordnew") String passwordnew);

    int checkPassword(@Param("password") String    password,@Param("userId") Integer    userId);

    int checkEmailByUserId(@Param("email")String   email,@Param("userId") Integer   userId);

}