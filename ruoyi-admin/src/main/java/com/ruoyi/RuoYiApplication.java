package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  SZX启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
            "************************************************** \n" +
            "   SSSSSS            ZZZZZZZZZZZZ     XX       XX \n"+
            " SS      SS                   ZZ       XX     XX\n"+
            " SS                         ZZ          XX   XX\n"+
            "  SS                      ZZ             XXXXX\n"+
            "    SSSS                ZZ                XXX\n"+
            "        SS             ZZ                XXXXX\n"+
            "         SS           ZZ                XX   XX\n"+
            " SS      SS          ZZ                XX     XX\n"+
            "   SSSSSS           ZZZZZZZZZZZZ     XX        XX\n+" +
            "************************************************** \n");
        //自动生成有趣文字
        //http://patorjk.com/software/taag/#p=display&h=0&v=3&f=X992&t=SZX
    }
}
