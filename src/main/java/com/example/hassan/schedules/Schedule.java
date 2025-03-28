package com.example.hassan.schedules;

import com.example.hassan.config.jwt.entity.Authority;
import com.example.hassan.dal.rpository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class Schedule {

    private final SystemInfo systemInfo = new SystemInfo();

    private final ApplicationContext applicationContext;


    private final AuthorityRepository authorityRepository;


    //    @Scheduled(fixedDelay = 10 * 60 * 1000)
//    @Scheduled(fixedRate = 6000)
    public void startApplication() {
        log.info("Schedule startApplication started--------------------------------------------------------------------");
        OperatingSystem os = systemInfo.getOperatingSystem();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String user = System.getProperty("user.name");
        System.out.println("=== Scheduled System Information ===");
        System.out.println("Current Time: " + now.format(formatter));
        System.out.println("Operating System: " + os);
        System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
        System.out.println("Physical Memory: " + memory.getTotal() / (1024 * 1024 * 1024) + " GB");
        System.out.println("Available Memory: " + memory.getAvailable() / (1024 * 1024 * 1024) + " GB");
        System.out.println("CPU Cores: " + processor.getLogicalProcessorCount());
        System.out.println("User Running Application: " + user);
        System.out.println("JVM Uptime: " + ManagementFactory.getRuntimeMXBean().getUptime() + " ms");
        log.info("Schedule startApplication finished--------------------------------------------------------------------");
    }

    //    @Scheduled(cron = "0 0 * * * ?")
    @Scheduled(fixedRate = 1000 * 60 * 60)
    @Transactional
    public void scanAndStoreAuthorities() {

        log.info("Schedule scanAndStoreAuthorities started--------------------------------------------------------------------");


        Set<String> authorities = new HashSet<>();

        // Get all beans and filter for those in the specific controller package
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);

            Class<?> beanClass = AopProxyUtils.ultimateTargetClass(bean);

            if (beanClass.getPackageName().startsWith("com.example.hassan.api.controller")) {
                Method[] methods = beanClass.getDeclaredMethods();

                for (Method method : methods) {
                    PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
                    if (preAuthorize != null) {
                        String authorityString = preAuthorize.value();
                        authorities.addAll(parseAuthorities(authorityString));
                    }
                }
            }
        }

        authorities.forEach(this::saveAuthorityIfNotExists);

        log.info("Schedule scanAndStoreAuthorities finished--------------------------------------------------------------------");

    }

    private Set<String> parseAuthorities(String authorityString) {
        Set<String> authorities = new HashSet<>();

        if (authorityString.contains("hasAnyAuthority")) {
            String extracted = authorityString
                    .replace("hasAnyAuthority(", "")
                    .replace(")", "")
                    .replace("'", "");
            String[] authorityArray = extracted.split(",");
            for (String authority : authorityArray) {
                authorities.add(authority.trim());
            }
        }

        return authorities;
    }

    private void saveAuthorityIfNotExists(String authorityName) {
        if (!authorityRepository.existsByName(authorityName)) {
            Authority authority = new Authority();
            authority.setName(authorityName);
            authorityRepository.save(authority);
        }


    }

}
