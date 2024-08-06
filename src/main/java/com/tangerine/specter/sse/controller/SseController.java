package com.tangerine.specter.sse.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.tangerine.specter.common.ServiceResult;
import com.tangerine.specter.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * SSE通信演示，主要用于类似 openAi回答式内容的输出
 * 即无法一次性完成的响应内容
 *
 * <p>
 * 原理：
 * SSE（Server-Sent Events）是一种浏览器与服务器之间的通信技术，它允许服务器推送事件和数据给浏览器。
 * 与传统的HTTP请求/响应模型不同，SSE建立了一个长连接，服务器可以随时向客户端发送新的数据，而无需等待客户端的请求。
 *
 * @doc https://mp.weixin.qq.com/s/AEsE_lasmtZWPVVlM_BboA
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sse")
public class SseController {

    private final SseService sseService;

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/index")
    public ModelAndView page(Model model) {
        return ServiceResult.jumpPage("/sse/index");
    }

    private static String text = "从前，有一位国王，他拥有无尽的财富和权力，但他并不快乐。国王终日被焦虑困扰，因为无论他怎么努力，都无法预测或控制未来。他想知道：“谁是最重要的人？什么是最重要的事？什么时候做这件事最好？”这三个问题的答案成了他内心的追求。\n" +
            "\n" +
            "国王决定向他的智者们寻求答案，但每个人的回答都不相同，让他更加困惑。最后，他决定去拜访一位隐居在森林中的圣人，据说这位圣人无所不知。\n" +
            "\n" +
            "国王装扮成平民，独自一人前往森林。在途中，他遇到一群正在砍伐树木的农民，其中一位老人似乎非常虚弱，无法继续工作。国王上前帮助老人，直到太阳落山，他才继续前行。\n" +
            "\n" +
            "当他终于找到圣人的小屋时，圣人正在照料一个受伤的士兵。国王放下自己的身份，协助圣人清洗伤口并包扎。在这个过程中，圣人询问国王为何而来，国王解释了他的三个问题。\n" +
            "\n" +
            "圣人微笑着回答：“你已经找到了答案。当你帮助那位老人时，他是最重要的人，你所做的事情就是最重要的事情，而你做的时候，就是最好的时刻。”\n" +
            "\n" +
            "国王突然意识到，每一个当下都有其重要性，而我们所面对的人，在那一刻，就是最重要的人。我们应该全心全意地对待现在，因为现在就是我们能够影响未来的唯一时刻。\n" +
            "\n" +
            "这个故事告诉我们，生活中的每一个瞬间都是宝贵的，我们应当珍惜眼前的人和事，把握当下，用心去体验和付出。";

    /**
     * 适用场景：
     * 1、实时通知和消息推送
     * 2、实时监控和数据更新
     * 3、即时聊天应用
     * 4、实时股票报价和新闻更新
     * <p>
     * 优点：
     * 1、实现了实时数据推送，减少了轮询服务器的开销
     * 2、适用于需要实时性的应用场景
     * 3、简单易用，无需复杂的WebSocket配置
     */
    @GetMapping("")
    public SseEmitter handleSse() {
        log.info("获取sse内容...");
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));

        new Thread(() -> {
            try {
                for (int i = 0; i < text.length(); i++) {
                    int incr = RandomUtil.randomInt(10);
                    int time = RandomUtil.randomInt(200);
                    emitter.send(SseEmitter.event()
                            .id(String.valueOf(counter.incrementAndGet()))
                            .name("message")
                            .data(StrUtil.sub(text, i, i + incr)));
                    Thread.sleep(time);
                    i = i + incr - 1;
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        }).start();

        log.info("返回sse...");
        return emitter;
    }

    public static void main(String[] args) {
        for (int i = 0; i < text.length(); i++) {
            System.out.println(text.charAt(i));
        }
    }
}
