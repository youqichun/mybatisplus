package com.springboot.mybatisplus.util.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * mysql 代码生成器
 * </p>
 */
public class GeneratorCode {
    public static String projectPath = "D:/codeGen";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir(projectPath+ "/src/main/java");
        gc.setFileOverride(true);// 是否覆盖文件
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("youqc");
        gc.setOpen(false);
        gc.setSwagger2(false);//Swagger文档
        //.setKotlin(true) 是否生成 kotlin 代码

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // .setEntityName("%sEntity");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);// 数据库类型
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                //    return DbColumnType.BOOLEAN;
                // }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/zq_blog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.springboot.mybatisplus");
        pc.setController("controller");// 这里是控制器包名，默认 web
        pc.setEntity("entity");
//        pc.setModuleName("");//模块名
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[] { "tb_"});//此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "person" });//需要生成的表
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);// 【实体】是否为lombok模型（默认 false）
        strategy.setRestControllerStyle(true);//Controller,RestController注解
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);// Boolean类型字段是否移除is前缀处理
        //strategy.setExclude(new String[]{"test"});// 排除生成的表
        // .setSuperEntityClass("com.github.leon.security.admin.entity.BaseEntity")// 自定义实体父类
        // .setSuperEntityColumns(superEntityColumns)// 自定义实体，公共字段
        //.setTableFillList(tableFillList)// 自定义需要填充的字段
        //.setSuperMapperClass("com.github.leon.security.admin.mapper.BaseMapper")// 自定义 mapper 父类
        // .setSuperServiceClass("com.github.leon.security.admin.service.BaseService")// 自定义 service 父类
        // .setSuperServiceImplClass("com.github.leon.security.admin.service.impl.BaseServiceImpl")// 自定义 service 实现类父类
        // .setSuperControllerClass("com.github.leon.security.admin.controller.BaseController") // 自定义 controller 父类
        // .setEntityColumnConstant(true)// 【实体】是否生成字段常量（默认 false）// public static final String ID = "test_id";
        // .setEntityBuilderModel(true)// 【实体】是否为构建者模型（默认 false）// public User setName(String name) {this.name = name; return this;}
        mpg.setStrategy(strategy);
        mpg.execute();
    }




}