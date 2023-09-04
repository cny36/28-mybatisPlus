package com.cny.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cny.mybatisplus.dao.UserMapper;
import com.cny.mybatisplus.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void selectTest() {
        UserEntity userEntity = userMapper.selectById(1l);
        System.out.println(userEntity);
    }


    @Test
    void insertTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("zsss");
        userEntity.setAge(19);
        userEntity.setEmail("123@qq.com");
        userMapper.insert(userEntity);
        System.out.println(userEntity.getId());
    }

    @Test
    void deleteTest() {
        userMapper.deleteById("1696882075392614401");
    }

    @Test
    void updateTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1l);
        userEntity.setName("fsdfsdfdssdfdf");
        userMapper.updateById(userEntity);


        //update user set age=88 where id=1;
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setAge(88);
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1l);
        userMapper.update(userEntity2, queryWrapper);


        //update user set age=99 where id=2;
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 2l).set("age", 99);
        userMapper.update(null, updateWrapper);
    }


    @Test
    void selectPageTest(){
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getAge, 19);
        IPage<UserEntity> page = userMapper.selectPage(new Page<>(1, 2), queryWrapper);
        System.out.println(page.getRecords());
    }

}
