# HandlerLeakTest
内存泄漏方案解决示例
本demo中采用最常见的handler进行，采用leakcanary进行监控，采用静态内部类、软引用、逻辑处理3方面示例
由于采用leakcanary检测，若存在泄漏，隔几秒会在通知栏提醒
