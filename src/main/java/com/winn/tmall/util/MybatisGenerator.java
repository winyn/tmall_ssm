package com.winn.tmall.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 15:35 2018/6/14
 * @ModifiedBy:
 */
public class MybatisGenerator {
    public static void main(String[] args) throws Exception {
        String today = "2018-6-14";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fmtd = sdf.parse(today);
        Date now = new Date();

        if(now.getTime() > fmtd.getTime()+1000*60*60*24){
            System.err.println("===================运行失败====================");
            System.err.println("本程序只能运行一次，如果必须再运行请修改today变量为今天，如："+sdf.format(now));
            return;
        }

        if(false)
            return;
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream is= MybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").openStream();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("生成代码成功，刷新项目");

    }
}
