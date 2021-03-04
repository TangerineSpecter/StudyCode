package com.tangerine.specter.design_pattern.handler.service;

import com.tangerine.specter.design_pattern.handler.pojo.Post;

/**
 * 帖子处理器
 */
public abstract class PostHandler {
    /**
     * 后继者
     */
    protected PostHandler successor;

    public void setSuccessor(PostHandler successor) {
        this.successor = successor;
    }

    public abstract void handlerRequest(Post post);

    protected final void next(Post post) {
        if (this.successor != null) {
            this.successor.handlerRequest(post);
        }
    }
}
