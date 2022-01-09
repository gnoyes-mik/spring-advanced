package me.gnoyes.advanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import me.gnoyes.advanced.trace.threadlocal.code.FieldService;
import me.gnoyes.advanced.trace.threadlocal.code.ThreadLocalService;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> service.logic("userA");
        Runnable userB = () -> service.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); // 동시성 문제 발생 X(A가 완전히 끝난 뒤 B 실행)
        sleep(100); // 동시성 문제 발생 O(A가 완전히 끝나지 않은 상태에서 B 실행)
        threadB.start();

        sleep(3000); // 메인 스레드 종료 대기
        log.info("main exit");

    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
